<%@ page import="com.ebury.entity.UsuarioEntity" %>
<%@ page import="java.util.List" %>
<html>
    <head>
        <title>Gestor</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    </head>
    <body>
    <%
        List<UsuarioEntity> usuarios = (List<UsuarioEntity>) request.getAttribute("usuarios");
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
            <%
                for(UsuarioEntity usuario: usuarios){

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
            <%=usuario.getFechaNacimiento()%>
            <br>
            <%
                }
            %>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>