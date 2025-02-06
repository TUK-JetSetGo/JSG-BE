package org.tukcapstone.jetsetgo.domain.auth.service;

import org.tukcapstone.jetsetgo.domain.auth.dto.AuthResponse;

public interface AuthService {
    AuthResponse.LoginResponse kakaoLogin(String accessToken);
}
