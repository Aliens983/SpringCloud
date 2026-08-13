package com.laoliu.connect.connected.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;

/**
 * @author 25516
 */
@Slf4j
@RestController
@RequestMapping("/message")
public class MessageController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/getMessage")
    @SentinelResource(value = "getMessage", fallback = "getMessageFallback")
    public String getMessage(@RequestParam String secret){
        if("111".equals(secret)){
            log.info("port:{}", port);
            return "Fuck all.";
        } else if ("222".equals(secret)) {
            return "What the hell are you doing?";
        }else {
            return "Shit";
        }
    }

    public String getMessageFallback(String secret, Throwable e) {
        log.error("getMessage 业务异常", e);
        return "Service is temporarily unavailable due to flow control or degradation.";
    }
}