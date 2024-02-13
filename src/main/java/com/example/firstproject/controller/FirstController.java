package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //컨트롤러 선언
public class FirstController {

    @GetMapping("/hi") // localhost:8080/hi 누르면 아래의 greetings를 반환하라는 뜻, url 요청 접수
    public String niceToMeetYou(Model model) { // 메서드 수행, Model model : 모델 객체 가져오기
        model.addAttribute("username", "안유나"); // 모델 변수 등록
        return "greetings"; // 뷰 템플릿 페이지 변환 // greetings.mustache 확인해보셈!!
    }
}
