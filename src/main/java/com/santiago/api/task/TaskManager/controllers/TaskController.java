package com.santiago.api.task.TaskManager.controllers;

import com.santiago.api.task.TaskManager.entities.Task;
import com.santiago.api.task.TaskManager.services.TaskService;
import com.santiago.api.task.TaskManager.services.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;


    @GetMapping
    public List<Task>getAllTasks(){
        return taskService.getAllTasks();
    }
}
