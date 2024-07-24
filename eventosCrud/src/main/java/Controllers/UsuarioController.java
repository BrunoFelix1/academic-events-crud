package Controllers;

import java.util.ArrayList;
import java.util.List;

import Interfaces.iUsuarioPersistenciaControlador;
import Models.Evento;
import Models.Inscricao;
import Models.SubEvento;
import Models.Trilha;
import Models.Usuario;
import Persistence.PersistenceEvento;
import Persistence.PersistenceUsuario;

public class UsuarioController {

   
    private PersistenceUsuario usuarioP = new PersistenceUsuario();

    public static boolean validarCPF(String cpf) { //Método para ver se o cpf é válido (GPT)



        cpf = cpf.replaceAll("[^0-9]", "");
        if (cpf.length() != 11) {
            return false;
        }
        if (cpf.chars().distinct().count() == 1) {
            return false;
        }
        int[] cpfDigits = cpf.chars().map(c -> c - '0').toArray();
        int firstDigit = calcularDigito(cpfDigits, 10);
        if (firstDigit != cpfDigits[9]) {
            return false;
        }
        int secondDigit = calcularDigito(cpfDigits, 11);
        return secondDigit == cpfDigits[10];
    }

    public static boolean fazerValidacaoCPF(String CPF) { //Realiza a validação do cpf
        if (validarCPF(CPF)) {
            return true;
        } else {
            System.out.println("CPF inválido.");
            return false;
        }
    }

    private static int calcularDigito(int[] cpfDigits, int weight) { //Método que auxilia a validação de cpf 
        int sum = 0;
        for (int i = 0; i < cpfDigits.length - 2; i++) {
            sum += cpfDigits[i] * weight--;
        }
        int remainder = (sum * 10) % 11;
        return remainder == 10 ? 0 : remainder;
    }
    
    private boolean checarCadastroCPF(Usuario usuario) { //Método que checa se o CPF a ser cadastrado já existe

        if (fazerValidacaoCPF(usuario.getCPF())) {
            ArrayList<Usuario> usuarios = usuarioP.getTodosUsuarios();
            boolean usuarioExistente = false;
            for (Usuario u : usuarios) {
                if (u.getCPF().equals(usuario.getCPF())) {
                    usuarioExistente = true;
                    break;
                }
            }
            if (usuarioExistente) {
                System.out.println("Erro ao realizar cadastro. " + usuario.getCPF() + " já existe.");
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private boolean checarCadastroUsuario(Usuario usuario) { //Método que checa se o usuário a ser cadastrado já existe

        ArrayList<Usuario> usuarios = usuarioP.getTodosUsuarios();
        boolean usuarioExistente = false;
        for (Usuario u : usuarios) {
            if (u.equals(usuario)) {
                usuarioExistente = true;
                break;
            }
        }
        if (usuarioExistente) {
            System.out.println("Erro ao realizar cadastro. O usuário " + usuario + " já existe.");
            return false;
        } else {
            return true;
        }
    }

    private void cadastrarUsuario(Usuario usuario) { //Método que cadastra o usuário, se o CPF e usuário não já tiverem sido cadastrados
        if (checarCadastroCPF(usuario) && (checarCadastroUsuario(usuario))) {
            System.out.println("Cadastro realizado com sucesso!");
            usuarioP.addUsuario(usuario);
        } else {
            System.out.println("Erro ao realizar cadastro. ");
        }
    }

    private void logarUsuario(Usuario usuario) {  //Falta implementar a lógica do login


    }

    


    









    //Exemplo de comunicação com as interfaces
    //Porque pode mudar pra qualquer objeto que implemente a interface





    @Override
    public List<Evento> ListarEventosDisponiveis() {
        return List.of();
    }

    @Override
    public void ParticiparDeEvento(Usuario usuario, Evento evento) {

    }

    @Override
    public void ParticiparDeSubEvento(Usuario usuario, SubEvento subevento) {

    }

    @Override
    public void ParticiparDeTrilha(Usuario usuario, Trilha trilha) {

    }

    @Override
    public List<Inscricao> ListarInscricoes(Usuario usuario) {
        return List.of();
    }

    @Override
    public boolean CancelarInscricaoTrilha(Usuario usuario, Trilha trilha) {
        return false;
    }

    @Override
    public Certificado EmitirCertificadoTrilha(Usuario usuario, Trilha trilha) {
        return null;
    }
}
