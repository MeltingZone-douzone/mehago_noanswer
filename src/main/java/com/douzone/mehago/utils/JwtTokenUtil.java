package com.douzone.mehago.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.annotation.PostConstruct;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.douzone.mehago.vo.Account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil{

    // private static final Logger log =LoggerFactory.getLogger(JwtTokenUtil.class);

    @Value("${spring.jwt.secret}")
    private String secretKey;

    @PostConstruct // 주입 받은뒤 실행하는 초기화
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String generateToken(Account account){
        String token = null;    
        try {
            token = JWT.create()
                        .withClaim("userNo", account.getNo())
                        .withClaim("userNickname", account.getNickname())
                        // .withExpiresAt(expiresAt)
                        .sign(generateAlgorithm());

        } catch (Exception e) {
            //TODO: handle exception
            // log.error(e.getMessage());
            e.getStackTrace();
        }

        return token;
    }

    private Algorithm generateAlgorithm() throws UnsupportedEncodingException{
        return Algorithm.HMAC256(secretKey);
    }
}