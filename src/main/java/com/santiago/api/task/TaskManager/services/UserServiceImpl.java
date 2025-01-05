package com.santiago.api.task.TaskManager.services;

import com.santiago.api.task.TaskManager.entities.User;
import com.santiago.api.task.TaskManager.repositories.TaskRepository;
import com.santiago.api.task.TaskManager.repositories.UserRepostory;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepostory userRepostory;

    @Override
    public List<User> userList() {
        return (List<User>) userRepostory.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepostory.save(user);
    }

    @Override
    public boolean existUser(String username) {
        return false;
    }
}
