package com.persistencia;

import java.util.List;
import java.util.logging.Logger;

import com.miproyecto.model.Reserva;
import com.miproyecto.model.Usuario;
import java.util.logging.Level;

public class ControladoraPersistencia {

    private ReservaJpaController reservaJpa = new ReservaJpaController();
    private UsuarioJpaController usuarioJpa = new UsuarioJpaController();

    // Métodos para manejar reservas
    public void crearReserva(Reserva reserva) throws Exception {
        reservaJpa.create(reserva);
    }

    public List<Reserva> traerReservas() {
        return reservaJpa.findReservaEntities();
    }

    public Reserva traerReserva(Long reservaId) {
        return reservaJpa.findReserva(reservaId);
    }

    public void borrarReserva(Long reservaId) {
        try {
            reservaJpa.destroy(reservaId);
        } catch (Exception e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void editarReserva(Reserva reserva) {
        try {
            reservaJpa.edit(reserva);
        } catch (Exception e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // Métodos para manejar usuarios
    public void crearUsuario(Usuario usuario) throws Exception {
        usuarioJpa.create(usuario);
    }

    public List<Usuario> traerUsuarios() {
        return usuarioJpa.findUsuarioEntities();
    }

    public Usuario traerUsuario(int id) {
        return usuarioJpa.findUsuario(id);
    }

    public void borrarUsuario(int id) {
        try {
            usuarioJpa.destroy(id);
        } catch (Exception e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void editarUsuario(Usuario usuario) {
        try {
            usuarioJpa.edit(usuario);
        } catch (Exception e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // Método adicional para obtener un usuario por correo
    public Usuario traerUsuarioPorCorreo(String correo) {
        return usuarioJpa.findUsuarioByCorreo(correo);
    }
}
