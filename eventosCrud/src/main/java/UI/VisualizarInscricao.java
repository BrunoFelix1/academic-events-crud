package UI;

import java.util.List;
import java.util.Scanner;

public class VisualizarInscricao {

    public static void visualizarInscricao(Scanner scanner) {
        System.out.println("=== Visualização de Inscrições ===");

        System.out.print("Digite o ID do usuário: ");
        String idUsuario = scanner.nextLine();

        // Simulação da obtenção dos dados processados
        List<String[]> inscricoes = obterInscricoesProcessadas(idUsuario);

        if (inscricoes.isEmpty()) {
            System.out.println("Nenhuma inscrição encontrada para o usuário " + idUsuario + ".");
        } else {
            System.out.println("Inscrições do usuário " + idUsuario + ":");
            System.out.println("--------------------------------------");
            for (String[] inscricao : inscricoes) {
                System.out.println("Evento: " + inscricao[0]);
                System.out.println("Seção: " + inscricao[1]);
                System.out.println("Subevento: " + inscricao[2]);
                System.out.println("Trilha: " + inscricao[3]);
                System.out.println("--------------------------------------");
            }
        }

        System.out.println("Visualização de inscrições concluída.");
    }

    private static List<String[]> obterInscricoesProcessadas(String idUsuario) {
        // Simulação de dados processados
        return List.of(
            new String[]{"Evento 1", "Sessão 1", "Subevento 1", "Trilha 1"},
            new String[]{"Evento 2", "Sessão 2", "Subevento 2", "Trilha 2"}
        );
    }
}
