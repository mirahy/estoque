/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.ifms.estoque.dao;

import java.util.List;

/**
 *
 * @author nicholas.santos
 * @param <T>
 */
public interface IDao<T> {
    public T inserir(T object);

    public T alterar(T object);
    public void excluir(Object object);
    public List<T> listar();
    public T buscarPorId(Object object);

}
