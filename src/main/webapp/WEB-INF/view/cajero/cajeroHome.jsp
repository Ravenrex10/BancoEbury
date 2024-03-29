<!--Juan Salmerón-->
<%@ page import="com.ebury.dto.UsuarioDTO" %>
<html>
<head>
    <title>Cajero Ebury</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<jsp:include page="cajeroHeader.jsp"></jsp:include>

<% UsuarioDTO usuarioDTO = (UsuarioDTO) request.getAttribute("usuario");%>
<div class="container">
    <h1> Bienvenido/a, <%=usuarioDTO.getPrimerNombre()%></h1>
    <div class="container">
    <a class="btn btn-primary btn-lg" href="/cajero/efectivo" role="button">Sacar dinero</a>
    <a class="btn btn-primary btn-lg" href="/cajero/transferencias" role="button">Realizar transferencia</a>
    <br>
    <a class="btn btn-primary btn-lg" href="/cajero/cambioDivisa" role="button">Cambiar divisas</a>
    <a class="btn btn-primary btn-lg" href="/cajero/listaTransferencias" role="button">Revisar operaciones</a>
    <br>
    <br>
    <a href="/cajero/datos" class="link-primary">¿Quieres modificar tus datos? Haz click aquí</a>
    <br>
    <a href="/cajero/desbloquearCuentas" class="link-danger">¿Cuenta bloqueada? Haga click aquí para pedir su desbloqueo</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>