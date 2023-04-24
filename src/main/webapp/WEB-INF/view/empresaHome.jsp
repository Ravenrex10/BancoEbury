<%@ page import="com.ebury.entity.EmpresaEntity" %>
<%@ page import="com.ebury.dto.EmpresaDTO" %>
<html>
<head>
    <title>Empresa Home</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<%EmpresaDTO e = (EmpresaDTO) request.getAttribute("empresa");%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="empresaOperacion">Operaciones</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="empresaAlta">Solicitud de alta</a>
                </li>
            </ul>
            <ul class="navbar-nav ms-auto">
                <li class="nav-item ms-auto me-auto">
                    <a class="nav-link" href="logout">Cerrar sesión</a>
                </li>
            </ul>

        </div>
    </div>
</nav>
<h1>Eres la empresa <%=e.getNombre()%>
</h1>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>