package com.github.swapbook.api.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyExample {

    private static final String template = "Hello, %s!";

    @GetMapping("/hello-world")
    @ResponseBody
    public String sayHello(@RequestParam(defaultValue="Stranger") String name) {
        return String.format(template, name);
    }
}
