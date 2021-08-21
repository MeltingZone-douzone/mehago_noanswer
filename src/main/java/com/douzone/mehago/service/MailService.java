package com.douzone.mehago.service;


import com.douzone.mehago.utils.MailHandler;
import com.douzone.mehago.vo.Mail;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailService {
    private JavaMailSender mailSender;
    // private static final String FROM_ADDRESS = "mehagoChat@gmail.com";

    public void mailSend(Mail mailDto) {
        try {
            MailHandler mailHandler = new MailHandler(mailSender);
            // 받는 사람
           mailHandler.setTo(mailDto.getAddress());

           System.out.println(mailDto.getAddress());
            // 보내는 사람
        //    mailHandler.setFrom(MailService.FROM_ADDRESS);
            // 제목
           mailHandler.setSubject(mailDto.getTitle());
            // HTML Layout
            String htmlContent = "<p>" + mailDto.getMessage() +"<p>";
            mailHandler.setText(htmlContent, true);

            mailHandler.send();
            System.out.println("send완료");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}