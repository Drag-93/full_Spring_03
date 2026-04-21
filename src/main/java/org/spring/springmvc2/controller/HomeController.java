package org.spring.springmvc2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // /index, / ,
    @GetMapping({"","/","/index"})
    public String index(Model model) {
        String data = "Spring Boot Project";
        model.addAttribute("data", data);

        return "index";
    }
}