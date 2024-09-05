package Task;

import java.util.Optional;
import Time.Time; 

public abstract class MaybeTimmedTask extends Task{
  Optional<Time> rightTimming; // como lidar para nao copiar os optionals?

  protected MaybeTimmedTask(Task newTask, Optional<Time> newRightTimming){
    super(newTask); 
    this.rightTimming = newRightTimming.map(rTime -> new Time(rTime));
  }
 
  // Safe Copy with "map" as copilot said so
  public Optional<Time> getRightTimming() { return this.rightTimming.map(rTime -> new Time(rTime)); }

}
