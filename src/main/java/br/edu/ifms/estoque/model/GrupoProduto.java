/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author santos
 */
@Entity
@Table(name = "grupo_produto")
public class GrupoProduto implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    
    @Column
    private String nome;
    
    @ManyToOne
    private GrupoProduto subgrupo;

    public GrupoProduto() {
    }

    public GrupoProduto(Long id, String nome, GrupoProduto subgrupo) {
        this.id = id;
        this.nome = nome;
        this.subgrupo = subgrupo;
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

    public GrupoProduto getSubgrupo() {
        return subgrupo;
    }

    public void setSubgrupo(GrupoProduto subgrupo) {
        this.subgrupo = subgrupo;
    }
    
    public Object[] toArray() {
        Object[] array = new Object[3];
        array[0] = this.id;
        array[1] = this.nome;
        array[2] = this.subgrupo;
        return array;
    }
}
