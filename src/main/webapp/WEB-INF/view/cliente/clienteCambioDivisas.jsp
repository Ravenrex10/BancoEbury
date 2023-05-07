<!--@author Diego-->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.ebury.entity.EmpresaEntity" %>
<%@ page import="com.ebury.dto.EmpresaDTO" %>
<%@ page import="com.ebury.dto.UsuarioDTO" %>
<%@ page import="com.ebury.dto.CuentaDTO" %>
<html>
<head>
    <title>Cambio de Divisas</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<% UsuarioDTO usuarioDTO = (UsuarioDTO) request.getAttribute("usuario");%>
<jsp:include page="clienteHeader.jsp"></jsp:include>
<h1>
    <div class="container">
        Cambio de divisa:
    </div>
</h1>
<div class="container">
    <form:form method="post" action="/cliente/cambiarDivisa" modelAttribute="newDivisa">
        Cuenta: <form:select path="cuentaId" items="${cuentaDTOS}" itemLabel="iban" itemValue="id"></form:select><br>
        Divisa: <form:select path="divisaNombre" items="${divisas}"></form:select><br>
        <form:button type="submit" value="Submit" class="btn btn-primary">Cambiar divisa</form:button>
    </form:form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
