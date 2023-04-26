package com.suhacan.springsecuritydemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @GetMapping("/index")
    public String index() {
        return "Index Page, Welcome";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Login success. Dashboard page.";
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "Just for Admins, Admin page.";
    }
}
