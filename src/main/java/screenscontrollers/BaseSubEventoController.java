package screenscontrollers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class BaseSubEventoController {

    protected void trocarCena(String caminhoFXML, Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void voltarParaMenuADM(Stage stage) {
        trocarCena("/screens/MenuADM.fxml", stage);
    }

    protected void irParaTelaGerenciarSubEvento(Stage stage) {
        trocarCena("/screens/MenuADM_ger_subevento.fxml", stage);
    }

    protected void irParaTelaAdicionarSubEvento(Stage stage) {
        trocarCena("/screens/MenuADM_ger_subevento_adicionarsubevento.fxml", stage);
    }

    protected void irParaTelaListarSubEventos(Stage stage) {
        trocarCena("/screens/MenuADM_ger_subevento_ListarSubevento.fxml", stage);
    }

    protected void irParaTelaAtualizarSubEventoTela1(Stage stage) {
        trocarCena("/screens/MenuADM_ger_subevento_atualizar_tela1.fxml", stage);
    }

    protected void irParaTelaAtualizarSubEventoTela2(Stage stage) {
        trocarCena("/screens/MenuADM_ger_subevento_atualizar_tela2.fxml", stage);
    }

    protected void irParaTelaDeletarSubEvento(Stage stage) {
        trocarCena("/screens/MenuADM_ger_subevento_deletar.fxml", stage);
    }
}
