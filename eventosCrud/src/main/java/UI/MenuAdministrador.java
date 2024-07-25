package UI;

import java.util.Scanner;

public class MenuAdministrador {

    public static void mostrarMenuAdmin(Scanner scanner, String usuario) {
        boolean sair = false;

        while (!sair) {
            System.out.println("Escolha uma das opções:");
            System.out.println("1. Gerenciar Eventos");
            System.out.println("2. Gerenciar Subeventos");
            System.out.println("3. Gerenciar Seções");
            System.out.println("4. Gerenciar Trilhas");
            System.out.println("5. Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    gerenciarEventos(scanner);
                    break;
                case 2:
                    gerenciarSubeventos(scanner);
                    break;
                case 3:
                    gerenciarSecoes(scanner);
                    break;
                case 4:
                    gerenciarTrilhas(scanner);
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

    private static void gerenciarEventos(Scanner scanner) {
        boolean sair = false;

        while (!sair) {
            System.out.println("Gerenciamento de Eventos:");
            System.out.println("1. Adicionar Evento");
            System.out.println("2. Listar Eventos");
            System.out.println("3. Atualizar Evento");
            System.out.println("4. Deletar Evento");
            System.out.println("5. Voltar");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    CadastrarEvento.cadastrarEvento(scanner);
                    break;
                case 2:
                    CadastrarEvento.listarEventos(scanner);
                    break;
                case 3:
                    CadastrarEvento.atualizarEvento(scanner);
                    break;
                case 4:
                    CadastrarEvento.deletarEvento(scanner);
                    break;
                case 5:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
    }

    private static void gerenciarSubeventos(Scanner scanner) {
        boolean sair = false;

        while (!sair) {
            System.out.println("Gerenciamento de Subeventos:");
            System.out.println("1. Adicionar Subevento");
            System.out.println("2. Listar Subeventos");
            System.out.println("3. Atualizar Subevento");
            System.out.println("4. Deletar Subevento");
            System.out.println("5. Voltar");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    // Implementar a lógica de adicionar subevento
                    break;
                case 2:
                    // Implementar a lógica de listar subeventos
                    break;
                case 3:
                    // Implementar a lógica de atualizar subevento
                    break;
                case 4:
                    // Implementar a lógica de deletar subevento
                    break;
                case 5:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
    }

    private static void gerenciarSecoes(Scanner scanner) {
        boolean sair = false;

        while (!sair) {
            System.out.println("Gerenciamento de Seções:");
            System.out.println("1. Adicionar Seção");
            System.out.println("2. Listar Seções");
            System.out.println("3. Atualizar Seção");
            System.out.println("4. Deletar Seção");
            System.out.println("5. Voltar");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    // Implementar a lógica de adicionar seção
                    break;
                case 2:
                    // Implementar a lógica de listar seções
                    break;
                case 3:
                    // Implementar a lógica de atualizar seção
                    break;
                case 4:
                    // Implementar a lógica de deletar seção
                    break;
                case 5:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
    }

    private static void gerenciarTrilhas(Scanner scanner) {
        boolean sair = false;

        while (!sair) {
            System.out.println("Gerenciamento de Trilhas:");
            System.out.println("1. Adicionar Trilha");
            System.out.println("2. Listar Trilhas");
            System.out.println("3. Atualizar Trilha");
            System.out.println("4. Deletar Trilha");
            System.out.println("5. Voltar");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    // Implementar a lógica de adicionar trilha
                    break;
                case 2:
                    // Implementar a lógica de listar trilhas
                    break;
                case 3:
                    // Implementar a lógica de atualizar trilha
                    break;
                case 4:
                    // Implementar a lógica de deletar trilha
                    break;
                case 5:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
    }
}

