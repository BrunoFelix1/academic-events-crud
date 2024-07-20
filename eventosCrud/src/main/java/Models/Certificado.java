package Models;

public class Certificado extends AbstractInfo {

    public Certificado(Usuario usuario, Evento evento, SubEvento subevento, Secao secao, Trilha trilha, boolean status) {
        super(usuario, evento, subevento, secao, trilha, status);
    }

    public void checarParticipacao() {
        // Implementação para checar participação
    }

    public void emitirCertificado() {
        // Implementação para emitir certificado
        if (this.status) {
            System.out.println("Certificado emitido para o usuário " + usuario.getNome());
        } else {
            System.out.println("Usuário não participou do evento");
        }
    }
}
