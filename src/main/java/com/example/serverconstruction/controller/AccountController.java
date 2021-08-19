package com.example.serverconstruction.controller;

import com.example.serverconstruction.service.AccountService;
import com.example.serverconstruction.vo.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AccountController {
    
    @Autowired
    private AccountService accountService;

    @PostMapping("/spring/account/sign-up")
    public ResponseEntity<?> signUp(@RequestBody Account account) {
        System.out.println("아아 여기는 부트");
        System.out.println(account);
        accountService.signUp(account);
        
        return ResponseEntity.ok().build();
    }
}
