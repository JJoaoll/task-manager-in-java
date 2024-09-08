package com.draell.diary;

import java.util.List;
import java.util.LinkedList;
import java.util.Optional;
import com.draell.diary.task.Task;
import com.draell.diary.task.DeadLinedTask;
import com.draell.diary.task.MaybeTimmedTask;
import com.draell.diary.task.DayTask;
import com.draell.diary.task.WeeklyTask;
import com.draell.diary.time.Date;

public class Calendar {
  private int year;
  private List<Task>[][] tasks; 

  public Calendar(int newYear, LinkedList<Task> newTasks) {
    this.tasks = createCalendarStructure(newYear);
    this.year = newYear;

    this.addTasks(newTasks);
  }

  private LinkedList<Task>[][] createCalendarStructure(int thatYear) {
    LinkedList<Task>[][] calendarStructure = new LinkedList[12][];
    calendarStructure[0] = new LinkedList[31]; // JAN
    calendarStructure[2] = new LinkedList[31]; // MAR
    calendarStructure[3] = new LinkedList[30]; // APR
    calendarStructure[4] = new LinkedList[31]; // MAIO
    calendarStructure[5] = new LinkedList[30]; // JUN
    calendarStructure[6] = new LinkedList[31]; // JUL
    calendarStructure[7] = new LinkedList[31]; // AGO
    calendarStructure[8] = new LinkedList[30]; // SET
    calendarStructure[9] = new LinkedList[31]; // OUT
    calendarStructure[10] = new LinkedList[30];// NOV
    calendarStructure[11] = new LinkedList[31];// DEZ

    if(isALeapYear(thatYear))                  // FEB
      calendarStructure[1] = new LinkedList[29]; 
    else
      calendarStructure[1] = new LinkedList[28];
    
    for(int i = 0; i < 12; i++) {
      for(int j = 0; j < calendarStructure[i].length; j++) {
        calendarStructure[i][j] = new LinkedList<Task>();
      } 
    }     

    return calendarStructure;
  }

  private boolean isALeapYear(int testMe) {    
    if (testMe % 4 == 0)      
      return testMe % 100 != 0 || testMe % 400 == 0;
    else 
      return false;
  }

  private void addTask(Task t, Date d) {
    if(d.getYear() == this.year)
      this.tasks[d.getMonth()-1][d.getDay()-1].addLast(t);
    return;
  }

  public void addTask(Task t) {
    //System.out.println("Passou aqui");
    // can be optimezed using isPresent(d -> this.addTask(t, d))
    if(t instanceof DayTask && t.getSDate().isPresent()) {          
      Date d = t.getSDate().orElse(new Date());
      this.addTask(t, d);        
    }

    else if(t instanceof WeeklyTask && t.getWeekDays().isPresent()) {
      boolean[] bug = {false,false,false,false,false,false,false};
      boolean[] weekDays = t.getWeekDays().orElse(bug);

      Date d = t.getCreationTime().getDate();
      int wd = d.getWeekDay();

      while(d.getYear() == this.year) {
        for(; wd<7; wd++) {
          // can be optimezed
          if(weekDays[wd] && d.getYear() == this.year) 
            this.addTask(t, d); 
          d = d.tomorrow();
        }
        wd = 0;
      }
    }

    else if(t instanceof DeadLinedTask && t.getSDate().isPresent()) {
      Date d = t.getSDate().orElse(new Date());
      this.addTask(t, d);
    }

    return;
  }

  public void addTasks(LinkedList<Task> taskList) {
    for(Task t : taskList) {
      this.addTask(t);
    }
    return;
  }

  public void printTaskDatesTEST() {
    for(int i = 0; i < 12; i++) {
      for(int j = 0; j < tasks[i].length; j++) {
        if(tasks[i][j].size() > 0)
          System.out.println("DateCheck:  "+(j+1)+"/"+(i+1)+"/"+this.year);
      }
    }
  }



}
