package com.laoliu.mp;

import com.laoliu.mp.service.UserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IServiceLambdaTest {

    @Resource
    private UserService userService;


}
