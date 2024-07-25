
package UI;

import Models.Usuario;

import java.util.Scanner;

public class MenuUsuario {

    public static void mostrarMenuUsuario(Usuario u, Scanner scanner) {
        Usuario usuario = u;
        boolean sair = false;

        while (!sair) {
            System.out.println("Escolha uma das opções:");
            System.out.println("1. Listar Eventos");
            System.out.println("2. Participar de Evento");
            System.out.println("3. Participar de Subevento");
            System.out.println("4. Participar de Trilha");
            System.out.println("5. Listar Inscrições");
            System.out.println("6. Cancelar Inscrição de Trilha");
            System.out.println("7. Emitir Certificado de Trilha");
            System.out.println("8. Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    // Implementar a lógica de listar eventos
                    break;
                case 2:
                    // Implementar a lógica de participar de evento
                    break;
                case 3:
                    // Implementar a lógica de participar de subevento
                    break;
                case 4:
                    // Implementar a lógica de participar de trilha
                    break;
                case 5:
                    VisualizarInscricao.visualizarInscricao(scanner);
                    break;
                case 6:
                    CancelarInscricao.cancelarInscricao(scanner);
                    break;
                case 7:
                    EmitirCertificado.emitirCertificado(scanner);
                    break;
                case 8:
                    System.out.println("Saindo...");
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
    }
}