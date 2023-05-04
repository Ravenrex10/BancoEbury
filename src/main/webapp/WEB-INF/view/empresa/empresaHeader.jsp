<!--@author Diego-->
<%@ page import="com.ebury.dto.UsuarioDTO" %>
<%@ page import="java.util.List" %><% UsuarioDTO usuarioDTO = (UsuarioDTO) request.getAttribute("usuario");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/empresa/">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/empresa/datos">Datos</a>
                </li>
                <li>
                    <a class="nav-link" href="cuentas">Tus cuentas</a>
                </li>
                <li>
                    <a class="nav-link" href="/empresa/listaTransferencias">Lista de transferencias</a>
                </li>
                <li>
                    <a class="nav-link" href="/empresa/transferencias">Realizar transferencia</a>
                </li>
                <li>
                    <a class="nav-link">Cambio de divisas (TODO)</a>
                </li>
                <% if (usuarioDTO.getRolName().equals("FundadorEmpresa")) { %>
                <li class="nav-item">
                    <a class="nav-link" href="fundadorAlta">Registrar Socio/Autorizado</a>
                </li>
                <% }; %>
                <% if(usuarioDTO.getRolName().equals("FundadorEmpresa") || usuarioDTO.getRolName().equals("SocioEmpresa")) { %>
                <li class="nav-item">
                    <a class="nav-link" href="listaUsuarios">Lista de Socios/Autorizados</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="bloquearUsuarios">Bloquear usuarios</a>
                </li>
                <% }; %>
            </ul>
            <ul class="navbar-nav ms-auto">
                <li class="nav-item ms-auto me-auto">
                    <a class="nav-link" href="/logout">Cerrar sesión</a>
                </li>
            </ul>

        </div>
    </div>
</nav>