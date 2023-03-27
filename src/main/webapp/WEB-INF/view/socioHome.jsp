<html>
<head>
    <title>Socio</title>
</head>
<body>
<h1>BIENVENIDO, Manolo</h1>
<h2>Tus datos:</h2>
<h4>Primer nombre: </h4> <input value="Manolo">
<button type="submit">Modificar</button>
<h4>Segundo nombre: </h4> <input value="">
<button type="submit">Modificar</button>
<h4>Primer apellido: </h4> <input value="Gómez">
<button type="submit">Modificar</button>
<h4>Segundo apellido: </h4> <input value="Maldonado">
<button type="submit">Modificar</button>
<h4>Fecha de nacimiento: </h4> <input type="date" value="2002-02-27">
<button type="submit">Modificar</button>
<h4>Contraseña:</h4> <input type="password" value="carmen00">
<button type="submit">Modificar</button>
<h4>Rol: </h4> Socio
<h2>Tu empresa:</h2>
<h4>MANZANEROS S.A.</h4>
<button>Modificar</button>
<h2>Lista de socios/autorizados:</h2>
<table>
    <h5>Filtrar: <input></h5>
    <tr>
        <th style="width:50%; border: 1px solid black;  border-collapse: collapse;">Nombre</th>
        <th style="width:50%; border: 1px solid black;  border-collapse: collapse;">Rol</th>
        <th style="width:50%; border: 1px solid black;  border-collapse: collapse;">Empresa</th>
        <th></th>
    </tr>
    <tr>
        <td style="width:50%; border: 1px solid black;  border-collapse: collapse;">Manolo Gómez Maldonado</td>
        <td style="width:50%; border: 1px solid black;  border-collapse: collapse;">Socio</td>
        <td style="width:50%; border: 1px solid black;  border-collapse: collapse;">MANZANEROSS S.A.</td>
        <td>
            <button type="submit">BLOQUEAR</button>
        </td>
        <td>
            <button>VER OPERACIONES</button>
        </td>
    </tr>
    <tr>
        <td style="width:50%; border: 1px solid black;  border-collapse: collapse;">Ana Conda</td>
        <td style="width:50%; border: 1px solid black;  border-collapse: collapse;">Autorizado</td>
        <td style="width:50%; border: 1px solid black;  border-collapse: collapse;">Mexico</td>
        <td>
            <button type="submit">BLOQUEAR</button>
        </td>
        <td>
            <button>VER OPERACIONES</button>
        </td>
    </tr>
    <tr>


    </tr>
</table>

<h2>Transferencia:</h2>
Cuenta bancaria recibidor del dinero: <input type="text"><br>
Dinero: <input type="text"><br>
<br>
<button>Hacer una transferencia</button>


<h2>Cambio de divisas:</h2>
Dinero: <input type="text"><br>
<label for="moneda">Nueva moneda: </label><br>

<select name="monedas" id="moneda">
    <option value="1">Dolares</option>
    <option value="2">Libras</option>
    <option value="3">Euros</option>
    <option value="4">Yenes</option>
</select>
<br>
<br>
<button>Cambiar divisa</button>

<h2>Activar cuenta: </h2>
<h3>Oh no, estás bloqueado.</h3>
<button>Solicitar desbloqueo</button>
</body>
</html>