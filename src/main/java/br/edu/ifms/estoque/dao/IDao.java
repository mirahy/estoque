/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.ifms.estoque.dao;

import java.util.List;

/**
 *
 * @author nicholas.santos
 */
public interface IDao {

    public Object inserir(Object object);

    public Object alterar(Object object);

    public void excluir(Object object);

    public List listar();
}
