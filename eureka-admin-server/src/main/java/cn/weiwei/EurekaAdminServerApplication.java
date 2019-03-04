package cn.weiwei;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@EnableAdminServer
@SpringBootApplication
public class EurekaAdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaAdminServerApplication.class, args);
    }

}
