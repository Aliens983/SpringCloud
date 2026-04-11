package com.laoliu.mp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.laoliu.mp.entity.User;
import com.laoliu.mp.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class QueryWrapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void testQueryWrapper() {
//        1. 构建查询条件
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>()
                .select("id", "name", "age", "email", "address", "phone", "sex", "balance")
                .like("name", "囧")
                .ge("balance", 1000);
//        2. 查询
        List<User> users = userMapper.selectList(userQueryWrapper);

//        3. 输出
//        users.forEach(System.out::println);
//        如果你想要以日志的形式输出你可以这样写
        log.info("查询了{}行数据", users.size());
        users.forEach(user -> log.info("查询到的数据:{}", user));
    }

    @Test
    void testUpdateWrapper() {
        // 1.要更新的数据
        User user = User.builder()
                .age(18)
                .email("123456@qq.com")
                .address("北京")
                .phone("18888888888")
                .sex(1)
                .balance(1000.01)
                .build();
        // 2.更新的条件
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<User>()
                .eq("name", "囧囧菌");
        // 3.执行更新

//        update 更新的话这里是有两个参数的，第一个参数是更新的数据，第二个参数是更新的条件(Wrapper)
        userMapper.update(user, userUpdateWrapper);
    }

    @Test
    void updateListViaWrapper() {
        List<Integer> integers = List.of(2, 3);
        UpdateWrapper<User> updates = new UpdateWrapper<User>()
                .set("age", 21)
                .setSql("balance = balance + 200")
                .in("id", integers);
        userMapper.update(null, updates);
    }


}
