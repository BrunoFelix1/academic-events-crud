package screenscontrollers;

import java.util.List;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Evento;

import facade.Facade;
import interfaces.IControladorTelas;

public class MenuAdmDeletarEventoController extends MenuAdmGerEventController implements IControladorTelas {

    @FXML
    private Button botaoDeletar;

    @FXML
    private TextField nomeEventoField;

    private Facade facade = new Facade();

    @FXML
    void deletarEvento() {
        try {
            String nomeEvento = nomeEventoField.getText();

            // Validar campo
            if (nomeEvento.isEmpty()) {
                exibirAlerta("Por favor, insira o nome do evento.");
                return;
            }

            // Buscar o evento pelo nome
            List<Evento> eventos = facade.listarEventos();
            Evento eventoParaDeletar = null;
            for (Evento evento : eventos) {
                if (nomeEvento.equals(evento.getTitulo())) {
                    eventoParaDeletar = evento;
                    break;
                }
            }

            // Verificar se o evento foi encontrado
            if (eventoParaDeletar == null) {
                exibirAlerta("Evento não encontrado.");
                return;
            }

            // Deletar o evento
            if (facade.deletarEvento(eventoParaDeletar.getId())) {
                exibirAlertaSucesso("Evento deletado com sucesso!");
                nomeEventoField.clear();
            }
        } catch (RuntimeException e) {
            exibirAlerta(e.getMessage());
        }
    }
}
