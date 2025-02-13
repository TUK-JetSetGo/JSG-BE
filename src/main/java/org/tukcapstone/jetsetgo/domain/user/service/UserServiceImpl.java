package org.tukcapstone.jetsetgo.domain.user.service;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.tukcapstone.jetsetgo.domain.user.entity.User;
import org.tukcapstone.jetsetgo.domain.user.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    @Override
    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }
}
