//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import Time.Time;
import Time.Date;
import Time.Session;

public class Main {
    public static void main(String[] args) {

        Date date1 = new Date(16, 8, 2024);
        Date date2 = new Date(23, 8, 2024);
        Time time1 = new Time(12,31,0);
        Time time2 = new Time(0,0,0);
        Session ses1 = new Session(date1, time1);
        Session ses2 = new Session(date2, time2);

        System.out.println("timming1: "+ ses1.toString());
        System.out.println("timming2: "+ ses2.toString());
        System.out.println("difference: "+ ses1.absoluteMinusInSession(ses2).toString());
        System.out.println("difference: "+ ses1.absoluteMinusInString(ses2));


    }
}

/* O Sistema de Gerenciamento de Tarefas é um projeto interessante que envolve a criação de uma aplicação para organizar e monitorar tarefas pessoais ou de equipe. Esse sistema pode ser bastante simples ou incrivelmente robusto, dependendo de quantas funcionalidades você deseja implementar. Aqui está uma descrição mais detalhada e algumas sugestões sobre como você pode estruturá-lo:

1. Estrutura de Classes

Prioridade (Enum):
Enum que define diferentes níveis de prioridade, como BAIXA, MEDIA, ALTA.
Usuário:

Atributos: Nome, ID, lista de tarefas.
Métodos: adicionarTarefa(), removerTarefa(), listarTarefas().
2. Funcionalidades
Criação e Gerenciamento de Tarefas:

Usuários podem criar tarefas simples ou recorrentes.
Definir a prioridade de cada tarefa.
Marcar tarefas como concluídas, com a opção de repetir tarefas recorrentes.
Editar detalhes da tarefa, como descrição, data, ou prioridade.
Organização e Filtragem:

Usar ArrayList ou LinkedList para armazenar tarefas.
Implementar métodos para ordenar tarefas por data, prioridade, ou status (concluída/pendente).
Usar um HashMap para agrupar tarefas por usuários ou categorias específicas.
Exceções Personalizadas:

Criar exceções para tratar erros comuns, como duplicidade de tarefas ou tarefas sem descrição.
Usar regex para validar entradas, como datas e descrições de tarefas.
Notificações e Lembretes:

Implementar um sistema de notificação usando Threads, que verifica em intervalos regulares se há tarefas próximas do prazo e notifica o usuário.
Usar LocalDate e LocalDateTime para gerenciar e calcular prazos.
Integração com Console ou GUI:

Implementar a interface com o usuário, que pode ser via console ou uma interface gráfica simples usando Swing/JavaFX.
Coletar input do usuário e mostrar a lista de tarefas, permitindo interações como marcar tarefas como concluídas, adicionar novas, etc.
3. Conceitos Avançados
Lambda Expressions e Streams:

Usar lambdas e streams para manipulação e filtragem de listas de tarefas.
Por exemplo, filtrar e exibir apenas tarefas de alta prioridade que estão próximas do vencimento.
Advanced Sorting:

Implementar ordenação avançada utilizando Comparator com múltiplos critérios, como prioridade e data, em uma única operação.
Persistência de Dados:

Salvar e carregar o estado das tarefas usando serialização ou banco de dados.
Implementar backups automáticos ou agendados usando threads.
Multithreading:

Paralelizar operações, como carregamento de dados em segundo plano enquanto o usuário interage com o sistema.
Usar sincronização para gerenciar acessos concorrentes aos dados de tarefas, se estiver implementando um sistema multiusuário.
4. Desafios Adicionais
Interface com Banco de Dados:
Persistir os dados das tarefas em um banco de dados como SQLite ou MySQL, permitindo que o sistema salve e recupere tarefas de maneira persistente.
Integração com APIs:
Conectar o sistema com APIs externas para adicionar funcionalidades como integração com calendários (Google Calendar) ou envio de notificações por e-mail.
Esse projeto, embora relativamente simples no conceito, oferece várias oportunidades para aplicar praticamente todos os conceitos de Java que você mencionou, de forma prática e significativa.*/