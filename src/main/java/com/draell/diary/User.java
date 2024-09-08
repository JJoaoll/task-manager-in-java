package com.draell.diary;

import java.util.List;
import java.util.LinkedList;

import com.draell.diary.Calendar;
import com.draell.diary.task.Task;

public class User {
  int id;
  String login;
  String password;

  List<Calendar> schedule;
  List<Task> freeTasks; 

  public User(int newID, String newLogin, String newPassword) {
    this.id = newID; this.login = newLogin; this. password = newPassword;
    this.schedule = new LinkedList<Calendar>();
    this.freeTasks = new LinkedList<Task>();
  }


}


