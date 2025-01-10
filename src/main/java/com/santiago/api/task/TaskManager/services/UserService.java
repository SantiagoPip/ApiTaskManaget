package com.santiago.api.task.TaskManager.services;

import com.santiago.api.task.TaskManager.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User>userList();
    User saveUser(User user);
    Optional<User>getUserById(Long id);
    void deleteUser(Long id);
    void updateUser(User user);
}
