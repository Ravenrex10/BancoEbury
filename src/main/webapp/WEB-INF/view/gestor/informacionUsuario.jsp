<%@ page import="com.ebury.dto.UsuarioDTO" %>
<%@ page import="com.ebury.dto.EmpresaDTO" %>
<%@ page import="com.ebury.dto.TransferenciaDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ebury.dto.CuentaDTO" %>
<html>
    <head>
        <title>Informacion</title>
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
                        <a class="nav-link" href="/gestorHome/">Usuarios</a>
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
        <%
            UsuarioDTO usuario = (UsuarioDTO) request.getAttribute("usuario");
            List<TransferenciaDTO> transferencias = (List<TransferenciaDTO>) request.getAttribute("transferencias");
            List<CuentaDTO> cuentas = (List<CuentaDTO>) request.getAttribute("cuentas");
        %>
        <div class="card container">
            <table>
                <tr>
                    <td style="width: 33%">
                        <h3>Datos:</h3>
                        NIF: <%=usuario.getNif()%><br>
                        Nombre: <%=usuario.getPrimerNombre() + " " + usuario.getSegundoNombre()%><br>
                        Apellidos: <%=usuario.getPrimerApellido() + " " + usuario.getSegundoApellido()%><br>
                    </td>
                    <td style="width: 50%">
                        <h3>Cuentas</h3>
                        <%
                            for(CuentaDTO cuenta : cuentas){
                        %>
                        IBAN: <%=cuenta.getIban()%>
                        <%
                            }
                        %>
                    </td>
                    <td>
                        <h3>Transferencias:</h3>
                        <%
                            for(TransferenciaDTO transferencia : transferencias){
                        %>
                        Cantidad: <%=transferencia.getCantidad()%>
                        <%
                            }
                        %>

                    </td>
                </tr>
                <tr>
                    <%
                        if(usuario.getEmpresa()!=null){
                    %>
                    <h3>Empresa</h3>
                    Nombre:
                    <%
                        }
                    %>
                </tr>
            </table>
        </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>