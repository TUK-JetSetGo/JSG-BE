package org.tukcapstone.jetsetgo.domain.auth.service;

import static org.tukcapstone.jetsetgo.global.response.exception.code.AuthErrorCode.INVALID_REFRESH_TOKEN;
import static org.tukcapstone.jetsetgo.global.response.exception.code.AuthErrorCode.INVALID_SOCIAL_TYPE;
import static org.tukcapstone.jetsetgo.global.response.exception.code.UserErrorCode.USER_NOT_FOUND;

import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tukcapstone.jetsetgo.domain.auth.converter.AuthConverter;
import org.tukcapstone.jetsetgo.domain.auth.dto.AuthResponse;
import org.tukcapstone.jetsetgo.domain.user.entity.User;
import org.tukcapstone.jetsetgo.domain.user.entity.enums.SocialType;
import org.tukcapstone.jetsetgo.domain.user.repository.UserRepository;
import org.tukcapstone.jetsetgo.global.response.exception.GeneralException;
import org.tukcapstone.jetsetgo.global.security.JwtTokenProvider;

@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{

    private final List<SocialAuthService> socialAuthServiceList;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final AuthConverter authConverter;
    private final RedisTemplate<String, String> redisTemplate;


    @Override
    @Transactional
    public AuthResponse.LoginResponse login(SocialType socialType, String accessToken) {
        SocialAuthService authService = socialAuthServiceList.stream()
                .filter(service -> service.getProvider().equals(socialType))
                .findFirst()
                .orElseThrow(() -> new GeneralException(INVALID_SOCIAL_TYPE));

        return authService.authenticate(accessToken);
    }

    @Override
    @Transactional
    public AuthResponse.LoginResponse refresh(String refreshToken) {
        Long userId = jwtTokenProvider.getId(refreshToken);

        String redisKey = "refreshToken:" + userId;
        String savedToken = redisTemplate.opsForValue().get(redisKey);

        if(savedToken == null || !savedToken.equals(refreshToken)){
            throw new GeneralException(INVALID_REFRESH_TOKEN);
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(USER_NOT_FOUND));

        String newAccessToken = jwtTokenProvider.createToken(user.getId(), user.getName());
        String newRefreshToken = jwtTokenProvider.createRefreshToken(user.getId());
        long refreshExpireMillis = jwtTokenProvider.getRefreshTokenValidityInMilliseconds();

        redisTemplate.opsForValue().set(redisKey, newRefreshToken, refreshExpireMillis, TimeUnit.MILLISECONDS);
        return authConverter.convertToLoginRefreshResponse(user,newAccessToken, newRefreshToken, false);
    }

    @Override
    @Transactional
    public void logout(User user, String accessToken, String refreshToken) {
        String redisKey = "refreshToken:" + user.getId();
        try{
            redisTemplate.delete(redisKey);

        }catch (Exception e){
            log.error("refreshToken 삭제에 실패하였습니다 {} : {}", user.getId(), e.getMessage());
        }

        try{
            long accessTokenExpiryMillis = jwtTokenProvider.getTokenExpiryInMilliseconds(accessToken);
            String accessBlacklistKey = "accessToken:" + accessToken;
            redisTemplate.opsForValue().set(accessBlacklistKey, "blacklisted", accessTokenExpiryMillis, TimeUnit.MILLISECONDS);
        }catch(Exception e){
            log.error("블랙리스트 처리에 실패했습니다 : {}", e.getMessage());
        }
    }
}
