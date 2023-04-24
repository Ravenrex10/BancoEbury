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
        <table style="width: 100%">
            <tr>
                <td style="width: 50%;">
                    <div class="container card">
                        <h2>Clientes</h2>
                        <%
                            for(UsuarioDTO usuario: usuarios){

                        %>
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
                        <br>
                        <%
                            }
                        %>
                    </div>
                </td>
                <td style="width: 50%;">
                    <div class="container card">
                        <h2>Empresas</h2>
                        <%
                            for(EmpresaDTO empresa: empresas){
                        %>
                        <%=empresa.getNombre()%>
                        <%
                            }
                        %>
                    </div>
                </td>
            </tr>
        </table>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>