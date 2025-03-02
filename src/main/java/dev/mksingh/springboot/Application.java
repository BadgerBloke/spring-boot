package dev.mksingh.springboot;

import dev.mksingh.springboot.run.Location;
import dev.mksingh.springboot.run.Repository;
import dev.mksingh.springboot.run.Run;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;

@SpringBootApplication
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    @Autowired
    private Environment env;

    public static void main(String[] args) {
        var context = SpringApplication.run(Application.class, args);
        var port = ((WebServerApplicationContext) context).getWebServer().getPort();
        logger.info("\uD83D\uDE80 Application has started and listening at http://localhost:{}", port);
    }

//    @Bean
//    CommandLineRunner runner(Repository runRepository) {
//        return args -> {
//            var run = new Run(1, "First run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 5, Location.OUTDOOR);
//            runRepository.create(run);
//            logger.info("Run: {}", run);
//        };
//    }

}
