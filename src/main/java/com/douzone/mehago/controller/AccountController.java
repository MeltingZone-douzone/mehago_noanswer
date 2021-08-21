package com.douzone.mehago.controller;

import java.util.concurrent.TimeUnit;

import javax.crypto.spec.SecretKeySpec;

import com.douzone.mehago.dto.CommonResponse;
import com.douzone.mehago.security.Auth;
import com.douzone.mehago.service.AccountService;
import com.douzone.mehago.utils.AES;
import com.douzone.mehago.utils.JwtDecoder;
import com.douzone.mehago.utils.JwtTokenUtil;
import com.douzone.mehago.vo.Account;

import org.apache.tomcat.util.net.openssl.ciphers.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;


@RequestMapping("/api/account")
@Controller
@RequiredArgsConstructor
public class AccountController {
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtDecoder jwtDecoder;
    @Autowired
    private AccountService accountService;
    
@Auth
@GetMapping("/test")
public ResponseEntity<?> test(){
    return ResponseEntity.ok().build();
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

