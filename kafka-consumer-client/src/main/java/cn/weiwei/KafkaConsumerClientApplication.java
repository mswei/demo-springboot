package cn.weiwei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class KafkaConsumerClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerClientApplication.class, args);
    }
}
