<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ebury.dto.UsuarioDTO" %>
<%@ page import="com.ebury.dto.EmpresaDTO" %>
<html>
    <head>
        <title>Gestor</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    </head>
    <body>
    <%
        List<UsuarioDTO> usuarios = (List<UsuarioDTO>) request.getAttribute("usuarios");
        List<EmpresaDTO> empresas = (List<EmpresaDTO>) request.getAttribute("empresas");
    %>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#">Usuarios</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="gestorAlta">Solicitud de alta</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Transferencias Sospechosas</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Usuarios inactivos</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <br>
            <div class="container card">
                <h2>Usuarios</h2>
                <form:form method="post" action="filtrarUsuarios" modelAttribute="filtroUsuarios">
                    <form:select path="filtro">
                        <form:option value="0" label=" "/>
                        <form:options items="${roles}" />
                    </form:select>
                    <form:button>Filtrar</form:button>
                </form:form>

                <table>
                    <tr>
                        <th>NIF</th>
                        <th>Nombre</th>
                        <th>Segundo Nombre</th>
                        <th>Apellidos</th>
                    </tr>
                    <%
                        for(UsuarioDTO usuario: usuarios){

                    %>
                    <tr>
                        <td>
                            <%=usuario.getNif()%>
                        </td>
                        <td>
                            <%=usuario.getPrimerNombre()%>
                        </td>
                        <td>
                            <%
                                if(usuario.getSegundoNombre()!=null){
                            %>
                            <%=usuario.getSegundoNombre()%>
                            <%
                                }
                            %>
                        </td>
                        <td>
                            <%=usuario.getPrimerApellido() + " "+usuario.getSegundoApellido()%>
                        </td>
                        <td><a href="informacionUsuario?usuario=<%=usuario.getId()%>" class="button">Ver Información</a></td>
                        <%
                            }
                        %>
                    </tr>
                </table>
                <br>

            </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>