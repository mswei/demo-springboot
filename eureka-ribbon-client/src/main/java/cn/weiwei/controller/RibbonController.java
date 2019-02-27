package cn.weiwei.controller;

import cn.weiwei.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RibbonController {

    private final RibbonService service;
    private final LoadBalancerClient loadBalancer; // 负载均衡器核心类

    @Autowired
    public RibbonController(RibbonService service, LoadBalancerClient loadBalancer) {
        this.service = service;
        this.loadBalancer = loadBalancer;
    }

    @GetMapping("/port")
    public String port(@RequestParam(required = false, defaultValue = "weiwei") String name) {
        return service.port(name);
    }

    @GetMapping("/test-ribbon")
    public String testRibbon() {
        ServiceInstance instance = loadBalancer.choose("eureka-client");
        return instance.getHost() + ":" + instance.getPort();
    }

}
