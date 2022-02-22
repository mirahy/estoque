/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.dao;

import br.edu.ifms.estoque.database.Conexao;
import br.edu.ifms.estoque.model.Cliente;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author professor
 */
public class ClienteDao implements IClienteDao {

    private static final String JPQL = "SELECT c FROM Cliente c";

    private EntityManager getEntityManager() {
        return Conexao.createEntityManager();
    }

    @Override
    public List<Cliente> buscarPorNome(String nome) {
        EntityManager em = getEntityManager();
        List<Cliente> clientes = em.createQuery(
                JPQL + " WHERE c.nome LIKE ?1 "
        ).setParameter(1, "%" + nome + "%")
                .getResultList();
        return clientes;
    }

    @Override
    public Cliente inserir(Cliente object) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
        return object;
    }

    @Override
    public Cliente alterar(Cliente object) {

    }

    @Override
    public void excluir(Object object) {

    }

    @Override
    public List<Cliente> listar() {

    }

    @Override
    public Cliente buscarPorId(Object object) {
        EntityManager em = getEntityManager();
        Long id = (Long) object;
        Cliente cliente = em.find(Cliente.class, id);
        return cliente;
    }

}
