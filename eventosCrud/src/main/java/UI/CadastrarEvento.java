package UI;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class CadastrarEvento {

    public static void cadastrarEvento(Scanner scanner) {
        System.out.println("=== Cadastro de Evento ===");

        System.out.print("Digite o nome do evento: ");
        String nomeEvento = scanner.nextLine();

        List<String> secoes = new ArrayList<>();
        List<String> subeventos = new ArrayList<>();
        List<String> trilhas = new ArrayList<>();

        // Cadastro de Seções
        boolean adicionarMaisSecoes;
        do {
            System.out.print("Digite o nome da seção: ");
            secoes.add(scanner.nextLine());

            System.out.print("Deseja adicionar outra seção? (s/n): ");
            adicionarMaisSecoes = scanner.nextLine().equalsIgnoreCase("s");
        } while (adicionarMaisSecoes);

        // Cadastro de Subeventos
        boolean adicionarMaisSubeventos;
        do {
            System.out.print("Digite o nome do subevento: ");
            subeventos.add(scanner.nextLine());

            // Cadastro de Trilhas para cada subevento
            boolean adicionarMaisTrilhas;
            do {
                System.out.print("Digite o nome da trilha para o subevento " + subeventos.get(subeventos.size() - 1) + ": ");
                trilhas.add(scanner.nextLine());

                System.out.print("Deseja adicionar outra trilha? (s/n): ");
                adicionarMaisTrilhas = scanner.nextLine().equalsIgnoreCase("s");
            } while (adicionarMaisTrilhas);

            System.out.print("Deseja adicionar outro subevento? (s/n): ");
            adicionarMaisSubeventos = scanner.nextLine().equalsIgnoreCase("s");
        } while (adicionarMaisSubeventos);

        // Exibir resumo do evento cadastrado
        System.out.println("\nEvento cadastrado com sucesso!");
        System.out.println("Nome do Evento: " + nomeEvento);
        System.out.println("Seções:");
        for (String secao : secoes) {
            System.out.println(" - " + secao);
        }
        System.out.println("Subeventos e Trilhas:");
        for (String subevento : subeventos) {
            System.out.println("Subevento: " + subevento);
            for (String trilha : trilhas) {
                System.out.println(" - Trilha: " + trilha);
            }
        }
    }
}
