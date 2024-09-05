import Time.Session;

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
    this.id = newID; this.creationTime = newCreationTime;
    this.description = newDesc; this.priority = newPriort; 
  }

  public Task(Task newTask) { 
   this.id = newTask.getID(); this.creationTime = newTask.getCreationTime();
   this.description = newTask.getDescription(); this.priority = newTask.getPriority();
  }   

  // GETTER'S
  public int getID() { return id; }
  public String getDescription() { return description; }
  public Session getCreationTime() { return creationTime; }
  public Session getConclusionTime() { return conclusionTime; }
  public int getPriority() { return priority; }

  // SETTER'S

  public void setDescription(String newDescription) { description = newDescription; }
  public void setPriority(int newPriority) { priority = newPriority; }



  //    Subclasses: TarefaSimples, TarefaRecorrente.
  //            TarefaSimples (Herda de Tarefa):
  //
  //    Uma tarefa com uma única data de conclusão.
  //    TarefaRecorrente (Herda de Tarefa):
  //
  //    Atributos: Intervalo de recorrência (diária, semanal, mensal).
  //    Métodos: repetirTarefa() que gera uma nova instância da tarefa quando a anterior é concluída.





}
