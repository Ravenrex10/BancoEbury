DROP DATABASE BancoEbury;
CREATE DATABASE BancoEbury;

USE BancoEbury;

-- socio, autorizado, gestor, asistente, particular
CREATE TABLE Rol (
    id INT,
    nombre VARCHAR(20),
    PRIMARY KEY (id)
);

-- Activo, Bloqueado, PendienteActivacion, PendienteBloqueo
CREATE TABLE EstadoCuenta (
    id INT,
    nombre VARCHAR(20),
    PRIMARY KEY (id)
);

CREATE TABLE Direccion(
    id INT, 
    calle VARCHAR(20),
    numero VARCHAR(10),
    planta VARCHAR(20),
    ciudad VARCHAR(20),
    pais VARCHAR(20),
    region VARCHAR(20),
    cp VARCHAR(10),
    PRIMARY KEY (id)
);

CREATE TABLE Empresa (
    cif INT,
    nombre VARCHAR(20),
    direccion INT,
    -- ...
    PRIMARY KEY (cif),
    FOREIGN KEY (direccion) REFERENCES Direccion(id)
);

CREATE TABLE Usuario (
    id INT, 
    primerNombre VARCHAR(20),
    segundoNombre VARCHAR(20),
    primerApellido VARCHAR(20),
    segundoApellido VARCHAR(20),
    fechaNacimiento DATE,
    contrasenya VARCHAR(100),
    -- referencia a la empresa, si es socio o autorizado
    empresa INT, 
    rol INT,
    PRIMARY KEY (id),
    FOREIGN KEY (empresa) REFERENCES Empresa(cif),
    FOREIGN KEY (rol) REFERENCES Rol(id)
);


/*
CREATE TABLE Socio (
    cliente INT,
    empresa INT,
    FOREIGN KEY (cliente) REFERENCES Cliente(id),
    FOREIGN KEY (empresa) REFERENCES Empresa(cif)
);

CREATE TABLE Autorizado (
    cliente INT,
    empresa INT,
    FOREIGN KEY (cliente) REFERENCES Cliente(id),
    FOREIGN KEY (empresa) REFERENCES Empresa(cif)
); */
 

-- en principio una cuenta puede tener varios saldos, cada uno en una divisa
-- distinta
CREATE TABLE Cuenta (
    id INT,
    duenyo INT,
    estado INT,
    -- estado: Activada, Desactivada, PendienteBloqueo, PendienteDesbloqueo
    PRIMARY KEY (id),
    FOREIGN KEY (duenyo) REFERENCES Usuario(id),
    FOREIGN KEY (estado) REFERENCES EstadoCuenta(id)
);


CREATE TABLE Divisa (
    id INT,
    nombre VARCHAR(20),
    valor DOUBLE, -- valor actual de la divisa (como referencia podemos usar USD por ejemplo)
    PRIMARY KEY (id)
);

CREATE TABLE Saldo(
    id INT,
    cuenta INT,
    cantidad DOUBLE,
    divisa INT,
    PRIMARY KEY (id),
    FOREIGN KEY (cuenta) REFERENCES Cuenta(id),
    FOREIGN KEY (divisa) REFERENCES Divisa(id)
);

-- transferencia de dinero entre dos cuentas
CREATE TABLE Transferencia (
    id INT, -- ID de esta transferencia
    cuentaOrigen INT, -- ID de la cuenta que envía el dinero
    cuentaDestino INT, -- ID de la cuenta que recibe el dinero
    divisaOrigen INT, -- ID de la divisa utilizada
    cantidad DOUBLE, -- Cantidad enviada en la transferencia
    divisaDestino INT,
    fecha DATE,
    PRIMARY KEY (id),
    FOREIGN KEY (cuentaOrigen) REFERENCES Cuenta(id),
    FOREIGN KEY (cuentaDestino) REFERENCES Cuenta(id),
    FOREIGN KEY (divisaOrigen) REFERENCES Divisa(id),
    FOREIGN KEY (divisaDestino) REFERENCES Divisa(id)
);
/*
CREATE TABLE CambioDivisa (
    id INT,
    cuenta INT, -- ID de la cuenta donde se hace el cambio de divisa
    divisaOrigen INT, -- ID de la divisa original
    cantidad DOUBLE, -- cantidad de la divisa original que se quiere cambiar a la divisa nueva
    divisaNueva INT, -- ID de la nueva divisa
    fecha DATE,
    PRIMARY KEY (id),
    FOREIGN KEY (cuenta) REFERENCES Cuenta(id),
    FOREIGN KEY (divisaOrigen) REFERENCES Divisa(id),
    FOREIGN KEY (divisaNueva) REFERENCES Divisa(id)
);  */

-- una conversación (conjunto de mensajes) entre dos personas
CREATE TABLE Chat (
    id INT, 
    clienteA INT, -- ID de un cliente de la conversacion
    clienteB INT, -- ID del otro cliente de la conversacion
    PRIMARY KEY (id),
    FOREIGN KEY (clienteA) REFERENCES Usuario(id),
    FOREIGN KEY (clienteB) REFERENCES Usuario(id)
);

CREATE TABLE Mensaje (
    id INT,
    contenido VARCHAR(50), -- Contenido del mensaje, hay que decidir la longitud maxima
    enviadoPorA BOOLEAN, -- Para distinguir quien ha enviado cada mensaje en una conversacion
    chat INT, -- ID del chat al que pertenece el mensaje
    fecha DATE, -- Instante en el que se envió el mensaje
    PRIMARY KEY (id),
    FOREIGN KEY (chat) REFERENCES Chat(id)
);
