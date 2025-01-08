package com.santiago.api.task.TaskManager.repositories;

import com.santiago.api.task.TaskManager.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepostory extends CrudRepository<User, Integer> {
}
