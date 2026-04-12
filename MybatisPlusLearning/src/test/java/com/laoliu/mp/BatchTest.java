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

        return User.builder()
                .name("囧囧菌")
                .age(i)
                .email(i+"@qq.com")
                .address("上海")
                .phone("1234567890")
                .sex(1)
                .balance((double) i)
                .build();
    }

    @Test
    void batchInsertTest1() {
        // 每一条数据都会分别去提交,提交10000次网络请求,
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
                // 采用jdbc底层的预编译方案,会在,但是是1000条SQL语句一起提交,
                // 最好采用仅仅使用一个SQL语句进行批量插入,这样性能会更高
                userService.saveBatch(list);
                list.clear();
            }
        }
        long e = System.currentTimeMillis();
        log.info("批量插入10万条数据耗时: {}ms", e - b);
    }




}
