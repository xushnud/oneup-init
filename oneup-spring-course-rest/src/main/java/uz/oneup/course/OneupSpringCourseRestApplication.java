package uz.oneup.course;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class OneupSpringCourseRestApplication {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private String applicationPort;

    public static void main(String[] args) {
        SpringApplication.run(OneupSpringCourseRestApplication.class, args);
    }

    @GetMapping
    public ResponseEntity<String> init() {

        return new ResponseEntity<>(
                applicationName+ " is Running on Port "+ applicationPort,
                HttpStatus.OK
        );
    }
}
