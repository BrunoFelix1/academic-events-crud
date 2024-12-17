package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Trilha;

import facade.Facade;
import models.Secao;

public class MenuAdmAtualizarTrilhaController extends MenuAdmGerenciarTrilhaController {

    private Facade facade = new Facade();

    @FXML
    private Button botaoSalvarAlteracoes;

    @FXML
    private TextField idTrilhaField;

    @FXML
    private TextField nomeTrilhaField;

    @FXML
    private TextField localTrilhaField;

    @FXML
    private TextField horarioTrilhaField;

    @FXML
    private TextField sessaoRelacionadaField;

    @FXML
    void salvarAlteracoes() {
        try {
            String idTrilhaStr = idTrilhaField.getText();
            String nomeTrilha = nomeTrilhaField.getText();
            String sessaoRelacionadaStr = sessaoRelacionadaField.getText();

            // Validar campos
            if (idTrilhaStr.isEmpty() || nomeTrilha.isEmpty() || sessaoRelacionadaStr.isEmpty()) {
                // ...mensagem de erro...
                System.out.println("Por favor, preencha todos os campos.");
                return;
            }

            // Converter strings para tipos apropriados
            Long idTrilha = Long.parseLong(idTrilhaStr);
            Long sessaoId = Long.parseLong(sessaoRelacionadaStr);

            // Buscar a Secao relacionada
            Secao secao = facade.buscarSecao(sessaoId);

            // Buscar a Trilha existente
            Trilha trilha = facade.buscarTrilha(idTrilha);

            // Atualizar os atributos da Trilha
            trilha.setNome(nomeTrilha);
            trilha.setSecao(secao);

            // Utilizar a Facade para atualizar a trilha
            facade.atualizarTrilha(idTrilha, trilha);

            // Limpar os campos após salvar
            idTrilhaField.clear();
            nomeTrilhaField.clear();
            sessaoRelacionadaField.clear();

            // ...mensagem de sucesso...
            System.out.println("Trilha atualizada com sucesso!");
        } catch (NumberFormatException e) {
            // ...tratamento de erro para conversão de números...
            System.out.println("Erro na conversão de IDs. Certifique-se de que todos os IDs são válidos.");
        } catch (Exception e) {
            // ...tratamento de erro genérico...
            System.out.println("Erro ao atualizar a trilha: " + e.getMessage());
        }
    }
}
