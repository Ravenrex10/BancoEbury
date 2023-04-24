<%@ page import="com.ebury.dto.EmpresaDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Registro de socio</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<% EmpresaDTO empresaDTO = (EmpresaDTO) request.getAttribute("empresaRegistered");%>
<div class="container">
    <h1>Vamos a registrar a un socio para la empresa <%=empresaDTO.getNombre()%></h1>
    <form:form method="post" action="/registerSocio/register" modelAttribute="newUser">
        <h1>Datos del socio: </h1>


        <input type="submit" value="Submit">
    </form:form>

</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>