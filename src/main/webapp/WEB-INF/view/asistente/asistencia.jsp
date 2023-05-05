<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="java.util.List" %>
<%@ page import="com.ebury.dto.UsuarioDTO" %>
<%@ page import="com.ebury.dto.ChatDTO" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- @author Daniel --%>
<html>
<head>
    <title>Centro de ayuda</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
</head>
<%
    List<UsuarioDTO> usuarios = (List<UsuarioDTO>) request.getAttribute("usuarios");
    List<ChatDTO> chats = (List<ChatDTO>) request.getAttribute("chats");
%>
<body>
<div class="container">
<h1>CENTRO DE AYUDA</h1>
<table>
    <tr>
        <th>Asistente</th>
        <th>Fecha</th>
        <th></th>
    </tr>
    <% for (ChatDTO chat : chats) { %>
    <tr>
        <td><%=chat.getNombreUsuarioB()%></td>
        <td>
            <a href="/chat?chatId=<%=chat.getId()%>">Ir al chat</a>
        </td>
        <td>
            <strong><%=chat.isCerrado()? "Cerrado" : ""%></strong>
        </td>
    </tr>
    <% } %>
    <h3>Necesitas ayuda? Solicita un chat con un asistente:</h3>
    <%-- <form action="/nuevoChat" method="post">
        <select name="chatUserId">
            <% for (UsuarioDTO usuario: usuarios) { %>
            <option value="<%=usuario.getId()%>"><%=usuario.getPrimerNombre()%></option>
            <% } %>
        </select>
        <button>Nuevo chat</button>
    </form> --%>
</table>
<form action="/asistencia/asignarAsistente" method="post">
    <button>Nuevo chat</button>
</form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
