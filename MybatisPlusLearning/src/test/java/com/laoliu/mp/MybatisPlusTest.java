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
class MybatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void wayTest() {
        int i = userMapper.updateById(new User(8, "囧囧菌", 334, "235626@qq.com", null, null,  null, null));
        log.info("更新了{}行数据", i);
        userMapper.selectByIds(List.of(1, 2, 3)).forEach(System.out::println);
    }

    @Test
    void wrapperQueryTestByQueryWrapper() {
//        1.构建查询条件
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>()
                .select("id", "name", "age", "email")
                .like("name", "a")
                .ge("age", 18);
//        2.查询
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
        log.info("查询了{}行数据", users.size());

    }

    @Test
    void wrapperUpdateTestByQueryWrapper() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>()
                .eq("name", "张三")
                .ge("age", 18);

        int i = userMapper.update(new User(null, "阿囧囧囧", 188, "93459828673@qq.com", "北京" , null, null, null), userQueryWrapper);
        log.info("更新了{}行数据", i);
    }


    @Test
    void updateByUpdateWrapper() {
        List<Integer> integers = List.of(1, 2, 3);
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<User>()
                .setSql("age = age -160")
                .in("id", integers);
        int update = userMapper.update(null, userUpdateWrapper);
        log.info("更新了{}行数据", update);
    }

    @Test
    void testLambdaQueryWrapper(){
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .select(User::getId, User::getName, User::getAge, User::getEmail)
                .like(User::getName, "B")
                .ge(User::getAge, 18);

        List<User> users = userMapper.selectList(userLambdaQueryWrapper);
        users.forEach(System.out::println);
        log.info("查询了{}行数据", users.size());
    }

    @Test
    void testUserSqlUpdate(){
        int num = 400;
        List<Integer> integers = List.of(2, 3);
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .in(User::getId, integers);
        int update = userMapper.updateByWrapperAndNum(userLambdaQueryWrapper,num);
        log.info("更新了{}行数据", update);
    }
}
