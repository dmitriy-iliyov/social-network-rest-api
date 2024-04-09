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

    public String generateToken(Authentication authentication){
        UserDetailsImplementation userDetails = (UserDetailsImplementation) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new  Date().getTime() + lifetime))
                .signWith(SignatureAlgorithm.ES256, secret)
                .compact();
    }

    public String getNameFromJwt(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody().getSubject();
    }
}

