package com.santiago.api.task.TaskManager.services;

import com.santiago.api.task.TaskManager.entities.Task;
import com.santiago.api.task.TaskManager.entities.TaskStatus;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task>getTasksByUser(Long idUser);
    List<Task>getAllTasks();
    Optional<Task> getTaskById(Long id);
    Task saveTask(Task task);
    void deleteTask(Task task);
    Task addTaskUser(Long idUser, Task task);
    Task updateTaskStatus(TaskStatus task,Long taskId);
    List<Task> getTasksByUserName(String username);
}
