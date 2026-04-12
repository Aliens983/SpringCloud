package com.laoliu.mp.controller;

import com.laoliu.mp.entity.User;
import com.laoliu.mp.service.UserService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 25516
 */
@Tag(name = "NewUserController", description = "New User Controller")
@RestController
@RequestMapping("/new-user")

// via use this annotation to generate constructor for final fields,
// and Spring will use it for dependency injection automatically,
// then don't need to create constructor manually
@RequiredArgsConstructor
public class NewUserController {

    private final UserService userService;

    @Schema(description = "Save User")
    @PostMapping
    public User saveUser(){
        User user = User.builder()
                .name("laoliu")
                .age(18)
                .email("235626@qq.com")
                .address("上海")
                .phone("1234567890")
                .sex(1)
                .balance(100.0)
                .build();
        userService.save(user);
        return user;
    }

    @PostMapping("delete/{id}")
    public boolean deleteUser(@Schema(description = "User ID") @PathVariable Integer id){
        // 实际上这里你需要创建一个VO来接受前端传递过来的json对象,然后使用convert把VO转换成dto对象
        return userService.removeById(id);
    }

    @PostMapping("{id}/decrease/{amount}")
    public Boolean deductUserBalance(@Schema(description = "User ID") @PathVariable Integer id,
                                  @Schema(description = "Deduction Amount") @PathVariable Double amount){
        // 这里你需要创建一个VO来接受前端传递过来的json对象,然后使用convert把VO转换成dto对象
        return userService.deductUserBalance(id, amount);
    }


    @PostMapping("{id}/update")
    public User updateUser(@Schema(description = "User ID") @PathVariable Integer id,
                           @Schema(description = "User") User user){
        // 这里你需要创建一个VO来接受前端传递过来的json对象,然后使用convert把VO转换成dto对象
        userService.updateUserById(user);
        return user;
    }


}
