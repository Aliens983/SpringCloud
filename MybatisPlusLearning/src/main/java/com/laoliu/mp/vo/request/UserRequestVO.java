package com.laoliu.mp.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 25516
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestVO {
    private String name;
    private Integer age;
    private String email;
    private String address;
}
