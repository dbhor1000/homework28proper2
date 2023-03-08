package com.example.homework28proper2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping("/testConnection")
    public String initialMessage() {

        return ("Приложение запущено. :))");

    }
}