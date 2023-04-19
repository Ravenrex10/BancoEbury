
<%@ page import="java.util.List" %>
<%@ page import="com.ebury.dto.UsuarioDTO" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chats</title>
</head>
<%
    UsuarioDTO usuario = (UsuarioDTO) request.getAttribute("usuario");
%>
<body>

    <h3>Chat iniciado con <%=usuario.getEmail()%></h3>

</body>
</html>