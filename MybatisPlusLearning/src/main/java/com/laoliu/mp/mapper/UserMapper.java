package com.laoliu.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.laoliu.mp.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 25516
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    int deleteByPrimaryKey(Integer id);

    int updateByWrapperAndNum(@Param(Constants.WRAPPER) LambdaQueryWrapper<User> userLambdaQueryWrapper, @Param("num") int num);

    void setAgeForUserById(@Param("id") Integer id, @Param("age") Integer age);

    void updateByCustomSQL(@Param("amount") int amount, @Param(Constants.WRAPPER) LambdaQueryWrapper<User> lambdaQueryWrapper);
}
