package com.draell.diary.controller;

import com.draell.diary.entity.User;
import com.draell.diary.entity.Task;
import com.draell.diary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @PostMapping
  public User createUser(@RequestBody User user) {
    // ID não é relevante aqui
    return userRepository.save(user);
  }

  @GetMapping("/{id}")
  public User getUserById(@PathVariable int id) {
    Optional<User> user = userRepository.findById(id);
    return user.orElse(null); // Retorna null se o usuário não for encontrado
  }

  @PutMapping("/{id}")
  public User updateUserPassword(@PathVariable int id, @RequestBody String newPassword) {
    Optional<User> userOptional = userRepository.findById(id);
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      user.setPassword(newPassword);
      return userRepository.save(user);
    }
    return null; // Retorna null se o usuário não for encontrado
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable int id) {
    userRepository.deleteById(id);
  }

  @PostMapping("/{id}/addTask")
  public User addTaskToUser(@PathVariable int id, @RequestBody Task task) {
    Optional<User> userOptional = userRepository.findById(id);
    if (userOptional.isPresent()) {
      User user = userOptional.get();

      // Usando o novo construtor da Task
      Task newTask = new Task(task.getId(), user, task.getCreationTime(), 
          task.getDescription(), task.getPriority(), 
          task.getSDate(), task.getRTimming(), 
          task.getWeekDays(), task.getTaskType());

      user.getTasks().add(newTask);
      return userRepository.save(user); // Salva o usuário com a nova tarefa
    }
    return null; // Retorna null se o usuário não for encontrado
  }

  @PostMapping("/{id}/addTasks")
  public User addTasksToUser(@PathVariable int id, @RequestBody List<Task> tasks) {
    Optional<User> userOptional = userRepository.findById(id);
    if (userOptional.isPresent()) {
      User user = userOptional.get();

      tasks.forEach(task -> {
        Task newTask = new Task(task.getId(), user, task.getCreationTime(), 
            task.getDescription(), task.getPriority(), 
            task.getSDate(), task.getRTimming(), 
            task.getWeekDays(), task.getTaskType());
        user.getTasks().add(newTask);
      });
      
      return userRepository.save(user); // Salva o usuário com as novas tarefas
    }
    return null; // Retorna null se o usuário não for encontrado
  }
}

