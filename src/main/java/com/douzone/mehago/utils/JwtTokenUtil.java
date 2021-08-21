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
<<<<<<< HEAD
                        // .withExpiresAt(expiresAt)
                        .sign(generateAlgorithm());  // 첫 번째 인자에는 payload가 들어가고 두 번째 인자에는 비밀키 값이 들어감
=======
                        .withExpiresAt(new Date(System.currentTimeMillis()+ACCESS_TOKEN_EXPIRE_TIME))
                        .sign(generateAlgorithm());
>>>>>>> origin/sewon

        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
            
            // TODO: Exception
        } catch (Exception e) {
<<<<<<< HEAD
            // TODO: handle exception
            // log.error(e.getMessage());
            e.getStackTrace();
=======
            e.printStackTrace();
>>>>>>> origin/sewon
        }

        return token;
    }

    // 토큰에 필요한 알고리즘 생성 
    // Algorithrm, Header에 담을 정보, payload 정보를 JWTCreator 클래스의 sign 메소드를 호출하여 토큰을 만들게 됨
    private Algorithm generateAlgorithm() throws UnsupportedEncodingException{
        return Algorithm.HMAC256(secretKey);
    }
}