package com.laoliu.connect.controller;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author 25516
 */
@RestController
@RequestMapping
//@RequiredArgsConstructor 给加final的属性生成构造函数
public class GetMessageController {

    private final RestTemplate restTemplate;

    public GetMessageController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public String getMessageFromConnected(@RequestParam String id){
        ResponseEntity<String> exchange = restTemplate.exchange(
                "http://localhost:8081/getMessage?secret={id}",
                HttpMethod.GET,
                null,
                String.class,
                //如果返回值类型是对象的集合
                //new ParameterizedTypeReference<List<User>>() {},
                id
        );
        if (!exchange.getStatusCode().is2xxSuccessful()){
            return "Error";
        }
        return exchange.getBody();
    }
}
