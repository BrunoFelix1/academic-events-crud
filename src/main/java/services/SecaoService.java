package services;

import org.springframework.beans.factory.annotation.Autowired;
import repositories.SecaoDAO;
import org.springframework.stereotype.Service;

import models.Secao;

import java.util.List;

@Service
public class SecaoService {

    @Autowired
    private SecaoDAO secaoRepository;

    // Adicionar Secao
    public Secao adicionarSecao(Secao secao) {
        return secaoRepository.save(secao);
    }

    // Atualizar Secao
    public Secao atualizarSecao(Long id, Secao secaoAtualizada) {
        Secao secao = secaoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Secao não encontrada"));

        secao.setNome(secaoAtualizada.getNome());
        secao.setLocal(secaoAtualizada.getLocal());
        secao.setHorario(secaoAtualizada.getHorario());
        secao.setEvento(secaoAtualizada.getEvento());
        secao.setSubEvento(secaoAtualizada.getSubEvento());

        return secaoRepository.save(secao);
    }

    // Deletar Secao
    public void deletarSecao(Long id) {
        if (!secaoRepository.existsById(id)) {
            throw new RuntimeException("Secao não encontrada");
        }
        secaoRepository.deleteById(id);
    }

    // Listar todas as Secoes
    public List<Secao> listarTodasSecoes() {
        return secaoRepository.findAll();
    }

    // Buscar Secao por ID
    public Secao buscarSecaoPorId(Long id) {
        return secaoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Secao não encontrada"));
    }
}