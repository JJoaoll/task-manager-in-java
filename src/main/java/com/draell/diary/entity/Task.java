package com.draell.diary.entity;

import com.draell.diary.entity.embeddables.DateEmbeddable;
import com.draell.diary.entity.embeddables.TimeEmbeddable;
import com.draell.diary.entity.embeddables.SessionEmbeddable;

import java.util.Optional;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "date.days",   column = @Column(name = "creationTime_date_days")),
    @AttributeOverride(name = "date.year",   column = @Column(name = "creationTime_date_year")),
    @AttributeOverride(name = "time.hour",   column = @Column(name = "creationTime_time_hour")),
    @AttributeOverride(name = "time.min",    column = @Column(name = "creationTime_time_min")),
    @AttributeOverride(name = "time.sec",    column = @Column(name = "creationTime_time_sec"))
  }) 
  private SessionEmbeddable creationTime;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "date.days",   column = @Column(name = "conclusionTime_date_days",   nullable = true)),
    @AttributeOverride(name = "date.year",   column = @Column(name = "conclusionTime_date_year",  nullable = true)),
    @AttributeOverride(name = "time.hour",   column = @Column(name = "conclusionTime_time_hour", nullable = true)),
    @AttributeOverride(name = "time.min",    column = @Column(name = "conclusionTime_time_min",   nullable = true)),
    @AttributeOverride(name = "time.sec",    column = @Column(name = "conclusionTime_time_sec",   nullable = true))
  }) 
  @Column(nullable = true)
  private SessionEmbeddable conclusionTime;

  @Column(nullable = true)
  private String description;

  @Column // QUERO VER ENUMS!!
  int priority;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "days", column = @Column(name = "sDate_days",  nullable = true)),
    @AttributeOverride(name = "year", column = @Column(name = "sDate_year",  nullable = true))
  })
  private DateEmbeddable sDate;


  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "hour", column = @Column(name = "rTimming_hour", nullable = true)),
    @AttributeOverride(name = "min", column =  @Column(name = "rTimming_min",  nullable = true)),
    @AttributeOverride(name = "sec", column =  @Column(name = "rTimming_sec",  nullable = true))
  })
  private TimeEmbeddable rTimming;

  @ElementCollection
  private List<Boolean> weekDays;

  // esse aqui vai usar ENUM!
  // e classes de equivalencia mod(m)
  @Column
  private int taskType; 

  //Constructor's
  public Task() {}

  public Task(int newID, SessionEmbeddable newCreationTime, Optional<String> newDescription,
      int newPriority, Optional<DateEmbeddable > newSDate, Optional<TimeEmbeddable > newRTimming, 
      Optional<List<Boolean>> newWeekDays, int newTaskType) {
    this.id = newID; 
    this.creationTime = new SessionEmbeddable(newCreationTime);
    newDescription.ifPresent(s -> this.description = s);
    this.priority = newPriority;
    newSDate.ifPresent(d -> this.sDate = new DateEmbeddable(d));
    newRTimming.ifPresent(t -> this.rTimming = new TimeEmbeddable(t));
    this.weekDays = null;
    newWeekDays.ifPresent(l -> this.weekDays = new LinkedList<>(l));
    this.taskType = newTaskType;

  }

  // Getters e setters
}
