<%@ page import="com.ebury.dto.CuentaDTO" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Pedir desbloqueo</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<% List<CuentaDTO> cuentasUsuario = (List<CuentaDTO>) request.getAttribute("cuentasUsuario");%>
<jsp:include page="cajeroHeader.jsp"></jsp:include>
<h1>
    <div class="container">
        Tus cuentas
    </div>
</h1>
<div class="container">
    <table class="table">
        <thead>
        <th>IBAN</th>
        <th>Estado</th>
        <th>Saldo</th>
        </thead>
        <% for (CuentaDTO cuenta : cuentasUsuario) { %>
        <tr>
            <td>
                <%=cuenta.getIban()%>
            </td>
            <td>
                <%=cuenta.getEstado()%>
            </td>
            <td>
                <%if(cuenta.getEstado().equals("Bloqueada")){ %>
                <a href="solicitarDesbloqueo?cuenta=<%=cuenta.getId()%>" class="btn btn-primary btn-lg">Solicitar Desbloqueo</a>
                <%} else {%>
                <a href="solicitarDesbloqueo?cuenta=<%=cuenta.getId()%>" class="btn btn-primary btn-lg disabled" >Solicitar Desbloqueo</a>
                <%}%>
            </td>

        </tr>
        <%}%>
    </table>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>