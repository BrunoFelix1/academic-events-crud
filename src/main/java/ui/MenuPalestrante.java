package ui;

import controllers.AtividadeController;
import interfaces.iControladorUI;
import models.Atividade;
import models.Usuario;
import java.util.Scanner;

public class MenuPalestrante {

    public static void mostrarMenuPalestrante(Usuario u, Scanner scanner) {
        Usuario usuario = u;
        iControladorUI<Atividade> atividadeController = new AtividadeController();

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
                    Atividade atividade = new Atividade();
                    System.out.print("Tipo de Submissão (PALESTRA, WORKSHOP, ARTIGO): ");
                    atividade.setTipoSubmissao(scanner.nextLine());
                    System.out.print("Resumo: ");
                    atividade.setResumo(scanner.nextLine());
                    System.out.print("Id da trilha que a atividade estará relacionada: ");
                    atividade.setIdTrilha(Integer.parseInt(scanner.nextLine()));
                    atividade.setAutor(usuario.getNome());
                    atividade.setId(0);

                    atividadeController.cadastrar(atividade);
                    System.out.println("Atividade cadastrada com sucesso!");
                    break;
                case 2:
                    System.out.println("Qual atividade você gostaria de remover?");
                    int id = scanner.nextInt();
                    if (atividadeController.deletar(id)){
                        System.out.println("Atividade removida.");
                    }
                    else { System.out.println("Atividade não encontrada");}
                    break;
                case 3:
                    Atividade atividadeAtualizada = new Atividade();
                    System.out.print("Id da atividade que deseja atualizar: ");
                    atividadeAtualizada.setId(Integer.parseInt(scanner.nextLine()));
                    System.out.print("Tipo de Submissão: ");
                    atividadeAtualizada.setTipoSubmissao(scanner.nextLine());
                    System.out.print("Resumo: ");
                    atividadeAtualizada.setResumo(scanner.nextLine());
                    System.out.print("Id da trilha que a atividade estará relacionada: ");
                    atividadeAtualizada.setIdTrilha(Integer.parseInt(scanner.nextLine()));
                    atividadeAtualizada.setAutor(usuario.getNome());


                    atividadeController.atualizar(atividadeAtualizada);
                    break;
                case 4:
                    System.out.println("Atividades disponíveis:");
                    for (Atividade a : atividadeController.listar()) {
                        System.out.println("ID: " + a.getId() + ", Tipo: " + a.getTipoSubmissao() +
                                ", Autor: " + a.getAutor() + ", Resumo: " + a.getResumo() +
                                ", ID Trilha: " + a.getIdTrilha());
                    }
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
}