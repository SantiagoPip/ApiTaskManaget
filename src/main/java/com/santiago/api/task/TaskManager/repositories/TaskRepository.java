package com.santiago.api.task.TaskManager.repositories;

import com.santiago.api.task.TaskManager.entities.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
