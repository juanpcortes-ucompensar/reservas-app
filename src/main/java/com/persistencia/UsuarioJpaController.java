package com.persistencia;

import com.miproyecto.model.Usuario;

import javax.persistence.*;
import java.util.List;

public class UsuarioJpaController {

    private EntityManagerFactory emf;

    // Constructor que inicializa el EntityManager
    public UsuarioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("reservasAppPU");
    }

    // Método que obtiene un EntityManager
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Registrar un nuevo usuario
    public void create(Usuario usuario) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
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

    // Buscar un usuario por su correo
    public Usuario findUsuarioByCorreo(String correo) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.correo = :correo", Usuario.class);
            query.setParameter("correo", correo);
            List<Usuario> usuarios = query.getResultList();
            return (usuarios.isEmpty()) ? null : usuarios.get(0);  // Si no existe, devolver null
        } finally {
            em.close();
        }
    }

    // Buscar un usuario por su ID
    public Usuario findUsuario(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);  // Buscar la entidad Usuario por su ID
        } finally {
            em.close();
        }
    }

    // Listar todos los usuarios
    public List<Usuario> findUsuarioEntities() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);  // Consulta para obtener todos los usuarios
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Eliminar un usuario por su ID
    public void destroy(int id) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Usuario usuario = em.getReference(Usuario.class, id);  // Obtener la referencia a la entidad Usuario
            em.remove(usuario);  // Eliminar la entidad
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

    // Editar un usuario existente
    public void edit(Usuario usuario) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            usuario = em.merge(usuario);  // Actualizar la entidad usuario
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
