<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!--Juan Salmerón-->
<%@ page import="com.ebury.dto.UsuarioDTO" %>
<html>
<head>
    <title>Sacar dinero en efectivo</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<jsp:include page="cajeroHeader.jsp"></jsp:include>

<% UsuarioDTO usuarioDTO = (UsuarioDTO) request.getAttribute("usuario"); %>
<div class="container">
    <h1>
        Sacar Efectivo
    </h1>

    <form:form modelAttribute="newTransferencia" method="post" action="/cajero/sacarEfectivo">
        Cuenta origen: <form:select path="cuentaOrigen.id" itemLabel="iban" itemValue="id" items="${cuentasUsuario}"></form:select>
        <br>
        <br>
        Cantidad: <form:input path="cantidad"></form:input>
        <br>
        <br>
        <form:button type="submit" class="btn btn-primary btn-lg">Sacar Efectivo</form:button>
    </form:form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>