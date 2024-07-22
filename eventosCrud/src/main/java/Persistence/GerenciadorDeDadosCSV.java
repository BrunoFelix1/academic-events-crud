package Persistence;
import java.util.ArrayList;

import Enum.TipoDeAtividade;
import Enum.TipoDeUsuario;
import Models.*;

public class GerenciadorDeDadosCSV {
    //Caminho dos arquivos para armazenamento dos dados
    private String pathAtividade = "C:\\Users\\PC TESTE\\Desktop\\Docs para P2\\Atividades.txt";
    private String pathEvento = "C:\\Users\\PC TESTE\\Desktop\\Docs para P2\\Eventos.txt";
    private String pathInscricao = "C:\\Users\\PC TESTE\\Desktop\\Docs para P2\\Inscrições.txt";
    private String pathSecao = "C:\\Users\\PC TESTE\\Desktop\\Docs para P2\\Seções.txt";
    private String pathSubEvento = "C:\\Users\\PC TESTE\\Desktop\\Docs para P2\\SubEventos.txt";
    private String pathTrilha = "C:\\Users\\PC TESTE\\Desktop\\Docs para P2\\Trilhas.txt";
    private String pathUsuario = "C:\\Users\\PC TESTE\\Desktop\\Docs para P2\\Usuarios.txt";

    ManipuladorArquivos manipulador = new ManipuladorArquivos();

    //Método toCSV retorna uma linha no formato que está na tabela
    private String ToCSV(Atividade atividade){
        String linha = atividade.getId() + "," + atividade.getTipoSubmissao()+ "," + atividade.getAutor() + "," +
        atividade.getResumo()+ "," + atividade.getIdTrilha();
        return linha;
    }

    private String ToCSV(Evento evento){
        String linha = evento.getId() + "," + evento.getTitulo()+ "," + evento.getHorario() + "," +
        evento.getLocal()+ "," + evento.getDescricao();
        return linha;
    }

    private String ToCSV(Inscricao inscricao){
        String linha = inscricao.getIdUsuario() + "," + inscricao.getIdEvento()+ "," + inscricao.getIdSubEvento() + "," +
        inscricao.getIdSecao()+ "," + inscricao.getIdTrilha();
        return linha;
    }

    private String ToCSV(Secao secao){
        String linha = secao.getId() + "," + secao.getId_evento()+ "," + secao.getId_subEvento() + "," +
        secao.getLocal()+ "," + secao.getHorario();
        return linha;
    }

    private String ToCSV(SubEvento subEvento){
        String linha = subEvento.getId() + "," + subEvento.getIdEvento()+ "," + subEvento.getTitulo() + "," +
        subEvento.getLocal()+ "," + subEvento.getHorario() + "," + subEvento.getDescricao();
        return linha;
    }

    private String ToCSV(Trilha trilha){
        String linha = trilha.getId() + "," + trilha.getIdSecao()+ "," + trilha.getNome();
        return linha;
    }

    private String ToCSV(Usuario usuario){
        String linha = usuario.getId() + "," + usuario.getCPF()+ "," + usuario.getNome() + "," +
        usuario.getIdade()+ "," + usuario.getInstituicao()+ "," +usuario.getTipoDeUsuario();
        return linha;
    }

    //Métodos que retornam uma lista dos tipos dos arquivos
    public ArrayList<Atividade> getTodasAtividades() {
        Atividade atividadeDaVez = new Atividade();
        String linha;
        ArrayList<Atividade> atividades = new ArrayList<>();
        manipulador.abrirArquivoParaLeitura();
        while ((linha = manipulador.lerLinhaArquivo()) != null){
            //Desconsiderando cabeçalho
            if (linha.contains("id")){
                continue;
            }
            String dados [] = linha.split(",");
            atividadeDaVez.setId(Integer.parseInt(dados[0]));
            atividadeDaVez.setTipoSubmissao(TipoDeAtividade.valueOf(dados[1]));
            atividadeDaVez.setAutor(dados[2]);
            atividadeDaVez.setResumo(dados[3]);
            atividadeDaVez.setIdTrilha(Integer.parseInt(dados[4]));
            atividades.add(atividadeDaVez);
        }
        manipulador.fecharArquivoParaLeitura();
        return atividades;
    }

    public ArrayList<Evento> getTodosEventos() {
        Evento eventoDaVez = new Evento();
        String linha;
        ArrayList<Evento> eventos = new ArrayList<>();
        manipulador.abrirArquivoParaLeitura();
        while ((linha = manipulador.lerLinhaArquivo()) != null){
            //Desconsiderando cabeçalho
            if (linha.contains("id")){
                continue;
            }
            String dados [] = linha.split(",");
            eventoDaVez.setId(Integer.parseInt(dados[0]));
            eventoDaVez.setTitulo(dados[1]);
            eventoDaVez.setLocal(dados[2]);
            eventoDaVez.setHorario(dados[3]);
            eventoDaVez.setDescricao(dados[4]);
            eventos.add(eventoDaVez);
        }
        manipulador.fecharArquivoParaLeitura();
        return eventos;
    }

    public ArrayList<Inscricao> getTodasInscricoes() {
        Inscricao inscricaoDaVez = new Inscricao();
        String linha;
        ArrayList<Inscricao> inscricoes = new ArrayList<>();
        manipulador.abrirArquivoParaLeitura();
        while ((linha = manipulador.lerLinhaArquivo()) != null){
            //Desconsiderando cabeçalho
            if (linha.contains("id")){
                continue;
            }
            String dados [] = linha.split(",");
            inscricaoDaVez.setIdUsuario(Integer.parseInt(dados[0]));
            inscricaoDaVez.setIdEvento(Integer.parseInt(dados[1]));
            inscricaoDaVez.setIdSubEvento(Integer.parseInt(dados[2]));
            inscricaoDaVez.setIdSecao(Integer.parseInt(dados[3]));
            inscricaoDaVez.setIdTrilha(Integer.parseInt(dados[4]));
            inscricoes.add(inscricaoDaVez);
        }
        manipulador.fecharArquivoParaLeitura();
        return inscricoes;
    }

    public ArrayList<Secao> getTodasSecoes() {
        Secao secaoDaVez = new Secao();
        String linha;
        ArrayList<Secao> secoes = new ArrayList<>();
        manipulador.abrirArquivoParaLeitura();
        while ((linha = manipulador.lerLinhaArquivo()) != null){
            //Desconsiderando cabeçalho
            if (linha.contains("id")){
                continue;
            }
            String dados [] = linha.split(",");
            secaoDaVez.setId(Integer.parseInt(dados[0]));
            secaoDaVez.setId_evento(Integer.parseInt(dados[1]));
            secaoDaVez.setId_subEvento(Integer.parseInt(dados[2]));
            secaoDaVez.setLocal(dados[3]);
            secaoDaVez.setHorario(dados[4]);
            secoes.add(secaoDaVez);
        }
        manipulador.fecharArquivoParaLeitura();
        return secoes;
    }

    public ArrayList<SubEvento> getTodosSubEventos() {
        SubEvento subEventoDaVez = new SubEvento();
        String linha;
        ArrayList<SubEvento> subEventos = new ArrayList<>();
        manipulador.abrirArquivoParaLeitura();
        while ((linha = manipulador.lerLinhaArquivo()) != null){
            //Desconsiderando cabeçalho
            if (linha.contains("id")){
                continue;
            }
            String dados [] = linha.split(",");
            subEventoDaVez.setId(Integer.parseInt(dados[0]));
            subEventoDaVez.setIdEvento(Integer.parseInt(dados[1]));
            subEventoDaVez.setTitulo(dados[2]);
            subEventoDaVez.setLocal(dados[3]);
            subEventoDaVez.setHorario(dados[4]);
            subEventoDaVez.setDescricao(dados[5]);
            subEventos.add(subEventoDaVez);
        }
        manipulador.fecharArquivoParaLeitura();
        return subEventos;
    }

    public ArrayList<Trilha> getTodasTrilhas() {
        Trilha trilhaDaVez = new Trilha();
        String linha;
        ArrayList<Trilha> trilhas = new ArrayList<>();
        manipulador.abrirArquivoParaLeitura();
        while ((linha = manipulador.lerLinhaArquivo()) != null){
            //Desconsiderando cabeçalho
            if (linha.contains("id")){
                continue;
            }
            String dados [] = linha.split(",");
            trilhaDaVez.setId(Integer.parseInt(dados[0]));
            trilhaDaVez.setIdSecao(Integer.parseInt(dados[1]));
            trilhaDaVez.setNome(dados[2]);
            trilhas.add(trilhaDaVez);
        }
        manipulador.fecharArquivoParaLeitura();
        return trilhas;
    }

    public ArrayList<Usuario> getTodosUsuarios() {
        Usuario usuarioDaVez = new Usuario();
        String linha;
        ArrayList<Usuario> usuarios = new ArrayList<>();
        manipulador.abrirArquivoParaLeitura();
        while ((linha = manipulador.lerLinhaArquivo()) != null){
            //Desconsiderando cabeçalho
            if (linha.contains("id")){
                continue;
            }
            String dados [] = linha.split(",");
            usuarioDaVez.setId(Integer.parseInt(dados[0]));
            usuarioDaVez.setCPF(dados[1]);
            usuarioDaVez.setNome(dados[2]);
            usuarioDaVez.setIdade(Integer.parseInt(dados[3]));
            usuarioDaVez.setInstituicao(dados[4]);
            usuarioDaVez.setTipoDeUsuario(TipoDeUsuario.valueOf(dados[5]));
            usuarios.add(usuarioDaVez);
        }
        manipulador.fecharArquivoParaLeitura();
        return usuarios;
    }

    //Métodos para adicionar as entidades em suas respectivas tabelas
    //Só precisa chamar o addNoCSV e passar como parâmetro o que quer adicionar

    public void addNoCSV(Atividade atividade) {
        manipulador.setPath(pathAtividade);
        String linha = ToCSV(atividade);
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivoPorUltimo(linha);
    }

    public void addNoCSV(Evento evento) {
        manipulador.setPath(pathEvento);
        String linha = ToCSV(evento);
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivoPorUltimo(linha);
    }

    public void addNoCSV(Inscricao inscricao) {
        manipulador.setPath(pathInscricao);
        String linha = ToCSV(inscricao);
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivoPorUltimo(linha);
    }

    public void addNoCSV(Secao secao) {
        manipulador.setPath(pathSecao);
        String linha = ToCSV(secao);
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivoPorUltimo(linha);
    }

    public void addNoCSV(SubEvento subEvento) {
        manipulador.setPath(pathSubEvento);
        String linha = ToCSV(subEvento);
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivoPorUltimo(linha);
    }

    public void addNoCSV(Trilha trilha) {
        manipulador.setPath(pathTrilha);
        String linha = ToCSV(trilha);
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivoPorUltimo(linha);
    }

    public void addNoCSV(Usuario usuario) {
        manipulador.setPath(pathUsuario);
        String linha = ToCSV(usuario);
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivoPorUltimo(linha);
    }

    //Métodos de Delete em suas respectivas tabelas
    public void deleteNoCSV (Atividade atividade) {
        manipulador.setPath(pathAtividade);
        ArrayList<Atividade> atividades = new ArrayList<>();
        atividades = getTodasAtividades();
        for (int i = 0; i < atividades.size(); i++){
            if (atividade.getId() == atividades.get(i).getId()){
                atividades.remove(i);
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivo("id,tipoDeAtividade,autor,resumo,id_trilha");
        for (Atividade u : atividades){
            manipulador.escreverNoArquivo(ToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public void deleteNoCSV (Evento evento) {
        manipulador.setPath(pathEvento);
        ArrayList<Evento> eventos = new ArrayList<>();
        eventos = getTodosEventos();
        for (int i = 0; i < eventos.size(); i++){
            if (evento.getId() == eventos.get(i).getId()){
                eventos.remove(i);
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivo("id,titulo,local,horario,descricao");
        for (Evento u : eventos){
            manipulador.escreverNoArquivo(ToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public void deleteNoCSV (Inscricao inscricao) {
        manipulador.setPath(pathInscricao);
        ArrayList<Inscricao> inscricoes = new ArrayList<>();
        inscricoes = getTodasInscricoes();
        for (int i = 0; i < inscricoes.size(); i++){
            if (inscricao.getIdEvento() == inscricoes.get(i).getIdEvento() &&
            inscricao.getIdUsuario() == inscricoes.get(i).getIdUsuario() &&
            inscricao.getIdSubEvento() == inscricoes.get(i).getIdSubEvento() &&
            inscricao.getIdSecao() == inscricoes.get(i).getIdSecao() &&
            inscricao.getIdTrilha() == inscricoes.get(i).getIdTrilha()){
                inscricoes.remove(i);
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivo("usuario_id,evento_id,subevento_id,secao_id,trilha_id");
        for (Inscricao u : inscricoes){
            manipulador.escreverNoArquivo(ToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public void deleteNoCSV (Secao secao) {
        manipulador.setPath(pathSecao);
        ArrayList<Secao> secoes = new ArrayList<>();
        secoes = getTodasSecoes();
        for (int i = 0; i < secoes.size(); i++){
            if (secao.getId() == secoes.get(i).getId()){
                secoes.remove(i);
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivo("id,evento_id,titulo,local,horario,descricao");
        for (Secao u : secoes){
            manipulador.escreverNoArquivo(ToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public void deleteNoCSV (SubEvento subEvento) {
        manipulador.setPath(pathSubEvento);
        ArrayList<SubEvento> subEventos = new ArrayList<>();
        subEventos = getTodosSubEventos();
        for (int i = 0; i < subEventos.size(); i++){
            if (subEvento.getId() == subEventos.get(i).getId()){
                subEventos.remove(i);
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivo("id,evento_id,titulo,local,horario,descricao");
        for (SubEvento u : subEventos){
            manipulador.escreverNoArquivo(ToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public void deleteNoCSV (Trilha trilha) {
        manipulador.setPath(pathTrilha);
        ArrayList<Trilha> trilhas = new ArrayList<>();
        trilhas = getTodasTrilhas();
        for (int i = 0; i < trilhas.size(); i++){
            if (trilha.getId() == trilhas.get(i).getId()){
                trilhas.remove(i);
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivo("id,secao_id,nome");
        for (Trilha u : trilhas){
            manipulador.escreverNoArquivo(ToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public void deleteNoCSV (Usuario usuario) {
        manipulador.setPath(pathUsuario);
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios = getTodosUsuarios();
        for (int i = 0; i < usuarios.size(); i++){
            if (usuario.getId() == usuarios.get(i).getId()){
                usuarios.remove(i);
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivo("id,cpf,nome,idade,instituicao,tipoDeUsuario");
        for (Usuario u : usuarios){
            manipulador.escreverNoArquivo(ToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    //Métodos de atualização

    public void updateNoCSV (Atividade atividadeAntiga, Atividade atividadeNova) {
        manipulador.setPath(pathAtividade);
        ArrayList<Atividade> atividades = new ArrayList<>();
        atividades = getTodasAtividades();
        for (int i = 0; i < atividades.size(); i++){
            if (atividadeAntiga.getId() == atividades.get(i).getId()) {
                atividades.get(i).setTipoSubmissao(atividadeNova.getTipoSubmissao());
                atividades.get(i).setAutor(atividadeNova.getAutor());
                atividades.get(i).setResumo(atividadeNova.getResumo());
                atividades.get(i).setIdTrilha(atividadeNova.getIdTrilha());
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivo("id,tipoDeAtividade,autor,resumo,id_trilha");
        for (Atividade u : atividades) {
            manipulador.escreverNoArquivo(ToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public void updateNoCSV (Evento eventoAntigo, Evento eventoNovo) {
        manipulador.setPath(pathEvento);
        ArrayList<Evento> eventos = new ArrayList<>();
        eventos = getTodosEventos();
        for (int i = 0; i < eventos.size(); i++){
            if (eventoAntigo.getId() == eventos.get(i).getId()) {
                eventos.get(i).setTitulo(eventoNovo.getTitulo());
                eventos.get(i).setLocal(eventoNovo.getLocal());
                eventos.get(i).setHorario(eventoNovo.getHorario());
                eventos.get(i).setDescricao(eventoNovo.getDescricao());
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivo("id,titulo,local,horario,descricao");
        for (Evento u : eventos) {
            manipulador.escreverNoArquivo(ToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public void updateNoCSV (Inscricao inscricaoAntiga, Inscricao inscricaoNova) {
        manipulador.setPath(pathInscricao);
        ArrayList<Inscricao> inscricoes = new ArrayList<>();
        inscricoes = getTodasInscricoes();
        for (int i = 0; i < inscricoes.size(); i++){
            if (inscricaoAntiga.getIdEvento() == inscricoes.get(i).getIdEvento() &&
            inscricaoAntiga.getIdUsuario() == inscricoes.get(i).getIdUsuario() &&
            inscricaoAntiga.getIdSubEvento() == inscricoes.get(i).getIdSubEvento() &&
            inscricaoAntiga.getIdSecao() == inscricoes.get(i).getIdSecao() &&
            inscricaoAntiga.getIdTrilha() == inscricoes.get(i).getIdTrilha()){
                inscricoes.get(i).setIdEvento(inscricaoNova.getIdEvento());
                inscricoes.get(i).setIdUsuario(inscricaoNova.getIdUsuario());
                inscricoes.get(i).setIdSubEvento(inscricaoNova.getIdSubEvento());
                inscricoes.get(i).setIdSecao(inscricaoNova.getIdSecao());
                inscricoes.get(i).setIdTrilha(inscricaoNova.getIdTrilha());
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivo("usuario_id,evento_id,subevento_id,secao_id,trilha_id");
        for (Inscricao u : inscricoes) {
            manipulador.escreverNoArquivo(ToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public void updateNoCSV (Secao secaoAntiga, Secao secaoNova) {
        manipulador.setPath(pathSecao);
        ArrayList<Secao> secoes = new ArrayList<>();
        secoes = getTodasSecoes();
        for (int i = 0; i < secoes.size(); i++){
            if (secaoAntiga.getId() == secoes.get(i).getId()) {
                secoes.get(i).setId_evento(secaoNova.getId_evento());
                secoes.get(i).setId_subEvento(secaoNova.getId_subEvento());
                secoes.get(i).setLocal(secaoNova.getLocal());
                secoes.get(i).setHorario(secaoNova.getHorario());
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivo("id,evento_id,titulo,local,horario,descricao");
        for (Secao u : secoes) {
            manipulador.escreverNoArquivo(ToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public void updateNoCSV (SubEvento subEventoAntigo, SubEvento subEventoNovo) {
        manipulador.setPath(pathSubEvento);
        ArrayList<SubEvento> subEventos = new ArrayList<>();
        subEventos = getTodosSubEventos();
        for (int i = 0; i < subEventos.size(); i++){
            if (subEventoAntigo.getId() == subEventos.get(i).getId()) {
                subEventos.get(i).setIdEvento(subEventoNovo.getIdEvento());
                subEventos.get(i).setTitulo(subEventoNovo.getTitulo());
                subEventos.get(i).setLocal(subEventoNovo.getLocal());
                subEventos.get(i).setHorario(subEventoNovo.getHorario());
                subEventos.get(i).setDescricao(subEventoNovo.getDescricao());
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivo("id,evento_id,titulo,local,horario,descricao");
        for (SubEvento u : subEventos) {
            manipulador.escreverNoArquivo(ToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public void updateNoCSV (Trilha trilhaAntiga, Trilha trilhaNova) {
        manipulador.setPath(pathTrilha);
        ArrayList<Trilha> trilhas = new ArrayList<>();
        trilhas = getTodasTrilhas();
        for (int i = 0; i < trilhas.size(); i++){
            if (trilhaAntiga.getId() == trilhas.get(i).getId()) {
                trilhas.get(i).setIdSecao(trilhaNova.getIdSecao());
                trilhas.get(i).setNome(trilhaNova.getNome());
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivo("id,secao_id,nome");
        for (Trilha u : trilhas) {
            manipulador.escreverNoArquivo(ToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public void updateNoCSV (Usuario usuarioAntigo, Usuario usuarioNovo) {
        manipulador.setPath(pathUsuario);
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios = getTodosUsuarios();
        for (int i = 0; i < usuarios.size(); i++){
            if (usuarioAntigo.getId() == usuarios.get(i).getId()) {
                usuarios.get(i).setCPF(usuarioNovo.getCPF());
                usuarios.get(i).setNome(usuarioNovo.getNome());
                usuarios.get(i).setIdade(usuarioNovo.getIdade());
                usuarios.get(i).setInstituicao(usuarioNovo.getInstituicao());
                usuarios.get(i).setTipoDeUsuario(usuarioNovo.getTipoDeUsuario());
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivo("id,cpf,nome,idade,instituicao,tipoDeUsuario");
        for (Usuario u : usuarios) {
            manipulador.escreverNoArquivo(ToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }
}
