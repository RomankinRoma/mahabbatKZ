package kz.reself.dbstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = "kz.reself")
@EnableEurekaClient
public class DbStructApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbStructApplication.class, args);
    }

}
