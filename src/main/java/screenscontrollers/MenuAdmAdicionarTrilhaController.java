package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Trilha;

import facade.Facade;
import models.Secao;
import interfaces.IControladorTelas;

public class MenuAdmAdicionarTrilhaController extends MenuAdmGerenciarTrilhaController implements IControladorTelas {

    @FXML
    private Button botaoSalvarTrilha;

    @FXML
    private TextField nomeTrilhaField;

    @FXML
    private TextField localTrilhaField;

    @FXML
    private TextField horarioTrilhaField;

    @FXML
    private TextField sessaoRelacionadaField;

    private Facade facade = new Facade();

    @FXML
    void salvarTrilha() {
        try {
            String nomeTrilha = nomeTrilhaField.getText();
            String sessaoRelacionadaStr = sessaoRelacionadaField.getText();

            // Validar campos
            if (nomeTrilha.isEmpty() || sessaoRelacionadaStr.isEmpty()) {
                exibirAlerta("Por favor, preencha todos os campos.");
                return;
            }

            // Converter strings para tipos apropriados
            Long sessaoId = Long.parseLong(sessaoRelacionadaStr);

            // Buscar a Secao relacionada
            Secao secao = facade.buscarSecao(sessaoId);

            // Criar objeto Trilha sem definir o ID manualmente
            Trilha novaTrilha = new Trilha(secao, nomeTrilha);

            // Utilizar a Facade para adicionar a nova trilha
            if (facade.adicionarTrilha(novaTrilha)) {
                exibirAlertaSucesso("Trilha adicionada com sucesso!");
                nomeTrilhaField.clear();
                sessaoRelacionadaField.clear();
            }
        } catch (RuntimeException e) {
            exibirAlerta(e.getMessage());
        }
    }
}