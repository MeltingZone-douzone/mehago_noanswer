package com.douzone.mehago.controller;

import javax.crypto.spec.SecretKeySpec;

import com.douzone.mehago.service.AccountService;
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
    
    private final AccountService accountService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody Account account) {
        // accountService.signUp(account);

        return ResponseEntity.ok().build();
    }


    @GetMapping("get-user")
    public void getUser() {


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

