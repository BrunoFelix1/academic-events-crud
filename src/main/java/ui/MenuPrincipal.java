package ui;

import controllers.UsuarioController;
import interfaces.IUsuarioUI;
import models.Usuario;
import exception.UsuarioNaoEncontradoException;
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
        IUsuarioUI usuarioController = new UsuarioController();
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

        System.out.println("Login realizado com sucesso como " + usuario.getNome() + " - " + usuario.getTipoDeUsuario());

        if (usuario.getTipoDeUsuario().equalsIgnoreCase("ADMINISTRADOR")){
            MenuAdministrador.mostrarMenuAdmin(usuario, scanner);
        }
        else if (usuario.getTipoDeUsuario().equalsIgnoreCase("PALESTRANTE")){
            MenuPalestrante.mostrarMenuPalestrante(usuario, scanner);
        }
        else if (usuario.getTipoDeUsuario().equalsIgnoreCase("COMUM")){
            MenuUsuario.mostrarMenuUsuario(usuario, scanner);
        }
        else {
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

        boolean idadeConferida = false;
        while (idadeConferida == false) {
            System.out.print("Digite sua idade: ");
            try {
                int idade = Integer.parseInt(scanner.nextLine());
                usuario.setIdade(idade);
                idadeConferida = true;
            } catch (NumberFormatException e) {
                System.out.print("Sua idade está inválida, insira novamente.\n");
            }
        }

        System.out.print("Digite sua instituição: ");
        usuario.setInstituicao(scanner.nextLine());

        boolean tipoConferido = false;
        while (tipoConferido == false){
            System.out.print("Digite o tipo de usuário (COMUM, PALESTRANTE): ");
            usuario.setTipoDeUsuario(scanner.nextLine());
            if (usuario.getTipoDeUsuario().equalsIgnoreCase("COMUM") || usuario.getTipoDeUsuario().equalsIgnoreCase("PALESTRANTE")){
                tipoConferido = true;
            }
            else{
                System.out.print("O tipo de usuário desejado é inválido, tente novamente.\n");
            }
        }

        IUsuarioUI usuarioController = new UsuarioController();
        boolean verificadorCadastro = usuarioController.cadastrar(usuario);
        if (verificadorCadastro == true){
            System.out.println("Usuário cadastrado com sucesso!");
        }
        else {
            System.out.println("O usuário não foi cadastrado! Houve um erro nas informações passadas, tente novamente.");
        }

    }
}