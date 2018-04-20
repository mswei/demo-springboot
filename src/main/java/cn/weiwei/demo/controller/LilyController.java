package cn.weiwei.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LilyController {

    @Value("${me.name}")
    private String name;
    @Value("${me.age}")
    private String age;

    @RequestMapping(value = "/lily")
    public String lily() {
        return "My name is " + name + ", age is " + age + ".";
    }
}
