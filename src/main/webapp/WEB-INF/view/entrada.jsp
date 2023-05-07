<%@ page import="com.ebury.entity.UsuarioEntity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--
    @author Lucas Colbert Eastgate
    @author Dani
    @author Diego
    @author Jaime
    @author Juan Salmerón
-->
<html>
<head>
    <title>Iniciar sesión</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">

</head>
<body>
<div class="container">
<h1 class="display-1 text-center"><strong>Banco Ebury</strong></h1>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-7">
            <h1>Iniciar sesión:</h1>
            <form action="/" method="post">
                <h2>Usuario:</h2>
                <label>Usuario: </label><br>
                <input type="text" name="usuario"><br>
                <label>Contraseña: </label><br>
                <input type="password" name="clave"><br><br>
                <button class="btn btn-primary" type="submit">Iniciar sesión</button>
                <br>
            </form>
        </div>
        <div class="col-md-3">
            <div class="container">
                <h1>Registrarse:</h1>
                <br>
                <ul class="list-group">
                     <a class="list-group-item list-group-item-action" href="/registerUsuario"> Como usuario</a></li>
                     <a class="list-group-item list-group-item-action" href="/registerEmpresa">Como empresa</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
