/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model;

import br.edu.ifms.estoque.converter.LocalDateTimeAttributeConverter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author santos
 */
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private BigDecimal estoque;
    private BigDecimal estoqueMinimo;
    
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime dataUltimaCompra;
    
    @ManyToOne
    private GrupoProduto grupoProduto;
    
    @ManyToOne
    private Marca marca;
    
    @ManyToOne
    private UnidadeMedida unidadeMedida;

    public Produto() {
        grupoProduto = new GrupoProduto();
        marca = new Marca();
        unidadeMedida = new UnidadeMedida();
    }

    public Produto(Long id, String nome, String descricao, BigDecimal preco, 
            BigDecimal estoque, BigDecimal estoqueMinimo, 
            LocalDateTime dataUltimaCompra, GrupoProduto grupoProduto, 
            Marca marca, UnidadeMedida unidadeMedida) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
        this.estoqueMinimo = estoqueMinimo;
        this.dataUltimaCompra = dataUltimaCompra;
        this.grupoProduto = grupoProduto;
        this.marca = marca;
        this.unidadeMedida = unidadeMedida;
    }

    public Produto(ProdutoDiretor diretor) {
        id = diretor.id;
        nome = diretor.nome;
        descricao = diretor.descricao;
        preco = diretor.preco;
        estoque = diretor.estoque;
        estoqueMinimo = diretor.estoqueMinimo;
        dataUltimaCompra = diretor.dataUltimaCompra;
        grupoProduto = diretor.grupoProduto;
        marca = diretor.marca;
        unidadeMedida = diretor.unidadeMedida;
        
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getEstoque() {
        return estoque;
    }

    public void setEstoque(BigDecimal estoque) {
        this.estoque = estoque;
    }

    public BigDecimal getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(BigDecimal estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public LocalDateTime getDataUltimaCompra() {
        return dataUltimaCompra;
    }

    public void setDataUltimaCompra(LocalDateTime dataUltimaCompra) {
        this.dataUltimaCompra = dataUltimaCompra;
    }

    public GrupoProduto getGrupoProduto() {
        return grupoProduto;
    }

    public void setGrupoProduto(GrupoProduto grupoProduto) {
        this.grupoProduto = grupoProduto;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }
    
    public static class ProdutoDiretor{
        private Long id;
        private String nome;
        private String descricao;
        private BigDecimal preco;
        private BigDecimal estoque;
        private BigDecimal estoqueMinimo;
        private LocalDateTime dataUltimaCompra;
        private GrupoProduto grupoProduto;
        private Marca marca;
        private UnidadeMedida unidadeMedida;
        
        public ProdutoDiretor wihtId(Long id){
            this.id = id;
            return this;
        }
        
        public ProdutoDiretor wihtNome(String nome){
            this.nome = nome;
            return this;
        }
        
        public ProdutoDiretor wihtDescricao(String descricao){
            this.descricao = descricao;
            return this;
        }
        
        public ProdutoDiretor wihtPreco(BigDecimal preco){
            this.preco = preco;
            return this;
        }
        
        public ProdutoDiretor wihtEstoque(BigDecimal estoque){
            this.estoque = estoque;
            return this;
        }
        
        public ProdutoDiretor wihtEstoqueMinimo(BigDecimal estoqueMinimo){
            this.estoqueMinimo = estoqueMinimo;
            return this;
        }
        
        public ProdutoDiretor wihtDataUltimaCompra(LocalDateTime dataUltimaCompra){
            this.dataUltimaCompra = dataUltimaCompra;
            return this;
        }
        
        public ProdutoDiretor wihtGrupoProduto(GrupoProduto grupoProduto){
            this.grupoProduto = grupoProduto;
            return this;
        }
        
        public ProdutoDiretor wihtMarca(Marca marca){
            this.marca = marca;
            return this;
        }
        
        public ProdutoDiretor wihtUnidadeMedida(UnidadeMedida unidadeMedida){
            this.unidadeMedida = unidadeMedida;
            return this;
        }
        
        public Produto construir(){
            return new Produto(this);
        }
    }
    
}
