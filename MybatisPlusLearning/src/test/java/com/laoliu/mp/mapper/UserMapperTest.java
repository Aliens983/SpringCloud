package com.laoliu.mp.mapper;

import com.laoliu.mp.entity.User;
import com.laoliu.mp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserService userService;

    @Test
    void functionTestOfIService () {

        //save user
        boolean save = userService.save(new User(null, "KingOfAll", 18, "235626@qq.com", "上海"));
        log.info("保存了{}行数据", save ? 1 : 0);

        // query user by ids
        List<Integer> integers = List.of(1, 2, 3);
        List<User> users = userService.listByIds(integers);
        users.forEach( user  -> log.info("查询到的数据:{}", user));



    }

}