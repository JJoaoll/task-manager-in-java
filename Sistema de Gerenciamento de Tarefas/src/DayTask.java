import Time.Date;
import Time.Session;

import java.util.Optional;
public class DayTask extends Task {
  private Date appointedDay; 

  public DayTask(int newID, Session newCreationTime, Session newConclusionTime, String newDescription,
      int newPriority, Date newAppointedDay) {
    super(newID, newCreationTime, newConclusionTime, newDescription, newPriority);
    this.appointedDay = newAppointedDay; 
  }

  public DayTask(int newID, Session newCreationTime, Session newConclusionTime, String newDescription,
      int newPriority, Date newAppointedDay, Session newRightTimming) {
    super(newID, newCreationTime, newConclusionTime, newDescription, newPriority);
    this.appointedDay = newAppointedDay; this.rightTimming = Optional.ofNullable(newRightTimming);
  }

  public DayTask(int newID, Session newCreationTime, Session newConclusionTime, String newDescription,
      int newPriority, Date newAppointedDay, Optional<Session> newRightTimming) {
    super(newID, newCreationTime, newConclusionTime, newDescription, newPriority);
    this.appointedDay = newAppointedDay; this.rightTimming = newRightTimming;
  } 

  public DayTask(Task newTask, Date newAppointedDay) {
    super(newTask.getID(), newTask.getCreationTime(), newTask.getConclusionTime(), 
        newTask.getDescription(), newTask.getPriority(), newTask.getRightTimming());
    this.appointedDay = newAppointedDay;  
  }


}
