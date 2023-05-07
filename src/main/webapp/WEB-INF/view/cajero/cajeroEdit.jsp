<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Editar Datos</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>

<div class="container">
    <form:form method="post" action="/cajero/edit" modelAttribute="usuario">

        <form:hidden path="id"></form:hidden>
        NIF (*): <form:input path="nif"></form:input><br>
        Email (*): <form:input path="email"></form:input><br>
        Contraseña (*): <form:input path="contrasenya" type="password"></form:input><br>
        Primer nombre (*): <form:input path="primerNombre"></form:input><br>
        Segundo nombre: <form:input path="segundoNombre"></form:input><br>
        Primer apellido (*): <form:input path="primerApellido"></form:input><br>
        Segundo apellido (*): <form:input path="segundoApellido"></form:input><br>
        Fecha de nacimiento (*): <form:input type="date" path="fechaNacimiento"></form:input><br>
        <br>

        <form:button type="submit" value="Submit" class="btn btn-primary btn-lg">Editar</form:button>

    </form:form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>