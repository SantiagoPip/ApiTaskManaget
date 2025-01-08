package com.santiago.api.task.TaskManager.controllers;

import com.santiago.api.task.TaskManager.services.TaskService;
import com.santiago.api.task.TaskManager.services.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;



}
