package br.com.etecia.epictask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.etecia.epictask.model.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>  {

    List<Task> findByStatusLessThan(int status);
}
