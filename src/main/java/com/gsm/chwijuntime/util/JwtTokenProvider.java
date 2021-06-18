package com.gsm.chwijuntime.util;

import com.gsm.chwijuntime.model.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

//    public final static long TOKEN_VALIDATION_SECOND = 1000L * 86400;  //하루를 accessToken 만료 기간으로 잡는다
//    public final static long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 3600 * 24 * 210; //7개월을 refreshToken 만료 기간으로 잡는다.

    public final static long TOKEN_VALIDATION_SECOND = 1000L * 60;  //하루를 accessToken 만료 기간으로 잡는다
    public final static long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 3600; //7개월을 refreshToken 만료 기간으로 잡는다.

    final static public String ACCESS_TOKEN_NAME = "accessToken";
    final static public String REFRESH_TOKEN_NAME = "refreshToken";

    private Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractAllClaims(String token) throws ExpiredJwtException {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUserEmail(String token){
        return extractAllClaims(token).get("userEmail", String.class);
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    public String generateToken(Member member) {
        return doGenerateToken(member.getMemberEmail(), member.getRoles(), member.getMemberClassNumber(), TOKEN_VALIDATION_SECOND);
    }

    public String generateRefreshToken(Member member) {
        return doGenerateToken(member.getMemberEmail(), member.getRoles(), member.getMemberClassNumber(), REFRESH_TOKEN_VALIDATION_SECOND);
    }

    public String doGenerateToken(String userEmail, List<String> roles, String classNumber, long expireTime) {

        Claims claims = Jwts.claims();
        claims.put("userEmail", userEmail);
        claims.put("classNumber", classNumber);
        claims.put("roles", roles);

        String jwt = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSigningKey(SECRET_KEY), SignatureAlgorithm.HS256)
                .compact();
        return jwt;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUserEmail(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}