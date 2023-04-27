-- MariaDB dump 10.19  Distrib 10.11.2-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: BancoEbury
-- ------------------------------------------------------
-- Server version	10.11.2-MariaDB

USE BancoEbury;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Chat`
--

DROP TABLE IF EXISTS `Chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Chat` (
  `id` int(11) NOT NULL auto_increment,
  `clienteA` int(11) DEFAULT NULL,
  `clienteB` int(11) DEFAULT NULL,
  `cerrado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `clienteA` (`clienteA`),
  KEY `clienteB` (`clienteB`),
  CONSTRAINT `Chat_ibfk_1` FOREIGN KEY (`clienteA`) REFERENCES `Usuario` (`id`),
  CONSTRAINT `Chat_ibfk_2` FOREIGN KEY (`clienteB`) REFERENCES `Usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Chat`
--

LOCK TABLES `Chat` WRITE;
/*!40000 ALTER TABLE `Chat` DISABLE KEYS */;
/*!40000 ALTER TABLE `Chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cuenta`
--

DROP TABLE IF EXISTS `Cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cuenta` (
  `id` int(11) NOT NULL auto_increment,
  `iban` varchar(24) DEFAULT NULL,
  `duenyo` int(11) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `duenyo` (`duenyo`),
  KEY `estado` (`estado`),
  CONSTRAINT `Cuenta_ibfk_1` FOREIGN KEY (`duenyo`) REFERENCES `Usuario` (`id`),
  CONSTRAINT `Cuenta_ibfk_2` FOREIGN KEY (`estado`) REFERENCES `EstadoCuenta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cuenta`
--

LOCK TABLES `Cuenta` WRITE;
/*!40000 ALTER TABLE `Cuenta` DISABLE KEYS */;
/*!40000 ALTER TABLE `Cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Direccion`
--

DROP TABLE IF EXISTS `Direccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Direccion` (
  `id` int(11) NOT NULL auto_increment,
  `calle` varchar(20) DEFAULT NULL,
  `numero` varchar(10) DEFAULT NULL,
  `planta` varchar(20) DEFAULT NULL,
  `ciudad` varchar(20) DEFAULT NULL,
  `pais` varchar(20) DEFAULT NULL,
  `region` varchar(20) DEFAULT NULL,
  `cp` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Direccion`
--

LOCK TABLES `Direccion` WRITE;
/*!40000 ALTER TABLE `Direccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `Direccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Divisa`
--

DROP TABLE IF EXISTS `Divisa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Divisa` (
  `id` int(11) NOT NULL auto_increment,
  `nombre` varchar(20) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Divisa`
--

LOCK TABLES `Divisa` WRITE;
/*!40000 ALTER TABLE `Divisa` DISABLE KEYS */;
/*!40000 ALTER TABLE `Divisa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Empresa`
--

DROP TABLE IF EXISTS `Empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Empresa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cif` int(11) NOT NULL DEFAULT '0',
  `nombre` varchar(20) DEFAULT NULL,
  `direccion` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `direccion` (`direccion`),
  CONSTRAINT `Empresa_ibfk_1` FOREIGN KEY (`direccion`) REFERENCES `Direccion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Empresa`
--

LOCK TABLES `Empresa` WRITE;
/*!40000 ALTER TABLE `Empresa` DISABLE KEYS */;
/*!40000 ALTER TABLE `Empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EstadoCuenta`
--

DROP TABLE IF EXISTS `EstadoCuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EstadoCuenta` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EstadoCuenta`
--

LOCK TABLES `EstadoCuenta` WRITE;
/*!40000 ALTER TABLE `EstadoCuenta` DISABLE KEYS */;
INSERT INTO `EstadoCuenta` VALUES
(0,'Desactivada'),
(1,'Activada');
/*!40000 ALTER TABLE `EstadoCuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Mensaje`
--

DROP TABLE IF EXISTS `Mensaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Mensaje` (
  `id` int(11) NOT NULL auto_increment,
  `contenido` varchar(50) DEFAULT NULL,
  `enviadoPorA` tinyint(1) DEFAULT NULL,
  `chat` int(11) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `chat` (`chat`),
  CONSTRAINT `Mensaje_ibfk_1` FOREIGN KEY (`chat`) REFERENCES `Chat` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Mensaje`
--

LOCK TABLES `Mensaje` WRITE;
/*!40000 ALTER TABLE `Mensaje` DISABLE KEYS */;
/*!40000 ALTER TABLE `Mensaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Rol`
--

DROP TABLE IF EXISTS `Rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Rol` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Rol`
--

LOCK TABLES `Rol` WRITE;
/*!40000 ALTER TABLE `Rol` DISABLE KEYS */;
INSERT INTO `Rol` VALUES
(1,'Cliente'),
(2,'SocioEmpresa'),
(3,'Gestor'),
(4,'FundadorEmpresa'),
(5,'Asistente'),
(6,'AutorizadoEmpresa');
/*!40000 ALTER TABLE `Rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Saldo`
--

DROP TABLE IF EXISTS `Saldo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Saldo` (
  `id` int(11) NOT NULL auto_increment,
  `cuenta` int(11) DEFAULT NULL,
  `cantidad` double DEFAULT NULL,
  `divisa` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cuenta` (`cuenta`),
  KEY `divisa` (`divisa`),
  CONSTRAINT `Saldo_ibfk_1` FOREIGN KEY (`cuenta`) REFERENCES `Cuenta` (`id`),
  CONSTRAINT `Saldo_ibfk_2` FOREIGN KEY (`divisa`) REFERENCES `Divisa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Saldo`
--

LOCK TABLES `Saldo` WRITE;
/*!40000 ALTER TABLE `Saldo` DISABLE KEYS */;
/*!40000 ALTER TABLE `Saldo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Transferencia`
--

DROP TABLE IF EXISTS `Transferencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Transferencia` (
  `id` int(11) NOT NULL auto_increment,
  `cuentaOrigen` int(11) DEFAULT NULL,
  `cuentaDestino` int(11) DEFAULT NULL,
  `divisaOrigen` int(11) DEFAULT NULL,
  `cantidad` double DEFAULT NULL,
  `divisaDestino` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cuentaOrigen` (`cuentaOrigen`),
  KEY `cuentaDestino` (`cuentaDestino`),
  KEY `divisaOrigen` (`divisaOrigen`),
  KEY `divisaDestino` (`divisaDestino`),
  CONSTRAINT `Transferencia_ibfk_1` FOREIGN KEY (`cuentaOrigen`) REFERENCES `Cuenta` (`id`),
  CONSTRAINT `Transferencia_ibfk_2` FOREIGN KEY (`cuentaDestino`) REFERENCES `Cuenta` (`id`),
  CONSTRAINT `Transferencia_ibfk_3` FOREIGN KEY (`divisaOrigen`) REFERENCES `Divisa` (`id`),
  CONSTRAINT `Transferencia_ibfk_4` FOREIGN KEY (`divisaDestino`) REFERENCES `Divisa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Transferencia`
--

LOCK TABLES `Transferencia` WRITE;
/*!40000 ALTER TABLE `Transferencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `Transferencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Usuario` (
  `id` int(11) NOT NULL auto_increment,
  `nif` int(11) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `primerNombre` varchar(20) DEFAULT NULL,
  `segundoNombre` varchar(20) DEFAULT NULL,
  `primerApellido` varchar(20) DEFAULT NULL,
  `segundoApellido` varchar(20) DEFAULT NULL,
  `fechaNacimiento` date DEFAULT NULL,
  `contrasenya` varchar(100) DEFAULT NULL,
  `empresa` int(11) DEFAULT NULL,
  `rol` int(11) DEFAULT NULL,
  `alta` tinyint(1) DEFAULT NULL,
  `altaSolicitada` tinyint(1) DEFAULT NULL,
  `fechaUltimaOperacion` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `empresa` (`empresa`),
  KEY `rol` (`rol`),
  CONSTRAINT `Usuario_ibfk_1` FOREIGN KEY (`empresa`) REFERENCES `Empresa` (`id`),
  CONSTRAINT `Usuario_ibfk_2` FOREIGN KEY (`rol`) REFERENCES `Rol` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-18 14:06:29
