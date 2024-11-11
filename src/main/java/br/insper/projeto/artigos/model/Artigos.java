package br.insper.projeto.artigos.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Entity
@Document(collection = "prova")
public class Artigos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String titulo;
    private String autor;
    private String conteudo;

    public Artigos() {
    }

    public Artigos(String titulo, String autor, String conteudo) {
        this.titulo = titulo;
        this.autor = autor;
        this.conteudo = conteudo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}
