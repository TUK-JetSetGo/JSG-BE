package org.tukcapstone.jetsetgo.global.security.token;


public interface TokenBlacklistProvider {
    void blacklistToken(String token, long expiryTimeMillis);
    boolean isTokenBlacklisted(String token);
}
