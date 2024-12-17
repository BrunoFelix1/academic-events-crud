package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import controllers.EventoController;
import controllers.SecaoController;
import controllers.SubEventoController;
import models.Evento;
import models.Secao;
import models.SubEvento;

import java.time.LocalDateTime;

public class MenuAdmAtualizarSessaoController extends MenuAdmGerSessaoController {

    @FXML
    private Button botaoSalvarAlteracoes;

    @FXML
    private TextField idSessaoField;

    @FXML
    private TextField nomeSessaoField;

    @FXML
    private TextField localSessaoField;

    @FXML
    private TextField horarioSessaoField;

    @FXML
    private TextField eventoRelacionadoField;

    @FXML
    private TextField subEventoRelacionadoField;

    private SecaoController secaoController = new SecaoController();
    private EventoController eventoController = new EventoController(); // Instanciar EventoController para buscar Evento relacionado
    private SubEventoController subEventoController = new SubEventoController(); // Instanciar SubEventoController para buscar SubEvento relacionado

    @FXML
    void salvarAlteracoes() {
        try {
            String idSessaoStr = idSessaoField.getText();
            String nomeSessao = nomeSessaoField.getText();
            String localSessao = localSessaoField.getText();
            String horarioSessaoStr = horarioSessaoField.getText();
            String eventoRelacionadoStr = eventoRelacionadoField.getText();
            String subEventoRelacionadoStr = subEventoRelacionadoField.getText();

            // Validar campos
            if (idSessaoStr.isEmpty() || nomeSessao.isEmpty() || localSessao.isEmpty()
                || horarioSessaoStr.isEmpty() || eventoRelacionadoStr.isEmpty()
                || subEventoRelacionadoStr.isEmpty()) {
                // ...mensagem de erro...
                System.out.println("Por favor, preencha todos os campos.");
                return;
            }

            // Converter strings para tipos apropriados
            Long idSessao = Long.parseLong(idSessaoStr);
            LocalDateTime horarioSessao = LocalDateTime.parse(horarioSessaoStr);
            Long eventoId = Long.parseLong(eventoRelacionadoStr);
            Long subEventoId = Long.parseLong(subEventoRelacionadoStr);

            // Buscar o Evento e SubEvento relacionados
            Evento evento = eventoController.buscarEventoPorId(eventoId);
            SubEvento subEvento = subEventoController.buscarSubEventoPorId(subEventoId);

            // Buscar a Secao existente
            Secao secao = secaoController.buscarSecaoPorId(idSessao);

            // Atualizar os atributos da Secao
            secao.setNome(nomeSessao);
            secao.setLocal(localSessao);
            secao.setHorario(horarioSessao);
            secao.setEvento(evento);
            secao.setSubEvento(subEvento);

            // Utilizar o SecaoController para atualizar a sessão
            secaoController.atualizarSecao(secao.getId(), secao);

            // Limpar os campos após salvar
            idSessaoField.clear();
            nomeSessaoField.clear();
            localSessaoField.clear();
            horarioSessaoField.clear();
            eventoRelacionadoField.clear();
            subEventoRelacionadoField.clear();

            // ...mensagem de sucesso...
            System.out.println("Sessão atualizada com sucesso!");
        } catch (NumberFormatException e) {
            // ...tratamento de erro para conversão de números...
            System.out.println("Erro na conversão de IDs. Certifique-se de que todos os IDs são válidos.");
        } catch (Exception e) {
            // ...tratamento de erro genérico...
            System.out.println("Erro ao atualizar a sessão: " + e.getMessage());
        }
    }
}
