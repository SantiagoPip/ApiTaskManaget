package com.santiago.api.task.TaskManager.repositories;

import com.santiago.api.task.TaskManager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepostory extends JpaRepository<User, Integer> {
}
