public class WeeklyTask extends Task {

  // Falta achar uma solucao melhor
  private boolean[] weekDays = new boolean[7];
  // Use the ports like this:
  // Sunday -----> 0
  // Monday -----> 1
  // Tuesday ----> 2
  // Wednesday --> 3
  // Thursday ---> 4
  // Friday -----> 5
  // Saturday ---> 6  

  public WeeklyTask(Task newTask, boolean[] newWeekDays) {
    super(newTask.getID(), newTask.getCreationTime(), newTask.getConclusionTime(), 
        newTask.getDescription(), newTask.getPriority(), newTask.getRightTimming());
    // This throw makes me unproud, should do better
    if (newWeekDays.length != 7) {
      throw new IllegalArgumentException("Devem ser 7 valores booleanos para os dias da semana");
    }    
    System.arraycopy(newWeekDays, 0, this.weekDays, 0, 7);
  }
}
