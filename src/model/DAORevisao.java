/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author guilherme
 */
public class DAORevisao {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("AutoMobilePU");
    EntityManager em = emf.createEntityManager();

    public boolean inserir(Revisao r) {
        em.getTransaction().begin();
        try {
            em.persist(r);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
    }

    public boolean editar(Revisao r) {
        em.getTransaction().begin();
        try {
            em.merge(r);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
    }

    public boolean excluir(Revisao r) {
        em.getTransaction().begin();
        try {
            em.remove(r);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
    }

    public List<Revisao> listar() {
        Query consulta = em.createQuery("select r from Revisao r ");
        return consulta.getResultList();
    }

    public List<Revisao> listar(String placa) {
        Query consulta = em.createQuery("select r from Revisao r where r.idautomovel.placa=:n");
        consulta.setParameter("n", placa);
        return consulta.getResultList();
    }

    public Revisao ultimaRevisao(Automovel a) {
        try {
            Query consulta = em.createQuery("select r from Revisao r where r.idautomovel =:n");
            consulta.setParameter("n", a);
            consulta.setMaxResults(1);

            return (Revisao) consulta.getSingleResult();
        } catch (NoResultException e) {
            return null;

        }

    }

}
