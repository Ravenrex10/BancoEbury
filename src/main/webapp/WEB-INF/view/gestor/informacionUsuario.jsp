<%@ page import="com.ebury.dto.UsuarioDTO" %>
<%@ page import="com.ebury.dto.EmpresaDTO" %>
<html>
    <head>
        <title>Informacion</title>
    </head>
    <body>
        <%
            UsuarioDTO usuario = (UsuarioDTO) request.getAttribute("usuario");
        %>
        <table>
            <tr>
                <td>
                    <h3>Datos:</h3>
                    NIF: <%=usuario.getNif()%><br>
                    Nombre: <%=usuario.getPrimerNombre() + " " + usuario.getSegundoNombre()%><br>
                    Apellidos: <%=usuario.getPrimerApellido() + " " + usuario.getSegundoApellido()%><br>
                </td>
                <td>
                    <h3>Transferencias:</h3>
                </td>
            </tr>
            <tr>
                <%
                    if(usuario.getEmpresa()!=null){
                %>
                <h3>Empresa</h3>
                Nombre:
                <%
                    }
                %>
            </tr>
        </table>
    </body>
</html>