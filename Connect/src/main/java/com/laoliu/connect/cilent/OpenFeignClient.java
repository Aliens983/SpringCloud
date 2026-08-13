package com.laoliu.connect.cilent;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 25516
 */
@FeignClient(name = "connected")
public interface OpenFeignClient {

    @GetMapping("/message/getMessage")
    String getMessage(@RequestParam("secret") String secret);


}
