// Classe para Participar de Evento
package UI;

import java.util.List;
import java.util.Scanner;

public class ParticiparEvento {
    public static void participarEvento(Scanner scanner) {
        boolean participarMaisEventos;
        do {
            // Exibir os eventos
            List<String> eventos = List.of("Evento 1", "Evento 2", "Evento 3");
            System.out.println("Eventos disponíveis:");
            for (int i = 0; i < eventos.size(); i++) {
                System.out.println((i + 1) + ". " + eventos.get(i));
            }
            System.out.print("Escolha um evento para participar: ");
            int eventoEscolhido = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            System.out.print("Deseja participar de alguma sessão do evento? (s/n): ");
            boolean participarDeSecao = scanner.nextLine().equalsIgnoreCase("s");

            if (participarDeSecao) {
                boolean participarMaisSecoes;
                do {
                    // Exibir as seções do evento
                    List<String> secoes = List.of("Sessão 1", "Sessão 2", "Sessão 3");
                    System.out.println("Seções disponíveis no " + eventos.get(eventoEscolhido - 1) + ":");
                    for (int i = 0; i < secoes.size(); i++) {
                        System.out.println((i + 1) + ". " + secoes.get(i));
                    }
                    System.out.print("Escolha uma seção para participar: ");
                    int secaoEscolhida = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer do scanner

                    System.out.println("Participando da " + secoes.get(secaoEscolhida - 1) + "...");

                    System.out.print("Deseja participar de outra seção? (s/n): ");
                    participarMaisSecoes = scanner.nextLine().equalsIgnoreCase("s");
                } while (participarMaisSecoes);
            }

            boolean participarMaisSubeventos;
            do {
                // Exibir os subeventos
                List<String> subeventos = List.of("Subevento 1", "Subevento 2", "Subevento 3");
                System.out.println("Subeventos disponíveis:");
                for (int i = 0; i < subeventos.size(); i++) {
                    System.out.println((i + 1) + ". " + subeventos.get(i));
                }
                System.out.print("Escolha um subevento para participar: ");
                int subeventoEscolhido = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer do scanner

                boolean participarMaisTrilhas;
                do {
                    // Exibir as trilhas do subevento escolhido
                    List<String> trilhas = List.of("Trilha 1", "Trilha 2", "Trilha 3");
                    System.out.println("Trilhas disponíveis no " + subeventos.get(subeventoEscolhido - 1) + ":");
                    for (int i = 0; i < trilhas.size(); i++) {
                        System.out.println((i + 1) + ". " + trilhas.get(i));
                    }
                    System.out.print("Escolha uma trilha para participar: ");
                    int trilhaEscolhida = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer do scanner

                    System.out.println("Participando da " + trilhas.get(trilhaEscolhida - 1) + "...");

                    System.out.print("Deseja participar de outra trilha? (s/n): ");
                    participarMaisTrilhas = scanner.nextLine().equalsIgnoreCase("s");
                } while (participarMaisTrilhas);

                System.out.print("Deseja participar de outro subevento? (s/n): ");
                participarMaisSubeventos = scanner.nextLine().equalsIgnoreCase("s");
            } while (participarMaisSubeventos);

            System.out.print("Deseja participar de outro evento? (s/n): ");
            participarMaisEventos = scanner.nextLine().equalsIgnoreCase("s");
        } while (participarMaisEventos);
    }
}