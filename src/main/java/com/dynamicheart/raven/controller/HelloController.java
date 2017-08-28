package com.dynamicheart.raven.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dynamicheart on 8/8/2017.
 */
@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greeting from Spring Boot!";
    }
}
