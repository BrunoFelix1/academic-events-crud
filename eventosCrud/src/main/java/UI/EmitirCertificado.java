package UI;

import java.util.Scanner;

public class EmitirCertificado {

    public static void emitirCertificado(Scanner scanner) {
        System.out.println("=== Emissão de Certificado ===");

        System.out.print("Digite o ID do evento: ");
        String idEvento = scanner.nextLine();

        System.out.print("Digite o ID do usuário: ");
        String idUsuario = scanner.nextLine();

        // TODO: Adicionar lógica para verificar se o usuário participou do evento
        // TODO: Adicionar lógica para verificar se o usuário completou as atividades necessárias

        // Se todas as verificações passarem, emitir o certificado
        System.out.println("Emitindo certificado para o usuário " + idUsuario + " no evento " + idEvento + "...");
        // TODO: Implementar a lógica de emissão do certificado
        System.out.println("Certificado emitido com sucesso!");
    }
}
