package com.draell.diary.task;

import com.draell.diary.time.Date;
import com.draell.diary.time.Time;

import java.util.Optional;

public class DayTask extends MaybeTimmedTask {
  private Date appointedDay;   

  // CONSTRUCTORS:
  // ------------------------------
  public DayTask(Task newTask, Optional<Time> newRightTimming, Date newApptdDay) {
    super(newTask, newRightTimming);
    this.appointedDay = new Date (newApptdDay);
  }  

  public DayTask(DayTask copyMe) {
    super(copyMe.getTask(), copyMe.getRightTimming());
    this.appointedDay = getAppointedDate();
  }

  // getters:
  public Date getAppointedDate() { return new Date(this.appointedDay); }

  @Override
  public Optional<Date> getSDate() { return Optional.of(new Date(this.appointedDay));}
}
