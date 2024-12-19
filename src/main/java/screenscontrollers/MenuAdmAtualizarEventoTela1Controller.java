package screenscontrollers;

import facade.Facade;
import interfaces.IControladorTelas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Evento;

public class MenuAdmAtualizarEventoTela1Controller extends MenuAdmGerEventController implements IControladorTelas {

    @FXML
    private Button botaoProximo;

    @FXML
    private Button botaoSalvarAlteracoes;

    @FXML
    private TextField nomeAtualEventoField;

    @FXML
    private TextField nomeEventoField;

    @FXML
    private TextField localEventoField;

    @FXML
    private TextField horarioEventoField;

    @FXML
    private TextField descricaoEventoField;

    private Facade facade = new Facade();

    @FXML
    void salvarAlteracoes() {
        try {
            String nomeAtualEvento = nomeAtualEventoField.getText();
            String novoNomeEvento = nomeEventoField.getText();
            String localAtualizado = localEventoField.getText();
            String horarioAtualizado = horarioEventoField.getText();
            String descricaoAtualizada = descricaoEventoField.getText();

            // Validar campos
            if (nomeAtualEvento.isEmpty() || novoNomeEvento.isEmpty() || localAtualizado.isEmpty() || horarioAtualizado.isEmpty() || descricaoAtualizada.isEmpty()) {
                exibirAlerta("Por favor, preencha todos os campos.");
                return;
            }

            // Buscar o evento pelo nome atual
            Evento eventoParaAtualizar = facade.buscarEventoPorNome(nomeAtualEvento);

            // Verificar se o evento foi encontrado
            if (eventoParaAtualizar == null) {
                exibirAlerta("Evento não encontrado.");
                return;
            }

            // Atualizar os atributos do evento
            eventoParaAtualizar.setTitulo(novoNomeEvento);
            eventoParaAtualizar.setLocal(localAtualizado);
            eventoParaAtualizar.setHorario(horarioAtualizado);
            eventoParaAtualizar.setDescricao(descricaoAtualizada);

            // Utilizar a Facade para atualizar o evento
            if (facade.atualizarEvento(eventoParaAtualizar.getId(), eventoParaAtualizar)) {
                exibirAlertaSucesso("Evento atualizado com sucesso!");
                nomeAtualEventoField.clear();
                nomeEventoField.clear();
                localEventoField.clear();
                horarioEventoField.clear();
                descricaoEventoField.clear();
            }
        } catch (RuntimeException e) {
            exibirAlerta(e.getMessage());
        }
    }
}