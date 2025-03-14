package com.miproyecto.servlets;

import com.miproyecto.model.Reserva;
import com.miproyecto.model.Usuario;
import com.persistencia.ControladoraPersistencia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/reservas")
public class svReserva extends HttpServlet {

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        // Inicializamos la controladora de persistencia
        control = new ControladoraPersistencia();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("listar")) {
            try {
                // Listar todas las reservas usando la controladora
                List<Reserva> reservas = control.traerReservas();
                request.setAttribute("reservas", reservas);
                request.getRequestDispatcher("/reservas.jsp").forward(request, response);
            } catch (Exception e) {
                // Manejar el error y redirigir al error.jsp
                request.setAttribute("errorMessage", "Error al cargar las reservas: " + e.getMessage());
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } else if (action != null && action.equals("eliminar")) {
            // Eliminar una reserva usando la controladora
            String reservaId = request.getParameter("id");
            if (reservaId != null) {
                try {
                    control.borrarReserva(Long.parseLong(reservaId));
                    response.sendRedirect("reservas?action=listar");
                } catch (Exception e) {
                    // Redirigir a error.jsp con el mensaje de error
                    request.setAttribute("errorMessage", "Error al eliminar la reserva: " + e.getMessage());
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
            }
        } else {
            // Redirigir a la página de inicio si no se especifica ninguna acción
            response.sendRedirect("index");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parámetros del formulario de reserva
        String usuarioCorreo = request.getParameter("usuarioCorreo");
        String duracionStr = request.getParameter("duracion"); // Obtén la duración en horas desde el formulario
        String espacio = request.getParameter("espacio"); // Obtener el espacio desde el formulario
        String fechaReservaStr = request.getParameter("fechaReserva"); // Obtener la fecha desde el formulario

        // Convertir la duración a int
        int duracion = Integer.parseInt(duracionStr);

        try {
            // Validar que la fecha no esté vacía
            if (fechaReservaStr == null || fechaReservaStr.isEmpty()) {
                throw new IllegalArgumentException("La fecha de reserva no puede estar vacía.");
            }

            // Convertir la fecha de reserva desde String a java.util.Date
            java.util.Date fechaReserva = java.sql.Date.valueOf(fechaReservaStr);

            // Validar que la fecha de reserva no sea una fecha pasada
            java.util.Date fechaActual = new java.util.Date();  // Fecha actual
            if (fechaReserva.before(fechaActual)) {
                throw new IllegalArgumentException("La fecha de reserva no puede ser una fecha pasada.");
            }

            // Obtener el usuario por su correo usando la controladora
            Usuario usuario = control.traerUsuarioPorCorreo(usuarioCorreo);
            if (usuario != null) {
                // Crear una nueva reserva usando la controladora
                Reserva nuevaReserva = new Reserva(fechaReserva, usuario, duracion, espacio);
                control.crearReserva(nuevaReserva);
                response.sendRedirect("reservas?action=listar"); // Redirigir a la lista de reservas
            } else {
                // Redirigir a error.jsp si el usuario no es encontrado
                request.setAttribute("errorMessage", "Usuario no encontrado.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
            
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (Exception e) {
            // Redirigir a error.jsp con el mensaje de error
            request.setAttribute("errorMessage", "Error al crear la reserva: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

}
