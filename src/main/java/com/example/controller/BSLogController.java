package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by bsheen on 6/1/17.
 */

@Controller
public class BSLogController {

    @GetMapping("/dash")
    public String dash(){
        return "dash";
    }
}
