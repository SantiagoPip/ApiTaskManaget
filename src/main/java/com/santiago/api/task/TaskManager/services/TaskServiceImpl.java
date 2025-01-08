package com.santiago.api.task.TaskManager.services;

import com.santiago.api.task.TaskManager.entities.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TaskServiceImpl implements TaskService {
    @Override
    public List<Task> getTasksByUser(Long idUser) {
        return List.of();
    }

    @Override
    public List<Task> getAllTasks() {
        return List.of();
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return Optional.empty();
    }

    @Override
    public Task saveTask(Task task) {
        return null;
    }

    @Override
    public void deleteTask(Task task) {

    }

}
