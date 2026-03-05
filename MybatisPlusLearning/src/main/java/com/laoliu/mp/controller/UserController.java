package com.laoliu.mp.controller;

import com.laoliu.mp.converter.UserConverter;
import com.laoliu.mp.entity.User;
import com.laoliu.mp.mapper.UserMapper;
import com.laoliu.mp.vo.request.UserRequestVO;
import com.laoliu.mp.vo.response.UserResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 25516
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserMapper userMapper;
    private final UserConverter userConverter;

    public UserController(UserMapper userMapper, UserConverter userConverter) {
        this.userMapper = userMapper;
        this.userConverter = userConverter;
    }


    @GetMapping("/{id}")
    public int getUser(@PathVariable Integer id) {
//        return userMapper.insert(new User(id, "张三", 18, "235626@qq.com", "上海"));
        User user = new User();
        user.setName("laoliu");
        user.setAge(18);
        user.setEmail("235626@qq.com");
        user.setAddress("上海");
        return userMapper.insert(user);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> addUser(@RequestBody UserRequestVO userRequestVO) {
        User user = userConverter.convertRequestVOToUser(userRequestVO);
        userMapper.insert(user);
        UserResponseVO convert = userConverter.convertUserToResponseVO(user);
        Map<String, Object> result = new HashMap<>();
        result.put("data", convert);
        return ResponseEntity.ok(result);
    }

}
