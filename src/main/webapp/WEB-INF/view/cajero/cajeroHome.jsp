<%@ page import="com.ebury.dto.UsuarioDTO" %>
<html>
<head>
    <title>Cajero Ebury</title>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<% UsuarioDTO usuarioDTO = (UsuarioDTO) request.getAttribute("usuario");%>
    <h1> Bienvenido/a, <%=usuarioDTO.getPrimerNombre()%></h1>
    <button style="height:50px;width:200px">Sacar dinero</button>
    <button style="height:50px;width:200px">Realizar transferencia</button>
    <br>
    <button style="height:50px;width:200px">Cambio de divisa</button>
    <button style="height:50px;width:200px">Revisar operaciones</button>
    <br>
    <br>
    <h5>¿Quieres modificar tus datos? Haz click aquí</h5>
</body>
</html>