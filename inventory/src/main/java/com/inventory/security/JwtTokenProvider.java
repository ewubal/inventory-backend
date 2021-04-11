package com.inventory.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@AllArgsConstructor
public class JwtTokenProvider {

    private final JwtConfig jwtConfig;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public String getIdFromToken(String token) {
        return getClaimFromToken(token, Claims::getId);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String generateToken(MyUser userDetails) {
        return doGenerateToken(new HashMap<>(), userDetails.getUsername(), userDetails.getId());
    }

    public boolean validateToken(String token, MyUser userDetails) {
        return getUsernameFromToken(token).equals(userDetails.getUsername())
                && getIdFromToken(token).equals(userDetails.getId().toString())
                && !isTokenExpired(token);
    }

    public String createTokenForTests(String username, Integer id) {
        return doGenerateToken(new HashMap<>(), username, id);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtConfig.getSecret()).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject, Integer id) {
        String idStr = id.toString();
        long currentTimeMs = System.currentTimeMillis();
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setId(idStr)
                .setIssuedAt(new Date(currentTimeMs))
                .setExpiration(new Date(currentTimeMs + jwtConfig.getDurationMillis()))
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
                .compact();
    }
}
