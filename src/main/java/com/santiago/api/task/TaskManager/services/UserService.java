package com.santiago.api.task.TaskManager.services;

import com.santiago.api.task.TaskManager.entities.User;

import java.util.List;

public interface UserService {

    List<User>userList();
    User saveUser(User user);
    boolean existUser(String username);
    void deleteUser(Long id);
    void updateUser(User user);
}
