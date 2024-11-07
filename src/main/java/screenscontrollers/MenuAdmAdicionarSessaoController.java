package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Secao;
import persistence.PersistenceSecao;

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

    private PersistenceSecao persistence = new PersistenceSecao();

    @FXML
    void salvarSessao() {
        String nomeSessao = nomeSessaoField.getText();
        String localSessao = localSessaoField.getText();
        String horarioSessao = horarioSessaoField.getText();
        int idEventoRelacionado = Integer.parseInt(eventoRelacionadoField.getText());
        int idSubEventoRelacionado = Integer.parseInt(subEventoRelacionadoField.getText());
        int idSecao = persistence.getTodos().size()+1;

        Secao novaSecao = new Secao(idSecao, idEventoRelacionado, idSubEventoRelacionado, localSessao, horarioSessao, nomeSessao);

        persistence.add(novaSecao);


        nomeSessaoField.clear();
        localSessaoField.clear();
        horarioSessaoField.clear();
        eventoRelacionadoField.clear();
        subEventoRelacionadoField.clear();
    }
}
