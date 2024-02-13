package com.example.firstproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // rest api 용 컨트롤러
public class FirstApiController {
    @GetMapping("/api/hello") // url 요청 접수
    public String hello() {
        return "hello world~";
    }
}
