<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>

</head>

<body>
<h1> Registrar usuario </h1>
<h2> Datos de usuario </h2>

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

    <input type="submit" value="Submit">

</form:form>


</body>
</html>