package dev.mksingh.springboot.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/runs")
public class Controller {

    private final Repository runRepository;

    public Controller(Repository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("")
    List<Run> findAll() {
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id) {
        var run = runRepository.findById(id);
        if (run.isEmpty()) {
            throw new NotFoundException();
        }
        return run.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Run run) {
        runRepository.create(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@RequestBody Run run, @PathVariable Integer id) {
        runRepository.update(run, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        runRepository.delete(id);
    }

    @GetMapping("/count")
    Integer count() {
        return runRepository.count();
    }

    @GetMapping("/by-location/{location}")
    List<Run> runsByLocation(@PathVariable String location) {
        return runRepository.findByLocation(location);
    }
}
