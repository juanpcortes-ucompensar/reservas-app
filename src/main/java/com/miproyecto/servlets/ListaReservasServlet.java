package com.miproyecto.servlets;

import com.miproyecto.model.Reserva;
import com.persistencia.ControladoraPersistencia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listarReservas")
public class ListaReservasServlet extends HttpServlet {

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        // Inicializamos la controladora de persistencia
        control = new ControladoraPersistencia();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener la lista de reservas desde la base de datos
            List<Reserva> reservas = control.traerReservas();
            // Establecer la lista de reservas como atributo de la solicitud
            request.setAttribute("reservas", reservas);
            // Redirigir a la página JSP para mostrar las reservas
            request.getRequestDispatcher("/reservas.jsp").forward(request, response);
        } catch (Exception e) {
            // Manejar cualquier error y redirigir al error.jsp
            request.setAttribute("errorMessage", "Error al cargar las reservas: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
