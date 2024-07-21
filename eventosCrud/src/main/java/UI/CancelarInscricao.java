package UI;

import java.util.Scanner;

public class CancelarInscricao {

    public static void cancelarInscricao(Scanner scanner) {
        System.out.println("=== Cancelamento de Inscrição ===");

        System.out.print("Digite o ID do evento: ");
        String idEvento = scanner.nextLine();

        System.out.print("Digite o ID do usuário: ");
        String idUsuario = scanner.nextLine();

        // TODO: Adicionar lógica para verificar se o usuário está inscrito no evento

        // TODO: Adicionar lógica para cancelar a inscrição
        System.out.println("Inscrição cancelada com sucesso para o usuário " + idUsuario + " no evento " + idEvento + "!");
    }
}
