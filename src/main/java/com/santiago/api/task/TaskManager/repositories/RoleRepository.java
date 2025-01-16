package com.santiago.api.task.TaskManager.repositories;

import com.santiago.api.task.TaskManager.entities.Role;
import com.santiago.api.task.TaskManager.entities.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
