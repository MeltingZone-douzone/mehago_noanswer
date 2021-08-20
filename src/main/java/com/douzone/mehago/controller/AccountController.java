package com.douzone.mehago.controller;

import javax.crypto.spec.SecretKeySpec;

import com.douzone.mehago.dto.JsonResult;
import com.douzone.mehago.dto.MailDto;
import com.douzone.mehago.service.AccountService;
import com.douzone.mehago.service.MailService;
import com.douzone.mehago.util.RandomPassword;
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
    private final MailService mailService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody Account account) {
        // accountService.signUp(account);

        return ResponseEntity.ok().build();
    }


    @GetMapping("get-user")
    public void getUser() {


    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account){  
        Account result = accountService.getAccount(account);  
       
        return ResponseEntity.ok().body(result == null ? "cant find Account" : result);         
    }

    @PostMapping(value = "/findByNameAndEmail")
    public JsonResult findByNameAndEmail(@RequestBody Account account, MailDto mailDto){
        Account accountVo = null;
        try{
            String name = account.getName();
            String email = account.getEmail();
            accountVo = accountService.searchAccount(name, email);
            System.out.println(accountVo);

            if(accountVo.getEmail() != null){
                System.out.println("이메일 있음 보낼꺼임");
                String rendomPassword = RandomPassword.getRamdomPassword(10);
                accountService.changeRandomPassword(rendomPassword, email);

                mailDto.setTitle("MEHAGO 임시 비밀번호입니다.");
                mailDto.setMessage("요청하신 임시 비밀번호는 다음과 같습니다." + rendomPassword);
                mailDto.setAddress("mehagochat@gmail.com");
                System.out.println(mailDto.getAddress() + " in controller");
                mailService.mailSend(mailDto);

            } 
        } catch (Exception e){
            return JsonResult.fail(e.toString());
        }
        
        return JsonResult.success(accountVo != null );
    }

    @PostMapping(value = "/searchEmail")
    public JsonResult searchEmail(@RequestBody Account account){
        Account accountVo = null;
        try {
            String name = account.getName();
            String phoneNumber = account.getPhoneNumber();
            accountVo = accountService.searchEmail(name, phoneNumber);                
            System.out.println(accountVo);
            System.out.println(accountVo.getEmail());
        } catch (Exception e) {
            return JsonResult.fail(e.toString());
        }

        return JsonResult.success(accountVo != null ? accountVo.getEmail(): "No Search Email");
    }
}
