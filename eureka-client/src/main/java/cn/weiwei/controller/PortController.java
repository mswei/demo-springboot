package cn.weiwei.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/port")
    public String port(@RequestParam String name) {
        return "Hi " + name + ", now the port is: " + port;
    }

}
