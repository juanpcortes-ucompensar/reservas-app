package com.miproyecto.servlets;

import com.miproyecto.model.Usuario;
import com.persistencia.ControladoraPersistencia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class svUsuario extends HttpServlet {

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        // Inicializamos la ControladoraPersistencia
        control = new ControladoraPersistencia();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            List<Usuario> usuarios = control.traerUsuarios();
            request.setAttribute("usuarios", usuarios);
            request.setAttribute("action", "listar");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtenemos los parámetros del formulario de registro
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        String nombre = request.getParameter("nombre");

        try {
            // Comprobamos si el usuario ya existe usando la controladora
            Usuario existingUser = control.traerUsuarioPorCorreo(correo);
            if (existingUser != null) {
                // Redirigir a error.jsp si el usuario ya existe
                request.setAttribute("errorMessage", "El correo ya está registrado.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
                dispatcher.forward(request, response);
                return;
            }

            // Creamos un nuevo usuario usando la controladora
            Usuario nuevoUsuario = new Usuario(correo, contrasena, nombre);
            control.crearUsuario(nuevoUsuario);
            response.sendRedirect("index");  // Redirige a la página de inicio (index)

        } catch (Exception e) {
            e.printStackTrace();
            // En caso de error al registrar, redirigir a la página de error
            request.setAttribute("errorMessage", "Error al registrar el usuario: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

}
