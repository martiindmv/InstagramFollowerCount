package com.example.InstagramFollowerCount.model;

import org.springframework.ui.Model;

public class PersonController {

    public String getPeople(Model model) {
        model.addAttribute("Mar", new Person("Alice"));
        return "people";
    }
}
