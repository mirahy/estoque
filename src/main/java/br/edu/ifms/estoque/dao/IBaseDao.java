/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.dao;

import java.util.List;

/**
 *
 * @author santos
 */
public interface IBaseDao<T> extends IDao<T> {

    public List<T> buscarPorNome(String nome);
}
