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
 * @author santos
 */
public class ClienteDao implements IClienteDao {

    private static final String jpql = " select c from Cliente c ";

    public ClienteDao() {

    }

    private EntityManager getEntityManager() {
        return Conexao.createEntityManager();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        EntityManager em = getEntityManager();
        Cliente cliente = em.find(Cliente.class, id);
        em.detach(cliente);
        return cliente;
    }

    @Override
    public List<Cliente> buscarPorNome(String nome) {
        EntityManager em = getEntityManager();
        List<Cliente> clientes = em.createQuery(String.format("%s WHERE c.nome like ?1 ", jpql))
                .setParameter(1, "%" + nome + "%")
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
        EntityManager em = getEntityManager();
        em.detach(object);
        em.getTransaction().begin();
        em.merge(object);
        em.getTransaction().commit();
        return object;
    }

    @Override
    public void excluir(Object object) {
        Long id = (Long) object;
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.remove(em.getReference(Cliente.class, id));
        em.getTransaction().commit();
    }

    @Override
    public List<Cliente> listar() {
        EntityManager em = getEntityManager();
        List<Cliente> clientes = em.createQuery(jpql)
                .getResultList();
        return clientes;
    }

}
