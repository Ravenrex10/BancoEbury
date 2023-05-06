<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!--Juan Salmerón-->
<%@ page import="com.ebury.dto.UsuarioDTO" %>
<html>
<head>
    <title>Realizar Transferencia</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<% UsuarioDTO usuarioDTO = (UsuarioDTO) request.getAttribute("usuario"); %>
<div class="container">
    <h1>
        Transferencia
    </h1>
    <form:form modelAttribute="newTransferencia" method="post" action="/cajero/transferir">
        Cuenta origen: <form:select path="cuentaOrigen.id" itemLabel="iban" itemValue="id" items="${cuentasUsuario}"></form:select><br>
        Cuenta destino: <form:select path="cuentaDestino.id" itemLabel="iban" itemValue="id" items="${cuentasDestino}"></form:select><br>
        Cantidad: <form:input path="cantidad"></form:input><br>
        <form:button type="submit" class="btn btn-primary">Transferir</form:button>
    </form:form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>