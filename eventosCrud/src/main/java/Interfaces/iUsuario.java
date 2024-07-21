package Interfaces;

import Models.*;

import java.util.List;

public interface iUsuario {
     boolean Logar(String login, String senha);
     void Cadastrar(Usuario usuario);
     List<Evento> ListarEventosDisponiveis();
     void ParticiparDeEvento(Usuario usuario, Evento evento);
     void ParticiparDeSubEvento(Usuario usuario, SubEvento subevento);
     void ParticiparDeTrilha(Usuario usuario, Trilha trilha);
     List<Inscricao> ListarInscricoes(Usuario usuario);
     boolean CancelarInscricaoTrilha(Usuario usuario, Trilha trilha);
     Certificado EmitirCertificadoTrilha(Usuario usuario, Trilha trilha);
}
