package com.spring.blogapp_Springboot.Security;

import org.springframework.stereotype.Service;

import java.util.Date;

import static org.springframework.security.config.Elements.JWT;

@Service
public class JWTService {
    //TODO: Move jwt token ti .env
    private static final String JWT_KEY = "kjsbcbsakckascnaskcas";
    private Algorithm algorithm = Algorithm.HMAC256(JWT_KEY);

    public String createJWT(Long userId){
        return JWT.create()
               .withSubject(userId.toString())
               .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
               .sign(algorithm);
    }
    public Long reteriveUserId(String jwtString){
        var decodeJWT = JWT.decode(jwtString);
        var userId = Long.valueOf(decodeJWT.getSubject());
        return userId;
    }
}
