package com.santiago.api.task.TaskManager.controllers;

import com.santiago.api.task.TaskManager.entities.Task;
import com.santiago.api.task.TaskManager.entities.TaskStatus;
import com.santiago.api.task.TaskManager.services.TaskService;
import com.santiago.api.task.TaskManager.services.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;




    @GetMapping("all")
    public List<Task>getAllTasks(){
        return taskService.getAllTasks();
    }
    @GetMapping()
    public List<Task> getTasks(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return taskService.getTasksByUserName(username);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<?>addTaskUser(@PathVariable Long userId, @RequestBody Task task){
        Task taskUser = taskService.addTaskUser(userId,task);
        return ResponseEntity.status(HttpStatus.OK).body(taskUser);
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<?>updateTaskStatus(@PathVariable Long id, @RequestBody Map<String,String>status){

        TaskStatus taskStatus = TaskStatus.valueOf(status.get("status"));
        try{
            // Validar que el campo 'status' esté presente en la solicitud
            if (!status.containsKey("status") || status.get("status") == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing or null 'status' field.");
            }
            Task taskUpdate = taskService.updateTaskStatus(taskStatus,id);
            return ResponseEntity.status(HttpStatus.OK).body(taskUpdate);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
