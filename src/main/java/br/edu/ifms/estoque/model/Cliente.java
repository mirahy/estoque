/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author professor
 */
@Entity
public class Cliente implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nome;
    private String telefone;
    private String email;
    private String cpf;

    public Cliente() {
    }
    
    public Cliente(ClienteDiretor diretor) {
         id = diretor.id;
         nome = diretor.nome;
         telefone = diretor.telefone;
         email = diretor.email;
         cpf = diretor.cpf;
     }

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
    
    public static class ClienteDiretor{
        private Long id;
        private String nome;
        private String telefone;
        private String email;
        private String cpf;
        
        public ClienteDiretor wihtId(Long id){
            this.id = id;
            return this;
        }
        
        public ClienteDiretor wihtNome(String nome){
            this.nome = nome;
            return this;
        }
        
        public ClienteDiretor wihtTelefone(String telefone){
            this.telefone = telefone;
            return this;
        }
        
        public ClienteDiretor wihtEmail(String email){
            this.email = email;
            return this;
        }
        
        public ClienteDiretor wihtCpf(String cpf){
            this.cpf = cpf;
            return this;
        }
        
        public Cliente construir(){
            return new Cliente(this);
        }
    }
}
