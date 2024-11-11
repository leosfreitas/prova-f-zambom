package br.insper.projeto.artigos.dto;

public class PlanoUsuarioDTO {

    private String email;
    private String nome;
    private String papel;

    public PlanoUsuarioDTO() {
    }

    public PlanoUsuarioDTO(String email, String nome, String papel) {
        this.email = email;
        this.nome = nome;
        this.papel = papel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }
}
