package com.laoliu.connect.controller;

import com.laoliu.connect.cilent.OpenFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;

/**
 * @author 25516
 */
@Slf4j
@RestController
@RequestMapping("/byIDs")
public class GetMessageController {

    private final OpenFeignClient openFeignClient;

    public GetMessageController(OpenFeignClient openFeignClient) {
        this.openFeignClient = openFeignClient;
    }

    //使用OpenFeign
    @PostMapping
    @SentinelResource(value = "getMessageFromConnected", fallback = "getMessageFallback")
    public String getMessageFromConnected(@RequestParam String id){
        return openFeignClient.getMessage(id);
    }

    /**
     * fallback：业务异常（如远程调用失败、404 等）时触发。
     * 注意：带 Throwable 参数的 fallback 只处理业务异常，不处理限流(BlockException)；
     * 之后若做限流，需另配 blockHandler。
     */
    public String getMessageFallback(String id, Throwable e) {
        log.error("调用 connected 服务失败", e);
        return "Service is temporarily unavailable due to flow control or degradation.";
    }
}