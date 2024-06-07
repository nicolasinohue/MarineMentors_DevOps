package br.com.fiap.classes;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "eventos")
@SequenceGenerator(name="eventos_seq", sequenceName = "", allocationSize = 1)
public class Eventos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_evento")
    private int id;

    @Column(name="titulo", length = 200)
    private String titulo;

    @Column(name="descricao")
    private String descricao;

    @Column(name="data_inicio")
    private Date data_inicio;

    @Column(name="data_fim")
    private Date data_fim;

    @Column(name="local", length = 200)
    private String local;

    @Column(name="url", length = 300)
    private String url;

    public Eventos() {}

    @PostPersist //Executa o metodo apos o persist
    private void executar() {
        System.out.println("Executando o método..");
    }

    @Override
    public String toString() {
        return  '\n' + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-" + '\n' +
                "[ Informações Eventos ]" + '\n' +
                "ID: " + id + '\n' +
                "Titulo: " + titulo + '\n' +
                "Descrição: " + descricao + '\n' +
                "Data-Inicio: " + data_inicio + '\n' +
                "Data-Fim: " + data_fim + '\n' +
                "Local: " + local + '\n' +
                "URL: " + url;
    }
}