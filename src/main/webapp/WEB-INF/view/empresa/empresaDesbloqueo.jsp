<!--@author Diego-->
<%@ page import="com.ebury.entity.EmpresaEntity" %>
<%@ page import="com.ebury.dto.EmpresaDTO" %>
<%@ page import="com.ebury.dto.UsuarioDTO" %>
<%@ page import="com.ebury.dto.CuentaDTO" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Empresa Cuentas</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<% List<CuentaDTO> cuentasUsuario = (List<CuentaDTO>) request.getAttribute("cuentasUsuario"); %>
<jsp:include page="empresaHeader.jsp"></jsp:include>
<h1>
    <div class="container">
        Tus cuentas
    </div>
</h1>
<div class="container">
    <table class="table">
        <% for (CuentaDTO c : cuentasUsuario) { %>
        <tr>
            <td>
            <%=c.getIban()%>
            </td>
            <td>
                <%=c%>
            </td>
            <td>
                <%=c.getEstado()%>
            </td>
            <td>
            <%if(c.getEstado().equals("Bloqueada")){ %>
                <a href="solicitarDesbloqueo?cuenta=<%=c.getId()%>" class="btn btn-primary">Solicitar Desbloqueo</a>
            <%}%>
            </td>
        </tr>
        <%}%>
    </table>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>