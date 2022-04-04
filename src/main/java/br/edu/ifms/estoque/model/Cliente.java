/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model;

import java.io.Serializable;
<<<<<<< HEAD
import javax.persistence.Column;
=======
>>>>>>> 245f21c7f8360abe5cb99d82c446b8f18786d626
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
<<<<<<< HEAD
 * @author santos
 */
@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

=======
 * @author professor
 */
@Entity
public class Cliente implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
>>>>>>> 245f21c7f8360abe5cb99d82c446b8f18786d626
    private String nome;
    private String telefone;
    private String email;
    private String cpf;
<<<<<<< HEAD
    private String endereco;
    
    public Cliente() {
        
    }
    
    public Boolean isSetId() {
        return id != null;
    }
=======
>>>>>>> 245f21c7f8360abe5cb99d82c446b8f18786d626

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
<<<<<<< HEAD

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
=======
>>>>>>> 245f21c7f8360abe5cb99d82c446b8f18786d626
}
