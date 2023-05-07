<!--@author Jaime-->
<%--
@author Daniel
--%>
<%@ page import="com.ebury.entity.UsuarioEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ebury.dto.UsuarioDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% UsuarioDTO usuarioDTO = (UsuarioDTO) request.getAttribute("usuario");%>

<html>
<head>
    <title>Cliente</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">

</head>
<body>
<jsp:include page="clienteHeader.jsp"></jsp:include>

<div class="container">
    <h1> Usuario Home</h1>
    <h2><%= usuarioDTO.getPrimerNombre()%>
    </h2>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
