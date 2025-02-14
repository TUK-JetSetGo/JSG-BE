package org.tukcapstone.jetsetgo.domain.user.service;

import static org.tukcapstone.jetsetgo.global.response.exception.code.UserErrorCode.USER_NOT_FOUND;

import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tukcapstone.jetsetgo.domain.user.converter.UserConverter;
import org.tukcapstone.jetsetgo.domain.user.dto.UserRequest;
import org.tukcapstone.jetsetgo.domain.user.dto.UserResponse;
import org.tukcapstone.jetsetgo.domain.user.entity.User;
import org.tukcapstone.jetsetgo.domain.user.repository.UserRepository;
import org.tukcapstone.jetsetgo.global.response.exception.GeneralException;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    @Override
    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public UserResponse.UserProfile getUserProfileById(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(USER_NOT_FOUND));

        return userConverter.convertToProfileResponse(user);
    }

    @Override
    public UserResponse.UserProfile updateUserProfile(Long userId, UserRequest.UserProfile updateRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(USER_NOT_FOUND));

        if (updateRequest.getName().isPresent()) {
            user.setName(updateRequest.getName().get());
        }
        if (updateRequest.getEmail().isPresent()) {
            user.setEmail(updateRequest.getEmail().get());
        }

        return userConverter.convertToProfileResponse(user);
    }

}
