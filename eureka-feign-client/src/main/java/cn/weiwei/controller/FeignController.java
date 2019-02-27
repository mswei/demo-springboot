package cn.weiwei.controller;

import cn.weiwei.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    private final FeignService service;

    @Autowired
    public FeignController(FeignService service) {
        this.service = service;
    }

    @GetMapping("/feign")
    public String port(@RequestParam(required = false, defaultValue = "weiwei") String name) {
        return service.port(name);
    }

}
