package com.douzone.mehago.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.douzone.mehago.vo.Account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil{

    // private static final Logger log =LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;            // 30분

    @Value("${spring.jwt.secret}")
    private String secretKey;

    @Value("${spring.jwt.issuer}")
    private String issuer;

    @PostConstruct // 주입 받은뒤 실행하는 초기화
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String generateAccessToken(Account account){
        String token = null;    
        try {
            token = JWT.create()
                        .withIssuer(issuer)
                        .withClaim("userNo", account.getNo())
                        .withClaim("userNickname", account.getNickname())
                        .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME))
                        .sign(generateAlgorithm());

        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
            
            // TODO: Exception
        } catch (Exception e) {
            e.printStackTrace();
        }

        return token;
    }

    private Algorithm generateAlgorithm() throws UnsupportedEncodingException{
        return Algorithm.HMAC256(secretKey);
    }
}