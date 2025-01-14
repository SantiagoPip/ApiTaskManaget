package com.santiago.api.task.TaskManager.services;

import com.santiago.api.task.TaskManager.entities.Task;
import com.santiago.api.task.TaskManager.entities.User;
import com.santiago.api.task.TaskManager.repositories.UserRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepostory userRepostory;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> userList() {
        return (List<User>) userRepostory.findAll();
    }

    @Override
    public User saveUser(User user) {
        String passwordEncoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);
        return userRepostory.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepostory.getUserById(id);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userRepostory.findById(id);
        if (user.isPresent()) {
            userRepostory.delete(user.get());
        } else {
            throw new RuntimeException("User not found with ID: " + id);
        }
    }
    @Override
    public void updateUser(User user) {

    }

    @Override
    public List<Task> getTasksByUser(Long id) {
        Optional<User>userOptional = userRepostory.getUserById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getTasks();
        }
         throw new RuntimeException("User not found with ID: " + id);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepostory.findUsername(username);
    }
}
