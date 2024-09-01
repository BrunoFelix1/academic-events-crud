package UI;

import java.util.Scanner;

public class CancelarInscricao {

    public static void cancelarInscricao(Scanner scanner) {
        System.out.println("=== Cancelamento de Inscrição ===");

        System.out.print("Digite o ID do evento: ");
        String idEvento = scanner.nextLine();

        System.out.print("Digite o ID do usuário: ");
        String idUsuario = scanner.nextLine();


        System.out.println("Inscrição cancelada com sucesso para o usuário " + idUsuario + " no evento " + idEvento + "!");
    }
}
