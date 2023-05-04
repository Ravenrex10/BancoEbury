<!--@author Diego-->
<%@ page import="com.ebury.entity.EmpresaEntity" %>
<%@ page import="com.ebury.dto.EmpresaDTO" %>
<%@ page import="com.ebury.dto.UsuarioDTO" %>
<%@ page import="com.ebury.dto.CuentaDTO" %>
<html>
<head>
    <title>Empresa Home</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<% UsuarioDTO usuarioDTO = (UsuarioDTO) request.getAttribute("usuario");
    EmpresaDTO empresaDTO = (EmpresaDTO) request.getAttribute("empresa");%>
<jsp:include page="empresaHeader.jsp"></jsp:include>
<h1>
    <div class="container">
        Bienvenid@, <%=usuarioDTO.getPrimerNombre()%>
    </div>
</h1>
<h2>
    <div class="container">
        <%=empresaDTO.getNombre()%>
    </div>

</h2>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>