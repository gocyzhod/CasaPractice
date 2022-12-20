package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")

public class ApiTest {

    @GetMapping("/getMapping")
    public String test01() throws  Exception{
        return "Success";
    }

}
