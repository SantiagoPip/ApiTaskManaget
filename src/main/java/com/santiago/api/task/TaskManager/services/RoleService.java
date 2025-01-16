package com.santiago.api.task.TaskManager.services;

import com.santiago.api.task.TaskManager.entities.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role>findRoleByName(String roleName);

}
