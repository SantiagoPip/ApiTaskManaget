package com.santiago.api.task.TaskManager.services;

import com.santiago.api.task.TaskManager.entities.Role;
import com.santiago.api.task.TaskManager.entities.Task;
import com.santiago.api.task.TaskManager.entities.User;
import com.santiago.api.task.TaskManager.repositories.RoleRepository;
import com.santiago.api.task.TaskManager.repositories.UserRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepostory userRepostory;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<User> userList() {
        return (List<User>) userRepostory.findAll();
    }

    @Override
    public User saveUser(User user) {
        Optional<Role>optionalRoleUser = roleRepository.findByName("ROLE_USER");
        List<Role>roles = new ArrayList<>();
        optionalRoleUser.ifPresent(roles::add);
        System.out.println(user.isAdmin()+"is admiiiiiiin");

        if(user.isAdmin()){
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }
        user.setRoles(roles);
        System.out.println(roles + "creado");
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
