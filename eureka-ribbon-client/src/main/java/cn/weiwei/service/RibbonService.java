package cn.weiwei.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RibbonService {

    private final RestTemplate restTemplate;

    @Autowired
    public RibbonService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "portErr")
    public String port(String name) {
        return restTemplate.getForObject("http://eureka-client/port?name=" + name, String.class);
    }

    private String portErr(String name) {
        return "Service Call Error: " + name;
    }

}
