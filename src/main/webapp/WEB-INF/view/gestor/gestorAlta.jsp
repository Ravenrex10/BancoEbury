<!--Autor: Lucas Colbert Eastgate-->
<%@ page import="java.util.List" %>
<%@ page import="com.ebury.dto.UsuarioDTO" %>
<html>
<head>
    <title>Gestor</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<%
    List<UsuarioDTO> usuarios = (List<UsuarioDTO>) request.getAttribute("usuariosSinAlta");
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
                    <a class="nav-link active" aria-current="page" href="#">Solicitud de alta</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="cuentasSospechosas">Transferencias Sospechosas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="inactivos">Usuarios inactivos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="bloqueadas">Solicitudes de desbloqueo</a>
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
    <h2>Usuarios</h2>
    <div class="d-flex align-items-center justify-content-between">
        <div class="col">
            <table>
                <%
                    for(UsuarioDTO usuario: usuarios){
                %>
                <tr>
                    <td>
                        <%=usuario.getPrimerNombre()%>
                        <%
                            if(usuario.getSegundoNombre()!=null){
                        %>
                        <%=usuario.getSegundoNombre()%>
                        <%
                            }
                        %>
                        <%=usuario.getPrimerApellido()%>
                        <%=usuario.getSegundoApellido()%>
                    </td>
                    <td>
                        <a href="informacionUsuario?usuario=<%=usuario.getId()%>" class="btn btn-primary">Ver información</a>
                        <a href="darDeAlta?usuario=<%=usuario.getId()%>" class="btn btn-primary">Validar</a>
                        <a href="denegarAlta?usuario=<%=usuario.getId()%>" class="btn btn-danger">No validar</a>
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