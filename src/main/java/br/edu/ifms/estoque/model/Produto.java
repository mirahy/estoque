/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author santos
 */
public class Produto {
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

    public Produto() {
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
    
}
