package screenscontrollers;

import facade.Facade;
import interfaces.IControladorTelas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Evento;
import models.SubEvento;

public class MenuAdmAtualizarSubEventoTela1Controller extends MenuAdmGerenciarSubEventoController implements IControladorTelas {

    @FXML
    private Button botaoSalvarAlteracoes;

    @FXML
    private TextField nomeAtualSubEventoField;

    @FXML
    private TextField nomeSubEventoField;

    @FXML
    private TextField localSubEventoField;

    @FXML
    private TextField horarioSubEventoField;

    @FXML
    private TextField descricaoSubEventoField;

    @FXML
    private TextField eventoAssociadoField;

    private Facade facade = new Facade();

    @FXML
    void salvarAlteracoes() {
        try {
            String nomeAtualSubEvento = nomeAtualSubEventoField.getText();
            String novoNomeSubEvento = nomeSubEventoField.getText();
            String localAtualizado = localSubEventoField.getText();
            String horarioAtualizado = horarioSubEventoField.getText();
            String descricaoAtualizada = descricaoSubEventoField.getText();
            String nomeEventoAssociado = eventoAssociadoField.getText();

            // Validar campos
            if (nomeAtualSubEvento.isEmpty() || novoNomeSubEvento.isEmpty() || localAtualizado.isEmpty() || horarioAtualizado.isEmpty() || descricaoAtualizada.isEmpty() || nomeEventoAssociado.isEmpty()) {
                exibirAlerta("Por favor, preencha todos os campos.");
                return;
            }

            // Buscar o subevento pelo nome atual
            SubEvento subEventoParaAtualizar = facade.buscarSubEventoPorNome(nomeAtualSubEvento);

            // Verificar se o subevento foi encontrado
            if (subEventoParaAtualizar == null) {
                exibirAlerta("Subevento não encontrado.");
                return;
            }

            // Buscar o evento associado pelo nome
            Evento eventoAssociado = facade.buscarEventoPorNome(nomeEventoAssociado);
            if (eventoAssociado == null) {
                exibirAlerta("Evento associado não encontrado.");
                return;
            }

            // Atualizar os atributos do subevento
            subEventoParaAtualizar.setNome(novoNomeSubEvento);
            subEventoParaAtualizar.setLocal(localAtualizado);
            subEventoParaAtualizar.setHorario(horarioAtualizado);
            subEventoParaAtualizar.setDescricao(descricaoAtualizada);
            subEventoParaAtualizar.setEvento(eventoAssociado);

            // Utilizar a Facade para atualizar o subevento
            if (facade.atualizarSubEvento(subEventoParaAtualizar.getId(), subEventoParaAtualizar)) {
                exibirAlertaSucesso("Subevento atualizado com sucesso!");
                nomeAtualSubEventoField.clear();
                nomeSubEventoField.clear();
                localSubEventoField.clear();
                horarioSubEventoField.clear();
                descricaoSubEventoField.clear();
                eventoAssociadoField.clear();
            }
        } catch (RuntimeException e) {
            exibirAlerta(e.getMessage());
        }
    }
}