package ui;

import controllers.EventoController;
import controllers.TrilhaController;
import controllers.SubeventoController;
import controllers.SecaoController;
import controllers.UsuarioController;
import interfaces.IControladorUI;
import interfaces.IUsuarioUI;
import models.Evento;
import models.Trilha;
import models.SubEvento;
import models.Secao;
import models.Inscricao;
import models.Usuario;
import persistence.PersistenceEvento;
import persistence.PersistenceTrilha;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuUsuario {
    private static UsuarioController usuarioController = new UsuarioController() ;

    private static final String DESCRICAO = "Descrição: ";
    public static void mostrarMenuUsuario(Usuario user, Scanner scanner) {

        IControladorUI<Trilha> trilhaController = new TrilhaController();


        IUsuarioUI usuarioController = new UsuarioController();
        boolean sair = false;

        while (!sair) {
            System.out.println("Escolha uma das opções:");
            System.out.println("1. Listar Eventos");
            System.out.println("2. Listar Trilhas");
            System.out.println("3. Listar Subeventos");
            System.out.println("4. Listar Seções");
            System.out.println("5. Participar de Evento");
            System.out.println("6. Participar de Trilha");
            System.out.println("7. Listar Inscrições");
            System.out.println("8. Cancelar Inscrição");
            System.out.println("9. Emitir Certificado de Trilha");
            System.out.println("10. Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    ListarEventos();
                    break;
                case 2:
                    ListarTrilhas();
                    break;
                case 3:
                    ListarSubEvento();
                    break;
                case 4:
                    ListarSecoes();
                    break;
                case 5:
                    ParticiparEvento(user,scanner);
                    break;
                case 6:
                    ParticiparTrilha(user,scanner);
                    break;
                case 7:
                    ListarInscricoesUsuario(user);
                    break;
                case 8:
                    CancelarInscricao(user, scanner);
                    break;
                case 9:
                    EmitirCertificado(user,scanner);
                    break;
                case 10:
                    System.out.println("Saindo...");
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
    }
    private static void ListarEventos() {
        IControladorUI<Evento> eventoController = new EventoController();
        List<Evento> eventos = eventoController.listar();
        System.out.println("Eventos disponíveis:");
        for (Evento e : eventos) {
            System.out.println("Nome do evento: " + e.getTitulo() + ", " + DESCRICAO + e.getDescricao() + ", Horário: " + e.getHorario() + ", Local:" + e.getLocal());
        }
    }
    private static void ListarTrilhas() {
        IControladorUI<Trilha> trilhaController = new TrilhaController();
        List<Trilha> trilhas = trilhaController.listar();
        System.out.println("Trilhas disponíveis:");
        for (Trilha t : trilhas) {
            System.out.println("Nome da trilha: " + t.getNome() + ", " + DESCRICAO);
        }
    }
    private static void ListarSubEvento() {
        IControladorUI<SubEvento> subeventoController = new SubeventoController();
        List<SubEvento> subeventos = subeventoController.listar();
        System.out.println("Subeventos disponíveis:");
        for (SubEvento s : subeventos) {
            System.out.println("Nome do subevento: " + s.getTitulo() + ", " + DESCRICAO + s.getDescricao() + ", Id do Evento: " + s.getIdEvento());
        }
    }
    private static void ListarSecoes() {
        IControladorUI<Secao> secaoController = new SecaoController();
        List<Secao> secoes = secaoController.listar();
        System.out.println("Seções disponíveis:");
        for (Secao s : secoes) {
            System.out.println("Nome da seção: " + s.getNome() + ", Horario: " + s.getHorario() + ", Local" + s.getLocal() + ", Id do Subevento: " + s.getId_subEvento() + ", Id do Evento: " + s.getId_evento());
        }
    }
    private static void ParticiparEvento(Usuario u, Scanner scanner) {
        ListarEventos();
        System.out.println("Digite o nome do EVENTO que gostaria de participar: ");
        String nomeEvento = scanner.next();
        usuarioController.InscricaoEvento(u, nomeEvento);
    }
    private static void ParticiparTrilha(Usuario user, Scanner scanner){
        System.out.println("Digite o nome da TRILHA que gostaria de participar: ");
        String nomeTrilha = scanner.nextLine();
        usuarioController.InscricaoTrilha(user, nomeTrilha);
    }
    private static void ListarInscricoesUsuario(Usuario user) {
        ArrayList<Inscricao> listaInscricoes = usuarioController.listaInscricoes(user.getId());
        PersistenceEvento eventoP = new PersistenceEvento();
        for (Inscricao i : listaInscricoes) {
            if (i.getIdEvento() != 0) {
                System.out.println("Você está inscrito no evento :");
                Evento evento = eventoP.getPorId(i.getIdEvento());
                System.out.println(evento.getTitulo());
            }
        }
        PersistenceTrilha trilhaP = new PersistenceTrilha();
        for (Inscricao i : listaInscricoes) {
            if (i.getIdTrilha() != 0) {
                System.out.println("Você está inscrito na trilha :");
                Trilha trilha = trilhaP.getPorId(i.getIdTrilha());
                System.out.println(trilha.getNome());
            }
        }
    }
    private static void CancelarInscricao(Usuario user, Scanner scanner){
        System.out.println("Você gostaria de cancelar a inscrição em um Evento (1) ou Trilha (2)?");
        int i = scanner.nextInt();
        if (i == 1) {
            System.out.println("Qual o nome do evento que você quer remover?");
            String nomeEventoRemover = scanner.next();
            usuarioController.CancelarInscriçãoEvento(user, nomeEventoRemover);
        } else if (i == 2) {
            System.out.println("Qual o nome da trilha que você quer remover?");
            String nomeTrilhaRemover = scanner.next();
            usuarioController.CancelarInscriçãoTrilha(user, nomeTrilhaRemover);
        } else {
            System.out.println("Opção inválida");
        }
    }
    private static void EmitirCertificado(Usuario user, Scanner scanner){
        System.out.println("De qual trilha você quer emitir o certificado?");
        String nomeTrilhaCertificado = scanner.nextLine();

        boolean estaInscrito = usuarioController.EmitirCertificado(user, nomeTrilhaCertificado);
        if (estaInscrito) {
            System.out.println("Certificado emitido!");
        } else {
            System.out.println("Você não está inscrito nessa trilha");
        }
    }
}
