<%@ page import="com.ebury.entity.EmpresaEntity" %>
<%@ page import="com.ebury.dto.UsuarioDTO" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Empresa Operaciones</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<% UsuarioDTO usuarioDTO = (UsuarioDTO) request.getAttribute("usuario");
    List<UsuarioDTO> listUsuarios = (List<UsuarioDTO>) request.getAttribute("listaUsuarios");
 %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <% if(usuarioDTO.getRolName().equals("FundadorEmpresa")) { %>
                    <a class="nav-link" href="/fundadorHome/">Inicio</a>
                    <% }; %>
                    <% if(usuarioDTO.getRolName().equals("SocioEmpresa")) { %>
                    <a class="nav-link" href="/socioHome/">Inicio</a>
                    <% }; %>
                    <% if(usuarioDTO.getRolName().equals("AutorizadoEmpresa")) { %>
                    <a class="nav-link" href="/autorizadoHome/">Inicio</a>
                    <% }; %>
                </li>
                <% if (usuarioDTO.getRolName().equals("FundadorEmpresa")) { %>
                <li class="nav-item">
                    <a class="nav-link" href="fundadorAlta">Solicitud de alta</a>
                </li>
                <% }; %>
                <% if(usuarioDTO.getRolName().equals("FundadorEmpresa") ||usuarioDTO.getRolName().equals("SocioEmpresa")) { %>
                <li class="nav-item">
                    <a class="nav-link" href="listaUsuarios">Socios/Autorizados</a>
                </li>
                <% }; %>
            </ul>
            <ul class="navbar-nav ms-auto">
                <li class="nav-item ms-auto me-auto">
                    <a class="nav-link" href="/logout">Cerrar sesión</a>
                </li>
            </ul>

        </div>
    </div>
</nav>
<div class="container">
    <h1>Lista de socios/autorizados</h1>
    <div class = "container">
        <table border="1px solid black" class="table">
            <tr>
                <th>NIF</th>
                <th>Primer nombre</th>
                <th>Segundo nombre</th>
                <th>Primer Apellido</th>
                <th>Segundo Apellido</th>
                <th>Email</th>
                <th>Rol</th>
            </tr>
            <%
                for(UsuarioDTO u : listUsuarios)
                { %>
            <tr>
                <td><%=u.getNif()%></td>
                <td><%=u.getPrimerNombre()%></td>
                <td><%=u.getSegundoNombre()%></td>
                <td><%=u.getPrimerApellido()%></td>
                <td><%=u.getSegundoApellido()%></td>
                <td><%=u.getEmail()%></td>
                <td><%=u.getRolName()%></td>
            </tr>
            <%};%>
        </table>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>