/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author santos
 */
@Entity
public class GrupoProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private GrupoProduto subgrupo;

    public GrupoProduto() {
    }

    public GrupoProduto(GrupoProdutoDiretor diretor) {
        id = diretor.id;
        nome = diretor.nome;
        subgrupo = diretor.subgrupo;
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
    
    public static class GrupoProdutoDiretor{
        private Long id;
        private String nome;
        private GrupoProduto subgrupo;
        
        public GrupoProdutoDiretor wihtId(Long id){
            this.id = id;
            return this;
        }
        
        public GrupoProdutoDiretor wihtNome(String nome){
            this.nome = nome;
            return this;
        }
        
        public GrupoProdutoDiretor wihtGrupoProduto(GrupoProduto subgrupo){
            this.subgrupo = subgrupo;
            return this;
        }
        
        public GrupoProduto contruir(){
            return new GrupoProduto(this);
        }
    }
}
