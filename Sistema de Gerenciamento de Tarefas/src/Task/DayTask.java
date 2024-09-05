import Time.Date;
import Time.Time;

import java.util.Optional;

public class DayTask extends MaybeTimmedTask {
  private Date appointedDay;   

  // CONSTRUCTORS:
  // ------------------------------
  public DayTask(Task newTask, Optional<Time> newRightTimming, Date newApptdDay) {
    super(newTask, newRightTimming);
    this.appointedDay = newApptdDay;
  }  

  // getters:
  public Date getAppointedDate() { return this.appointedDay; }
        
}
