package com.draell.diary.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String login;
    
    private String password;

    //Constructor's
    public User() {}

    public User(String newLogin, String newPassword) {
      this.login = newLogin; this.password = newPassword;
    }
    
    // Getters e setters
    public int getId() { return id; }

    public String getLogin() { return login; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
} 

