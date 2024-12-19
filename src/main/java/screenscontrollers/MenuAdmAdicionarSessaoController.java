package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Secao;
import models.Evento;
import models.SubEvento;

import facade.Facade;

public class MenuAdmAdicionarSessaoController extends MenuAdmGerSessaoController {

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
        String nomeSessao = nomeSessaoField.getText();
        String localSessao = localSessaoField.getText();
        String horarioSessao = horarioSessaoField.getText();
        String eventoRelacionadoStr = eventoRelacionadoField.getText();
        String subEventoRelacionadoStr = subEventoRelacionadoField.getText();

        Long idEventoRelacionado = Long.parseLong(eventoRelacionadoStr);
        Long idSubEventoRelacionado = Long.parseLong(subEventoRelacionadoStr);

        Evento evento = facade.buscarEvento(idEventoRelacionado);
        SubEvento subEvento = facade.buscarSubEvento(idSubEventoRelacionado);

        Secao novaSecao = new Secao(evento, subEvento, nomeSessao, localSessao, horarioSessao);

        facade.adicionarSecao(novaSecao);

        nomeSessaoField.clear();
        localSessaoField.clear();
        horarioSessaoField.clear();
        eventoRelacionadoField.clear();
        subEventoRelacionadoField.clear();
    }
}
