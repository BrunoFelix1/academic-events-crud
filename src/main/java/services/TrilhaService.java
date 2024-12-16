package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.TrilhaDAO;
import models.Trilha;

import java.util.List;

@Service
public class TrilhaService {

    @Autowired
    private TrilhaDAO trilhaRepository;

    // Adicionar Trilha
    public Trilha adicionarTrilha(Trilha trilha) {
        return trilhaRepository.save(trilha);
    }

    // Atualizar Trilha
    public Trilha atualizarTrilha(Long id, Trilha trilhaAtualizada) {
        Trilha trilha = trilhaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Trilha não encontrada"));

        trilha.setNome(trilhaAtualizada.getNome());
        trilha.setSecao(trilhaAtualizada.getSecao());

        return trilhaRepository.save(trilha);
    }

    // Deletar Trilha
    public void deletarTrilha(Long id) {
        if (!trilhaRepository.existsById(id)) {
            throw new RuntimeException("Trilha não encontrada");
        }
        trilhaRepository.deleteById(id);
    }

    // Listar todas as Trilhas
    public List<Trilha> listarTodasTrilhas() {
        return trilhaRepository.findAll();
    }

    // Buscar Trilha por ID
    public Trilha buscarTrilhaPorId(Long id) {
        return trilhaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Trilha não encontrada"));
    }
}


