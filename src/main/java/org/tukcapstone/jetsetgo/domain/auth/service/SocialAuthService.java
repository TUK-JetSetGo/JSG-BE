package org.tukcapstone.jetsetgo.domain.auth.service;


import org.tukcapstone.jetsetgo.domain.auth.dto.AuthResponse;
import org.tukcapstone.jetsetgo.domain.user.entity.enums.SocialType;

public interface SocialAuthService {
    SocialType getProvider();
    AuthResponse.LoginResponse authenticate(String accessToken);
}
