package com.games.calendar.rest;

import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@CrossOrigin
@RequiredArgsConstructor
public class TestController {

    String respGlobal;

    private final JavaMailSender javaMailSender;

    @GetMapping("/{word}")
    public ResponseEntity<String> sayHello(@PathVariable String word) throws InterruptedException {

//        String resp = word;
//        respGlobal = word;
//        Thread.sleep(5000);


        return  ResponseEntity.ok(respGlobal);
    }


}
