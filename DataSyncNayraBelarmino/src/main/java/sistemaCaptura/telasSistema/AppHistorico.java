package sistemaCaptura.telasSistema;
import sistemaCaptura.HistConsmRecurso;
import java.util.Scanner;
public class AppHistorico {
    public static void main(String[] args) {
        HistConsmRecurso histConsmRecurso = new HistConsmRecurso();
        Scanner scanner = new Scanner(System.in);
        // Suponha que você tenha uma instância da sua classe que contém os métodos
        // insertHistoricoCpu, insertHistoricoRam e insertHistoricoJanelas chamada "historico"
        // Historic historico = new Historic();

        int opcao;
        do {
            System.out.println("""
                    +------------------------------------+
                    |         Escolha uma opção:         |    
                    +------------------------------------+
                    | 1 - Visualizar dados da CPU        |
                    | 2 - Visualizar dados da RAM        |
                    | 3 - Visualizar dados das Janelas   |
                    | 4 - Visualizar dados gerais        |
                    | 0 - Sair                           |
                    +------------------------------------+
                    """);

            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    // Visualizar dados da CPU
                    System.out.println("+--------------------------+");
                    System.out.println("| Opção selecionada: 1     |");
                    System.out.println("+--------------------------+");
                    histConsmRecurso.insertHistoricoCpu();

                    break;
                case 2:
                    // Visualizar dados da RAM
                    System.out.println("+--------------------------+");
                    System.out.println("| Opção selecionada: 2     |");
                    System.out.println("+--------------------------+");
                    histConsmRecurso.insertHistoricoRam();
                    break;
                case 3:
                    // Visualizar dados das Janelas
                    System.out.println("+--------------------------+");
                    System.out.println("| Opção selecionada: 3     |");
                    System.out.println("+--------------------------+");
                    histConsmRecurso.insertHistoricoJanelas();
                    break;
                case 4:
                    // Visualizar dados gerais
                    System.out.println("+--------------------------+");
                    System.out.println("| Opção selecionada: 4     |");
                    System.out.println("+--------------------------+");
                    histConsmRecurso.insertHistorico();

                    break;
                case 0:
                    System.out.println("Espero ter ajudado. Até mais! :)");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);

        scanner.close();
    }
}