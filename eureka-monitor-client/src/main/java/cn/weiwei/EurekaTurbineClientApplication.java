package cn.weiwei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableTurbine
@SpringBootApplication
public class EurekaTurbineClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaTurbineClientApplication.class, args);
    }
}
