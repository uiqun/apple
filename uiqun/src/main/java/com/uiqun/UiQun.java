package com.uiqun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com/uiqun/dao")
@SpringBootApplication
public class UiQun {

    public static void main(String[] args) {
        SpringApplication.run(UiQun.class, args);
    }

}
