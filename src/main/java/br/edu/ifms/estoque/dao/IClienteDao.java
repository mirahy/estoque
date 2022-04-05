/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.ifms.estoque.dao;

import br.edu.ifms.estoque.model.Cliente;
import java.util.List;

/**
 *
 * @author professor
 */
public interface IClienteDao extends IDao<Cliente> {
 
    List<Cliente> buscarPorNome(String nome);
    
}
