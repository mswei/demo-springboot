package cn.weiwei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
// @EnableKafka
@SpringBootApplication
public class KafkaProducerClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerClientApplication.class, args);
    }
}
