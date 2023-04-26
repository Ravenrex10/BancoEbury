<%@ page import="com.ebury.entity.EmpresaEntity" %>
<%@ page import="com.ebury.dto.EmpresaDTO" %>
<%@ page import="com.ebury.dto.UsuarioDTO" %>
<html>
<head>
    <title>Empresa Home</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<% UsuarioDTO usuarioDTO = (UsuarioDTO) request.getAttribute("usuario"); %>
<jsp:include page="empresaHeader.jsp"></jsp:include>
<h1>
    <div class="container">
        Bienvenid@, <%=usuarioDTO.getPrimerNombre()%>
    </div>
</h1>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>