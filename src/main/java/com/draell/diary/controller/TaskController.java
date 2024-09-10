package com.draell.diary.controller;

import com.draell.diary.models.TaskModel;
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
    public TaskModel createTask(@RequestBody TaskModel task) {
        return taskRepository.save(task);
    }

    // Busca uma tarefa pelo ID
    @GetMapping("/{id}")
    public TaskModel getTaskById(@PathVariable int id) {
        Optional<TaskModel> task = taskRepository.findById(id);
        return task.orElse(null); // Retorna null se a tarefa não for encontrada
    }

    // Atualiza uma tarefa
    @PutMapping("/{id}")
    public TaskModel updateTask(@PathVariable int id, @RequestBody TaskModel updatedTask) {
        Optional<TaskModel> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            TaskModel task = taskOptional.get();

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
    public List<TaskModel> getAllTasks() {
        return taskRepository.findAll();
    }
}

