<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="java.util.List" %>
<%@ page import="com.ebury.dto.UsuarioDTO" %>
<%@ page import="com.ebury.dto.ChatDTO" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- @author Daniel --%>
<html>
<head>
    <title>Chats</title>
</head>
<%
    List<UsuarioDTO> usuarios = (List<UsuarioDTO>) request.getAttribute("usuarios");
    List<ChatDTO> chats = (List<ChatDTO>) request.getAttribute("chats");
%>
<body>
<h1>Chats</h1>
<form:form action="/filtrarChats" modelAttribute="filtro" method="post">
    Ordenar por: <form:select path="criterioOrdenacion">
        <form:option value="ascendente">Fecha de creación ascendente</form:option>
        <form:option value="descendente">Fecha de creación descendente</form:option>
    </form:select>
    Mostrar chats cerrados<form:checkbox path="mostrarCerrados" value="true"/>
    Mostrar solo mis chats<form:checkbox path="mostrarSoloPropios" value="true"/>
    <br/><form:button>Actualizar</form:button>
</form:form>
<table>
    <tr>
        <th>Usuario solicitante</th>
        <th>Asistente asignado</th>
        <th>Fecha</th>
        <th>Último mensaje</th>
        <th>Ir a chat</th>
        <th>Cerrado</th>

    </tr>
    <% for (ChatDTO chat : chats) { %>
        <tr>
            <td><%=chat.getNombreUsuarioA()%></td>
            <td><%=chat.getNombreUsuarioB()%></td>
            <td>N/A</td>
            <td>N/A</td>
            <td>
                <a href="/chat?chatId=<%=chat.getId()%>">Ir al chat</a>
            </td>
            <td><strong><%=chat.isCerrado()? "Cerrado" : ""%></strong></td>
        </tr>
    <% } %>
    <h3>Nuevo chat</h3>
    <form action="/nuevoChat" method="post">
        <select name="chatUserId">
            <% for (UsuarioDTO usuario: usuarios) { %>
                <option value="<%=usuario.getId()%>"><%=usuario.getEmail()%></option>
            <% } %>
        </select>
        <button>Nuevo chat</button>
    </form>

</table>
</body>
</html>
