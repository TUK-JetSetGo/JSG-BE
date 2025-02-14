package org.tukcapstone.jetsetgo.domain.user.converter;

import org.springframework.stereotype.Component;
import org.tukcapstone.jetsetgo.domain.user.dto.UserResponse;
import org.tukcapstone.jetsetgo.domain.user.entity.User;

@Component
public class UserConverter {
    public UserResponse.UserProfile convertToProfileResponse(User user){
        return UserResponse.UserProfile.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
