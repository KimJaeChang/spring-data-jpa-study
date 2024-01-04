package kr.co.spring_data_jpa_study.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JpaController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}
