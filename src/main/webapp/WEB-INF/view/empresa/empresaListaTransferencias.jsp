<%@ page import="com.ebury.dto.TransferenciaDTO" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Empresa Transferencias</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<jsp:include page="empresaHeader.jsp"></jsp:include>
<% List<TransferenciaDTO> transferenciaDTO = (List<TransferenciaDTO>) request.getAttribute("transferencias"); %>
<div class="container">
    <h1>Lista de transferencias</h1>
    <div class = "container">
        <table border="1px solid black" class="table">
            <tr>
                <th>Cuenta Origen</th>
                <th>Cuenta Destino</th>
                <th>Cantidad</th>
                <th>Divisa usada</th>
                <th>Fecha</th>
            </tr>
            <% for(TransferenciaDTO t : transferenciaDTO)
            { %>
            <tr>
                <td><%=t.getCuentaOrigen().getIban()%></td>
                <td><%=t.getCuentaDestino().getIban()%></td>
                <td><%=t.getCantidad()%></td>
                <td><%=t.getDivisaOrigen()%></td>
                <td><%=t.getFecha()%></td>
            </tr>
            <% }%>
        </table>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>