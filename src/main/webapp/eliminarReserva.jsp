<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.miproyecto.model.Reserva" %>

<html>
<head>
    <title>Confirmación de Eliminación</title>
</head>
<body>
    <h1>¿Estás seguro de que deseas eliminar esta reserva?</h1>

    <!-- Mostrar los detalles de la reserva -->
    <%
        // Recuperamos la reserva pasada desde el servlet
        Reserva reserva = (Reserva) request.getAttribute("reserva");
        if (reserva != null) {
    %>
        <p><strong>Espacio reservado:</strong> <%= reserva.getEspacio() %></p>
        <p><strong>Fecha de la Reserva:</strong> <%= reserva.getFechaReserva() %></p>
        <p><strong>Usuario:</strong> <%= reserva.getUsuario().getNombre() %></p>
        <p><strong>Duración:</strong> <%= reserva.getDuracion() %> horas</p>

        <!-- Formulario de confirmación de eliminación -->
        <form action="eliminarReserva" method="post">
            <input type="hidden" name="idReserva" value="<%= reserva.getId() %>">
            <button type="submit">Sí, eliminar</button>
        </form>
    <%
        } else {
    %>
        <p>No se encontró la reserva.</p>
    <%
        }
    %>

    <!-- Enlace para cancelar y regresar a la lista de reservas -->
    <a href="reservas?action=listar">Cancelar y regresar</a>

</body>
</html>
