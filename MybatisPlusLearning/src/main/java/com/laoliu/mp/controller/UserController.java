package com.laoliu.mp.controller;

import com.laoliu.mp.converter.UserConverter;
import com.laoliu.mp.entity.User;
import com.laoliu.mp.mapper.UserMapper;
import com.laoliu.mp.service.UserService;
import com.laoliu.mp.vo.request.UserRequestVO;
import com.laoliu.mp.vo.response.UserResponseVO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 25516
 */
@RestController
@RequestMapping("/user")
// can use this annotation to generate constructor for final fields, and Spring will use it for dependency injection automatically
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserConverter userConverter;
    private final UserService userService;

//    public UserController(UserMapper userMapper, UserConverter userConverter) {
//        this.userMapper = userMapper;
//        this.userConverter = userConverter;
//    }


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

    @Operation(summary = "添加用户")
    @PostMapping
    public ResponseEntity<Map<String, Object>> addUser(@RequestBody UserRequestVO userRequestVO) {
        // can directly use BeanUtils to copy properties from userRequestVO to user, but it's better to use a converter for better maintainability and separation of concerns
//        User user = userConverter.convertRequestVOToUser(userRequestVO);
        User user = new User();
        BeanUtils.copyProperties(userRequestVO, user);
//        userMapper.insert(user);
        userService.save(user);
//        UserResponseVO convert = userConverter.convertUserToResponseVO(user);
        UserResponseVO convert = new UserResponseVO();
        BeanUtils.copyProperties(user, convert);
        Map<String, Object> result = new HashMap<>();
        result.put("data", convert);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Get user by ids")
    @GetMapping
    public ResponseEntity<Map<String, Object>> getUserByIds(@RequestParam("ids") List<Integer> ids) {
        List<User> users = userService.listByIds(ids);
        List<UserResponseVO> userResponseVOList = users.stream()
                .map(user -> {
                    UserResponseVO vo = new UserResponseVO();
                    BeanUtils.copyProperties(user, vo);
                    return vo;
                })
                .toList();
        Map<String, Object> result = new HashMap<>();
        result.put("data", userResponseVOList);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Deduce user's age by id")
    @PostMapping("/{id}/set-age/{age}")
    public ResponseEntity<Map<String, Object>> deduceUserAge(@PathVariable Integer id, @PathVariable Integer age) {
        // First update the database
        userService.setAgeForSpecificUser(id, age);
        // Then query the updated user to get the latest data
        User user = userService.getById(id);
        Map<String, Object> result = new HashMap<>();
        result.put("data", user);
        return ResponseEntity.ok(result);
    }


    @Operation(summary = "Query users by condition")
    @GetMapping("/query-by-ids")
    public ResponseEntity<Map<String, Object>> queryUsersByIdsAndSpecificCondition(UserRequestVO userRequestVO) {
        List<User> users = userService.queryUsersByIdsAndSpecificCondition(
                userRequestVO.getName(),
                userRequestVO.getAge(),
                userRequestVO.getEmail());
        Map<String, Object> result = new HashMap<>();
        result.put("data", users);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Update user by id")
    @PutMapping
    public ResponseEntity<Map<String, Object>> updateUserById(@RequestParam Integer id, @RequestBody UserRequestVO userRequestVO) {
        User user = new User();
        BeanUtils.copyProperties(userRequestVO, user);
        user.setId(id);
        userService.updateUserById(user);
        Map<String, Object> result = new HashMap<>();
        result.put("data", user);
        return ResponseEntity.ok(result);
    }


}
