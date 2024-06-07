package br.com.fiap.classes;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "usuario")
@SequenceGenerator(name="usuario_seq", sequenceName = "", allocationSize = 1)
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private int id;

    @Column(name="nome", length = 100)
    private String nome;

    @Column(name="email", length = 100)
    private String email;

    @Column(name="senha", length = 100)
    private String senha;

    @Column(name="data_criacao")
    private Date data_criacao;

    //Constructor
    public Usuario() {}

    @PostPersist //Executa o metodo apos o persist
    private void executar() {
        System.out.println("Executando o método..");
    }

    @Override
    public String toString() {
        return  '\n' + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-" + '\n' +
                "[ Informações Usuario ]" + '\n' +
                "ID: " + id + '\n' +
                "Nome: " + nome + '\n' +
                "E-Mail: " + email + '\n' +
                "Senha: " + senha + '\n' +
                "Data Criação: " + data_criacao;
    }
}