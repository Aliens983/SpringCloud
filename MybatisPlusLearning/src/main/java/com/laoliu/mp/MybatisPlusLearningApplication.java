package com.laoliu.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 25516
 */
@SpringBootApplication
@MapperScan("com.laoliu.mp.mapper")
public class MybatisPlusLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisPlusLearningApplication.class, args);
	}

}
