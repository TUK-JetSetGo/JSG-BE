package org.tukcapstone.jetsetgo.domain.auth.service;

import org.tukcapstone.jetsetgo.domain.auth.dto.AuthResponse;
import org.tukcapstone.jetsetgo.domain.user.entity.enums.SocialType;

public interface AuthService {
    AuthResponse.LoginResponse login(SocialType provider, String accessToken);
    AuthResponse.LoginResponse refresh(String refreshToken);
}
