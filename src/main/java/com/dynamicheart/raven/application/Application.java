package com.dynamicheart.raven.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by dynamicheart on 6/8/2017.
 */

@SpringBootApplication
public class Application extends SpringBootServletInitializer{

    public static void main(String [] args){
        SpringApplication.run(Application.class, args);
    }
}
