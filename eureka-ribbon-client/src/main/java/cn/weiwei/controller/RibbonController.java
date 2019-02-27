package cn.weiwei.controller;

import cn.weiwei.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RibbonController {

    private final RibbonService service;

    @Autowired
    public RibbonController(RibbonService service) {
        this.service = service;
    }

    @GetMapping("/port")
    public String port(@RequestParam(required = false, defaultValue = "weiwei") String name) {
        return service.port(name);
    }

}
