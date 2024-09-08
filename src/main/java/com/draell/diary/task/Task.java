package com.draell.diary.task;

import java.util.Optional;
import com.draell.diary.time.Date;
import com.draell.diary.time.Session;

public class Task {
  final int id;
  final Session creationTime;
  protected Session conclusionTime  /* final? */ ; 
  protected String description;
  protected int priority;
  // Maybe(Time + Period)  
  
  // CONSTRUTORES:
  // -----------------------------------------

  public Task(int newID, Session newCreationTime, String newDesc, int newPriort) {  
    this.id = newID; this.creationTime = new Session(newCreationTime);
    this.description = newDesc; this.priority = newPriort;
    this.conclusionTime = new Session(); 
  }

  public Task(Task newTask) { 
    this.id = newTask.getID(); this.creationTime = new Session (newTask.getCreationTime());
    this.description = newTask.getDescription(); this.priority = newTask.getPriority();
    this.conclusionTime = new Session(); 
  }   

  // GETTER'S
  public int getID() { return id; }
  public String getDescription() { return description; }
  public Session getCreationTime() { return new Session(creationTime); }
  public Session getConclusionTime() { return new Session(conclusionTime); }
  public int getPriority() { return priority; }
  protected Task getTask() {
    Task returnMe = new Task(this.id, new Session(this.creationTime),
        this.description, this.priority);
    returnMe.setConclusionTime(new Session(this.conclusionTime));

    return returnMe;
  }

  // deadLine or appointedDate deppending...
  // the name stands on "Specific Date" or "Special Date"
  public Optional<Date> getSDate() { return Optional.empty(); }
  public Optional<boolean[]> getWeekDays() { return Optional.empty(); }

  // SETTER'S

  public void setDescription(String newDescription) { description = newDescription; }
  public void setPriority(int newPriority) { priority = newPriority; }
  public void setConclusionTime(Session newConclTime) { this.conclusionTime = new Session(newConclTime); }

  

  //    Subclasses: TarefaSimples, TarefaRecorrente.
  //            TarefaSimples (Herda de Tarefa):
  //
  //    Uma tarefa com uma única data de conclusão.
  //    TarefaRecorrente (Herda de Tarefa):
  //
  //    Atributos: Intervalo de recorrência (diária, semanal, mensal).
  //    Métodos: repetirTarefa() que gera uma nova instância da tarefa quando a anterior é concluída.





}
