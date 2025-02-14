package org.tukcapstone.jetsetgo.domain.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tukcapstone.jetsetgo.domain.user.dto.UserResponse;
import org.tukcapstone.jetsetgo.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySocialId(Long socialId);
}
