package com.onboarding.projects.security;

import java.io.Serializable;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JwtUtil implements Serializable{

	private static final long serialVersionUID = -2550185165626007488L;
	
	// 5분 = 300초
	public static final long JWT_VALIDITY = 5 * 60;
	
	@Value("${jwt.secret}")
	private String secret;

	// JWT에서 ussername(subject)을 추출
	public String getUsernameFromToken(String token) {
		
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	// JWT에서 UserId 추출
	public Long getUserIdFromToken(String token) {
        String jwt = removeBearerPrefix(token);
		
        return getClaimFromToken(jwt, claims -> claims.get("userId", Long.class));
	}
	
	// JWT에서 만료 시간 추출
	public Date getExpirationDateFromToken(String token) {
		
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	// JWT Claim 추출
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        String jwt = removeBearerPrefix(token);
        
		Claims claims = getAllClaimsFromToken(token);
		
		return claimsResolver.apply(claims);
	}
	
	// JWT 전체 Claim 추출
	private Claims getAllClaimsFromToken(String token) {
		
		return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
	}

	// Secret Key 생성
    private SecretKey getSigningKey() {

        byte[] keyBytes = Decoders.BASE64.decode(secret);

        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    // Bearer 접두사 제거
    private String removeBearerPrefix(String token) {

        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }

        return token;
    }

    // JWT 만료 여부 확인
	private Boolean isTokenExpired(String token) {
		Date expiration = getExpirationDateFromToken(token);
		
		return expiration.before(new Date());
	}
	
	// JWT 생성
	public String generateToken(UserDetails userDetails, Long userId) {
		// Claims는 JWT의 payload를 담는 객체
		Map<String, Object> claims = new HashMap<>();
		
		claims.put("userId", userId);
		
		Date now = new Date();
		
		// JWT 생성
		return Jwts.builder()
					.setClaims(claims).setSubject(userDetails.getUsername())
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + JWT_VALIDITY * 1000))
					.signWith(new SecretKeySpec(Base64.getDecoder().decode(secret), SignatureAlgorithm.HS256.getJcaName()))
					.compact();
	}
	
	// JWT 검증
	public Boolean validateToken(String token, UserDetails userDetails) {
		String username = getUsernameFromToken(token);
		
		return (username.equals(userDetails.getUsername()) &&!isTokenExpired(token));
	}
	
}
