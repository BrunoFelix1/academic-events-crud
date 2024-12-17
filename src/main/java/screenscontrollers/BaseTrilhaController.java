package screenscontrollers;

import java.io.IOException;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class BaseTrilhaController {

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

    protected void irParaTelaGerenciarTrilha(Stage stage) {
        trocarCena("/screens/MenuADM_ger_trilha.fxml", stage);
    }

    protected void irParaTelaAdicionarTrilha(Stage stage) {
        trocarCena("/screens/MenuADM_ger_trilha_addTrilha.fxml", stage);
    }

    protected void irParaTelaListarTrilhas(Stage stage) {
        trocarCena("/screens/MenuADM_ger_trilha_ListarTrilha.fxml", stage);
    }

    protected void irParaTelaAtualizarTrilha(Stage stage) {
        trocarCena("/screens/MenuADM_ger_trilha_AtualizarTrilha.fxml", stage);
    }

    protected void irParaTelaDeletarTrilha(Stage stage) {
        trocarCena("/screens/MenuADM_ger_trilha_DeletarTrilha.fxml", stage);
    }
}
