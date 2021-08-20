package com.example.serverconstruction.controller;

import com.example.serverconstruction.service.AccountService;
import com.example.serverconstruction.vo.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AccountController {
    
    @Autowired
    private AccountService accountService;

    @PostMapping("/api/account/sign-up")
    public ResponseEntity<?> signUp(@RequestBody Account account) {
        System.out.println("아아 여기는 부트");
        System.out.println(account);
        accountService.signUp(account);
        
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/account/sign-up/valid-{name}")
    public ResponseEntity<?> validateAccount(@PathVariable String name, @RequestBody String value) {
        System.out.println(" name, value는 "+ name + " : " + value);
        String data = accountService.isExist(name, value);
        System.out.println(" name, result는 "+ name + " : " + data);
        System.out.println(data != null ? "이미있노 그래서 email고대로감" : "오 없다 그걸로해라 null로감");
        return ResponseEntity.ok().body(data != null ? data : "null");
    }
}
