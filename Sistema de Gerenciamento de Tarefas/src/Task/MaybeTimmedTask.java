import java.util.Optional;
import Time.Time; 

public abstract class MaybeTimmedTask extends Task{
  Optional<Time> rightTimming;

  protected MaybeTimmedTask(Task newTask, Optional<Time> newRightTimming){
    super(newTask); 
    this.rightTimming = newRightTimming;
  }

}
