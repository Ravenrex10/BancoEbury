<%@ page import="java.util.List" %>
<%@ page import="com.ebury.dto.CuentaDTO" %>
<html lang="es">
<head>
    <title>Gestor</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<%
    List<CuentaDTO> cuentas = (List<CuentaDTO>) request.getAttribute("cuentasSospechosas");
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
                    <a class="nav-link active" aria-current="page" href="#">Transferencias Sospechosas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="inactivos">Usuarios inactivos</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<br>
<div class="container card">
    <h2>Transferencias Sospechosas</h2>
    <div class="d-flex align-items-center justify-content-between">
        <div class="col">
            <table class="table">
                <%
                    for(CuentaDTO cuenta: cuentas){
                %>
                <tr>
                    <td>
                        <b>Iban: </b><%=cuenta.getIban()%> <br>
                        <b>Propietario: </b> <%=cuenta.getUsuario().getPrimerNombre() +
                            " "+ cuenta.getUsuario().getSegundoNombre() +
                            " " + cuenta.getUsuario().getPrimerApellido() +
                            " " + cuenta.getUsuario().getSegundoApellido()%>
                    </td>
                    <td>
                        <a href="desactivarCuenta?cuenta=<%=cuenta.getId()%>" class="btn btn-danger">Desactivar Cuenta</a>
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