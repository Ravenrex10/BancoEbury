<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.ebury.entity.UsuarioEntity" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Empresa Solicitud de Alta</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/fundadorHome/">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="fundadorOperacion">Operaciones</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Solicitud de alta</a>
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
    <h1>Solicitud de alta</h1>
    <form:form method="post" action="/fundadorHome/solicitarAlta" modelAttribute="newUsuarioDTO">
        <form:hidden path="id"></form:hidden>
        NIF: <form:input path="nif"></form:input><br>
        Primer nombre: <form:input path="primerNombre"></form:input><br>
        Segundo nombre: <form:input path="segundoNombre"></form:input><br>
        Primer apellido: <form:input path="primerApellido"></form:input><br>
        Segundo apellido: <form:input path="segundoApellido"></form:input><br>
        Rol <form:select path="rolName">
        <form:option value="SocioEmpresa" label="Socio">
        </form:option>
        <form:option value="AutorizadoEmpresa" label="Autorizado"></form:option>
    </form:select><br>
        Fecha de nacimiento: <form:input type="date" path="fechaNacimiento"></form:input><br>
        Email: <form:input path="email"></form:input><br>
        Contraseña: <form:input path="contrasenya" type="password"></form:input>
        <input type="submit" value="Submit">

    </form:form>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>