package com.laoliu.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laoliu.mp.entity.User;
import com.laoliu.mp.mapper.UserMapper;
import com.laoliu.mp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 25516
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    private final UserMapper userMapper;

    @Override
    public List<User> queryUsersByIdsAndSpecificCondition(String name, Integer age , String email) {

        return lambdaQuery()
                .like(name != null, User::getName, name)
                .eq(age != null, User::getAge, age)
                .eq(email != null, User::getEmail, email)
                .list();
    }

    @Override
    @Transactional
    public void updateUserById(User user) {
        // 这里建议加锁,可能存在并发更新同一个用户的情况,可以使用悲观锁或者乐观锁
        lambdaUpdate()
                .eq(User::getId, user.getId())
                .set(user.getName() != null, User::getName, user.getName())
                .set(user.getAge() != null, User::getAge, user.getAge())
                .set(user.getEmail() != null, User::getEmail, user.getEmail())
                .set(user.getAddress() != null, User::getAddress, user.getAddress())
                .update();
    }

    @Override
    @Transactional
    public void setAgeForSpecificUser(Integer id, Integer age) {
        userMapper.setAgeForUserById(id, age);
    }
}
