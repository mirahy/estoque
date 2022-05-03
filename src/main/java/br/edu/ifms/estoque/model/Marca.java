/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class Marca extends Object{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;

    public Marca() {
    }

    private Marca(MarcaDiretor diretor) {
        id = diretor.id;
        nome = diretor.nome;
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
    
    public Object[] toArray() {
        Object[] array = new Object[2];
        array[0] = this.id;
        array[1] = this.nome;
        return array;
    }
    
    public static class MarcaDiretor{
        private Long id;
        private String nome;
        
        public MarcaDiretor wihtId(Long id){
            this.id = id;
            return this;
        }
        
        public MarcaDiretor wihtNome(String nome){
            this.nome = nome;
            return this;
        }
        
        public Marca construir(){
            return new Marca(this);
        }
    }
}
