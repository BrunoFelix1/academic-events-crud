package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Trilha;

import controllers.SecaoController;
import controllers.TrilhaController;
import models.Secao;

public class MenuAdmAdicionarTrilhaController extends MenuAdmGerenciarTrilhaController {

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

    private TrilhaController trilhaController = new TrilhaController();
    private SecaoController secaoController = new SecaoController(); // Instanciar SecaoController para buscar Secao relacionada

    @FXML
    void salvarTrilha() {
        try {
            String nomeTrilha = nomeTrilhaField.getText();
            String sessaoRelacionadaStr = sessaoRelacionadaField.getText();

            // Validar campos
            if (nomeTrilha.isEmpty() || sessaoRelacionadaStr.isEmpty()) {
                // ...mensagem de erro...
                return;
            }

            // Converter strings para tipos apropriados
            Long sessaoId = Long.parseLong(sessaoRelacionadaStr);

            // Buscar a Secao relacionada
            Secao secao = secaoController.buscarSecaoPorId(sessaoId);

            // Criar objeto Trilha sem definir o ID manualmente
            Trilha novaTrilha = new Trilha(secao, nomeTrilha);

            // Utilizar o TrilhaController para adicionar a nova trilha
            trilhaController.adicionarTrilha(novaTrilha);

            // Limpar os campos após salvar
            nomeTrilhaField.clear();
            sessaoRelacionadaField.clear();

            // ...mensagem de sucesso...
        } catch (Exception e) {
            // ...tratamento de erro...
            System.out.println(e.getMessage());
        }
    }
}