package com.santiago.api.task.TaskManager.repositories;

import com.santiago.api.task.TaskManager.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepostory extends CrudRepository<User, Long> {
    @Query("select u from User u where u.id = ?1")
    Optional<User> getUserById(Long id);
    @Query("select u from User u where u.name = ?1")
    Optional<User> findUsername(String username);
}
