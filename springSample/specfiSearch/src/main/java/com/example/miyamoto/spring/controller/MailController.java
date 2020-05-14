package com.example.miyamoto.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.miyamoto.spring.entity.Company;

@RestController
public class MailController {
    private final JavaMailSender javaMailSender;
    @Autowired
    MailController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    
    @RequestMapping(value="/mail", method=RequestMethod.GET)
    public ModelAndView index(ModelAndView mov) {
    	mov.setViewName("mail/index.html");
//    	mov.addObject("hoge", null);
    	return mov;
    }

    @RequestMapping(value = "/mail/send", method = {RequestMethod.POST} )
    public String send() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("送信先");
        mailMessage.setReplyTo("返信先");
        mailMessage.setFrom("送信元");
        mailMessage.setSubject("テストメール");
        mailMessage.setText("テストメールです\nほげほげ");
        javaMailSender.send(mailMessage);
        return "メール送信しました";
    }
}