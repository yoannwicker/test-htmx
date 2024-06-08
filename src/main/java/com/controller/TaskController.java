package com.controller;

import com.model.Task;
import com.repository.TaskRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    @ResponseBody
    public String getTasks() {
        List<Task> tasks = taskRepository.findAll();
        return TaskListHtmlComponent.from(tasks).render().asString();
    }

    @PostMapping
    @ResponseBody
    public String addTask(@RequestParam String content) {
        Task task = new Task();
        task.setContent(content);
        taskRepository.save(task);
        return TaskHtmlComponent.from(task).render().asString();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}
