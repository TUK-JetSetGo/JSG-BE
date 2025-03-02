package org.tukcapstone.jetsetgo.global.security.token;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisTokenBlacklistProvider implements TokenBlacklistProvider {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void blacklistToken(String token, long expiryTimeMillis) {
        String redisKey = "accessToken:" + token;
        redisTemplate.opsForValue().set(redisKey, "blacklisted", expiryTimeMillis, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean isTokenBlacklisted(String token) {
        String redisKey = "accessToken:" + token;
        return Boolean.TRUE.equals(redisTemplate.hasKey(redisKey));
    }
}