<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.ebury.dto.UsuarioDTO" %>
<html>
<head>
    <title>Cambiar Divisas</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<jsp:include page="cajeroHeader.jsp"></jsp:include>

<% UsuarioDTO usuarioDTO = (UsuarioDTO) request.getAttribute("usuario");%>
<h1>
    <div class="container">
        Cambio de divisa:
    </div>
</h1>
<div class="container">
    <form:form method="post" action="/cajero/cambiarDivisa" modelAttribute="newDivisa">
        Cuenta: <form:select path="cuentaId" items="${cuentaDTOS}" itemLabel="iban" itemValue="id"></form:select>
        <br>
        Divisa: <form:select path="divisaNombre" items="${divisas}"></form:select>
        <br>
        <br>
        <form:button type="submit" value="Submit" class="btn btn-primary btn-lg">Cambiar divisa</form:button>
    </form:form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>