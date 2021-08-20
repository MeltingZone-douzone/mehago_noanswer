package com.douzone.mehago.controller;

import javax.crypto.spec.SecretKeySpec;

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

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody Account account) {
        // accountService.signUp(account);
        return ResponseEntity.ok().build();
    }

    // 암호화 테스트용.. localhost:9999/profile하면 볼 수 있음.
    @GetMapping("/get-user")
    public void getUser() {
        // String secretKey = "Peach";
        // String originalString = "asd003786!";

        // String encryptedString = AES.encrypt(originalString, secretKey);
        // String decryptedString = AES.decrypt(encryptedString, secretKey);

        // System.out.println(encryptedString);
        // System.out.println(decryptedString);

        Account account =  new Account();
        account.setNo(2L);
        account.setNickname("nickname");


        String token = jwtTokenUtil.generateToken(account);
        System.out.println(token);

        Account validAccount = jwtDecoder.decodeJwt(token);

        System.out.println(validAccount.toString());
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

