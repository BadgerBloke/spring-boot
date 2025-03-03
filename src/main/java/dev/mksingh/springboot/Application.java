package dev.mksingh.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerApplicationContext;

@SpringBootApplication
public class Application {

  private static final Logger logger = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    var context = SpringApplication.run(Application.class, args);
    var port = ((WebServerApplicationContext) context).getWebServer().getPort();
    logger.info("\uD83D\uDE80 Application has started and listening at http://localhost:{}", port);
  }

  // @Bean
  // CommandLineRunner runner(Repository runRepository) {
  // return args -> {
  // var run = new Run(1, "First run", LocalDateTime.now(),
  // LocalDateTime.now().plusHours(1), 5, Location.OUTDOOR);
  // runRepository.create(run);
  // logger.info("Run: {}", run);
  // };
  // }

}
