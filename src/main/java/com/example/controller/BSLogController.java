package com.example.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by bsheen on 6/1/17.
 */

@Controller
public class BSLogController {

    @GetMapping("/dash")
    public String dash(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println("\n\n\n"+currentPrincipalName);
        return "dash";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
