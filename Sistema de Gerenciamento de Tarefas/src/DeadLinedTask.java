import Time.Time;
import Time.Date;
import Time.Session;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DeadLinedTask extends Task {
    private Session deadLine;

    public DeadLinedTask(int newID, Session newCreationTime, Session newConclusionTime, String newDescription,
        int newPriority, Session newDeadLine) {
        super(newID, newCreationTime, newConclusionTime, newDescription, newPriority);
        this.deadLine = newDeadLine; 
    }

    public DeadLinedTask(int newID, Session newCreationTime, Session newConclusionTime, String newDescription,
        int newPriority, Session newDeadLine, Session newRightTimming) {
        super(newID, newCreationTime, newConclusionTime, newDescription, newPriority);
        this.deadLine = newDeadLine; this.rightTimming = Optional.ofNullable(newRightTimming);
    }
     
    public DeadLinedTask(int newID, Session newCreationTime, Session newConclusionTime, String newDescription,
        int newPriority, Session newDeadLine, Optional<Session> newRightTimming) {
      super(newID, newCreationTime, newConclusionTime, newDescription, newPriority);
      this.deadLine = newDeadLine; this.rightTimming = newRightTimming;
    } 

    public DeadLinedTask(Task newTask, Session newDeadLine) {
      super(newTask.getID(), newTask.getCreationTime(), newTask.getConclusionTime(), 
          newTask.getDescription(), newTask.getPriority(), newTask.getRightTimming());
      this.deadLine = newDeadLine; 
    }

    public Session getDeadLine() { return this.deadLine; }

    public Session getRemainingTime(){
        // Obtendo a data e hora local
        LocalDateTime localDateTime = LocalDateTime.now();

        // Definindo o formato desejado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Formatando a data e hora
        String formattedDateTime = localDateTime.format(formatter);

        Session myTime = Session.stringToSession(formattedDateTime);
        return myTime.timeBeforeInSession(this.getDeadLine());
        // Seria bom ter um resultado negativo...
    }

}
