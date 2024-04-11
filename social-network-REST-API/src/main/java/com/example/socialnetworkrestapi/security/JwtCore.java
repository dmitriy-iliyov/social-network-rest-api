package com.example.socialnetworkrestapi.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;


import java.util.Date;

@Component
public class JwtCore {

    @Value("${socialnetworkrestapi.app.secret}")
    private String secret;

    @Value("${socialnetworkrestapi.app.lifetime}")
    private int lifetime;

    public String generateToken(Authentication authentication) {
        UserDetailsImplementation userDetails = (UserDetailsImplementation) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + lifetime))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    public String getTokenFromHttpHeader(String header){
        String jwt = null;
        try{
            if(header != null && header.startsWith("Bearer "))
                jwt = header.substring(7);
            if (jwt != null)
                return jwt;
        } catch (Exception e){
            System.out.println("EXCEPTION  " + e.getMessage());
        }
        return null;
    }

    public Long getIdFromJwt(String token){
        Long id = null;
        try {
            Claims userData = Jwts.parser()
                    .setSigningKey(secret.getBytes())
                    .parseClaimsJwt(token)
                    .getBody();
        } catch (Exception e){
            System.out.println("EXCEPTION  " + e.getMessage());
        }
        return id;
    }

    public String getNameFromJwt(String token) {
        return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
