package org.tukcapstone.jetsetgo.domain.user.service;

import java.util.Optional;
import org.tukcapstone.jetsetgo.domain.user.dto.UserRequest;
import org.tukcapstone.jetsetgo.domain.user.dto.UserResponse;
import org.tukcapstone.jetsetgo.domain.user.entity.User;


public interface UserService {
    Optional<User> findUserById(Long userId);

    UserResponse.UserProfile getUserProfileById(Long id);
    UserResponse.UserProfile updateUserProfile(Long userId, UserRequest.UserProfile updateRequest);
}
