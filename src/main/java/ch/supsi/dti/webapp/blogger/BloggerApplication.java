package ch.supsi.dti.webapp.blogger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BloggerApplication {

    public static void main(String[] args) {
        //http://localhost:8080/
        SpringApplication.run(BloggerApplication.class, args);
    }
}
