package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Secao;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import services.SecaoService;

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

    @Autowired
    private SecaoService secaoService;

    @FXML
    void salvarSessao() {
        String nomeSessao = nomeSessaoField.getText();
        String localSessao = localSessaoField.getText();
        String horarioSessaoStr = horarioSessaoField.getText();
        String eventoRelacionadoStr = eventoRelacionadoField.getText();
        String subEventoRelacionadoStr = subEventoRelacionadoField.getText();

        Long idEventoRelacionado = Long.parseLong(eventoRelacionadoStr);
        Long idSubEventoRelacionado = Long.parseLong(subEventoRelacionadoStr);
        LocalDateTime horarioSessao = LocalDateTime.parse(horarioSessaoStr);

        Secao novaSecao = new Secao();
        novaSecao.setNome(nomeSessao);


        secaoService.adicionarSecao(novaSecao);

        nomeSessaoField.clear();
        localSessaoField.clear();
        horarioSessaoField.clear();
        eventoRelacionadoField.clear();
        subEventoRelacionadoField.clear();
    }
}
