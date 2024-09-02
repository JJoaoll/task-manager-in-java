import Time.Time;
import Time.Date;
import Time.Session;
abstract class Task {
    final int id;
    final Session creationTime;
    /* final? */ protected Session conclusionTime;
    protected String description;
    protected int priority;
    // Maybe(Time + Period)
   public Task(int newID, Session newCreationTime, Session newConclusionTime,
               String newDescription, int newPriority) {
       id = newID; description = newDescription;
       creationTime = newCreationTime; conclusionTime = newConclusionTime;
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
