package org.tukcapstone.jetsetgo.domain.user.service;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.tukcapstone.jetsetgo.domain.user.entity.User;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    @Override
    public Optional<User> findUserById(Long userId) {
        return Optional.empty();
    }
}
