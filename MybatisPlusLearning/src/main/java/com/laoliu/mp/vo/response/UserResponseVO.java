package com.laoliu.mp.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 25516
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseVO {
    private Integer id;
    private String name;
    private String email;

}
