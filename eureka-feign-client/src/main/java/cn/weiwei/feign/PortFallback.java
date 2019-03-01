package cn.weiwei.feign;

import org.springframework.stereotype.Component;

@Component
public class PortFallback implements EurekaFeignClient {

    @Override
    public String getPortFromFeignClient(String name) {
        return "Service Call Error: " + name;
    }
}
