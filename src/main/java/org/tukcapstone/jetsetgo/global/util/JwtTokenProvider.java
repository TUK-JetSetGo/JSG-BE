package org.tukcapstone.jetsetgo.global.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
    private final SecretKey secretKey;
    private final Long validityInMilliseconds;

    public JwtTokenProvider(
            @Value("${security.jwt.token.secret-key}") String secretKey,
            @Value("${security.jwt.token.expire-length}") Long validityInMilliseconds) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
        this.validityInMilliseconds = validityInMilliseconds;
    }

    public String createToken(Long id, String name){
        Date now = new Date();
        Date expiry = new Date(now.getTime() + validityInMilliseconds);
        return Jwts.builder()
                .claim("id", id)
                .claim("name", name)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(secretKey)
                .compact();
    }
    public boolean isValidateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getId(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("id", Long.class);
    }

    public String getRole(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }
}
