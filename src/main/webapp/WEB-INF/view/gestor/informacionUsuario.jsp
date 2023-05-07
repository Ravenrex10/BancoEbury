<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ebury.dto.*" %>
<html>
    <head>
        <title>Informacion</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                        <a class="nav-link active" aria-current="page" href="/gestorHome/">Usuarios</a>
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
        <%
            UsuarioDTO usuario = (UsuarioDTO) request.getAttribute("usuario");
            List<TransferenciaDTO> transferencias = (List<TransferenciaDTO>) request.getAttribute("transferencias");
            List<CuentaDTO> cuentas = (List<CuentaDTO>) request.getAttribute("cuentas");
            List<SaldoDTO> saldos = (List<SaldoDTO>) request.getAttribute("saldos");
            EmpresaDTO empresa = (EmpresaDTO) request.getAttribute("empresa");
        %>
        <div class="card container">
            <table>
                <tr>
                    <td style="width: 33%">
                        <h3>Datos:</h3>
                        <b>NIF: </b><%=usuario.getNif()%>
                        <b>Nombre: </b><%=usuario.getPrimerNombre() + " " + usuario.getSegundoNombre()%>
                        <b>Apellidos: </b><%=usuario.getPrimerApellido() + " " + usuario.getSegundoApellido()%>
                    </td>
                </tr>
                <tr>
                    <td style="width: 50%">
                        <table class="table">
                            <tr>
                                <th><h3>Cuentas</h3></th>
                            </tr>
                            <%
                                for(CuentaDTO cuenta : cuentas){
                            %>
                            <tr>
                                <td>
                                    IBAN: <%=cuenta.getIban()%> <br>
                                    <table>
                                        <%
                                        for(SaldoDTO saldo : saldos){
                                        %>
                                        <tr>
                                            <td>
                                                <div class="card container">
                                                    Divisa: <%=saldo.getDivisa()%> <br>
                                                    Saldo: <%=saldo.getCantidad()%>
                                                </div>
                                            </td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </table>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                        </table>
                    </td>
                    <td>
                        <table class="table">
                            <tr>
                                <th><h3>Transferencias</h3></th>
                                <td>
                                    <form:form method="post" action="filtrarTransferencias" modelAttribute="filtroTransferencias">
                                        <form:hidden path="usuario"></form:hidden>
                                        <form:select path="filtro">
                                            <form:option value="0" label=" " />
                                            <form:options items="${filtroItems}" />
                                        </form:select>
                                        <form:button>Filtrar</form:button>
                                    </form:form>
                                </td>
                                <td>
                                    <form:form method="post" action="ordenar" modelAttribute="ordenTransferencias">
                                        <form:hidden path="usuarioId"></form:hidden>
                                        <form:select path="orden">
                                            <form:option value="Fecha Descendente"></form:option>
                                            <form:option value="Fecha Ascendente"></form:option>
                                            <form:option value="Cantidad Ascendente"></form:option>
                                            <form:option value="Cantidad Descendente"></form:option>
                                        </form:select>
                                        <form:button>Ordenar</form:button>
                                    </form:form>
                                </td>
                            </tr>
                            <%
                                for(TransferenciaDTO transferencia : transferencias){
                            %>
                            <tr>
                                <td>
                                    <%
                                        if(transferencia.getCuentaOrigen().getUsuario().getId() == usuario.getId()){
                                    %>
                                    Enviado: <%=transferencia.getCantidad()+" "+transferencia.getDivisaOrigen()%> <br>
                                    Destino: <%=transferencia.getCuentaDestino().getUsuario().getPrimerNombre()%> <br>
                                    <%
                                        }else{
                                    %>
                                    Recibido: <%=transferencia.getCantidad()+" "+transferencia.getDivisaDestino()%> <br>
                                    Origen: <%=transferencia.getCuentaOrigen().getUsuario().getPrimerNombre()%> <br>
                                    <%
                                        }
                                    %>
                                    <%=transferencia.getFecha()%>

                                </td>
                            </tr>
                            <%
                                }
                            %>
                        </table>
                    </td>
                </tr>
                <tr>
                    <%
                        if(usuario.getEmpresa()!=null){
                    %>
                    <h3>Empresa</h3>
                    <b>Nombre: </b><%=empresa.getNombre()%>
                    <%
                        }
                    %>
                </tr>
            </table>
        </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>