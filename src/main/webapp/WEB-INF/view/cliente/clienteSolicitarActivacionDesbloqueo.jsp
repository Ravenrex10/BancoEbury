<%@ page import="java.util.List" %>
<%@ page import="com.ebury.dto.CuentaDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cliente</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="clienteHeader.jsp"></jsp:include>

<% List<CuentaDTO> cuentas = (List<CuentaDTO>) request.getAttribute("cuentasUsuario"); %>

<div class="container">
    <h1> Solicitar Activacion / Desbloqueo</h1>
    <h1> Cuentas: </h1>
    <% for( CuentaDTO cuenta : cuentas) { %>

        <%= cuenta.getIban()%>
        <a href="/cliente/solicitarActivacion?id=<%= cuenta.getId()%>" class="button">    Solicitar Activacion </a>
    <% } %>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
