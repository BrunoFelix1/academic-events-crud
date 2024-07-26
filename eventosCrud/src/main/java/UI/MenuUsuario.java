
package UI;

import Controllers.EventoController;
import Controllers.UsuarioController;
import Interfaces.iControladorUI;
import Interfaces.iPersistenciaControlador;
import Interfaces.iUsuarioUI;
import Models.Evento;
import Models.Inscricao;
import Models.Usuario;
import Persistence.PersistenceEvento;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuUsuario {

    public static void mostrarMenuUsuario(Usuario u, Scanner scanner) {
        iControladorUI<Evento> eventoController = new EventoController();
        iUsuarioUI usuarioController = new UsuarioController();
        Usuario usuario = u;
        boolean sair = false;

        while (!sair) {
            System.out.println("Escolha uma das opções:");
            System.out.println("1. Listar Eventos");
            System.out.println("2. Participar de Evento");
            System.out.println("3. Participar de Trilha");
            System.out.println("4. Listar Inscrições");
            System.out.println("5. Cancelar Inscrição de Trilha");
            System.out.println("6. Emitir Certificado de Trilha");
            System.out.println("7. Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    List<Evento> eventos = eventoController.listar();
                    System.out.println("Eventos disponíveis:");
                    for ( Evento e : eventos ){
                        System.out.println("Nome do evento: "+e.getTitulo()+", Descrição: "+e.getDescricao()+", Horário: "+e.getHorario()+", Local:"+e.getLocal());
                    }
                    break;
                case 2:
                    System.out.println("Digite o nome do EVENTO que gostaria de participar: ");
                    String nomeEvento = scanner.nextLine();
                    usuarioController.InscricaoEvento(u,nomeEvento);
                    break;
                case 3:
                    System.out.println("Digite o nome da TRILHA que gostaria de participar: ");
                    String nomeTrilha = scanner.nextLine();
                    usuarioController.InscricaoTrilha(u,nomeTrilha);
                    break;
                case 4:
                    ArrayList<Inscricao> listaInscricoes = usuarioController.listaInscricoes(u.getId());
                    System.out.println("Você está inscrito nos eventos :");
                    for ( Inscricao i : listaInscricoes){
                        System.out.println(i.getIdEvento());
                    }
                    System.out.println("Você está inscrito nas trilhas :");
                    for ( Inscricao i : listaInscricoes){
                        System.out.println(i.getIdTrilha());
                    }
                    break;
                case 5:
                    System.out.println("Você gostaria de cancelar a inscrição em um Evento (1) ou Trilha (2)?");
                    int i = scanner.nextInt();
                    if ( i == 1){
                        System.out.println("Qual o nome do evento que você quer remover?");
                        String nomeEventoRemover = scanner.nextLine();
                        usuarioController.CancelarInscriçãoEvento(u,nomeEventoRemover);
                    }
                    else if ( i == 2){
                        System.out.println("Qual o nome da trilha que você quer remover?");
                        String nomeTrilhaRemover = scanner.nextLine();
                        usuarioController.CancelarInscriçãoTrilha(u,nomeTrilhaRemover);
                    }
                    else {System.out.println("Opção inválida");}
                    break;
                case 6:
                    System.out.println("De qual trilha você quer emitir o certificado?");
                    String nomeTrilhaCertificado = scanner.nextLine();

                    boolean estaInscrito = usuarioController.EmitirCertificado(u, nomeTrilhaCertificado);
                    if ( estaInscrito ){
                        System.out.println("Inscrição removida.");
                    } else { System.out.println("Você não está inscrito nessa trilha");};
                    break;
                case 7:
                    System.out.println("Saindo...");
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
    }
}