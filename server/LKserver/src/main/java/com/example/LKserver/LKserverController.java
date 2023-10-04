package com.example.LKserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //initialises a new instance of restcontroller if one is not already present, this tells the application that the class defined below will handle our http requests.
public class LKserverController {
    
    @GetMapping("/")   //this annotation is to enable a http get request at localhost:8000
    public String Hello()
    { 
        return "hello world";
    }

    
}
