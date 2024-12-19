package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import facade.Facade;
import interfaces.IControladorTelas;
import models.Evento;
import models.Secao;
import models.SubEvento;


public class MenuAdmAtualizarSessaoController extends MenuAdmGerSessaoController implements IControladorTelas {

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

    private Facade facade = new Facade();

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
                exibirAlerta("Por favor, preencha todos os campos.");
                return;
            }

            // Converter strings para tipos apropriados
            Long idSessao = Long.parseLong(idSessaoStr);
            String horarioSessao = horarioSessaoStr;
            Long eventoId = Long.parseLong(eventoRelacionadoStr);
            Long subEventoId = Long.parseLong(subEventoRelacionadoStr);

            // Buscar o Evento e SubEvento relacionados
            Evento evento = facade.buscarEvento(eventoId);
            SubEvento subEvento = facade.buscarSubEvento(subEventoId);

            // Buscar a Secao existente
            Secao secao = facade.buscarSecao(idSessao);

            // Atualizar os atributos da Secao
            secao.setNome(nomeSessao);
            secao.setLocal(localSessao);
            secao.setHorario(horarioSessao);
            secao.setEvento(evento);
            secao.setSubEvento(subEvento);

            // Utilizar a Facade para atualizar a sessão
            if (facade.atualizarSecao(secao.getId(), secao)) {
                exibirAlertaSucesso("Sessão atualizada com sucesso!");
                idSessaoField.clear();
                nomeSessaoField.clear();
                localSessaoField.clear();
                horarioSessaoField.clear();
                eventoRelacionadoField.clear();
                subEventoRelacionadoField.clear();
            }
        } catch (NumberFormatException e) {
            exibirAlerta("Erro na conversão de IDs. Certifique-se de que todos os IDs são válidos.");
        } catch (RuntimeException e) {
            exibirAlerta(e.getMessage());
        }
    }
}
