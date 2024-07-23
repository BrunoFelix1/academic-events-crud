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

        // Simulação de verificação de tipo de usuário
        String tipoUsuario = verificarTipoUsuario(usuario, senha);

        System.out.println("Login realizado com sucesso como " + usuario);

        switch (tipoUsuario) {
            case "admin":
                mostrarMenuAdmin(scanner, usuario);
                break;
            case "palestrante":
                mostrarMenuPalestrante(scanner, usuario);
                break;
            case "usuario":
                mostrarMenuUsuario(scanner, usuario);
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

    private static void mostrarMenuUsuario(Scanner scanner, String usuario) {
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

    private static void mostrarMenuPalestrante(Scanner scanner, String usuario) {
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

    private static void mostrarMenuAdmin(Scanner scanner, String usuario) {
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
