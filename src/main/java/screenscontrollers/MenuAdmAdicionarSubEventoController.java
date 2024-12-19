package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.SubEvento;

import facade.Facade;
import models.Evento;
import interfaces.IControladorTelas;

public class MenuAdmAdicionarSubEventoController extends MenuAdmGerenciarSubEventoController implements IControladorTelas {

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

    private Facade facade = new Facade();

    @FXML
    void salvarSubEvento() {
        try {
            String nome = nomeSubEventoField.getText();
            String local = localSubEventoField.getText();
            String descricao = descricaoSubEventoField.getText();
            String horarioStr = horarioSubEventoField.getText();
            String eventoIdStr = eventoAssociadoField.getText();

            if (nome.isEmpty() || local.isEmpty() || descricao.isEmpty() || horarioStr.isEmpty() || eventoIdStr.isEmpty()) {
                exibirAlerta("Por favor, preencha todos os campos.");
                return;
            }

            Long eventoId = Long.parseLong(eventoIdStr);
            String horario = horarioStr;

            Evento evento = facade.buscarEvento(eventoId);

            SubEvento novoSubEvento = new SubEvento(evento, nome, local, horario, descricao);

            // Validar e adicionar o subevento usando a Facade
            if (facade.adicionarSubEvento(novoSubEvento)) {
                exibirAlertaSucesso("Subevento adicionado com sucesso!");
                nomeSubEventoField.clear();
                localSubEventoField.clear();
                descricaoSubEventoField.clear();
                horarioSubEventoField.clear();
                eventoAssociadoField.clear();
            }
        } catch (RuntimeException e) {
            exibirAlerta(e.getMessage());
        }
    }
}
