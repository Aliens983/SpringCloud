package com.laoliu.connect.controller;

import cn.hutool.core.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @author 25516
 */
@RestController
@RequestMapping
//@RequiredArgsConstructor 给加final的属性生成构造函数
public class GetMessageController {

    private final RestTemplate restTemplate;

    private final DiscoveryClient discoveryClient;

    public GetMessageController(RestTemplate restTemplate, DiscoveryClient discoveryClient) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }

    @PostMapping
    public String getMessageFromConnected(@RequestParam String id){
        //1.获取实例列表
        List<ServiceInstance> instances = discoveryClient.getInstances("connected");
        if (instances.isEmpty()){
            return "Error";
        }
        //2.从实例列表中获取一个实例
        ServiceInstance instance = instances.get(RandomUtil.randomInt(instances.size()));
        URI uri = instance.getUri();


        ResponseEntity<String> exchange = restTemplate.exchange(
                uri+"/getMessage?secret={id}",
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
