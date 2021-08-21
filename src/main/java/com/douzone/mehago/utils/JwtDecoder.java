package com.douzone.mehago.utils;

import java.util.Base64;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.douzone.mehago.vo.Account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class JwtDecoder {
    
    private static final Logger log = LoggerFactory.getLogger(JwtDecoder.class);
    
    @Value("${spring.jwt.secret}")
    private String secretKey;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public Account decodeJwt(String token){
        DecodedJWT decodedJWT = isValidToken(token).orElseThrow(()-> new InvalidJwtException("유효한 토큰이 아닙니다."));

        Long userNo = decodedJWT.getClaim("userNo").asLong();
        String userNickname = decodedJWT.getClaim("userNickname").asString();

        Account account = new Account();
        account.setNo(userNo);
        account.setNickname(userNickname);
        return account;
    }

    private Optional<DecodedJWT> isValidToken(String token){
        DecodedJWT jwt = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).build();

            jwt = verifier.verify(token);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return Optional.ofNullable(jwt);
    }
}
