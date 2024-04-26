package com.SecurityGuy.Security.config;

import com.SecurityGuy.Security.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    // From application properties
    @Value("${security.jwt.expiration-time}")
    private long EXPIRATION_TIME;

    // From application properties
    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;


    public String generateToken(User user, Map<String, Object> extraClaims) {
        // Get time of JWT token issued and expired, and put it in jwt.
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + (EXPIRATION_TIME));
        // Building the jwt token
        // Set claims and pass in extra claims (roles and name)
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    private Key generateKey(){
        byte[] secreteAsBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(secreteAsBytes);
    }

    public String extractUsername(String jwt) {
        // Use method from below
        return extractAllClaims(jwt).getSubject();
    }

    // Can be used for future uses
    private Claims extractAllClaims(String jwt) {
        return Jwts.parserBuilder().setSigningKey(generateKey()).build()
                .parseClaimsJws(jwt).getBody();
    }


}