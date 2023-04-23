<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Registro de empresa</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<div class="container">
    <form:form method="post" action="/registerEmpresa/register" modelAttribute="newEmpresaWrapper">
">
        <h1>Datos de la empresa: </h1>
        <form:hidden path="id"></form:hidden>
        Cif (*): <form:input path="newEmpresa.cif"></form:input><br>
        Nombre de la empresa (*): <form:input path="newEmpresa.nombre"></form:input><br>
        Contraseña (*): <form:input path="newEmpresa.contrasenya"></form:input>

        <h1>Dirección: </h1>

        <form:hidden path="newDireccion.id"></form:hidden>
        Calle (*): <form:input path="newDireccion.calle"></form:input><br>
        Número (*): <form:input path="newDireccion.numero"></form:input><br>
        Planta/Puerta/Oficina (*): <form:input path="newDireccion.planta"></form:input><br>
        Ciudad (*): <form:input path="newDireccion.ciudad"></form:input><br>
        País (*): <form:input path="newDireccion.pais"></form:input><br>
        Código Postal (*): <form:input path="newDireccion.cp"></form:input><br>

        <input type="submit" value="Submit">
    </form:form>

</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>