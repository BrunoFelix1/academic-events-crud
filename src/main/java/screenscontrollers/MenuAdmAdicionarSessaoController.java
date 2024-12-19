package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Secao;
import models.Evento;
import models.SubEvento;

import facade.Facade;
import interfaces.IControladorTelas;

public class MenuAdmAdicionarSessaoController extends MenuAdmGerSessaoController implements IControladorTelas {

    @FXML
    private Button botaoSalvarSessao;

    @FXML
    private TextField nomeSessaoField;

    @FXML
    private TextField localSessaoField;

    @FXML
    private TextField horarioSessaoField;

    @FXML
    private TextField eventoRelacionadoField;

    @FXML
    private TextField subEventoRelacionadoField;

    private Facade facade = new Facade();

    @FXML
    void salvarSessao() {
        try {
            String nomeSessao = nomeSessaoField.getText();
            String localSessao = localSessaoField.getText();
            String horarioSessao = horarioSessaoField.getText();
            String eventoRelacionadoStr = eventoRelacionadoField.getText();
            String subEventoRelacionadoStr = subEventoRelacionadoField.getText();

            if (nomeSessao == null || nomeSessao.trim().isEmpty()) {
                exibirAlerta("Por favor, insira o nome da sessão.");
                return;
            }
            if (localSessao == null || localSessao.trim().isEmpty()) {
                exibirAlerta("Por favor, insira o local da sessão.");
                return;
            }
            if (horarioSessao == null || horarioSessao.trim().isEmpty()) {
                exibirAlerta("Por favor, insira o horário da sessão.");
                return;
            }
            if (eventoRelacionadoStr == null || eventoRelacionadoStr.trim().isEmpty()) {
                exibirAlerta("Por favor, insira o ID do evento relacionado.");
                return;
            }
            if (subEventoRelacionadoStr == null || subEventoRelacionadoStr.trim().isEmpty()) {
                exibirAlerta("Por favor, insira o ID do subevento relacionado.");
                return;
            }

            Long idEventoRelacionado = Long.parseLong(eventoRelacionadoStr);
            Long idSubEventoRelacionado = Long.parseLong(subEventoRelacionadoStr);

            Evento evento = facade.buscarEvento(idEventoRelacionado);
            SubEvento subEvento = facade.buscarSubEvento(idSubEventoRelacionado);

            Secao novaSecao = new Secao(evento, subEvento, nomeSessao, localSessao, horarioSessao);

            // Validar e adicionar a sessão usando a Facade
            if (facade.adicionarSecao(novaSecao)) {
                exibirAlertaSucesso("Sessão adicionada com sucesso!");
                nomeSessaoField.clear();
                localSessaoField.clear();
                horarioSessaoField.clear();
                eventoRelacionadoField.clear();
                subEventoRelacionadoField.clear();
            }
        } catch (RuntimeException e) {
            exibirAlerta(e.getMessage());
        }
    }
}
