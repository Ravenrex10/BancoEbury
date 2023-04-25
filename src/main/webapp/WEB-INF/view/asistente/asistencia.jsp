<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="java.util.List" %>
<%@ page import="com.ebury.dto.UsuarioDTO" %>
<%@ page import="com.ebury.dto.ChatDTO" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CENTRO DE AYUDA</title>
</head>
<%
    List<UsuarioDTO> usuarios = (List<UsuarioDTO>) request.getAttribute("usuarios");
    List<ChatDTO> chats = (List<ChatDTO>) request.getAttribute("chats");
%>
<body>
<table>
    <h1>CHATS</h1>
    <tr>
        <th>Asistente</th>
        <th>Fecha</th>
        <th></th>
        <th></th>
    </tr>
    <% for (ChatDTO chat : chats) { %>
    <tr>
        <td><%=chat.getNombreUsuarioB()%></td>
        <td>N/A</td>
        <td>N/A</td>
        <td>
            <a href="/chat?chatId=<%=chat.getId()%>">Ir al chat</a>
        </td>
        <td>
            <a href="/chat/bloquear?chatId=<%=chat.getId()%>">Bloquear</a>
        </td>
    </tr>
    <% } %>
    <h3>Necesitas ayuda? Solicita un chat con un asistente:</h3>
    <form action="/nuevoChat" method="post">
        <select name="chatUserId">
            <% for (UsuarioDTO usuario: usuarios) { %>
            <option value="<%=usuario.getId()%>"><%=usuario.getPrimerNombre()%></option>
            <% } %>
        </select>
        <button>Nuevo chat</button>
    </form>

</table>
</body>
</html>
