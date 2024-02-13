package com.example.firstproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ByeController {

    @GetMapping("/Bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname", "홍길동");
        return "goodbye";
    }
}
