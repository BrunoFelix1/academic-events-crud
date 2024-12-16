package services;

import org.springframework.beans.factory.annotation.Autowired;
import repositories.SubEventoDAO;
import org.springframework.stereotype.Service;

import models.SubEvento;

import java.util.List;

@Service
public class SubEventoService {

    @Autowired
    private SubEventoDAO subEventoRepository;

    // Adicionar SubEvento
    public SubEvento adicionarSubEvento(SubEvento subEvento) {
        return subEventoRepository.save(subEvento);
    }

    // Atualizar SubEvento
    public SubEvento atualizarSubEvento(Long id, SubEvento subEventoAtualizado) {
        SubEvento subEvento = subEventoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("SubEvento não encontrado"));

        subEvento.setNome(subEventoAtualizado.getNome());
        subEvento.setDescricao(subEventoAtualizado.getDescricao());
        subEvento.setData(subEventoAtualizado.getData());

        return subEventoRepository.save(subEvento);
    }

    // Deletar SubEvento
    public void deletarSubEvento(Long id) {
        if (!subEventoRepository.existsById(id)) {
            throw new RuntimeException("SubEvento não encontrado");
        }
        subEventoRepository.deleteById(id);
    }

    // Listar todos os SubEventos
    public List<SubEvento> listarTodosSubEventos() {
        return subEventoRepository.findAll();
    }

    // Buscar SubEvento por ID
    public SubEvento buscarSubEventoPorId(Long id) {
        return subEventoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("SubEvento não encontrado"));
    }
}


