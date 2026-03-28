package com.laoliu.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laoliu.mp.entity.User;

import java.util.List;

/**
 * @author 25516
 */
public interface UserService extends IService<User> {
    void setAgeForSpecificUser(Integer id, Integer age);

    List<User> queryUsersByIdsAndSpecificCondition(String name, Integer age, String email);

    void updateUserById(User user);

    User selectUserWithAddressById(Integer id);
}
