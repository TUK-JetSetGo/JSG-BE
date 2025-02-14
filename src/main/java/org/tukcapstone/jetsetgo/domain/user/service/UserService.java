package org.tukcapstone.jetsetgo.domain.user.service;

import java.util.Optional;
import org.tukcapstone.jetsetgo.domain.user.entity.User;


public interface UserService {
    Optional<User> findUserById(Long userId);

}
