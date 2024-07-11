package com.github.dreamroute.git.hooks.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author w.dehai.2024/7/11.09:47
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
            int a = 10;
        int b = 20;
        int c = a + b;
        System.err.println(c);
    }
}
