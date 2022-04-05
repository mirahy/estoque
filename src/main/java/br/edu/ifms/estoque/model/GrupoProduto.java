/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model;

import java.util.Objects;

/**
 *
 * @author santos
 */
public class GrupoProduto {
    private Long id;
    private String nome;
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

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.nome);
        hash = 59 * hash + Objects.hashCode(this.subgrupo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GrupoProduto other = (GrupoProduto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
