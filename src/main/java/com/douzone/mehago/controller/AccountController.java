package com.douzone.mehago.controller;

<<<<<<< HEAD
=======
import java.util.concurrent.TimeUnit;

import javax.crypto.spec.SecretKeySpec;

import com.douzone.mehago.dto.CommonResponse;
>>>>>>> origin/sewon
import com.douzone.mehago.security.Auth;
import com.douzone.mehago.service.AccountService;
import com.douzone.mehago.service.MailService;
import com.douzone.mehago.vo.Account;
import com.douzone.mehago.vo.Mail;
import com.douzone.mehago.utils.AES;
import com.douzone.mehago.utils.JwtDecoder;
import com.douzone.mehago.utils.JwtTokenUtil;
import com.douzone.mehago.utils.RandomPassword;

<<<<<<< HEAD
=======
import org.apache.tomcat.util.net.openssl.ciphers.Encryption;
>>>>>>> origin/sewon
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
<<<<<<< HEAD
import javax.crypto.spec.SecretKeySpec;
=======
>>>>>>> origin/sewon

import org.apache.tomcat.util.net.openssl.ciphers.Encryption;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/account")
@Controller
public class AccountController {
    
<<<<<<< HEAD
    private final MailService mailService;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtDecoder jwtDecoder;
    private final AccountService accountService;
=======
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtDecoder jwtDecoder;
    @Autowired
    private AccountService accountService;
    
@Auth
@PostMapping("/test")
public ResponseEntity<?> test(){
    return ResponseEntity.ok().build();
}

@Auth
@GetMapping("/test")
public ResponseEntity<?> test2(){
    return ResponseEntity.ok().build();
}
>>>>>>> origin/sewon

    @Auth
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "hi";
    }

    @RequestMapping("/login")
    public ResponseEntity<?> login(){
        System.out.println("login");
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
<<<<<<< HEAD
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account){  
        Account result = accountService.getAccount(account);  
       
        return ResponseEntity.ok().body(result == null ? "cant find Account" : result);         
    }
=======

}
>>>>>>> origin/sewon




    @PostMapping("/findByNameAndEmail")
    public ResponseEntity<?> findByNameAndEmail(@RequestBody Account account, Mail mailDto){
        Account accountVo = null;
        try {
            String name = account.getName();
            String email = account.getEmail();
            accountVo = accountService.searchAccount(name, email);
            if(accountVo == null){
                return ResponseEntity.ok().body("cant find Account");         
            }
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
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return ResponseEntity.ok().body(accountVo);
    }

    @PostMapping("/searchEmail")
    public ResponseEntity<?> searchEmail(@RequestBody Account account){
        Account accountVo = null;
        try {
            String name = account.getName();
            String phoneNumber = account.getPhoneNumber();
            accountVo = accountService.searchEmail(name, phoneNumber);                
            System.out.println(accountVo);
            if(accountVo == null){
                return ResponseEntity.ok().body("cant find Account");         
            }
                
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.ok().body(accountVo.getEmail());
    }

    // @RequestBody 
    @PostMapping("/mail")
    public void execMail(Mail mail) {
        mail.setTitle("MEHAGO 임시 비밀번호입니다.");
        mail.setMessage("여기 임시비번");
        mail.setAddress("dmswltpwns1@gmail.com");
        // System.out.println(mailDto.getAddress() + " in controller");
        mailService.mailSend(mail);
    }


}
