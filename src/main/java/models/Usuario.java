package models;
public class Usuario {
    private int id;
    private String CPF;
    private String nome;
    private int idade;
    private String instituicao;
    private String tipoDeUsuario;
    private String login;
    private String senha;

    public Usuario(int id, String CPF, String nome, int idade, String instituicao, String tipoDeUsuario, String login, String senha) {
        this.id = id;
        this.CPF = CPF;
        this.nome = nome;
        this.idade = idade;
        this.instituicao = instituicao;
        this.tipoDeUsuario = tipoDeUsuario;
        this.login = login;
        this.senha = senha;
    }

    public Usuario () {}
    public boolean Logar(String login, String senha){
        if (this.login.equals(login) && this.senha.equals(senha)){
            return true;
        }
        else { return false; }

    }
    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
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
        if (tipoDeUsuario.equalsIgnoreCase("COMUM")){
            this.tipoDeUsuario = tipoDeUsuario.toUpperCase();
        }
        else if (tipoDeUsuario.equalsIgnoreCase("PALESTRANTE")){
            this.tipoDeUsuario = tipoDeUsuario.toUpperCase();
        }
        else if (tipoDeUsuario.equalsIgnoreCase("ADMINISTRADOR")){
            this.tipoDeUsuario = tipoDeUsuario.toUpperCase();
        }
        else {
            this.tipoDeUsuario = "ERRADO";
        }
    }

    public String getLogin(){
        return this.login;
    }

    public String getSenha(){
        return this.senha;
    }

    public void setLogin(String s){
        this.login = s;
    }

    public void setSenha(String a){
        this.senha = a;
    }

    public boolean ValidarCPF(){
        String cpf = this.CPF;
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
