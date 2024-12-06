package screenscontrollers;

import controllers.SubeventoController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MenuAdmDeletarSubEventoController extends MenuAdmGerenciarSubEventoController {
    private ControllersFactory controllerFactory = new DefaultControllersFactory();


    @FXML
    private Button botaoDeletar;

    @FXML
    private TextField nomeSubEventoField;

    @FXML
    void deletarSubEvento() {
        // Obtendo o valor do campo de texto
        String nomeSubEventoStr = nomeSubEventoField.getText();

        // Verificar se o campo de texto contém um valor numérico válido
        int idSubEvento;
        try {
            // Converter o valor do campo de texto em inteiro
            idSubEvento = Integer.parseInt(nomeSubEventoStr);
        } catch (NumberFormatException e) {
            // Exibir mensagem de erro caso o valor não seja um número válido
            System.out.println("O campo 'nome do subevento' deve conter um número válido.");
            return;
        }

        // Implementar lógica para deletar o subevento usando o ID
        SubeventoController subeventoController = controllerFactory.createSubeventoController();
        subeventoController.deletar(idSubEvento);

        // Limpar o campo de texto após a operação
        nomeSubEventoField.clear();

        // Exibir mensagem de sucesso ou feedback
        System.out.println("Subevento deletado com sucesso!");
    }

}
