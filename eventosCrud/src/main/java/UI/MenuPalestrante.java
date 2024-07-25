package UI;

import Models.Usuario;

import java.util.Scanner;

public class MenuPalestrante {

    public static void mostrarMenuPalestrante(Usuario u, Scanner scanner) {
        Usuario usuario = u;
        boolean sair = false;

        while (!sair) {
            System.out.println("Escolha uma das opções:");
            System.out.println("1. Submeter Atividade");
            System.out.println("2. Apagar Atividade");
            System.out.println("3. Atualizar Atividade");
            System.out.println("4. Listar Atividades");
            System.out.println("5. Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    // Implementar a lógica de submeter atividade
                    break;
                case 2:
                    // Implementar a lógica de apagar atividade
                    break;
                case 3:
                    // Implementar a lógica de atualizar atividade
                    break;
                case 4:
                    // Implementar a lógica de listar atividades
                    break;
                case 5:
                    System.out.println("Saindo...");
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
    }
}