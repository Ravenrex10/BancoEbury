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
<table border="1px solid black">
    <tr>
        <td>
            <form action="/" method="post">
                <h2>Usuario:</h2>
                <label>Usuario: </label><br>
                <input type="text" name="usuario"><br>
                <label>Contraseña: </label><br>
                <input type="text" name="clave"><br>
                <button type="submit">Enviar</button><br>
            </form>
        </td>
        <td>
            <h2>Empresa:</h2>
            <form action="/empresa" method="post">
                <label>CIF: </label><br>
                <input type="text" name="cif"><br>
                <label>Contraseña: </label><br>
                <input type="text" name="clave"><br>
                <button type="submit">Enviar</button>
            </form>
        </td>
    </tr>
</table>

<h1>Registrarse</h1>
<ul>
    <li> Como usuario</li>
    <li><a href="/registerEmpresa">Como empresa</a></li>
</ul>
</body>
</html>
