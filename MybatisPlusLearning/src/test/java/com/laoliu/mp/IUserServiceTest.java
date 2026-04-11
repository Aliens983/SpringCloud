package com.laoliu.mp;

import com.laoliu.mp.entity.User;
import com.laoliu.mp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class IUserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testSave() {

        User user = User.builder()
                .name("laoliu")
                .age(18)
                .phone("272520572573")
                .build();
        // save user
        userService.save(user);
    }

    @Test
    void testQuery() {
        // query user by ids
        List<Integer> integers = List.of(2, 3);
        List<User> users = userService.listByIds(integers);
        users.forEach(user -> log.info("查询到的数据:{}", user));
    }
}
