package kz.reself.banservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BanServiceApplication.class, args);
    }

}
