package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Trilha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import services.TrilhaService;
import services.SecaoService;
import models.Secao;

@Controller
public class MenuAdmAtualizarTrilhaController extends MenuAdmGerenciarTrilhaController {
    @Autowired
    private TrilhaService trilhaService;

    @Autowired
    private SecaoService secaoService;

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
            Secao secao = secaoService.buscarSecaoPorId(sessaoId);

            // Buscar a Trilha existente
            Trilha trilha = trilhaService.buscarTrilhaPorId(idTrilha);

            // Atualizar os atributos da Trilha
            trilha.setNome(nomeTrilha);
            trilha.setSecao(secao);

            // Utilizar o TrilhaService para atualizar a trilha
            trilhaService.atualizarTrilha(idTrilha, trilha);

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
