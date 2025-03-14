<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Error</title>
</head>
<body>
    <h1>Ocurrió un Error</h1>
    
    <p><strong>Error: </strong><%= request.getAttribute("errorMessage") %></p>
    
    <!-- Enlace para regresar a la página principal -->
    <a href="index">Volver a la página principal</a>
</body>
</html>
