package com.draell.diary.task;

import com.draell.diary.time.Session;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadLinedTask extends Task {
    private Session deadLine;
    
    // CONSTRUCTORS:
    // ----------------------
    public DeadLinedTask(Task newTask, Session newDeadLine) {
      super(newTask); 
      this.deadLine = new Session(newDeadLine);
    }

    public DeadLinedTask(DeadLinedTask copyMe) {
      super(copyMe.getTask());
      this.deadLine = copyMe.getDeadLine();
    }

    public Session getDeadLine() { return new Session(this.deadLine); }
    

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
