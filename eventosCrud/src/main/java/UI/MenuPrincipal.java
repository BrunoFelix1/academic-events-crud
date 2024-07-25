package UI;

import Controllers.UsuarioController;
import Models.Usuario;
import Exception.UsuarioNaoEncontradoException;
import java.util.Scanner;

public class MenuPrincipal {

    public static void main(String[] args) throws UsuarioNaoEncontradoException {
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

    private static void login(Scanner scanner) throws UsuarioNaoEncontradoException {
        System.out.print("Digite seu usuário: ");
        String login = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        // verificação de tipo de usuário
        UsuarioController usuarioController = new UsuarioController();
        Usuario usuario = usuarioController.AutenticarUsuario(login, senha);

        System.out.println("Login realizado com sucesso como " + usuario);

        switch (usuario.getTipoDeUsuario()) {
            case ADMINISTRADOR:
                MenuAdministrador.mostrarMenuAdmin(usuario);
                break;
            case PALESTRANTE:
                MenuPalestrante.mostrarMenuPalestrante(usuario);
                break;
            case COMUM:
                MenuUsuario.mostrarMenuUsuario(usuario);
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

        // Aqui, você adicionaria a lógica para registrar o novo usuário com as informações coletadas
        System.out.println("Usuário " + novoUsuario + " cadastrado com sucesso!");
        System.out.println("Informações cadastradas:");
        System.out.println("CPF: " + cpf);
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Instituição: " + instituicao);
    }
}