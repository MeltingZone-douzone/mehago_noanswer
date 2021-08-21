package com.douzone.mehago.controller;

import com.douzone.mehago.security.Auth;
import com.douzone.mehago.service.AccountService;
import com.douzone.mehago.utils.AES;
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
import org.springframework.web.bind.annotation.ResponseBody;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.net.openssl.ciphers.Encryption;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor  // TODO 이게 머지
@RequestMapping("/api/account")
@Controller
public class AccountController {
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtDecoder jwtDecoder;
    private final AccountService accountService;

    @Auth
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "hi";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody Account account) {
        System.out.println("아아 여기는 부트");
        System.out.println(account);
        Boolean result = accountService.signUp(account);
        System.out.println(result);
        return ResponseEntity.ok().body(result ? result : "signup failed");
    }

    @PostMapping("/signup/valid-{name}")
    public ResponseEntity<?> validateAccount(@PathVariable String name, @RequestBody String value) {
        System.out.println(" name, value는 "+ name + " : " + value);
        String data = accountService.isExist(name, value);
        System.out.println(" name, data는 "+ name + " : " + data);
        // return ResponseEntity.ok().build();
        return ResponseEntity.ok().body(data != null ? data : "null");
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
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account){  
        Account result = accountService.getAccount(account);  
       
        return ResponseEntity.ok().body(result == null ? "cant find Account" : result);         
    }
}
