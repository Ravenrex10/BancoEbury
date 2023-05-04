<!--@author Diego-->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.ebury.entity.EmpresaEntity" %>
<%@ page import="com.ebury.dto.UsuarioDTO" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Empresa Operaciones</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<% UsuarioDTO usuarioDTO = (UsuarioDTO) request.getAttribute("usuario");
    List<UsuarioDTO> listUsuarios = (List<UsuarioDTO>) request.getAttribute("listaUsuarios");
 %>
<jsp:include page="empresaHeader.jsp"></jsp:include>
<div class="container">
    <h1>Lista de socios/autorizados</h1>
    <div class = "container">
        <form:form modelAttribute="newFiltro" action="/empresa/filtrarLista" method="post">
            <form:select path="filtro">
                <form:option value="0" label=" "></form:option>
                <form:options items="${rolesList}"></form:options>
            </form:select>
            <form:button type="submit" class="btn btn-primary">Filtrar</form:button>
        </form:form>
    </div>
    <div class = "container">
        <table border="1px solid black" class="table">
            <tr>
                <th>NIF</th>
                <th>Primer nombre</th>
                <th>Segundo nombre</th>
                <th>Primer Apellido</th>
                <th>Segundo Apellido</th>
                <th>Email</th>
                <th>Rol</th>
            </tr>
            <%
                for(UsuarioDTO u : listUsuarios)
                { %>
            <tr>
                <td><%=u.getNif()%></td>
                <td><%=u.getPrimerNombre()%></td>
                <td><%=u.getSegundoNombre()%></td>
                <td><%=u.getPrimerApellido()%></td>
                <td><%=u.getSegundoApellido()%></td>
                <td><%=u.getEmail()%></td>
                <td><%=u.getRolName()%></td>
            </tr>
            <%};%>
        </table>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>