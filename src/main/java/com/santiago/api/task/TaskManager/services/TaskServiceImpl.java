package com.santiago.api.task.TaskManager.services;

import com.santiago.api.task.TaskManager.entities.Task;
import com.santiago.api.task.TaskManager.entities.TaskStatus;
import com.santiago.api.task.TaskManager.entities.User;
import com.santiago.api.task.TaskManager.repositories.TaskRepository;
import com.santiago.api.task.TaskManager.repositories.UserRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepostory userRepostory;

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

    @Override
    public Task addTaskUser(Long idUser, Task task) {
        Optional<User> userOpt = userRepostory.getUserById(idUser);
        if(userOpt.isPresent()) {
            User user = userOpt.get();
            task.setUser(user);
            user.addTask(task);
            taskRepository.save(task);
            userRepostory.save(user);
            return task;
        }else{
            throw new RuntimeException("User not found with ID: " + idUser);
        }


    }

    @Override
    public Task updateTaskStatus(TaskStatus task, Long idTask) {
        Optional<Task> taskOpt = taskRepository.findById(idTask);
        if(taskOpt.isPresent()) {
            Task task1 = taskOpt.get();
            task1.setStatus(task);
            taskRepository.save(task1);
            return task1;
        }else{
            throw new RuntimeException("Task not found with ID: " + idTask);
        }


    }

}
