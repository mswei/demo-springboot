package cn.weiwei.service;

import cn.weiwei.feign.EurekaFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeignService {

    private final EurekaFeignClient feignClient;

    @Autowired
    public FeignService(EurekaFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    public String port(String name) {
        return feignClient.getPortFromFeignClient(name);
    }

}
