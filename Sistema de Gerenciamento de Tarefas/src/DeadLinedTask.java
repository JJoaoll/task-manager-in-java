import Time.Time;
import Time.Date;
import Time.Session;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadLinedTask extends Task {
    private Session deadLine;



    public DeadLinedTask(int newID, Session newCreationTime, Session newConclusionTime, String newDescription, int newPriority) {
        super(newID, newCreationTime, newConclusionTime, newDescription, newPriority);
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
