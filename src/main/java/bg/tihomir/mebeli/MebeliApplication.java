package bg.tihomir.mebeli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MebeliApplication {

    public static void main(String[] args) {
        SpringApplication.run(MebeliApplication.class, args);
    }

}
