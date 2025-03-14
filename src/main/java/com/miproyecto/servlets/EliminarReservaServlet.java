package com.miproyecto.servlets;

import com.miproyecto.model.Reserva;
import com.persistencia.ControladoraPersistencia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/eliminarReserva")
public class EliminarReservaServlet extends HttpServlet {

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraPersistencia();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String reservaIdStr = request.getParameter("id");
        if (reservaIdStr != null) {
            try {
                Long reservaId = Long.parseLong(reservaIdStr);
                // Obtener la reserva de la base de datos
                Reserva reserva = control.traerReserva(reservaId); // Método correcto de la controladora
                if (reserva != null) {
                    // Pasar la reserva al JSP para confirmación
                    request.setAttribute("reserva", reserva);
                    request.getRequestDispatcher("/eliminarReserva.jsp").forward(request, response);
                } else {
                    // Enviar mensaje de error si la reserva no se encuentra
                    request.setAttribute("errorMessage", "Reserva no encontrada");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
            } catch (Exception e) {
                // Enviar mensaje de error si ocurre una excepción
                request.setAttribute("errorMessage", "Error al obtener la reserva: " + e.getMessage());
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } else {
            // Enviar mensaje de error si el parámetro "id" no es válido
            request.setAttribute("errorMessage", "ID de reserva no proporcionado");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el ID de la reserva que se desea eliminar
        String reservaIdStr = request.getParameter("idReserva");

        if (reservaIdStr != null) {
            try {
                Long reservaId = Long.parseLong(reservaIdStr);
                
                // Eliminar la reserva usando la controladora
                control.borrarReserva(reservaId);
                
                // Redirigir a la lista de reservas
                response.sendRedirect("reservas?action=listar");
            } catch (Exception e) {
                // Enviar mensaje de error si ocurre un problema durante la eliminación
                request.setAttribute("errorMessage", "Error al eliminar la reserva: " + e.getMessage());
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } else {
            // Enviar mensaje de error si no se recibe el ID de la reserva
            request.setAttribute("errorMessage", "ID de reserva no proporcionado para eliminación");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
