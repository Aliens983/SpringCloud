package com.laoliu.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laoliu.mp.entity.User;
import com.laoliu.mp.mapper.UserMapper;
import com.laoliu.mp.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author 25516
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

}
