
<%@ page import="java.util.List" %>
<%@ page import="com.ebury.dto.UsuarioDTO" %>
<%@ page import="com.ebury.dto.ChatDTO" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chats</title>
</head>
<%
    ChatDTO chat = (ChatDTO) request.getAttribute("chat");
%>
<body>

    <h3>Mostrando chat entre <%=chat.getNombreUsuarioA()%> y <%=chat.getNombreUsuarioB()%>, con ID <%=chat.getId()%></h3>

</body>
</html>