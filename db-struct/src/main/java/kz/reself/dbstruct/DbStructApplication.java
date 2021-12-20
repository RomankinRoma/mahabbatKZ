package kz.reself.dbstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "kz.reself")
public class DbStructApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbStructApplication.class, args);
    }

}
