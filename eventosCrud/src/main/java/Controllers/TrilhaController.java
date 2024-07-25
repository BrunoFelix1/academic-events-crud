package Controllers;

import java.util.ArrayList;
import java.util.Scanner;
import Models.Trilha;
import Persistence.PersistenceTrilha;

public class TrilhaController {

    private PersistenceTrilha trilhaT = new PersistenceTrilha();
    private Scanner scanner = new Scanner(System.in);

    protected void cadastrarTrilha(Trilha trilha) {

        ArrayList<Trilha> trilhas = trilhaT.getTodos();
        boolean trilhaExistente = false;
        for (Trilha t : trilhas) {
            if (t.getId() == trilha.getId()) {
                trilhaExistente = true;
                break;
            }
        }
        if (trilhaExistente) {
            System.out.println("Trilha com ID " + trilha.getId() + " já existe.");
        } else {
            trilhaT.add(trilha);
            System.out.println("Trilha cadastrada com sucesso.");
        }
    }

    protected void listarTrilhas() {
        ArrayList<Trilha> trilhas = trilhaT.getTodos();
        if (trilhas.isEmpty()) {
            System.out.println("Nenhuma trilha cadastrada.");
        } else {
            for (Trilha trilha : trilhas) {
                System.out.println("Nome da trilha: " + trilha.getNome());
                System.out.println("ID da trilha: " + trilha.getId());
                System.out.println("ID da seção: " + trilha.getIdSecao());
                System.out.println("----------------------------");
            }
        }
    }
    
    protected void deletarTrilha() {
        System.out.println("Digite o ID da trilha que deseja excluir:");
        int id = scanner.nextInt();
        ArrayList<Trilha> trilhas = trilhaT.getTodos();
        boolean trilhaExistente = false;
        for (Trilha t : trilhas) {
            if (t.getId() == id) {
                trilhaExistente = true;
                trilhaT.delete(t);
                System.out.println("Trilha com ID " + id + " excluída com sucesso.");
                break;
            }
        }
        if (!trilhaExistente) {
            System.out.println("Trilha com ID " + id + " não encontrada.");
        }
    }

    public void AtualizarTrilha(Trilha trilha){
        Trilha antigaTrilha = trilhaT.getPorId(trilha.getId());
        trilhaT.update(antigaTrilha,trilha);
    }
}

