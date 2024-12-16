package services;

import org.springframework.beans.factory.annotation.Autowired;
import repositories.InscricaoDAO;
import org.springframework.stereotype.Service;

import models.Inscricao;
import models.InscricaoId;

import java.util.List;

@Service
public class InscricaoService {

    @Autowired
    private InscricaoDAO inscricaoRepository;

    // Adicionar Inscricao
    public Inscricao adicionarInscricao(Inscricao inscricao) {
        return inscricaoRepository.save(inscricao);
    }

    // Atualizar Inscricao
    public Inscricao atualizarInscricao(InscricaoId id, Inscricao inscricaoAtualizada) {
        Inscricao inscricao = inscricaoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Inscricao não encontrada"));

        inscricao.setUsuario(inscricaoAtualizada.getUsuario());
        inscricao.setEvento(inscricaoAtualizada.getEvento());
        inscricao.setSubEvento(inscricaoAtualizada.getSubEvento());
        inscricao.setSecao(inscricaoAtualizada.getSecao());
        inscricao.setTrilha(inscricaoAtualizada.getTrilha());

        return inscricaoRepository.save(inscricao);
    }

    // Deletar Inscricao
    public void deletarInscricao(InscricaoId id) {
        if (!inscricaoRepository.existsById(id)) {
            throw new RuntimeException("Inscricao não encontrada");
        }
        inscricaoRepository.deleteById(id);
    }

    // Listar todas as Inscricoes
    public List<Inscricao> listarTodasInscricoes() {
        return inscricaoRepository.findAll();
    }

    // Buscar Inscricao por ID
    public Inscricao buscarInscricaoPorId(InscricaoId id) {
        return inscricaoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Inscricao não encontrada"));
    }
}
