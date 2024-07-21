package UI;

import java.util.Scanner;

public class MenuPrincipal {

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
        boolean isAdmin = verificarPermissaoAdmin(usuario, senha);

        System.out.println("Login realizado com sucesso como " + usuario);

        if (isAdmin) {
            mostrarMenuAdmin(scanner, usuario);
        } else {
            mostrarMenuUsuario(scanner, usuario);
        }
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
                    ParticiparEvento.participarEvento(scanner);
                    break;
                case 2:
                    EmitirCertificado.emitirCertificado(scanner);
                    break;
                case 3:
                    CancelarInscricao.cancelarInscricao(scanner);
                    break;
                case 4:
                    VisualizarInscricao.visualizarInscricao(scanner);
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

    private static void mostrarMenuAdmin(Scanner scanner, String usuario) {
        boolean sair = false;

        while (!sair) {
            System.out.println("Escolha uma das opções:");
            System.out.println("1. Participar de Evento");
            System.out.println("2. Emitir Certificado");
            System.out.println("3. Cancelar Inscrição");
            System.out.println("4. Visualizar inscrições");
            System.out.println("5. Cadastrar Evento");
            System.out.println("6. Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    ParticiparEvento.participarEvento(scanner);
                    break;
                case 2:
                    EmitirCertificado.emitirCertificado(scanner);
                    break;
                case 3:
                    CancelarInscricao.cancelarInscricao(scanner);
                    break;
                case 4:
                    VisualizarInscricao.visualizarInscricao(scanner);
                    break;
                case 5:
                    CadastrarEvento.cadastrarEvento(scanner);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
    }

    private static boolean verificarPermissaoAdmin(String usuario, String senha) {
        // Simulação de verificação de permissão de administrador
        // Login padrão de administrador para testes
        if ("admin".equals(usuario) && "admin123".equals(senha)) {
            return true;
        }
        // TODO: Implementar a lógica de verificação real para outros usuários
        return false;
    }
}
