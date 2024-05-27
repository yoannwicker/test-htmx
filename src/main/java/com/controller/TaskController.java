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
        return renderTasks(tasks);
    }

    @PostMapping
    @ResponseBody
    public String addTask(@RequestParam String content) {
        Task task = new Task();
        task.setContent(content);
        taskRepository.save(task);
        return renderTask(task);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }

    private String renderTasks(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(renderTask(task));
        }
        return sb.toString();
    }

    private String renderTask(Task task) {
        return "<li id='task-" + task.getId() + "'>"
            + task.getContent()
            + " <button hx-delete='/tasks/" + task.getId() + "' hx-target='#task-" + task.getId() + "' hx-swap='outerHTML'>Delete</button>"
            + "</li>";
    }
}
