/*
SQLyog Community v13.3.1 (64 bit)
MySQL - 8.0.44 : Database - ticket_system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ticket_system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `ticket_system`;

/*Table structure for table `t_departamentos` */

DROP TABLE IF EXISTS `t_departamentos`;

CREATE TABLE `t_departamentos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `extension` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `t_departamentos` */

insert  into `t_departamentos`(`id`,`nombre`,`descripcion`,`extension`) values 
(1,'Sistemas','Departamento de tecnología y soporte técnico','ext. 100'),
(2,'Recursos Humanos','Gestión de personal y nómina','ext. 200'),
(3,'Biblioteca','Servicios bibliotecarios y préstamos','ext. 300'),
(4,'Registro','Gestión de matrícula y registros académicos','ext. 400'),
(5,'Financiero','Gestión de pagos y finanzas estudiantiles','ext. 500'),
(6,'Servicios Generales','jkajshnjahajhfa','correo@correo.com'),
(7,'fff','fff','fff');

/*Table structure for table `t_palabras_emocionales` */

DROP TABLE IF EXISTS `t_palabras_emocionales`;

CREATE TABLE `t_palabras_emocionales` (
  `id` int NOT NULL AUTO_INCREMENT,
  `palabra` varchar(100) NOT NULL,
  `emocion` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `palabra` (`palabra`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `t_palabras_emocionales` */

insert  into `t_palabras_emocionales`(`id`,`palabra`,`emocion`) values 
(1,'enojado','Frustración'),
(2,'frustrado','Frustración'),
(3,'urgente','Urgencia'),
(4,'importante','Urgencia'),
(5,'gracias','Positivo'),
(6,'excelente','Positivo'),
(7,'problema','Negativo'),
(8,'mal','Negativo'),
(9,'consultar','Neutral');

/*Table structure for table `t_palabras_tecnicas` */

DROP TABLE IF EXISTS `t_palabras_tecnicas`;

CREATE TABLE `t_palabras_tecnicas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `palabra` varchar(100) NOT NULL,
  `categoria` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `palabra` (`palabra`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `t_palabras_tecnicas` */

insert  into `t_palabras_tecnicas`(`id`,`palabra`,`categoria`) values 
(1,'wifi','Redes'),
(2,'internet','Redes'),
(3,'conexion','Conexion'),
(4,'impresora','Impresoras'),
(5,'imprimir','Impresoras'),
(6,'papel','Impresoras'),
(7,'software','Software'),
(8,'hardware','Hardware'),
(9,'usuario','Cuentas'),
(10,'contraseña','Cuentas'),
(11,'login','Cuentas');

/*Table structure for table `t_tickets` */

DROP TABLE IF EXISTS `t_tickets`;

CREATE TABLE `t_tickets` (
  `id` int NOT NULL AUTO_INCREMENT,
  `asunto` varchar(200) NOT NULL,
  `descripcion` text NOT NULL,
  `estado` enum('Nuevo','En progreso','Resuelto') DEFAULT 'Nuevo',
  `fecha_creacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `usuario_id` int NOT NULL,
  `departamento_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `departamento_id` (`departamento_id`),
  CONSTRAINT `t_tickets_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `t_usuarios` (`id`) ON DELETE CASCADE,
  CONSTRAINT `t_tickets_ibfk_2` FOREIGN KEY (`departamento_id`) REFERENCES `t_departamentos` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `t_tickets` */

insert  into `t_tickets`(`id`,`asunto`,`descripcion`,`estado`,`fecha_creacion`,`usuario_id`,`departamento_id`) values 
(1,'Pago atrasado','Pago atrasado','Nuevo','2025-12-04 20:32:44',2,2),
(2,'Molestia con colaborador','Estoy muy enojado y esto es muy urgente....','Resuelto','2025-12-04 20:33:59',2,2),
(3,'sss','sss','Nuevo','2025-12-04 21:05:57',5,7);

/*Table structure for table `t_usuarios` */

DROP TABLE IF EXISTS `t_usuarios`;

CREATE TABLE `t_usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `rol` enum('administrador','estudiante','funcionario') NOT NULL,
  `fecha_registro` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `correo` (`correo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `t_usuarios` */

insert  into `t_usuarios`(`id`,`nombre`,`correo`,`contrasena`,`telefono`,`rol`,`fecha_registro`) values 
(1,'Administrador Principal','admin@ucenfotec.ac.cr','placeholder','8888-8888','administrador','2025-12-04 20:11:34'),
(2,'Jose','correo@correo.com','xDufjZRhV3gxtdjsK7FfrA==$B7GgKHJ/sgUlCdQj2LpN14vQ+xmpbA+9QUAcb/S6jLY=','123456789','estudiante','2025-12-04 20:28:07'),
(3,'Funcionario Ejemplo','funcionario@ucenfotec.ac.cr','placeholder','7777-7777','funcionario','2025-12-04 20:53:54'),
(4,'Estudiante Ejemplo','estudiante@ucenfotec.ac.cr','placeholder','6666-6666','estudiante','2025-12-04 20:53:54'),
(5,'eee','eee','eXSiAr7XsRo0VPX9AKgAKQ==$urWd0slh4YLvI/+1agO/bpj4R4PgXiIYD7ntb+8iDqo=','eee','estudiante','2025-12-04 21:05:21');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
