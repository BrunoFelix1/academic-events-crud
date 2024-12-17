package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import controllers.SecaoController;

public class MenuAdmDeletarSessaoController extends MenuAdmGerSessaoController {

    @FXML
    private Button botaoDeletar;

    @FXML
    private TextField idSessaoField;

    private SecaoController secaoController = new SecaoController();

    @FXML
    void deletarSessao() {
        try {
            String idSessaoStr = idSessaoField.getText();

            // Validar campo
            if (idSessaoStr.isEmpty()) {
                // ...mensagem de erro...
                System.out.println("Por favor, insira o ID da sessão.");
                return;
            }

            // Converter string para tipo apropriado
            Long idSessao = Long.parseLong(idSessaoStr);

            // Deletar a sessão
            secaoController.deletarSecao(idSessao);

            // Limpar o campo após deletar
            idSessaoField.clear();

            // ...mensagem de sucesso...
            System.out.println("Sessão deletada com sucesso!");
        } catch (NumberFormatException e) {
            // ...tratamento de erro para conversão de números...
            System.out.println("Erro na conversão do ID. Certifique-se de que o ID é válido.");
        } catch (Exception e) {
            // ...tratamento de erro genérico...
            System.out.println("Erro ao deletar a sessão: " + e.getMessage());
        }
    }
}
