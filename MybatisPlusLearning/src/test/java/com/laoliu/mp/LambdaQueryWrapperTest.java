package com.laoliu.mp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.laoliu.mp.entity.User;
import com.laoliu.mp.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class LambdaQueryWrapperTest {
    @Resource
    private UserMapper userMapper;

    @Test
    void testLambdaQueryWrapper() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .select(User::getName, User::getAge, User::getEmail)
                .like(User::getName, "大")
                .eq(User::getAge, 18);
        // this is a query wrapper but not update, so there is no setSql method.
    }

    @Test
    void testCustomSQL(){

        List<Integer> integers = List.of(2, 3);

        int amount = 200;

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>().in(User::getId, integers);

        userMapper.updateByCustomSQL(amount, lambdaQueryWrapper);
    }
}
