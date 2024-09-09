package com.draell.diary.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

import java.util.List;
import java.util.LinkedList;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) 
  private int id;

  @Column 
  private String login;

  @Column(nullable = true) 
  private String password;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Task> tasks;

  //Constructor's
  public User() {}

  public User(String newLogin, String newPassword, List<Task> newTasks) {
    this.login = newLogin; this.password = newPassword;
    this.tasks = new LinkedList<>();
    this.tasks.addAll(newTasks);

  }

  public User(User copyMe) {
    this.id = copyMe.getId();
    this.login = copyMe.getLogin();
    this.tasks = new LinkedList<>();
    this.tasks.addAll(copyMe.getTasks());
  }

  // Getters e setters
  public int getId() { return id; }

  public String getLogin() { return login; }

  public String getPassword() { return password; }

  public List<Task> getTasks() { 
    List<Task> l = new LinkedList<>();
    l.addAll(this.tasks);
    return l;
  }

  public void setPassword(String password) { this.password = password; }
} 

