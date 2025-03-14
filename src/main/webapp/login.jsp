<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Inicio de Sesión</title>
</head>
<body>
    <h1>Inicio de Sesión</h1>

    <!-- Formulario para iniciar sesión -->
    <form action="login" method="post">
        <label for="correo">Correo:</label><br>
        <input type="email" id="correo" name="correo" required><br>

        <label for="contrasena">Contraseña:</label><br>
        <input type="password" id="contrasena" name="contrasena" required><br><br>

        <button type="submit">Iniciar sesión</button>
    </form>

    <hr>

    <!-- Enlace para volver al inicio -->
    <a href="index">Volver al inicio</a>
</body>
</html>
