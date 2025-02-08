package org.tukcapstone.jetsetgo.domain.auth.service;

import static org.tukcapstone.jetsetgo.global.response.exception.code.AuthErrorCode.INVALID_SOCIAL_TYPE;

import java.security.AuthProvider;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tukcapstone.jetsetgo.domain.auth.dto.AuthResponse;
import org.tukcapstone.jetsetgo.domain.auth.dto.AuthResponse.LoginResponse;
import org.tukcapstone.jetsetgo.domain.user.entity.enums.SocialType;
import org.tukcapstone.jetsetgo.domain.user.repository.UserRepository;
import org.tukcapstone.jetsetgo.global.external.KakaoResponse;
import org.tukcapstone.jetsetgo.global.external.KakaoUserClient;
import org.tukcapstone.jetsetgo.global.response.exception.GeneralException;
import org.tukcapstone.jetsetgo.global.response.exception.code.UserErrorCode;
import org.tukcapstone.jetsetgo.global.util.JwtTokenProvider;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final List<SocialAuthService> socialAuthServiceList;


    @Override
    @Transactional
    public AuthResponse.LoginResponse login(SocialType socialType, String accessToken) {
        SocialAuthService authService = socialAuthServiceList.stream()
                .filter(service -> service.getProvider().equals(socialType))
                .findFirst()
                .orElseThrow(() -> new GeneralException(INVALID_SOCIAL_TYPE));

        return authService.authenticate(accessToken);
    }
}
