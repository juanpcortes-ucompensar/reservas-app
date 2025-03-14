<%@ page import="java.util.List" %>
<%@ page import="com.miproyecto.model.Reserva" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Lista de Reservas</title>
</head>
<body>
    <h1>Lista de Reservas</h1>
    
    <!-- Mostrar todas las reservas -->
    <table border="1">
        <thead>
            <tr>
                <th>Espacio</th>
                <th>Fecha de Reserva</th>
                <th>Usuario</th>
                <th>Duración</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Reserva> reservas = (List<Reserva>) request.getAttribute("reservas");
                if (reservas != null) {
                    for (Reserva reserva : reservas) {
            %>
                <tr>
                    <td><%= reserva.getEspacio() %></td>
                    <td><%= reserva.getFechaReserva() %></td>
                    <td><%= reserva.getUsuario().getNombre() %></td>
                    <td><%= reserva.getDuracion() %> horas</td>
                    <!-- Acciones -->
                    <td>
                        <a href="eliminarReserva?id=<%= reserva.getId() %>">Eliminar</a>
                    </td>
                </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>

    <br>
    <a href="index">Regresar al inicio</a>
</body>
</html>
