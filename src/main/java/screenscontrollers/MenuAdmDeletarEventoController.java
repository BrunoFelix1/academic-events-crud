package screenscontrollers;

import java.util.List;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Evento;

import controllers.EventoController;

public class MenuAdmDeletarEventoController extends MenuAdmGerEventController {

    @FXML
    private Button botaoDeletar;

    @FXML
    private TextField nomeEventoField;

    private EventoController eventoController = new EventoController();

    @FXML
    void deletarEvento() {
        try {
            String nomeEvento = nomeEventoField.getText();

            // Validar campo
            if (nomeEvento.isEmpty()) {
                // ...mensagem de erro...
                System.out.println("Por favor, insira o nome do evento.");
                return;
            }

            // Buscar o evento pelo nome
            List<Evento> eventos = eventoController.listarTodosEventos();
            Evento eventoParaDeletar = null;
            for (Evento evento : eventos) {
                if (nomeEvento.equals(evento.getTitulo())) {
                    eventoParaDeletar = evento;
                    break;
                }
            }

            // Verificar se o evento foi encontrado
            if (eventoParaDeletar == null) {
                // ...mensagem de erro...
                System.out.println("Evento não encontrado.");
                return;
            }

            // Deletar o evento
            eventoController.deletarEvento(eventoParaDeletar.getId());

            // Limpar o campo após deletar
            nomeEventoField.clear();

            // ...mensagem de sucesso...
            System.out.println("Evento deletado com sucesso!");
        } catch (Exception e) {
            // ...tratamento de erro genérico...
            System.out.println("Erro ao deletar o evento: " + e.getMessage());
        }
    }
}
