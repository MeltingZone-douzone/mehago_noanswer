package com.douzone.mehago.controller;

import com.douzone.mehago.dto.JsonResult;
import com.douzone.mehago.dto.MailDto;
import com.douzone.mehago.service.AccountService;
import com.douzone.mehago.service.MailService;
import com.douzone.mehago.util.RandomPassword;
import com.douzone.mehago.vo.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/api/account")
@RestController
public class SearchController {
    @Autowired
    private AccountService accountService;

    private final MailService mailService;

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

    // @RequestBody 
    @PostMapping("/mail")
    public void execMail(MailDto mailDto) {
        mailDto.setTitle("MEHAGO 임시 비밀번호입니다.");
        mailDto.setMessage("여기 임시비번");
        mailDto.setAddress("dmswltpwns1@gmail.com");
        // System.out.println(mailDto.getAddress() + " in controller");
        mailService.mailSend(mailDto);
    }

}
