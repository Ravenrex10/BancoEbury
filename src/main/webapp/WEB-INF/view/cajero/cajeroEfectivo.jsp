<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!--Juan Salmerón-->
<%@ page import="com.ebury.dto.UsuarioDTO" %>
<html>
<head>
    <title>Sacar dinero en efectivo</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<% UsuarioDTO usuarioDTO = (UsuarioDTO) request.getAttribute("usuario"); %>
<div class="container">
    <h1>
        Sacar Efectivo
    </h1>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>