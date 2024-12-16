package services;

import org.springframework.beans.factory.annotation.Autowired;
import repositories.AtividadeDAO;
import org.springframework.stereotype.Service;

import models.Atividade;

import java.util.List;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeDAO atividadeRepository;

    // Adicionar Atividade
    public Atividade adicionarAtividade(Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    // Atualizar Atividade
    public Atividade atualizarAtividade(Long id, Atividade atividadeAtualizada) {
        Atividade atividade = atividadeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Atividade não encontrada"));

        atividade.setTipoDeAtividade(atividadeAtualizada.getTipoDeAtividade());
        atividade.setResumo(atividadeAtualizada.getResumo());

        return atividadeRepository.save(atividade);
    }

    // Deletar Atividade
    public void deletarAtividade(Long id) {
        if (!atividadeRepository.existsById(id)) {
            throw new RuntimeException("Atividade não encontrada");
        }
        atividadeRepository.deleteById(id);
    }

    // Listar todas as Atividades
    public List<Atividade> listarTodasAtividades() {
        return atividadeRepository.findAll();
    }

    // Buscar Atividade por ID
    public Atividade buscarAtividadePorId(Long id) {
        return atividadeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Atividade não encontrada"));
    }
}


