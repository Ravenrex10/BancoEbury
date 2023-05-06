<%@ page import="java.util.List" %>
<%@ page import="com.ebury.dto.UsuarioDTO" %>
<%@ page import="com.ebury.dto.CuentaDTO" %>
<html lang="es">
<head>
    <title>Gestor</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<%
    List<CuentaDTO> cuentas = (List<CuentaDTO>) request.getAttribute("cuentasBloqueadas");
%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/gestorHome/">Usuarios</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="gestorAlta">Solicitud de alta</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="cuentasSospechosas">Transferencias Sospechosas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="inactivos">Usuarios inactivos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Solicitudes de desbloqueo</a>
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
<br>
<div class="container card">
    <h2>Solicitudes de Desbloqueo</h2>
    <div class="d-flex align-items-center justify-content-between">
        <div class="col">
            <table class="table">
                <%
                    for(CuentaDTO cuenta: cuentas){
                %>
                <tr>
                    <td>
                        <%=cuenta.getIban()%>
                        <%=cuenta.getUsuario().getPrimerNombre()+" "
                                +cuenta.getUsuario().getSegundoNombre()+" "
                                +cuenta.getUsuario().getPrimerApellido()+" "
                                +cuenta.getUsuario().getSegundoApellido()%>
                    </td>
                    <td>
                        <a href="informacionUsuario?usuario=<%=cuenta.getUsuario().getId()%>" class="btn btn-primary">Ver información de Usuario</a>
                        <a href="desbloquear?cuenta=<%=cuenta.getId()%>" class="btn btn-success">Desbloquear Cuenta</a>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>