package com.laoliu.connect.connected.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 25516
 */
@RestController
@RequestMapping
public class MessageController {
    @GetMapping("/getMessage")
    public String getMessage(@RequestParam String secret){
        if("111".equals(secret)){
            return "Fuck all.";
        } else if ("222".equals(secret)) {
            return "What the hell are you doing?";
        }else {
            return "Shit";
        }
    }
}
