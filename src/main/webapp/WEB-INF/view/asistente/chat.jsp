
<%@ page import="java.util.List" %>
<%@ page import="com.ebury.dto.UsuarioDTO" %>
<%@ page import="com.ebury.dto.ChatDTO" %>
<%@ page import="com.ebury.entity.MensajeEntity" %>
<%@ page import="com.ebury.dto.MensajeDTO" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chats</title>
</head>
<%
    ChatDTO chat = (ChatDTO) request.getAttribute("chat");
    List<MensajeDTO> mensajes = (List<MensajeDTO>) request.getAttribute("mensajes");
    boolean usuarioPuedeEscribir = (boolean) request.getAttribute("usuarioPuedeEscribir");
%>
<body>

    <h3>Mostrando chat entre <%=chat.getNombreUsuarioA()%> y <%=chat.getNombreUsuarioB()%>, con ID <%=chat.getId()%></h3>
    <table>
        <tr>
            <th>Fecha</th>
            <th>Mensaje</th>
        </tr>
        <% for (MensajeDTO mensaje : mensajes) { %>
            <tr>
                <td><%=mensaje.getFecha()%></td>
                <td>
                    <% if (mensaje.isEnviadoPorUsuarioActual()) { %>
                        <strong><%=mensaje.getContenido()%></strong>
                    <% } else { %>
                        <%=mensaje.getContenido()%>
                    <% } %>
                </td>
            </tr>
        <% } %>
    </table>
    <% if (usuarioPuedeEscribir) { %>
    <form action="/enviarMensaje" method="post">
        <input type="text" name="mensaje" maxlength="50"/>
        <input type="hidden" name="chatId" value="<%=chat.getId()%>"/>
        <button>Enviar</button>
    </form>
    <% } else { %>
        <h5>No puedes enviar mensajes a este chat. <h5>
    <% } %>
            <a href="/volverAChats">Volver a chats</a>
</body>
</html>