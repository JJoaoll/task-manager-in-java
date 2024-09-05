package Task;

import Time.Date;
import Time.Time;

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
        
}
