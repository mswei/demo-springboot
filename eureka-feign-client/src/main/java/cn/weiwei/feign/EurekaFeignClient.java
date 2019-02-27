package cn.weiwei.feign;

import config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "eureka-client", configuration = FeignConfig.class)
public interface EurekaFeignClient {

    @GetMapping(value = "/port")
    String getPortFromFeignClient(@RequestParam(value = "name") String name);

}
