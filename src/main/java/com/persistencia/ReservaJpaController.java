package com.persistencia;

import com.miproyecto.model.Reserva;

import javax.persistence.*;
import java.util.List;

public class ReservaJpaController {

    private EntityManagerFactory emf;

    // Constructor que inicializa el EntityManager
    public ReservaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("reservasAppPU");
    }

    // Método que obtiene un EntityManager
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear una nueva reserva
    public void create(Reserva reserva) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(reserva);
            em.getTransaction().commit();
        } catch (RuntimeException re) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw re;
        } finally {
            em.close();
        }
    }

    // Listar todas las reservas
    public List<Reserva> findReservaEntities() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Reserva> query = em.createQuery("SELECT r FROM Reserva r", Reserva.class);  // Consulta para obtener todas las reservas
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Buscar una reserva por su ID
    public Reserva findReserva(Long reservaId) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reserva.class, reservaId);  // Buscar la reserva por su ID
        } finally {
            em.close();
        }
    }

    // Eliminar una reserva por su ID
    public void destroy(Long reservaId) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Reserva reserva = em.getReference(Reserva.class, reservaId);  // Obtener la referencia a la reserva
            em.remove(reserva);  // Eliminar la reserva
            em.getTransaction().commit();
        } catch (RuntimeException re) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw re;
        } finally {
            em.close();
        }
    }

    // Editar una reserva existente
    public void edit(Reserva reserva) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            reserva = em.merge(reserva);  // Actualizar la reserva
            em.getTransaction().commit();
        } catch (RuntimeException re) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw re;
        } finally {
            em.close();
        }
    }
}
