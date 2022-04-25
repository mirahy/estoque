/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.ifms.estoque.dao;

import br.edu.ifms.estoque.model.Marca;
import java.util.List;

/**
 *
 * @author nicholas.santos
 */
public interface IGrupoProdutoDao extends IDao<Marca> {

    public List<Marca> buscarPorNome(String nome);
}
