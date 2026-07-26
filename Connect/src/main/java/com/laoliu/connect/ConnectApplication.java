package com.laoliu.connect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 25516
 */
@EnableFeignClients
@SpringBootApplication
public class ConnectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConnectApplication.class, args);
    }

}
