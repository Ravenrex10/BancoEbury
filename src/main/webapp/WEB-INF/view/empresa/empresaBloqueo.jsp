<!--@author Diego-->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.ebury.entity.EmpresaEntity" %>
<%@ page import="com.ebury.dto.EmpresaDTO" %>
<%@ page import="com.ebury.dto.UsuarioDTO" %>
<html>
<head>
    <title>Bloquear</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<jsp:include page="empresaHeader.jsp"></jsp:include>
<h1>
    <div class="container">
        Bloquear usuario
    </div>
</h1>
<div class="container">
    <h2>
        Elija al usuario que desee bloquear:
    </h2>
    <form:form modelAttribute="blockUser" method="post" action="/empresa/bloquear">
        <form:select path="id" itemLabel="email" itemValue="id" items="${listaUsuarios}"></form:select><br><br>
        <form:button type="submit" class="btn btn-danger">Bloquear</form:button>
    </form:form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>