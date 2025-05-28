package com.quipux.prueba_java.security;

import com.quipux.prueba_java.constant.Messages;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.secret.key}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    @Value("${jwt.issuer}")
    private String issuer;

    private Key key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String createToken(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String authorities = authentication.getAuthorities().stream()
                .map(a -> a.getAuthority().startsWith("ROLE_") ? a.getAuthority() : "ROLE_" + a.getAuthority())
                .collect(Collectors.joining(","));

        Map<String, Object> claims = new HashMap<>();
        claims.put(Messages.USER_ID, userDetails.getId());
        claims.put(Messages.AUTHORITIES, authorities);

        var token = Jwts.builder()
                .setClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setIssuer(issuer)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // 1 hora
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            throw new JwtException(Messages.INVALID_TOKEN);
        }
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .requireIssuer(issuer)
                .build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public String extractSpecificClaim(String token, String claimName) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .requireIssuer(issuer)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get(claimName, String.class);
    }
}

