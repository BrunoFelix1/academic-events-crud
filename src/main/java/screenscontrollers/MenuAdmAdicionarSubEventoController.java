package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.SubEvento;

import models.Evento;
import java.time.LocalDateTime;

import controllers.EventoController;
import controllers.SubEventoController;

public class MenuAdmAdicionarSubEventoController extends MenuAdmGerenciarSubEventoController {

    @FXML
    private Button botaoSalvarSubEvento;

    @FXML
    private TextField nomeSubEventoField;

    @FXML
    private TextField localSubEventoField;

    @FXML
    private TextField descricaoSubEventoField;

    @FXML
    private TextField horarioSubEventoField;

    @FXML
    private TextField eventoAssociadoField;

    private SubEventoController subEventoController = new SubEventoController();
    private EventoController eventoController = new EventoController();

    @FXML
    void salvarSubEvento() {
        try {
            String nome = nomeSubEventoField.getText();
            String local = localSubEventoField.getText();
            String descricao = descricaoSubEventoField.getText();
            String horarioStr = horarioSubEventoField.getText();
            String eventoIdStr = eventoAssociadoField.getText();

            if (nome.isEmpty() || local.isEmpty() || descricao.isEmpty() || horarioStr.isEmpty() || eventoIdStr.isEmpty()) {
                return;
            }

            Long eventoId = Long.parseLong(eventoIdStr);
            LocalDateTime horario = LocalDateTime.parse(horarioStr);

            Evento evento = eventoController.buscarEventoPorId(eventoId);

            SubEvento novoSubEvento = new SubEvento(evento, nome, local, horario, descricao);

            subEventoController.adicionarSubEvento(novoSubEvento);

            nomeSubEventoField.clear();
            localSubEventoField.clear();
            descricaoSubEventoField.clear();
            horarioSubEventoField.clear();
            eventoAssociadoField.clear();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
