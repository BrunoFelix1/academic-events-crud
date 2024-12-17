package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import controllers.TrilhaController;

public class MenuAdmDeletarTrilhaController extends MenuAdmGerenciarTrilhaController {
    @FXML
    private Button botaoDeletar;

    @FXML
    private TextField idTrilhaField;

    private TrilhaController trilhaController = new TrilhaController();

    @FXML
    void deletarTrilha() {
        try {
            String idTrilhaStr = idTrilhaField.getText();

            // Validar campo
            if (idTrilhaStr.isEmpty()) {
                // ...mensagem de erro...
                System.out.println("Por favor, insira o ID da trilha.");
                return;
            }

            // Converter string para tipo apropriado
            Long idTrilha = Long.parseLong(idTrilhaStr);

            // Deletar a trilha
            trilhaController.deletarTrilha(idTrilha);

            // Limpar o campo após deletar
            idTrilhaField.clear();

            // ...mensagem de sucesso...
            System.out.println("Trilha deletada com sucesso!");
        } catch (NumberFormatException e) {
            // ...tratamento de erro para conversão de números...
            System.out.println("Erro na conversão do ID. Certifique-se de que o ID é válido.");
        } catch (Exception e) {
            // ...tratamento de erro genérico...
            System.out.println("Erro ao deletar a trilha: " + e.getMessage());
        }
    }
}
