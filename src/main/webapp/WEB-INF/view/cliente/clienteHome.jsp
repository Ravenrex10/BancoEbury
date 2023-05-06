<%@ page import="com.ebury.entity.UsuarioEntity" %>
<%@ page import="java.util.List" %><%--
@author Daniel
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cliente</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="">Mis Datos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Realizar Transferencia</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="">Historial de operaciones</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="">Bloquear Cuenta</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/asistencia">Servicio de asistencia</a>
                </li>
            </ul>
            <ul class="navbar-nav ms-auto">
                <li class="nav-item ms-auto me-auto">
                    <a class="nav-link" href="logout">Cerrar sesión</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <h1> Usuario Home</h1>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
