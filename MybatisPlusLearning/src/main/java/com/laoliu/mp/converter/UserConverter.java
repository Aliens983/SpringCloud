package com.laoliu.mp.converter;

import com.laoliu.mp.entity.User;
import com.laoliu.mp.vo.request.UserRequestVO;
import com.laoliu.mp.vo.response.UserResponseVO;
import org.springframework.stereotype.Component;

/**
 * @author 25516
 */
@Component
public class UserConverter {
    public User convertRequestVOToUser(UserRequestVO userRequestVO) {
        User user = new User();
        user.setName(userRequestVO.getName());
        user.setAge(userRequestVO.getAge());
        user.setEmail(userRequestVO.getEmail());
        user.setAddress(userRequestVO.getAddress());
        return user;
    }
    
    public UserResponseVO convertUserToResponseVO(User user) {
        UserResponseVO userResponseVO = new UserResponseVO();
        userResponseVO.setId(user.getId());
        userResponseVO.setName(user.getName());
        userResponseVO.setEmail(user.getEmail());
        return userResponseVO;
    }

}
