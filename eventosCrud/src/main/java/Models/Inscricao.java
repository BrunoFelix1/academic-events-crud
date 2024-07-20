package Models;

public class Inscricao extends AbstractInfo {
    public Inscricao(Usuario usuario, Evento evento, SubEvento subevento, Secao secao, Trilha trilha, boolean status) {
        super(usuario, evento, subevento, secao, trilha, status);
    }

    public void confirmarInscricao() {
        // Implementação para confirmar inscrição
        this.status = true;
    }

    public void cancelarInscricao() {
        // Implementação para cancelar inscrição
        this.status = false;
    }
}
