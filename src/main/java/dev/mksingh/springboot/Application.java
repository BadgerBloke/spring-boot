package dev.mksingh.springboot;

import dev.mksingh.springboot.run.Location;
import dev.mksingh.springboot.run.Run;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class Application {

  private static final Logger logger = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
    logger.info("🚀 Application has started successfully");
  }

  @Bean
  CommandLineRunner runner() {
    return args -> {
        var run = new Run(1, "First run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 5, Location.OUTDOOR);
        logger.info("Run: {}", run);
    };
  }

}
