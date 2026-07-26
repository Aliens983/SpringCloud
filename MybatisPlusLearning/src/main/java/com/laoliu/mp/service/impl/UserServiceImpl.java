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
// 继承ServiceImpl的两个参数分别是数据库操作Mapper和实体类
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    private final UserMapper userMapper;
    private final UserService userService;

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
    public User selectUserWithAddressById(Integer id) {
        User user = getById(id);
//        // query order by id
//        List<Order> list = Db.lambdaQuery(Order.class)
//                .eq(Order::getUserId, id)
//                .list();
//        // package user
//        BeanUtils.copyProperties(user, UserResponseVO.class);
        return user;
    }

    @Override
    @Transactional
    public void setAgeForSpecificUser(Integer id, Integer age) {
        userMapper.setAgeForUserById(id, age);
    }

    @Override
    public Boolean deductUserBalance(Integer id, Double amount) {
        // 确实在扣减账户余额或者增加账户余额的时候需要校验用户是否存在,用户状态是否正常
        // 扣减的余额有没有成为负数等等,这些都需要在执行mapper前进行校验
        // 这里可以调用自己的方法去查询校验,这里可以直接使用this.XXX 方法去查询校验
        User user = this.getById(id);
        return userMapper.updateAmountById(id, amount);
    }
}
