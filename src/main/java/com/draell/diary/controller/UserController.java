package com.draell.diary.controller;

import com.draell.diary.entity.User;
import com.draell.diary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User createUser(@RequestBody User user) {
        // id dosnt matter here
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
}

