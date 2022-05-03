/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.dao;

import br.edu.ifms.estoque.database.Conexao;
import br.edu.ifms.estoque.model.Cliente;
import br.edu.ifms.estoque.model.Marca;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.HibernateException;

/**
 *
 * @author nicholas.santos
 */
public class MarcaDaoImpl implements IMarcaDao {

    private static final String JPQL = "SELECT m FROM Marca m";

    public MarcaDaoImpl(Conexao instance) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public MarcaDaoImpl() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    private EntityManager getEntityManager() {
        return Conexao.createEntityManager();
    }

    @Override
    public List<Marca> buscarPorNome(String nome) {
        EntityManager em = getEntityManager();
        String condicao = "";
        List<Marca> marcas = null;
        Boolean hasNome = nome != null && !nome.isBlank() && !nome.isEmpty();
        if (hasNome) {
            condicao = " WHERE m.nome LIKE ?1 ";
        }
        Query query = em.createQuery(JPQL + condicao);
        if (hasNome) {
            marcas = query.setParameter(1, "%" + nome + "%")
                    .getResultList();
        } else {
            marcas = query.getResultList();
        }
        em.close();
        return marcas;
    }

    @Override
    public Marca inserir(Marca object) {
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
    public Marca alterar(Marca object) {
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
    public List listar() {
        return buscarPorNome(null);
    }

    @Override
    public Marca buscarPorId(Object object) {
        EntityManager em = getEntityManager();
        Long id = (Long) object;
        Marca obj = em.find(Marca.class, id);
        em.close();
        return obj;
    }

}
