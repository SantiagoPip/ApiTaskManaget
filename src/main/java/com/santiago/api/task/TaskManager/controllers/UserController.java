package com.santiago.api.task.TaskManager.controllers;

import com.santiago.api.task.TaskManager.entities.User;
import com.santiago.api.task.TaskManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")

public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public List<User>getAllUsers() {
        return userService.userList();
    }

    @PostMapping
    public User createUser(@RequestBody  User user) {
        return userService.saveUser(user);

    }

}
