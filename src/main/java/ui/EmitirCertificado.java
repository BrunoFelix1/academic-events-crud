package ui;

import java.util.Scanner;

public class EmitirCertificado {

    public static void emitirCertificado(Scanner scanner) {
        System.out.println("=== Emissão de Certificado ===");

        System.out.print("Digite o ID do evento: ");
        String idEvento = scanner.nextLine();

        System.out.print("Digite o ID do usuário: ");
        String idUsuario = scanner.nextLine();


        // Se todas as verificações passarem, emitir o certificado
        System.out.println("Emitindo certificado para o usuário " + idUsuario + " no evento " + idEvento + "...");
        System.out.println("Certificado emitido com sucesso!");
    }
}
