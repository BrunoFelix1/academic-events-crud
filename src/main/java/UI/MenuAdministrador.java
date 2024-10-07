package UI;

import Controllers.EventoController;
import Controllers.TrilhaController;
import Controllers.SubeventoController;
import Controllers.SecaoController;
import Interfaces.iControladorUI;
import Models.Evento;
import Models.Trilha;
import Models.SubEvento;
import Models.Secao;
import Models.Usuario;

import java.util.List;
import java.util.Scanner;

public class MenuAdministrador {
    private static final String EscolhaPadrao  ="Escolha uma opção: ";
    private static final String OpcaoInvalida = "Opção inválida. Por favor, tente novamente.";
    public static void mostrarMenuAdmin(Usuario u, Scanner scanner) {
        boolean sair = false;

        while (!sair) {
            System.out.println("Escolha uma das opções:");
            System.out.println("1. Gerenciar Eventos");
            System.out.println("2. Gerenciar Subeventos");
            System.out.println("3. Gerenciar Seções");
            System.out.println("4. Gerenciar Trilhas");
            System.out.println("5. Sair");

            System.out.print(EscolhaPadrao);
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
                    System.out.println(OpcaoInvalida);
            }
        }
    }

    private static void gerenciarEventos(Scanner scanner) {
        iControladorUI<Evento> eventoController = new EventoController();
        boolean sair = false;

        while (!sair) {
            System.out.println("Gerenciamento de Eventos:");
            System.out.println("1. Adicionar Evento");
            System.out.println("2. Listar Eventos");
            System.out.println("3. Atualizar Evento");
            System.out.println("4. Deletar Evento");
            System.out.println("5. Voltar");

            System.out.print(EscolhaPadrao);
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    Evento ev = new Evento();
                    ev.setId(1);
                    System.out.println("Informe o título do novo evento:");
                    ev.setTitulo(scanner.nextLine());
                    System.out.println("Informe o local do evento:");
                    ev.setLocal(scanner.nextLine());
                    System.out.println("Informe o horario do evento:");
                    ev.setHorario(scanner.nextLine());
                    System.out.println("Descrição do evento:");
                    ev.setDescricao(scanner.nextLine());

                    eventoController.cadastrar(ev);
                    System.out.println("Evento cadastrado com sucesso!");
                    break;
                case 2:
                    List<Evento> eventos = eventoController.listar();
                    System.out.println("Eventos disponíveis:");
                    for (Evento e : eventos) {
                        System.out.println("Nome do evento: " + e.getTitulo() + ", Descrição: " + e.getDescricao() + ", Horário: " + e.getHorario() + ", Local:" + e.getLocal());
                    }
                    break;
                case 3:
                    Evento NovoEv = new Evento();
                    System.out.println("Informe o ID do evento que deseja atualizar:");
                    NovoEv.setId(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Informe o título do novo evento:");
                    NovoEv.setTitulo(scanner.nextLine());
                    System.out.println("Informe o local do evento:");
                    NovoEv.setLocal(scanner.nextLine());
                    System.out.println("Informe o horario do evento:");
                    NovoEv.setHorario(scanner.nextLine());
                    System.out.println("Descrição do evento:");
                    NovoEv.setDescricao(scanner.nextLine());

                    eventoController.atualizar(NovoEv);
                    System.out.println("Evento atualizado com sucesso!");
                    break;
                case 4:
                    System.out.println("Qual o id do Evento que deseja remover?");
                    int idEvento = scanner.nextInt();
                    if (eventoController.deletar(idEvento)){
                        System.out.println("Evento removido.");
                    }
                    else { System.out.println("Evento não encontrado.");}
                    break;
                case 5:
                    sair = true;
                    break;
                default:
                    System.out.println(OpcaoInvalida);
            }
        }
    }

    private static void gerenciarSubeventos(Scanner scanner) {
        iControladorUI<SubEvento> subeventoController = new SubeventoController();
        boolean sair = false;

        while (!sair) {
            System.out.println("Gerenciamento de Subeventos:");
            System.out.println("1. Adicionar Subevento");
            System.out.println("2. Listar Subeventos");
            System.out.println("3. Atualizar Subevento");
            System.out.println("4. Deletar Subevento");
            System.out.println("5. Voltar");

            System.out.print(EscolhaPadrao);
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    SubEvento subev = new SubEvento();
                    subev.setId(1);
                    System.out.println("Informe o nome do novo SubEvento:");
                    subev.setTitulo(scanner.nextLine());
                    System.out.println("Informe o local do Subevento:");
                    subev.setLocal(scanner.nextLine());
                    System.out.println("Informe o horario do Subevento:");
                    subev.setHorario(scanner.nextLine());
                    System.out.println("Descrição do Subevento:");
                    subev.setDescricao(scanner.nextLine());
                    System.out.println("A qual evento este SubEvento está relacionado? (digite apenas o ID): ");
                    subev.setIdEvento(Integer.parseInt(scanner.nextLine()));

                    subeventoController.cadastrar(subev);
                    System.out.println("Subvento cadastrado com sucesso!");
                    break;
                case 2:
                    List<SubEvento> subeventos = subeventoController.listar();
                    System.out.println("Subeventos disponíveis:");
                    for (SubEvento s : subeventos) {
                        System.out.println("Nome do subevento: " + s.getTitulo() + ", Descrição: " + s.getDescricao() +", Local: " + s.getLocal() + ", Id do Evento: " + s.getIdEvento());
                    }
                    break;
                case 3:
                    SubEvento NovoSubev = new SubEvento();
                    System.out.println("Informe o ID do SubEvento que deseja atualizar:");
                    NovoSubev.setId(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Informe o nome do novo SubEvento:");
                    NovoSubev.setTitulo(scanner.nextLine());
                    System.out.println("Informe o local do Subevento:");
                    NovoSubev.setLocal(scanner.nextLine());
                    System.out.println("Informe o horario do Subevento:");
                    NovoSubev.setHorario(scanner.nextLine());
                    System.out.println("Descrição do Subevento:");
                    NovoSubev.setDescricao(scanner.nextLine());
                    System.out.println("A qual evento este SubEvento está relacionado? (digite apenas o ID): ");
                    NovoSubev.setIdEvento(Integer.parseInt(scanner.nextLine()));

                    subeventoController.atualizar(NovoSubev);
                    System.out.println("Subvento atualizado com sucesso!");
                    break;
                case 4:
                    System.out.println("Qual o id do Subevento que deseja remover?");
                    int idSubevento = scanner.nextInt();
                    if (subeventoController.deletar(idSubevento)){
                        System.out.println("Subevento removido.");
                    }
                    else { System.out.println("Subevento não encontrado.");}
                    break;
                case 5:
                    sair = true;
                    break;
                default:
                    System.out.println(OpcaoInvalida);
            }
        }
    }

    private static void gerenciarSecoes(Scanner scanner) {
        iControladorUI<Secao> secaoController = new SecaoController();
        boolean sair = false;

        while (!sair) {
            System.out.println("Gerenciamento de Seções:");
            System.out.println("1. Adicionar Seção");
            System.out.println("2. Listar Seções");
            System.out.println("3. Atualizar Seção");
            System.out.println("4. Deletar Seção");
            System.out.println("5. Voltar");

            System.out.print(EscolhaPadrao);
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    Secao secao = new Secao();
                    secao.setId(0);
                    System.out.println("Informe o nome da Sessão: ");
                    secao.setNome(scanner.nextLine());
                    System.out.println("Informe local que a sessão acontecerá: ");
                    secao.setLocal(scanner.nextLine());
                    System.out.println("Informe horario que da sessão: ");
                    secao.setHorario(scanner.nextLine());
                    System.out.println("Qual o id do EVENTO que a sessão está relacionada: ");
                    secao.setId_evento(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Qual o id do Subevento que a sessão está relacionada: ");
                    secao.setId_subEvento(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Sessão cadastrada.");
                    secaoController.cadastrar(secao);
                    break;
                case 2:
                    List<Secao> secoes = secaoController.listar();
                    System.out.println("Seções disponíveis:");
                    for (Secao s : secoes) {
                        System.out.println("Nome da seção: " + s.getNome() + ", Local: " + s.getLocal() + ", Horario: " + s.getHorario() + ", ID do subevento"+s.getId_subEvento()+", ID do evento:"+s.getId_evento());
                    }
                    break;
                case 3:
                    Secao NovaSecao = new Secao();
                    System.out.println("Informe o ID da sessão que deseja atualizar: ");
                    NovaSecao.setId(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Informe o nome da Sessão: ");
                    NovaSecao.setNome(scanner.nextLine());
                    System.out.println("Informe local que a sessão acontecerá: ");
                    NovaSecao.setLocal(scanner.nextLine());
                    System.out.println("Informe horario que da sessão: ");
                    NovaSecao.setHorario(scanner.nextLine());
                    System.out.println("Qual o id do EVENTO que a sessão está relacionada: ");
                    NovaSecao.setId_evento(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Qual o id do Subevento que a sessão está relacionada: ");
                    NovaSecao.setId_subEvento(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Sessão atualizada.");
                    secaoController.atualizar(NovaSecao);
                    break;
                case 4:
                    System.out.println("Qual o id da seção que deseja remover?");
                    int idSecao= scanner.nextInt();
                    if (secaoController.deletar(idSecao)){
                        System.out.println("Seção removida.");
                    }
                    else { System.out.println("Seção não encontrada.");}
                    break;
                case 5:
                    sair = true;
                    break;
                default:
                    System.out.println(OpcaoInvalida);
            }
        }
    }

    private static void gerenciarTrilhas(Scanner scanner) {
        iControladorUI<Trilha> trilhaController = new TrilhaController();
        boolean sair = false;

        while (!sair) {
            System.out.println("Gerenciamento de Trilhas:");
            System.out.println("1. Adicionar Trilha");
            System.out.println("2. Listar Trilhas");
            System.out.println("3. Atualizar Trilha");
            System.out.println("4. Deletar Trilha");
            System.out.println("5. Voltar");

            System.out.print(EscolhaPadrao);
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    Trilha t = new Trilha();
                    t.setId(0);
                    System.out.println("Informe o nome da trilha: ");
                    t.setNome(scanner.nextLine());
                    System.out.println("Informe o id da seção em que a trilha vai acontecer:");
                    t.setIdSecao(Integer.parseInt(scanner.nextLine()));
                    trilhaController.cadastrar(t);
                    System.out.println("Trilha cadastrada.");
                    break;
                case 2:
                    List<Trilha> trilhas = trilhaController.listar();
                    System.out.println("Trilhas disponíveis:");
                    for (Trilha trilha : trilhas) {
                        System.out.println("Nome da trilha: " + trilha.getNome() + ", Acontecerá na sessão: " + trilha.getIdSecao());
                    }
                    break;
                case 3:
                    Trilha novaTrilha = new Trilha();
                    System.out.println("Informe o ID da trilha que deseja atualizar: ");
                    novaTrilha.setNome(scanner.nextLine());
                    System.out.println("Informe o nome da trilha: ");
                    novaTrilha.setNome(scanner.nextLine());
                    System.out.println("Informe o id da seção em que a trilha vai acontecer:");
                    novaTrilha.setIdSecao(Integer.parseInt(scanner.nextLine()));
                    trilhaController.atualizar(novaTrilha);
                    System.out.println("Trilha atualizada.");
                    break;
                case 4:
                    System.out.println("Qual o id da trilha que deseja remover?");
                    int idtrilha= scanner.nextInt();
                    if (trilhaController.deletar(idtrilha)){
                        System.out.println("Trilha removida.");
                    }
                    else { System.out.println("Trilha não encontrada.");}
                    break;
                case 5:
                    sair = true;
                    break;
                default:
                    System.out.println(OpcaoInvalida);
            }
        }
    }
}
