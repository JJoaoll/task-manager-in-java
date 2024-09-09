package com.draell.diary.controller;

import com.draell.diary.entity.Task;
import com.draell.diary.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    // Cria uma nova tarefa
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    // Busca uma tarefa pelo ID
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable int id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null); // Retorna null se a tarefa não for encontrada
    }

    // Atualiza uma tarefa
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable int id, @RequestBody Task updatedTask) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();

            task.setDescription(updatedTask.getDescription());
            task.setPriority(updatedTask.getPriority());
            task.setSDate(updatedTask.getSDate());
            task.setRTimming(updatedTask.getRTimming());
            task.setWeekDays(updatedTask.getWeekDays());
            task.setTaskType(updatedTask.getTaskType());

            return taskRepository.save(task);
        }
        return null;
    }

    // Deleta uma tarefa
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable int id) {
        taskRepository.deleteById(id);
    }

    // Retorna todas as tarefas
    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}

