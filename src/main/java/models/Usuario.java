package models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 11)
    private String cpf;

    @NotNull
    @Size(max = 100)
    private String nome;

    private Integer idade;
    private String instituicao;
    private String tipoDeUsuario;

    @NotNull
    @Size(max = 50)
    private String login;

    @NotNull
    @Size(max = 100)
    private String senha;

    public Usuario(Long id, String cpf, String nome, int idade, String instituicao, String tipoDeUsuario, String login, String senha) {
            this.id = id;
            this.cpf = cpf;
            this.nome = nome;
            this.idade = idade;
            this.instituicao = instituicao;
            this.tipoDeUsuario = tipoDeUsuario;
            this.login = login;
            this.senha = senha;
        }

    public Usuario() {}

    public boolean Logar(String login, String senha) {
        if (this.login.equals(login) && this.senha.equals(senha)) {
            return true;
        } else {
            return false;
        }
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public void setTipoDeUsuario(String tipoDeUsuario) {
        if (tipoDeUsuario.equalsIgnoreCase("COMUM")) {
            this.tipoDeUsuario = tipoDeUsuario.toUpperCase();
        } else if (tipoDeUsuario.equalsIgnoreCase("PALESTRANTE")) {
            this.tipoDeUsuario = tipoDeUsuario.toUpperCase();
        } else if (tipoDeUsuario.equalsIgnoreCase("ADMINISTRADOR")) {
            this.tipoDeUsuario = tipoDeUsuario.toUpperCase();
        } else {
            this.tipoDeUsuario = "ERRADO";
        }
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean ValidarCPF() {
        String cpf = this.cpf;
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF possui 11 dígitos
        if (cpf.length() != 11)
            return false;

        // Verifica se todos os dígitos são iguais (caso especial de CPF inválido)
        if (cpf.matches("(\\d)\\1{10}"))
            return false;

        // Calcula o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (10 - i) * Character.getNumericValue(cpf.charAt(i));
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 > 9)
            digito1 = 0;

        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (11 - i) * Character.getNumericValue(cpf.charAt(i));
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 > 9)
            digito2 = 0;

        // Verifica se os dígitos calculados são iguais aos dígitos informados
        return (digito1 == Character.getNumericValue(cpf.charAt(9))) && (digito2 == Character.getNumericValue(cpf.charAt(10)));
    }
}
