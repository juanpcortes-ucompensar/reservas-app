<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Registro de Usuario</title>
</head>
<body>
    <h1>Registrar Usuario</h1>

    <!-- Formulario para el registro de usuario -->
    <form action="index" method="post">
        <label for="nombre">Nombre:</label><br>
        <input type="text" id="nombre" name="nombre" required><br>

        <label for="correo">Correo:</label><br>
        <input type="email" id="correo" name="correo" required><br>

        <label for="contrasena">Contraseña:</label><br>
        <input type="password" id="contrasena" name="contrasena" required><br><br>

        <button type="submit">Registrar</button>
    </form>

    <hr>

    <!-- Enlace a la página principal -->
    <a href="index">Volver a la página principal</a>
</body>
</html>
