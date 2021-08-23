package com.douzone.mehago.controller;

import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import com.douzone.mehago.dto.CommonResponse;
import com.douzone.mehago.service.AccountService;
import com.douzone.mehago.utils.JwtDecoder;
import com.douzone.mehago.utils.JwtTokenUtil;
import com.douzone.mehago.vo.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/account")
@Controller
public class AccountController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtDecoder jwtDecoder;
    @Autowired
    private AccountService accountService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid Account account) {
        System.out.println(account);
        Boolean result = accountService.signUp(account);
        System.out.println(result);
        return ResponseEntity.ok().body(result ? result : "signup failed");
    }

    @PostMapping("/signup/valid-{name}")
    public ResponseEntity<?> validateAccount(@PathVariable String name, @RequestBody String value) {
        System.out.println(name + " name임");
        String data = accountService.existsData(name, value);
        return ResponseEntity.ok().body(data != null ? data : "null");
    }

    @GetMapping("/get-user")
    public ResponseEntity<?> getUser() {
        // String secretKey = "Peach";
        // String originalString = "asd003786!";

        // String encryptedString = AES.encrypt(originalString, secretKey);
        // String decryptedString = AES.decrypt(encryptedString, secretKey);
        // System.out.println(encryptedString);
        // System.out.println(decryptedString);

        System.out.println("안녕");

        Account account = new Account();
        account.setNo(2L);
        account.setNickname("nickname");

        String token = jwtTokenUtil.generateAccessToken(account);
        System.out.println(token);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.getStackTrace();
        }
        Account validAccount = jwtDecoder.decodeJwt(token);
        System.out.println(validAccount.toString());

        return ResponseEntity.ok().body(CommonResponse.success(token));
    }

    @PostMapping(value = "/update/nickname")
    public ResponseEntity<?> updateNickname(@RequestBody Account account) {
        accountService.updateNickname(account);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/update/password")
    public ResponseEntity<?> updatePassword(@RequestBody Account account) {
        accountService.updatePassword(account);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/update/userInfo")
    public ResponseEntity<?> updateUserInfo(@RequestBody Account account) {
        accountService.updateUserInfo(account);

        return ResponseEntity.ok().build();
    }
}
