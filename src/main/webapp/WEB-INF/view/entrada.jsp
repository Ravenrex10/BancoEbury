<%@ page import="com.ebury.entity.UsuarioEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: raven
  Date: 20/3/23
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">

</head>
<body>
<div class="container">
    <h1>Iniciar sesión:</h1>
    <form action="/" method="post">
        <h2>Usuario:</h2>
        <label>Usuario: </label><br>
        <input type="text" name="usuario"><br>
        <label>Contraseña: </label><br>
        <input type="password" name="clave"><br>
        <button type="submit">Enviar</button>
        <br>
    </form>
</div>

<div class="container">
    <h1>Registrarse</h1>
    <ul>
        <li> Como usuario</li>
        <li><a href="/registerEmpresa">Como empresa</a></li>
    </ul>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
