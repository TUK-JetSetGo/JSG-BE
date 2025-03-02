package org.tukcapstone.jetsetgo.global.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.tukcapstone.jetsetgo.domain.user.service.UserService;
import org.tukcapstone.jetsetgo.global.security.JwtAuthenticationToken;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7).trim();
            if (jwtTokenProvider.isValidateToken(token)) {
                Long userId = jwtTokenProvider.getId(token);
                userService.findUserById(userId).ifPresent(user -> {
                    JwtAuthenticationToken authentication =
                            new JwtAuthenticationToken(user, null, Collections.emptyList(), token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                });
            } else {
                log.warn("Invalid JWT token : {}", token);
            }
        }
        filterChain.doFilter(request, response);
    }
}