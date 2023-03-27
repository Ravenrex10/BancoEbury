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
</head>
<body>
    <h1>Iniciar sesión:</h1>
    <form action="/" method="post">
        <label>Usuario: </label>
        <input type="text" name="usuario">
        <label>Contraseña: </label>
        <input type ="text" name="clave">
        <button type="submit">Enviar</button>
    </form>
</body>
</html>
