package org.tukcapstone.jetsetgo.domain.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {
    @Id
    private Long userId;

    @Column(nullable = false, unique = true)
    private String token;

    public void updateToken(String newToken) {
        this.token = newToken;
    }
}