<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.miproyecto.model.Usuario" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Gestión de Reservas</title>
</head>
<body>
    <!-- Redireccion a servlet para cargar usuarios -->
    <%
        String action = (String) request.getAttribute("action");
        if (action == null || !action.equals("listar")) {
    %>   
    <script type="text/javascript">
        window.location.href = "/reservas-app/index";
    </script>
    <%
        } else {
    %>
    <h1>Bienvenido a la Gestión de Reservas</h1>

    <!-- Comprobar si hay usuarios -->
    <%
        List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
        if (usuarios == null || usuarios.isEmpty()) {
    %>        
        <h2>No hay usuarios registrados. Por favor, regístrate primero.</h2>
    <%
        } else {
    %>
        <!-- Si hay usuarios registrados, mostrar formulario para crear una reserva -->
        <h2>Crear Nueva Reserva</h2>
        <form action="reservas" method="post">
            
            <!-- Seleccionar usuario -->
            <label for="usuario">Seleccionar Usuario:</label><br>
            <select id="usuario" name="usuarioCorreo" required>
                <option value="" disabled selected>Selecciona un usuario...</option>
                <%
                    for (Usuario usuario : usuarios) {
                %>
                    <option value="<%= usuario.getCorreo() %>"><%= usuario.getNombre() %> (<%= usuario.getCorreo() %>)</option>
                <%
                    }
                %>
            </select><br><br>

            <!-- Fecha de reserva -->
            <label for="fechaReserva">Fecha de Reserva:</label><br>
            <input type="date" id="fechaReserva" name="fechaReserva" required><br><br>

            <!-- Espacio de trabajo -->
            <label for="espacio">Espacio de trabajo:</label><br>
            <select id="espacio" name="espacio" required>
                <option value="" disabled selected>Selecciona un espacio...</option>
                <option value="escritorio">Escritorio</option>
                <option value="sala">Sala de reuniones</option>
                <option value="oficina">Oficina privada</option>
            </select><br><br>

            <!-- Duración de la reserva -->
            <label for="duracion">Duración (horas):</label><br>
            <input type="number" id="duracion" name="duracion" min="1" required><br><br>

            <!-- Botón para crear la reserva -->
            <button type="submit">Crear Reserva</button>
        </form>
    <%
        }
    %>

    <hr>

    <!-- Enlace a la página de registro -->
    <a href="registro.jsp">Registrar un nuevo usuario</a>
    <br>
    <!-- Enlace a las reservas -->
    <a href="reservas?action=listar">Ver todas las reservas</a>
    <%
        }
    %>
</body>
</html>
