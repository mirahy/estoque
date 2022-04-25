/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author santos
 */
@Entity
public class UnidadeMedida {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
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

    public Boolean getFracionado() {
        return fracionado;
    }

    public void setFracionado(Boolean fracionado) {
        this.fracionado = fracionado;
    }
    
    public Boolean isFracionado() {
        return this.fracionado;
    }
    
}
