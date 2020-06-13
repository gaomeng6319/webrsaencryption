package com.wying.webrsaencryption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//配置扫码监听器等注解
@ServletComponentScan(basePackages = "com.wying.webrsaencryption.*")
public class WebrsaencryptionApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebrsaencryptionApplication.class, args);
    }

}
