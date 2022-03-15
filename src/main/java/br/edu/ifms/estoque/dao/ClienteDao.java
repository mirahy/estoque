/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.dao;

import br.edu.ifms.estoque.database.Conexao;
import br.edu.ifms.estoque.model.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.HibernateException;

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
        String condicao = "";
        List<Cliente> clientes = null;
        Boolean hasNome = nome != null && !nome.isBlank() && !nome.isEmpty();
        if (hasNome) {
            condicao = " WHERE c.nome LIKE ?1 ";
        }
        Query query = em.createQuery(JPQL + condicao);
        if (hasNome) {
            clientes = query.setParameter(1, "%" + nome + "%")
                    .getResultList();
        } else {
            clientes = query.getResultList();
        }
        em.close();
        return clientes;
    }

    @Override
    public Cliente inserir(Cliente object) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (HibernateException ex) {
            em.getTransaction().rollback();
        }
        em.close();
        return object;
    }

    @Override
    public Cliente alterar(Cliente object) {
        EntityManager em = getEntityManager();
        em.detach(object);
        try {
            em.getTransaction().begin();
            em.merge(object);
            em.getTransaction().commit();
        } catch (HibernateException ex) {
            em.getTransaction().rollback();
        }
        em.close();
        return object;
    }

    @Override
    public void excluir(Object object) {
        Long id = (Long) object;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.getReference(Cliente.class, id));
            em.getTransaction().commit();
        } catch (HibernateException ex) {
            em.getTransaction().rollback();
        }
        em.close();
    }

    @Override
    public List<Cliente> listar() {
        return buscarPorNome(null);
    }

    @Override
    public Cliente buscarPorId(Object object) {
        EntityManager em = getEntityManager();
        Long id = (Long) object;
        Cliente cliente = em.find(Cliente.class, id);
        em.close();
        return cliente;
    }

}
