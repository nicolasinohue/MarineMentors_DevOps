package br.com.fiap.classes;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "artigos")
@SequenceGenerator(name="artigos_seq", sequenceName = "", allocationSize = 1)
public class Artigo implements Serializable {
    @Id
    @Column(name="id_artigo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="titulo", length = 200)
    private String titulo;

    @Column(name="conteudo")
    private String conteudo;

    @Column(name="autor", length = 100)
    private String autor;

    @Column(name="data_publicacao")
    private Date data_publicacao;

    @Column(name="visualizacoes")
    private int visualizacoes;

    public Artigo() {}

    @PostPersist //Executa o metodo apos o persist
    private void executar() {
        System.out.println("Executando o método..");
    }

    @Override
    public String toString() {
        return  '\n' + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-" + '\n' +
                "[ Informações Artigo ]" + '\n' +
                "ID: " + id + '\n' +
                "Titulo: " + titulo + '\n' +
                "Conteudo: " + conteudo + '\n' +
                "Autor: " + autor + '\n' +
                "Data-Publicação: " + data_publicacao + '\n' +
                "Visualizações: " + visualizacoes;
    }
}