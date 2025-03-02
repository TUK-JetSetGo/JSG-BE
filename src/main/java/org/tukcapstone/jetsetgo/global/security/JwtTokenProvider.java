package org.tukcapstone.jetsetgo.global.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tukcapstone.jetsetgo.global.security.token.RedisTokenBlacklistProvider;
import org.tukcapstone.jetsetgo.global.security.token.TokenBlacklistProvider;

@Component
public class JwtTokenProvider {

    private final String secretKeyString;
    private final RedisTokenBlacklistProvider redisTokenBlacklistProvider;
    private SecretKey secretKey;

    @Getter
    private final long accessTokenValidityInMilliseconds;

    @Getter
    private final long refreshTokenValidityInMilliseconds;


    public JwtTokenProvider(
            @Value("${security.jwt.token.secret-key}") String secretKeyString,
            @Value("${security.jwt.token.expire-length}") long accessTokenValidityInMilliseconds,
            @Value("${security.jwt.token.refresh-expire-length}") long refreshTokenValidityInMilliseconds,
            TokenBlacklistProvider tokenBlacklistProvider,
            RedisTokenBlacklistProvider redisTokenBlacklistProvider) {
        this.secretKeyString = secretKeyString;
        this.accessTokenValidityInMilliseconds = accessTokenValidityInMilliseconds;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInMilliseconds;
        this.redisTokenBlacklistProvider = redisTokenBlacklistProvider;
    }

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKeyString));
    }

    public String createToken(Long id, String name) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + accessTokenValidityInMilliseconds);
        return Jwts.builder()
                .claim("id", id)
                .claim("name", name)
                .claim("tokenType", "access")
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(secretKey)
                .compact();
    }

    public String createRefreshToken(Long id) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + refreshTokenValidityInMilliseconds);
        return Jwts.builder()
                .claim("id", id)
                .claim("tokenType", "refresh")
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(secretKey)
                .compact();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isValidateToken(String token) {
        if (redisTokenBlacklistProvider.isTokenBlacklisted(token)){
            return false;
        }
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isValidateRefreshToken(String token) {
        try {
            Claims claims = getClaims(token);
            String tokenType = claims.get("tokenType", String.class);

            return "refresh".equals(tokenType);
        } catch (Exception e) {
            return false;
        }
    }

    public Long getId(String token) {
        return getClaims(token).get("id", Long.class);
    }

    public String getRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    public long getTokenExpiryInMilliseconds(String token) {
        Date expiration = getClaims(token).getExpiration();
        return expiration.getTime() - System.currentTimeMillis();
    }

}
