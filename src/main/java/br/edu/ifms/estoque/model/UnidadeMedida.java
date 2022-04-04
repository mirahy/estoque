/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author santos
 */
@Entity
@Table(name = "unidade_medida")
public class UnidadeMedida implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    
    @Column
    private String nome;
    private Boolean fracionado;

    public UnidadeMedida() {
    }

    public UnidadeMedida(Long id, String nome, Boolean fracionado) {
        this.id = id;
        this.nome = nome;
        this.fracionado = fracionado;
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

<<<<<<< HEAD
=======
    public Boolean getFracionado() {
        return fracionado;
    }

    public void setFracionado(Boolean fracionado) {
        this.fracionado = fracionado;
    }
    
    public Boolean isFracionado() {
        return this.fracionado;
    }
    
>>>>>>> 245f21c7f8360abe5cb99d82c446b8f18786d626
}
