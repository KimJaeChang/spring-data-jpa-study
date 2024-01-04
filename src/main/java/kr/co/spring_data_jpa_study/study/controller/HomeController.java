package kr.co.spring_data_jpa_study.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String helle(Model model) {
        model.addAttribute("data", "hello!!!");
        return "home";
    }

}
