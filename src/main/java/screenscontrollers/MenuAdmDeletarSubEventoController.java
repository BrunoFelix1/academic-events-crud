package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import facade.Facade;

public class MenuAdmDeletarSubEventoController extends MenuAdmGerenciarSubEventoController {

    @FXML
    private Button botaoDeletar;

    @FXML
    private TextField idSubEventoField;

    private Facade facade = new Facade();

    @FXML
    void deletarSubEvento() {
        try {
            String idSubEventoStr = idSubEventoField.getText();

            // Validar campo
            if (idSubEventoStr.isEmpty()) {
                // ...mensagem de erro...
                System.out.println("Por favor, insira o ID do subevento.");
                return;
            }

            // Converter string para tipo apropriado
            Long idSubEvento = Long.parseLong(idSubEventoStr);

            // Deletar o subevento
            facade.deletarSubEvento(idSubEvento);

            // Limpar o campo após deletar
            idSubEventoField.clear();

            // ...mensagem de sucesso...
            System.out.println("Subevento deletado com sucesso!");
        } catch (NumberFormatException e) {
            // ...tratamento de erro para conversão de números...
            System.out.println("Erro na conversão do ID. Certifique-se de que o ID é válido.");
        } catch (Exception e) {
            // ...tratamento de erro genérico...
            System.out.println("Erro ao deletar o subevento: " + e.getMessage());
        }
    }

}
