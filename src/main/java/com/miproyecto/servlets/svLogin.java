package com.miproyecto.servlets;

import com.miproyecto.model.Usuario;
import com.persistencia.UsuarioJpaController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class svLogin extends HttpServlet {

    private UsuarioJpaController usuarioJpaController;

    @Override
    public void init() throws ServletException {
        // Inicializamos el JPA Controller
        usuarioJpaController = new UsuarioJpaController();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtenemos los parámetros del formulario de login
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");

        // Comprobamos si el usuario existe
        Usuario usuario = usuarioJpaController.findUsuarioByCorreo(correo);
        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            // Si las credenciales son correctas, redirigimos a la página principal
            response.sendRedirect("reservas.jsp");
        } else {
            // Si no son correctas, mostramos un mensaje de error
            request.setAttribute("errorMessage", "Credenciales incorrectas. Intenta nuevamente.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        if (usuarioJpaController != null) {
            usuarioJpaController.getEntityManager().close();
        }
    }
}
