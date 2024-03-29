<!--@author Diego-->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Tus Datos</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<jsp:include page="empresaHeader.jsp"></jsp:include>
<div class="container">
    <form:form method="post" action="/empresa/edit" modelAttribute="newEmpresaWrapper">
        <h1>Datos de la empresa: </h1>
        <form:hidden path="newEmpresa.id"></form:hidden>
        Cif (*): <form:input path="newEmpresa.cif"></form:input><br>
        Nombre de la empresa (*): <form:input path="newEmpresa.nombre"></form:input><br>

        <h1>Dirección: </h1>

        <form:hidden path="newDireccion.id"></form:hidden>
        Calle (*): <form:input path="newDireccion.calle"></form:input><br>
        Número (*): <form:input path="newDireccion.numero"></form:input><br>
        Planta/Puerta/Oficina: <form:input path="newDireccion.planta"></form:input><br>
        Ciudad (*): <form:input path="newDireccion.ciudad"></form:input><br>
        País (*): <form:input path="newDireccion.pais"></form:input><br>
        Código Postal (*): <form:input path="newDireccion.cp"></form:input><br>

        <h1>Tu usuario:</h1>
        <form:hidden path="newUsuario.id"></form:hidden>
        NIF (*): <form:input path="newUsuario.nif"></form:input><br>
        Primer nombre (*): <form:input path="newUsuario.primerNombre"></form:input><br>
        Segundo nombre: <form:input path="newUsuario.segundoNombre"></form:input><br>
        Primer apellido (*): <form:input path="newUsuario.primerApellido"></form:input><br>
        Segundo apellido (*): <form:input path="newUsuario.segundoApellido"></form:input><br>
        Fecha de nacimiento (*): <form:input type="date" path="newUsuario.fechaNacimiento"></form:input><br>
        Email (*): <form:input path="newUsuario.email"></form:input><br>
        Contraseña (*): <form:input path="newUsuario.contrasenya" type="password"></form:input>

        <form:button type="submit" value="Submit" class="btn btn-primary">Editar</form:button>
    </form:form>

</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>