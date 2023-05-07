<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="java.util.List" %>
<%@ page import="com.ebury.dto.UsuarioDTO" %>
<%@ page import="com.ebury.dto.ChatDTO" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- @author Daniel --%>
<html>
<head>
    <title>Chats</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
</head>
<%
    List<UsuarioDTO> usuarios = (List<UsuarioDTO>) request.getAttribute("usuarios");
    List<ChatDTO> chats = (List<ChatDTO>) request.getAttribute("chats");
%>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item ms-auto me-auto">
                    <a class="nav-link" href="/logout">Cerrar sesión</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
<h1>Panel de control de asistente</h1>
<form:form action="/chats/filtrar" modelAttribute="filtro" method="post">
    Ordenar por: <form:select path="criterioOrdenacion">
        <form:option value="ascendente">Más antiguos primero</form:option>
        <form:option value="descendente">Más recientes primero</form:option>
    </form:select> <br/>
    Filtrar por usuario: <form:select path="filtroUsuario">
        <form:option value="-1" label="Todos">Todos</form:option>
        <form:options items="${usuarios}" itemValue="id" itemLabel="email"/>
    </form:select> <br/>
    Mostrar chats cerrados <form:checkbox path="mostrarCerrados" value="true"/>
    Mostrar solo mis chats <form:checkbox path="mostrarSoloPropios" value="true"/>
    <br/><form:button>Actualizar</form:button>
</form:form>
    <h3>Chats</h3>
<table class="table">
    <tr>
        <th>ID</th>
        <th>Usuario solicitante</th>
        <th>Asistente asignado</th>
        <th>Ir a chat</th>
        <th></th>

    </tr>
    <% for (ChatDTO chat : chats) { %>
        <tr>
            <td><%=chat.getId()%></td>
            <td><%=chat.getNombreUsuarioA()%></td>
            <td><%=chat.getNombreUsuarioB()%></td>
            <td>
                <a href="/chat?chatId=<%=chat.getId()%>">Ir al chat</a>
            </td>
            <td><strong><%=chat.isCerrado()? "Cerrado" : ""%></strong></td>
        </tr>
    <% } %>
    <%--<h3>Nuevo chat</h3>
    <form action="/nuevoChat" method="post">
        <select name="chatUserId">
            <% for (UsuarioDTO usuario: usuarios) { %>
                <option value="<%=usuario.getId()%>"><%=usuario.getEmail()%></option>
            <% } %>
        </select>
        <button>Nuevo chat</button>
    </form> --%>

</table>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</html>
