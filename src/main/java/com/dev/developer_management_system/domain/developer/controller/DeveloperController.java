package com.dev.developer_management_system.domain.developer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeveloperController {

    @GetMapping("/developers/test")
    public String test() {
        return "Developer API is running";
    }
}