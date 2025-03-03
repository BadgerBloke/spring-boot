package dev.mksingh.springboot.run;

import jakarta.validation.constraints.NotNull;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class Repository {
  private final JdbcClient dbClient;

  public Repository(JdbcClient jdbcClient) {
    this.dbClient = jdbcClient;
  }

  public List<Run> findAll() {
    return dbClient.sql("SELECT * FROM run").query(Run.class).list();
  }

  public Optional<Run> findById(Integer id) {
    return dbClient.sql("SELECT id,title,started_on,completed_on,miles,location FROM run WHERE id = :id")
        .param("id", id)
        .query(Run.class)
        .optional();
  }

  public void create(@NotNull Run run) {
    var created = dbClient.sql("INSERT INTO Run(id,title,started_on,completed_on,miles,location) VALUES(?,?,?,?,?,?)")
        .params(
            List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString()))
        .update();
    Assert.state(created == 1, "Failed to create run " + run.title());
  }

  public void update(@NotNull Run run, Integer id) {
    var updated = dbClient
        .sql("UPDATE run SET title = ?, started_on = ?, completed_on = ?, miles = ?, location = ? WHERE id = ?")
        .params(List.of(run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString(), id))
        .update();
    Assert.state(updated == 1, "Failed to update run " + run.title());
  }

  public void delete(Integer id) {
    var deleted = dbClient.sql("DELETE FROM run WHERE id = :id")
        .param("id", id)
        .update();
    Assert.state(deleted == 1, "Failed to delete run with id = " + id);
  }

  public int count() {
    return dbClient.sql("SELECT COUNT(*) FROM run")
        .query(Integer.class)
        .single();
  }

  public void saveAll(@NotNull List<Run> runs) {
    runs.forEach(this::create);
  }

  public List<Run> findByLocation(String location) {
    return dbClient.sql("SELECT id,title,started_on,completed_on,miles,location FROM run WHERE location = :location")
        .param("location", location)
        .query(Run.class)
        .list();
  }

}
