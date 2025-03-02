package dev.mksingh.springboot.run;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class JsonLoader implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(JsonLoader.class);
    private final Repository repository;
    private final ObjectMapper objectMapper;

    public JsonLoader(Repository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if(repository.count() == 0) {
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")) {
                Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
                logger.info("📦 Seeding {} runs into in-memory database.", allRuns.runs().size());
                repository.saveAll(allRuns.runs());
            } catch (IOException e) {
                throw new RuntimeException("❌ Failed to read JSON data", e);
            }
        } else {
            logger.info("🛫 Not loading Runs from JSON data because the collection contains data.");
        }
    }
}
