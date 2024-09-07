package com.draell.diary.task;

import com.draell.diary.time.Time;
import java.util.Optional;
import java.util.Arrays;
public class WeeklyTask extends MaybeTimmedTask {

  // Falta achar uma solucao melhor
  private boolean[] weekDays = new boolean[7];
  // Use the ports like this:
  // Sunday -----> 0
  // Monday -----> 1
  // Tuesday ----> 2
  // Wednesday --> 3
  // Thursday ---> 4
  // Friday -----> 5
  // Saturday ---> 6   

  // ------------------------------------------------------------------------------
  // CONSTRUCTOR'S
  // ------------------------------------------------------------------------------

  public WeeklyTask(Task newTask, Optional<Time> newRightTimming, boolean[] newWeekDays) {
    super(newTask, newRightTimming);  
    // This throw makes me unproud, should do better
    if (newWeekDays.length != 7) {
      throw new IllegalArgumentException("Devem ser 7 valores booleanos para os dias da semana");
    }    
    System.arraycopy(newWeekDays, 0, this.weekDays, 0, 7);    
  }

  public WeeklyTask(WeeklyTask copyMe) {
    super(new Task(copyMe.getTask()), copyMe.getRightTimming());
    this.weekDays = copyMe.getWeekDays();    
  }

  // ------------------------------------------------------------------------------
  // GETTER'S
  // ------------------------------------------------------------------------------

  public boolean [] getWeekDays() { 
    if (this.weekDays.length != 7) {
      throw new IllegalArgumentException("For some reason a weekDays has more than 7 slots");
    }    
    return Arrays.copyOf(this.weekDays, 7); 
  }

}


