<!--@author Diego-->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.ebury.entity.UsuarioEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ebury.dto.UsuarioDTO" %>
<html>
<head>
    <title>Solicitud de Alta</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<% UsuarioDTO usuarioDTO = (UsuarioDTO) request.getAttribute("usuario"); %>
<jsp:include page="empresaHeader.jsp"></jsp:include>
<div class="container">
    <h1>Solicitud de alta</h1>
    <form:form method="post" action="/empresa/solicitarAlta" modelAttribute="newUsuarioDTO">
        <form:hidden path="id"></form:hidden>
        NIF: <form:input path="nif"></form:input><br>
        Primer nombre: <form:input path="primerNombre"></form:input><br>
        Segundo nombre: <form:input path="segundoNombre"></form:input><br>
        Primer apellido: <form:input path="primerApellido"></form:input><br>
        Segundo apellido: <form:input path="segundoApellido"></form:input><br>
        Rol <form:select path="rolName">
        <form:option value="SocioEmpresa" label="Socio">
        </form:option>
        <form:option value="AutorizadoEmpresa" label="Autorizado"></form:option>
    </form:select><br>
        Fecha de nacimiento: <form:input type="date" path="fechaNacimiento"></form:input><br>
        Email: <form:input path="email"></form:input><br>
        Contraseña: <form:input path="contrasenya" type="password"></form:input><br>
        <form:button class="btn btn-primary" type="submit" value="Submit">Solicitar alta</form:button>

    </form:form>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>