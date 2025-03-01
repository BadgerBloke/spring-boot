package dev.mksingh.springboot.run;

import jakarta.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class Repository {
    private final List<Run> runs = new ArrayList<>();

    @PostConstruct
    private void init() {
        runs.add(new Run(1, "Monday morning run", LocalDateTime.now(), LocalDateTime.now().plusMinutes(10), 3, Location.INDOOR));
        runs.add(new Run(2, "Wednesday evening run", LocalDateTime.now(), LocalDateTime.now().plusMinutes(60), 6, Location.INDOOR));
    }

    List<Run> findAll() {
        return runs;
    }

    Optional<Run> findById(Integer id) {
        return runs.stream().filter(r -> r.id().equals(id)).findFirst();
    }

    void create(Run run) {
        runs.add(run);
    }

    void update(Run run, Integer id) {
        Optional<Run> existingRun = findById(id);
        existingRun.ifPresent(value -> runs.set(runs.indexOf(value), run));
    }

    void delete(Integer id) {
        runs.removeIf(run -> run.id().equals(id));
    }
}
