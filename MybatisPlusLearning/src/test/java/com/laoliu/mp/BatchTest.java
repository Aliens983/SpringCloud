package com.laoliu.mp;

import com.laoliu.mp.entity.User;
import com.laoliu.mp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
class BatchTest {

    @Autowired
    private UserService userService;
    User buildUsers(int i) {

        User user = new User();
        user.setName("批量插入测试" + i);
        user.setAge(i);
        user.setEmail("batchInsertTest" + i + "@qq.com");
        return user;
    }

    @Test
    void batchInsertTest1() {
        long b = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            userService.save(buildUsers(i));
        }
        long e = System.currentTimeMillis();
        log.info("单条插入10万条数据耗时: {}ms", e - b);
    }

    @Test
    void batchInsertTest2() {
        // 每次插入1000条数据，分批插入10万条数据
        List<User> list = new ArrayList<>(1000);
        long b = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list.add(buildUsers(i));
            if (list.size() == 1000) {
                userService.saveBatch(list);
                list.clear();
            }
        }
        long e = System.currentTimeMillis();
        log.info("批量插入10万条数据耗时: {}ms", e - b);
    }

}
