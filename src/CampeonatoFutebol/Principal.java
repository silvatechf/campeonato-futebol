
package CampeonatoFutebol;
import javax.swing.JOptionPane;

public class Principal {

    public static void main(String[] args) {
        // Nomes das 4 equipes da liga
        String[] nomesEquipes = {
                "Bela Vista FC",
                "Colorado EC",
                "Grêmio FC",
                "Barcelona FC"
        };

        CampeonatoFutebol.Liga liga = new CampeonatoFutebol.Liga(nomesEquipes);

        System.out.println("A temporada de futebol da primavera começou!");

        while (!liga.temporadaEncerrada()) {
            String input = JOptionPane.showInputDialog(
                    null,
                    "Digite a temperatura da semana (em Celsius):",
                    "Controle da Temporada",
                    JOptionPane.QUESTION_MESSAGE
            );

            // Se o usuário cancelar ou fechar a caixa de diálogo
            if (input == null) {
                System.out.println("Simulação encerrada pelo usuário.");
                break;
            }

            try {
                double temperatura = Double.parseDouble(input);
                liga.jogarSemana(temperatura);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Entrada inválida. Por favor, insira um número.",
                        "Erro de Entrada",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }

        // Imprime o relatório final
        liga.imprimirEstatisticasFinais();

        // Encerra o programa (necessário por causa da thread do JOptionPane)
        System.exit(0);
    }
}