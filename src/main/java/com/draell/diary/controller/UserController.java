package com.draell.diary.controller;

import com.draell.diary.models.UserModel;
import com.draell.diary.models.TaskModel;
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
  public UserModel createUser(@RequestBody UserModel user) {
    // ID não é relevante aqui
    return userRepository.save(user);
  }

  @GetMapping("/{id}")
  public UserModel getUserById(@PathVariable int id) {
    Optional<UserModel> user = userRepository.findById(id);
    return user.orElse(null); // Retorna null se o usuário não for encontrado
  }

  @PutMapping("/{id}")
  public UserModel updateUserPassword(@PathVariable int id, @RequestBody String newPassword) {
    Optional<UserModel> userOptional = userRepository.findById(id);
    if (userOptional.isPresent()) {
      UserModel user = userOptional.get();
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
  public UserModel addTaskToUser(@PathVariable int id, @RequestBody TaskModel task) {
    Optional<UserModel> userOptional = userRepository.findById(id);
    if (userOptional.isPresent()) {
      UserModel user = userOptional.get();

      // Usando o novo construtor da Task
      TaskModel newTask = new TaskModel(task.getId(), user, task.getCreationTime(), 
          task.getDescription(), task.getPriority(), 
          task.getSDate(), task.getRTimming(), 
          task.getWeekDays(), task.getTaskType());

      user.getTasks().add(newTask);
      return userRepository.save(user); // Salva o usuário com a nova tarefa
    }
    return null; // Retorna null se o usuário não for encontrado
  }

  @PostMapping("/{id}/addTasks")
  public UserModel addTasksToUser(@PathVariable int id, @RequestBody List<TaskModel> tasks) {
    Optional<UserModel> userOptional = userRepository.findById(id);
    if (userOptional.isPresent()) {
      UserModel user = userOptional.get();

      tasks.forEach(task -> {
        TaskModel newTask = new TaskModel(task.getId(), user, task.getCreationTime(), 
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

