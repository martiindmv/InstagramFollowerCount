package com.example.InstagramFollowerCount.model;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonController {

    @GetMapping
    public String getPeople(Model model) {
        model.addAttribute("Mar", new Person("Alice"));
        return "people";
    }
}
