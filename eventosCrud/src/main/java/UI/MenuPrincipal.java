package UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuCadastro {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        while (!sair) {
            System.out.println("Bem-vindo ao Sistema de Gerenciamento de Eventos!");
            System.out.println("1. Login");
            System.out.println("2. Cadastro");
            System.out.println("3. Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    cadastro(scanner);
                    break;
                case 3:
                    sair = true;
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }

        scanner.close();
    }

    private static void login(Scanner scanner) {
        System.out.print("Digite seu usuário: ");
        String usuario = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        // Aqui, você adicionaria a lógica para verificar o usuário e senha
        System.out.println("Login realizado com sucesso como " + usuario);

        // Exibir o menu específico do tipo de usuário após o login
        mostrarMenuUsuario(scanner, usuario);
    }

    private static void cadastro(Scanner scanner) {
        System.out.print("Digite seu novo usuário: ");
        String novoUsuario = scanner.nextLine();
        System.out.print("Digite sua nova senha: ");
        String novaSenha = scanner.nextLine();

        // Aqui, você adicionaria a lógica para registrar o novo usuário
        System.out.println("Usuário " + novoUsuario + " cadastrado com sucesso!");
    }

    private static void mostrarMenuUsuario(Scanner scanner, String usuario) {
        boolean sair = false;

        while (!sair) {
            System.out.println("Escolha uma das opções:");
            System.out.println("1. Participar de Evento");
            System.out.println("2. Emitir Certificado");
            System.out.println("3. Cancelar Inscrição");
            System.out.println("4. Visualizar inscrições");
            System.out.println("5. Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    participarEvento(scanner);
                    break;
                case 2:
                    // Implementar a lógica de emissão de certificados
                    System.out.println("Emitindo certificado...");
                    break;
                case 3:
                    // Implementar a lógica de cancelamento de inscrição
                    System.out.println("Cancelando inscrição...");
                    break;
                case 4:
                    // Implementar a lógica de visualização de inscrições
                    System.out.println("Visualizando inscrições...");
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

    private static void participarEvento(Scanner scanner) {
        boolean participarMaisEventos;
        do {
            // Exibir os eventos
            List<String> eventos = List.of("Evento 1", "Evento 2", "Evento 3");
            System.out.println("Eventos disponíveis:");
            for (int i = 0; i < eventos.size(); i++) {
                System.out.println((i + 1) + ". " + eventos.get(i));
            }
            System.out.print("Escolha um evento para participar: ");
            int eventoEscolhido = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            System.out.print("Deseja participar de alguma sessão do evento? (s/n): ");
            boolean participarDeSecao = scanner.nextLine().equalsIgnoreCase("s");

            if (participarDeSecao) {
                boolean participarMaisSecoes;
                do {
                    // Exibir as seções do evento
                    List<String> secoes = List.of("Sessão 1", "Sessão 2", "Sessão 3");
                    System.out.println("Seções disponíveis no " + eventos.get(eventoEscolhido - 1) + ":");
                    for (int i = 0; i < secoes.size(); i++) {
                        System.out.println((i + 1) + ". " + secoes.get(i));
                    }
                    System.out.print("Escolha uma seção para participar: ");
                    int secaoEscolhida = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer do scanner

                    System.out.println("Participando da " + secoes.get(secaoEscolhida - 1) + "...");

                    System.out.print("Deseja participar de outra seção? (s/n): ");
                    participarMaisSecoes = scanner.nextLine().equalsIgnoreCase("s");
                } while (participarMaisSecoes);
            }

            boolean participarMaisSubeventos;
            do {
                // Exibir os subeventos
                List<String> subeventos = List.of("Subevento 1", "Subevento 2", "Subevento 3");
                System.out.println("Subeventos disponíveis:");
                for (int i = 0; i < subeventos.size(); i++) {
                    System.out.println((i + 1) + ". " + subeventos.get(i));
                }
                System.out.print("Escolha um subevento para participar: ");
                int subeventoEscolhido = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer do scanner

                boolean participarMaisTrilhas;
                do {
                    // Exibir as trilhas do subevento escolhido
                    List<String> trilhas = List.of("Trilha 1", "Trilha 2", "Trilha 3");
                    System.out.println("Trilhas disponíveis no " + subeventos.get(subeventoEscolhido - 1) + ":");
                    for (int i = 0; i < trilhas.size(); i++) {
                        System.out.println((i + 1) + ". " + trilhas.get(i));
                    }
                    System.out.print("Escolha uma trilha para participar: ");
                    int trilhaEscolhida = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer do scanner

                    System.out.println("Participando da " + trilhas.get(trilhaEscolhida - 1) + "...");

                    System.out.print("Deseja participar de outra trilha? (s/n): ");
                    participarMaisTrilhas = scanner.nextLine().equalsIgnoreCase("s");
                } while (participarMaisTrilhas);

                System.out.print("Deseja participar de outro subevento? (s/n): ");
                participarMaisSubeventos = scanner.nextLine().equalsIgnoreCase("s");
            } while (participarMaisSubeventos);

            System.out.print("Deseja participar de outro evento? (s/n): ");
            participarMaisEventos = scanner.nextLine().equalsIgnoreCase("s");
        } while (participarMaisEventos);
    }
}
