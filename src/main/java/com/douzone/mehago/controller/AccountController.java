package com.douzone.mehago.controller;

import java.util.concurrent.TimeUnit;

import javax.crypto.spec.SecretKeySpec;

import com.douzone.mehago.dto.CommonResponse;
import com.douzone.mehago.service.AccountService;
import com.douzone.mehago.utils.AES;
import com.douzone.mehago.utils.JwtDecoder;
import com.douzone.mehago.utils.JwtTokenUtil;
import com.douzone.mehago.vo.Account;

import org.apache.tomcat.util.net.openssl.ciphers.Encryption;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;


@RequestMapping("/api/account")
@Controller
@RequiredArgsConstructor
public class AccountController {
    
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtDecoder jwtDecoder;
    private final AccountService accountService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account){  
        Account result = accountService.getAccount(account);  
        if(result == null){
            return ResponseEntity.ok().body("cant find Account");         
        }
        String token = jwtTokenUtil.generateAccessToken(result);
        return ResponseEntity.ok().body(token);
            
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody Account account) {
        // accountService.signUp(account);
        return ResponseEntity.ok().build();
    }

    // ?��?��?�� ?��?��?��?��.. localhost:9999/profile?���? �? ?�� ?��?��.
    @GetMapping("/get-user")
    public ResponseEntity<?> getUser() {
        // String secretKey = "Peach";
        // String originalString = "asd003786!";

        // String encryptedString = AES.encrypt(originalString, secretKey);
        // String decryptedString = AES.decrypt(encryptedString, secretKey);

        // System.out.println(encryptedString);
        // System.out.println(decryptedString);

        System.out.println("안녕");

        Account account =  new Account();
        account.setNo(2L);
        account.setNickname("nickname");


        String token = jwtTokenUtil.generateAccessToken(account);
        System.out.println(token);

        try{
            TimeUnit.SECONDS.sleep(2);
        }catch(Exception e){
            e.getStackTrace();
        }
        Account validAccount = jwtDecoder.decodeJwt(token);

        System.out.println(validAccount.toString());

        return ResponseEntity.ok().body(CommonResponse.success(token));
    }
    

    @PostMapping(value="/update/nickname")
    public ResponseEntity<?> updateNickname(@RequestBody Account account) {
        accountService.updateNickname(account);
        
        return ResponseEntity.ok().build();
    }

    @PostMapping(value="/update/password")
    public ResponseEntity<?> updatePassword(@RequestBody Account account) {
        accountService.updatePassword(account);
        
        return ResponseEntity.ok().build();
    }

    @PostMapping(value="/update/userInfo")
    public ResponseEntity<?> updateUserInfo(@RequestBody Account account) {
        accountService.updateUserInfo(account);
        
        return ResponseEntity.ok().build();
    }

}

