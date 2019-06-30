package com.uiqun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@MapperScan("com/uiqun/dao")
@ServletComponentScan(basePackageClasses={com.uiqun.utils.SessionCounter .class})
@SpringBootApplication
public class UiQun {

    public static void main(String[] args) {
        SpringApplication.run(UiQun.class, args);
    }

}
