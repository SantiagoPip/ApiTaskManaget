package com.santiago.api.task.TaskManager.controllers;

import com.santiago.api.task.TaskManager.entities.User;
import com.santiago.api.task.TaskManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")

public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<?>getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.userList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        Optional<User> userOp = userService.getUserById(id);
        if(userOp.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(userOp.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public User createUser(@RequestBody  User user) {
        return userService.saveUser(user);

    }

}
