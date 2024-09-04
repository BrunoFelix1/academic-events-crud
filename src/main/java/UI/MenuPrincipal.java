package UI;

import Controllers.UsuarioController;
import Interfaces.iUsuarioUI;
import Models.Usuario;
import Exception.UsuarioNaoEncontradoException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPrincipal {

    public void iniciarSistema() throws UsuarioNaoEncontradoException {
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        while (!sair) {
            System.out.println("Bem-vindo ao Sistema de Gerenciamento de Eventos!");
            System.out.println("1. Login");
            System.out.println("2. Cadastro");
            System.out.println("3. Sair");

            System.out.print("Escolha uma opção: ");
            try {
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
            } catch (InputMismatchException e) {
                System.out.println("Erro: Você deve inserir um número inteiro.");
                scanner.nextLine(); // Limpa o buffer para evitar loop infinito
            }
        }

        scanner.close();
    }

    private static void login(Scanner scanner) throws UsuarioNaoEncontradoException {
        boolean autenticado = false;
        iUsuarioUI usuarioController = new UsuarioController();
        Usuario usuario = new Usuario();
        while (autenticado != true){
            try {
                System.out.print("Digite seu usuário: ");
                String login = scanner.nextLine();
                System.out.print("Digite sua senha: ");
                String senha = scanner.nextLine();
                usuario = usuarioController.AutenticarUsuario(login, senha);
                autenticado = true;
            } catch (UsuarioNaoEncontradoException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Login realizado com sucesso como " + usuario.getNome());

        switch (usuario.getTipoDeUsuario()) {
            case "ADMINISTRADOR":
                MenuAdministrador.mostrarMenuAdmin(usuario, scanner);
                break;
            case "PALESTRANTE":
                MenuPalestrante.mostrarMenuPalestrante(usuario, scanner);
                break;
            case "COMUM":
                MenuUsuario.mostrarMenuUsuario(usuario, scanner);
                break;
            default:
                System.out.println("Tipo de usuário desconhecido.");
        }
    }

    private static void cadastro(Scanner scanner) {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        System.out.print("Digite seu novo usuário: ");
        usuario.setLogin(scanner.nextLine());

        System.out.print("Digite sua nova senha: ");
        usuario.setSenha(scanner.nextLine());

        System.out.print("Digite seu CPF: ");
        usuario.setCPF(scanner.nextLine());

        System.out.print("Digite seu nome: ");
        usuario.setNome(scanner.nextLine());

        System.out.print("Digite sua idade: ");
        usuario.setIdade(Integer.parseInt(scanner.nextLine()));

        System.out.print("Digite sua instituição: ");
        usuario.setInstituicao(scanner.nextLine());

        System.out.print("Digite o tipo de usuário ( COMUM, PALESTRANTE): ");
        usuario.setTipoDeUsuario(scanner.nextLine());

        iUsuarioUI usuarioController = new UsuarioController();
        usuarioController.cadastrar(usuario);

        System.out.println("Usuário cadastrado com sucesso!");

    }
}