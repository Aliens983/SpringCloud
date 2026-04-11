package com.laoliu.mp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 25516
 */
@Data
@TableName("user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    // 这里直接写type = IdType.AUTO，会自动生成id,
    // 因为这里的字段的名字已已经和数据库里面的字段的名字一致了,所以直接写type = IdType.AUTO即可
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("`name`")
    private String name;

    private Integer age;

    private String email;

//    @TableField(exist = false)
    private String address;

    private String phone;

    private Integer sex;

    private Double balance;

}
