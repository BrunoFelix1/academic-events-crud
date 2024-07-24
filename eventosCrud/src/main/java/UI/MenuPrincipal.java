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
            scanner.nextLine();

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

        // Simulação de verificação de tipo de usuário
        String tipoUsuario = verificarTipoUsuario(usuario, senha);

        System.out.println("Login realizado com sucesso como " + usuario);

        switch (tipoUsuario) {
            case "admin":
                MenuAdministrador.mostrarMenuAdmin(scanner, usuario);
                break;
            case "palestrante":
                MenuPalestrante.mostrarMenuPalestrante(scanner, usuario);
                break;
            case "usuario":
                MenuUsuario.mostrarMenuUsuario(scanner, usuario);
                break;
            default:
                System.out.println("Tipo de usuário desconhecido.");
        }
    }

    private static void cadastro(Scanner scanner) {
        System.out.print("Digite seu novo usuário: ");
        String novoUsuario = scanner.nextLine();
        System.out.print("Digite sua nova senha: ");
        String novaSenha = scanner.nextLine();
        System.out.print("Digite seu CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite sua idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer do scanner
        System.out.print("Digite sua instituição: ");
        String instituicao = scanner.nextLine();

        System.out.println("Usuário " + novoUsuario + " cadastrado com sucesso!");
        System.out.println("Informações cadastradas:");
        System.out.println("CPF: " + cpf);
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Instituição: " + instituicao);
    }

    private static String verificarTipoUsuario(String usuario, String senha) {
        // Simulação de verificação de tipo de usuário
        if ("admin".equals(usuario) && "admin123".equals(senha)) {
            return "admin";
        } else if ("palestrante".equals(usuario) && "palestrante123".equals(senha)) {
            return "palestrante";
        } else {
            return "usuario";
        }
    }
}
