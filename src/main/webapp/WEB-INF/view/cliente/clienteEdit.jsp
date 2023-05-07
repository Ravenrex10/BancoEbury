<!--@author Diego-->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Tus Datos</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<jsp:include page="clienteHeader.jsp"></jsp:include>

<div class="container">
    <form:form method="post" action="/registerUsuario/register" modelAttribute="newUsuarioWrapper">

        <form:hidden path="newUsuario.id"></form:hidden>
        NIF (*): <form:input path="newUsuario.nif"></form:input><br>
        Email (*): <form:input path="newUsuario.email"></form:input><br>
        Contraseña (*): <form:input path="newUsuario.contrasenya" type="password"></form:input><br>
        Primer nombre (*): <form:input path="newUsuario.primerNombre"></form:input><br>
        Segundo nombre: <form:input path="newUsuario.segundoNombre"></form:input><br>
        Primer apellido (*): <form:input path="newUsuario.primerApellido"></form:input><br>
        Segundo apellido (*): <form:input path="newUsuario.segundoApellido"></form:input><br>
        Fecha de nacimiento (*): <form:input type="date" path="newUsuario.fechaNacimiento"></form:input><br>

        <form:button type="submit" value="Submit" >Editar</form:button>

    </form:form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
