<%@ page import="com.ebury.entity.UsuarioEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: raven
  Date: 20/3/23
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <h1>Usuarios:</h1>
        <%
            List<UsuarioEntity> usuarios = (List<UsuarioEntity>) request.getAttribute("usuarios");
            for(UsuarioEntity user : usuarios){
            %>
                <%=user.getPrimerNombre()%>
            <% }%>
</body>
</html>
