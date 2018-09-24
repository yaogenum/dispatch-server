package com.snapshot.person.dispatch;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
@EnableAutoConfiguration
@ImportResource("classpath*:/spring/*.xml")
public class DispatchServerApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(DispatchServerApplication.class, args);
    }

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
}