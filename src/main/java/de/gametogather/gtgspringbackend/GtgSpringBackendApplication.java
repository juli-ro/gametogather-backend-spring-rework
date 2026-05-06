package de.gametogather.gtgspringbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GtgSpringBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(GtgSpringBackendApplication.class, args);
    }

}
