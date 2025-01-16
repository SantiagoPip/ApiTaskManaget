package com.santiago.api.task.TaskManager.services;

import com.santiago.api.task.TaskManager.entities.Role;
import com.santiago.api.task.TaskManager.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Optional<Role> findRoleByName(String roleName) {
        return roleRepository.findByName(roleName);
    }
}
