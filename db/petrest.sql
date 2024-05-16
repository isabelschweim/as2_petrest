-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.24-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Volcando estructura para tabla petrest.articulos
CREATE TABLE IF NOT EXISTS `articulos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `referencia` varchar(50) NOT NULL DEFAULT '0',
  `nombre` varchar(50) DEFAULT '""',
  `precio` float DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla petrest.articulos: ~83 rows (aproximadamente)
DELETE FROM `articulos`;
/*!40000 ALTER TABLE `articulos` DISABLE KEYS */;
INSERT INTO `articulos` (`id`, `referencia`, `nombre`, `precio`) VALUES
	(1, 'TA6450', 'ARBOL PARA GATO CON CASA 35X56 CM ', 25.15),
	(2, 'YH2245', 'ARBOL PARA GATO- CARA GATO AZUL 40X30X44 CM ', 30.2),
	(3, 'JC1268', 'BOLSAS RECOGE EXCREMENTOS X4 DE 15 PZAS ', 1.45),
	(4, 'GH1584', 'TRANSPORTIN MALLA GRANDE SURT ', 18.75),
	(5, 'JK2682', 'ARBOL PARA GATOS GRIS ', 18.75),
	(6, 'GX9800', 'ARBOL PARA GATOS GRIS ', 29.2),
	(7, 'PT4593', 'EMPAPADOR 10 PIEZAS 60X90 CM CON ADHESIVOS ', 7.1),
	(8, 'OB9925', 'BEBEDERO MASCOTA 500 ML SURTIDO COLORES ', 2.45),
	(9, 'VK3269', 'JUGUETE PARA PERRO FORMA POLLO ', 1.2),
	(10, 'RB4947', 'JUGUETE PERRO PELOTA FUTBOL ', 1.1),
	(11, 'LQ2656', 'JUEGO PERRO CUERDA 6 MODELOS TAMAÑO PM ', 1.9),
	(12, 'TH9002', 'PROTECTOR DE MASCOTAS PARA COCHE ', 8.25),
	(13, 'KW5933', 'ARBOL PARA GATO AZUL 23X20X40 CM ', 18.9),
	(14, 'TD9547', 'PECERA GRANDE 30X20 CM BLANCA CON ASA ', 11.5),
	(15, 'YG7269', 'PECERA MEDIANA 24X17 CM BLANCA CON ASA ', 7.4),
	(16, 'DT2386', 'CUNA MASCOTA HUELLAS RESPALDO 2 COL MI ', 18.75),
	(17, 'HI2907', 'CUNA MASCOTA OVAL TELA CUADROS SURT 2 ', 22.9),
	(18, 'HT6133', 'TRANSPORTIN MASCOTA TELA CUADROS SURT ', 16.65),
	(19, 'LQ7075', 'CUNA MASCOTA TELA CUADROS 50X70 SURT 2 ', 22.9),
	(20, 'FE3771', 'MANOPLA CEPILLO MASCOTA ', 3.7),
	(21, 'FX9008', 'RODILLO QUITA PELOS DE MASCOTAS +4 ', 1.9),
	(22, 'KO3715', 'CORREA AUTOMATICA DE PERRO 2.5 M NEGRA ', 2.4),
	(23, 'HB8640', 'BEBEDERO PORTATIL MASCOTAS 500 ML ', 2.55),
	(24, 'KS1923', 'PELOTA PINCHO PARA PERRO ', 1.65),
	(25, 'DN5087', 'PECERA PLASTICO C/ASA 27X17X16.5 CM ', 3.65),
	(26, 'BH5878', 'CHAMPU PERROS Y GATOS FLOWER 400 ML ', 7.6),
	(27, 'IF1321', 'BOLSA DE TRANSPORTE  BICOLOR ROJO / GRIS  46*25*12', 19.2),
	(28, 'DA5222', 'BOLSA DE TRANSPORTE  BICOLOR TOPO / GRIS  46*25*12', 19.2),
	(29, 'WV3059', 'COMEDERO DOBLE TAILOR ', 0.95),
	(30, 'KN8749', 'GATERA SURT ELEGANCE ', 3.6),
	(31, 'QG7990', 'COMEDERO DOBLE PEQUEÑO SURT ELEGANCE ', 1.1),
	(32, 'DV4975', 'COLLAR SILICONA 50X2.5CMCMX2MM ACABADO MATE ROSADO', 6.3),
	(33, 'AT6748', 'COLLAR SILICONA 50X2.5CMX2MM ACABADO MATE VERDE ME', 6.3),
	(34, 'IW4131', 'COLLAR SILICONA 50X2.5CMX2MM ACABADO MATE BLANCO ', 6.3),
	(35, 'EP5119', 'COLLAR SILICONA 40X2CMX2 MM ACABADO MATE VERDE MEN', 4.8),
	(36, 'AW5628', 'COLLAR SILICONA 40X2CMX2MM ACABADO MATE GRIS CLARO', 4.8),
	(37, 'BO7760', 'COLLAR SILICONA 40X2CMX2MM. ACABADO MATE BLANCO ', 4.8),
	(38, 'QG2811', 'ARNER REJUSTABLE 50-70CM NEGRO ', 4.5),
	(39, 'YX4942', 'ARNER REJUSTABLE 50-70CM KAKI ', 4.5),
	(40, 'WG9641', 'COLLAR MALLA REGLABLE 45-65 CM * 25 MM AZUL ', 3.8),
	(41, 'FO2644', 'COLLAR MALLA REGLABLE 45-65 CM * 25 MM ROSADO ', 3.8),
	(42, 'NQ5173', 'COLLAR MALLA REGLABLE 45-65 CM * 25 MM VERDE ', 3.8),
	(43, 'NO4192', 'COLLAR MALLA REGLABLE 40-55CM*20 MM AZUL ', 3),
	(44, 'AO1874', 'COLLAR MALLA REGLABLE 40-55CM*20 MM ROSADO ', 3),
	(45, 'EP8975', 'COLLAR MALLA REGLABLE 40-55CM*20 MM VERDE ', 3),
	(46, 'VP6901', 'COLLAR MALLA REGLABLE 30-45 CM*15 MM NEGRO ', 2.4),
	(47, 'WH6296', 'COLLAR MALLA REGLABLE 30-45 CM*15 MM AZUL ', 2.4),
	(48, 'EG2997', 'COLLAR MALLA REGLABLE 30-45 CM*15 MM VERDE ', 2.4),
	(49, 'XB9133', 'COJIN REDONDO POIESTER D60*80 CM THINK PAWSITIVE M', 17.35),
	(50, 'RX4463', 'COJIN REDONDO POIESTER D60*80 CM THINK PAWSITIVE C', 17.35),
	(51, 'PH8056', 'COJIN EN FORMADE HUESO POLIESTER 100*70*15 CM FAT ', 41.55),
	(52, 'KE3905', 'COJIN EN FORMADE HUESO POLIESTER 100*70*15 CM FAT ', 41.55),
	(53, 'BK5962', 'COJIN REDONDO POLYESTER 60*15 CM AMARILLO ', 41.55),
	(54, 'GY6869', 'COLLAR SILICONA 30*1.5 CM * 2 MM ACABADO MATE BLAN', 4),
	(55, 'BD3330', 'COLLAR SILICONA 30*1.5 CM * 2 MM ACABADO MATE GRIS', 4),
	(56, 'YQ8560', 'ARNES NORUEGO EN NYLON Y NEOPRENO TAMAÑO AJUSTABLE', 13.35),
	(57, 'AP5479', 'CORREA MALLA 120*1.5 CM VERDE ', 3.8),
	(58, 'ID6917', 'CORREA MALLA 120*1.5 CM NEGRO ', 3.8),
	(59, 'OT3616', 'JUGUETE PELUCHE MAPACHE POLYESTER SUPER RESISTENTE', 8.3),
	(60, 'QB8031', 'CUERDA ALGODON 2 NUDOS 28 CM ', 2),
	(61, 'XY9425', 'CUERDA EN FORMA DE 8 DE ALGODON CON PUNTO DE PLAST', 2.25),
	(62, 'FE6452', 'TAZON MOTIVO PATAS INOX BLANCO BRILLANT ', 6.55),
	(63, 'NW7248', 'TAZON MOTIVO PATAS INOX BLANCO BRILLANT ', 4.3),
	(64, 'NB3627', 'TAZON MOTIVO PATAS INOX ROJO BRILLANT ', 6.55),
	(65, 'MA2348', 'TAZON MOTIVO PATTAS INOX NEGRO BRILLANT ANTIDESLIZ', 3.3),
	(66, 'AR5832', 'TAZON MOTIVO PATAS INOX 17 CM ROJO BRILLANTE ', 4.3),
	(67, 'KC6558', 'TAZON IMPRESO METAL 21.5 CM LUXURY ANTIDESLIZANTE ', 6.95),
	(68, 'JE4836', 'TAZON IMPRESO METAL 15 CM LUXURY ANTIDESLIZANTE ', 4.55),
	(69, 'AD4555', 'TAZON CABEZA DE GATO IMPRESO EN DOLOMITA BLANC/NEG', 4.55),
	(70, 'AR9081', 'ALFOMBRA RASCADORA PARA GATO DE CARTON ', 16.6),
	(71, 'AS7753', 'TIJERAS PARA ENTRESACAR PELAJE DE ACERO INOX+MANCH', 8.55),
	(72, 'PT7289', 'BRHUESOSE MADERA DOBLE+PUNTAS DE ACERO GIGANTES 25', 3.3),
	(73, 'GB7119', 'PEINE PARA DESENREDAR PELAJE METAL+MANOG PP 20 CM ', 3.3),
	(74, 'WG6075', 'BOLSA DE TRANSPORTE  BICOLOR ROJO/GRIS ', 25.6),
	(75, 'PH5051', 'BOLSA DE TRANSPORTE  BICOLOR NEGRO/GRIS ', 25.6),
	(76, 'NF2612', 'COMEDERO DE PERRO BELLY 21 CM SURT ', 7.1),
	(77, 'HH1506', 'COMEDERO DE PERRO BELLY 16 CM SURT ', 4.9),
	(78, 'KX5186', 'COMEDERO DE PERRO BELLY 14 CM SURT ', 3.55),
	(79, 'RF8258', 'BOLSAS HECES MASCOTAS X5 B(15) ', 1.6),
	(80, 'EA2441', 'PECERA BREEZE 16X9.5X10.5 CM 3 SURTD ', 1.35),
	(81, 'AY2518', 'SET 3 PAQUETES X15 BOLSAS HECES MASCOTA ', 0.65),
	(82, 'BR4617', 'COMEDERO DE PERRO 30 CM ', 3.95),
	(83, 'BQ2910', 'CUBRE ASIENTOS MASCOTAS 142X147 CM ', 9.6);
/*!40000 ALTER TABLE `articulos` ENABLE KEYS */;

-- Volcando estructura para tabla petrest.clientes
CREATE TABLE IF NOT EXISTS `clientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT '""',
  `cif` varchar(50) DEFAULT '""',
  `direccion` varchar(50) DEFAULT '""',
  `descuento` float DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla petrest.clientes: ~19 rows (aproximadamente)
DELETE FROM `clientes`;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` (`id`, `nombre`, `cif`, `direccion`, `descuento`) VALUES
	(1, 'Barjam S.A.', 'A29808525', 'Calle del Amazonas 26, 29173 Málaga', 13),
	(2, 'Katai S.L.', 'B20377774', 'Pza Menéndez Pelayo 12, 20509 Guipúzcoa', 12.8),
	(3, 'Lemon S.L.', 'B28826362', 'Plaza de Zapola  6, 28289 Madrid', 14.9),
	(4, 'Europa S.A.', 'A49227247', 'Calle de José Manuel Cobián 12, 49543 Málaga', 0.5),
	(5, 'Frandel S.L.', 'B28917131', 'Calle Vaciabotas  38, 28269 Madrid', 6.3),
	(6, 'Hornos S.L.', 'B23009857', 'Calle Carabaña  40, 23842 Jaén', 14.2),
	(7, 'Cadoz S.L.', 'B49690786', 'Calle de Homero  39, 49155 Málaga', 14.6),
	(8, 'Felón S.L.', 'B47415164', 'Calle de Ercilla  23, 47522 Málaga', 1.2),
	(9, 'Mandor S.L.', 'B51178721', 'Calle Doctor Fleming  14, 51371 Málaga', 1.2),
	(10, 'Nolan S.L.', 'B14251158', 'Calle Tomás Bretón  42, 14247 Córdoba', 7.8),
	(11, 'Antares S.A.', 'A35349275', 'Camino Quijorna  35, 35371 Málaga', 10.9),
	(12, 'Palermo S.L.', 'B22392799', 'Calle Monte Cervino  17, 22857 Huesca', 0),
	(13, 'Quantum S.L.', 'B32933833', 'Plaza del Olivar  5, 32393 Málaga', 2.3),
	(14, 'Roten S.L.', 'B10210488', 'Calle Griñón  50, 10926 Cáceres', 11.1),
	(15, 'Vearn S.A.', 'A14893402', 'Calle de Irún  32, 14110 Córdoba', 9.6),
	(16, 'Sedar S.A.', 'A27127002', 'Calle Berlín  39, 27665 Lugo', 2.2),
	(17, 'Wendy S.A.', 'A14101747', 'Calle Río Jarama  23, 14752 Córdoba', 6),
	(18, 'Ron S.L.', 'B35303979', 'Calle Sáhara  34, 35983 Málaga', 7.1),
	(19, 'Xanon S.L.', 'B33052957', 'Calle Botero  18, 33358 Málaga', 0.5);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;

-- Volcando estructura para tabla petrest.facturas
CREATE TABLE IF NOT EXISTS `facturas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL DEFAULT 0,
  `id_pedido` int(11) NOT NULL DEFAULT 0,
  `importe` float NOT NULL DEFAULT 0,
  `descuento` float NOT NULL DEFAULT 0,
  `base` float NOT NULL DEFAULT 0,
  `iva` float NOT NULL DEFAULT 0,
  `total` float NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla petrest.facturas: ~0 rows (aproximadamente)
DELETE FROM `facturas`;
/*!40000 ALTER TABLE `facturas` DISABLE KEYS */;
/*!40000 ALTER TABLE `facturas` ENABLE KEYS */;

-- Volcando estructura para tabla petrest.items
CREATE TABLE IF NOT EXISTS `items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_pedido` int(11) DEFAULT NULL,
  `id_articulo` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla petrest.items: ~159 rows (aproximadamente)
DELETE FROM `items`;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` (`id`, `id_pedido`, `id_articulo`, `cantidad`) VALUES
	(1, 16, 67, 1),
	(2, 22, 73, 7),
	(3, 39, 34, 2),
	(4, 39, 16, 6),
	(5, 12, 29, 4),
	(6, 30, 47, 1),
	(7, 22, 30, 4),
	(8, 13, 39, 8),
	(9, 35, 11, 3),
	(10, 27, 3, 3),
	(11, 35, 31, 3),
	(12, 20, 6, 1),
	(13, 19, 74, 3),
	(14, 14, 49, 2),
	(15, 18, 79, 4),
	(16, 3, 40, 2),
	(17, 31, 82, 8),
	(18, 33, 63, 2),
	(19, 2, 18, 1),
	(20, 10, 63, 4),
	(21, 14, 26, 6),
	(22, 14, 59, 1),
	(23, 35, 13, 4),
	(24, 2, 29, 1),
	(25, 8, 29, 3),
	(26, 27, 61, 2),
	(27, 16, 65, 1),
	(28, 25, 1, 3),
	(29, 14, 73, 6),
	(30, 3, 81, 1),
	(31, 39, 66, 1),
	(32, 38, 18, 1),
	(33, 23, 62, 6),
	(34, 13, 20, 3),
	(35, 4, 66, 2),
	(36, 3, 83, 1),
	(37, 27, 40, 5),
	(38, 19, 78, 10),
	(39, 4, 45, 2),
	(40, 23, 17, 2),
	(41, 3, 1, 1),
	(42, 34, 55, 4),
	(43, 9, 70, 3),
	(44, 15, 71, 2),
	(45, 8, 73, 1),
	(46, 5, 66, 3),
	(47, 17, 43, 6),
	(48, 17, 40, 1),
	(49, 25, 39, 1),
	(50, 30, 75, 1),
	(51, 10, 29, 9),
	(52, 29, 75, 4),
	(53, 39, 34, 6),
	(54, 9, 29, 4),
	(55, 11, 79, 2),
	(56, 32, 16, 1),
	(57, 26, 14, 4),
	(58, 26, 3, 4),
	(59, 3, 78, 5),
	(60, 5, 60, 1),
	(61, 8, 27, 5),
	(62, 25, 54, 4),
	(63, 35, 49, 6),
	(64, 5, 61, 2),
	(65, 17, 14, 1),
	(66, 7, 27, 1),
	(67, 27, 64, 2),
	(68, 6, 27, 2),
	(69, 18, 81, 4),
	(70, 38, 60, 5),
	(71, 11, 40, 7),
	(72, 25, 75, 1),
	(73, 32, 25, 5),
	(74, 11, 77, 3),
	(75, 27, 18, 4),
	(76, 2, 8, 1),
	(77, 31, 3, 2),
	(78, 25, 24, 7),
	(79, 2, 16, 3),
	(80, 7, 55, 1),
	(81, 2, 15, 1),
	(82, 9, 43, 3),
	(83, 16, 51, 2),
	(84, 1, 47, 1),
	(85, 1, 38, 2),
	(86, 32, 32, 1),
	(87, 39, 31, 7),
	(88, 20, 49, 3),
	(89, 30, 74, 5),
	(90, 12, 4, 1),
	(91, 24, 11, 2),
	(92, 36, 24, 1),
	(93, 14, 78, 4),
	(94, 39, 22, 6),
	(95, 34, 32, 5),
	(96, 39, 20, 2),
	(97, 6, 5, 2),
	(98, 3, 9, 6),
	(99, 9, 67, 6),
	(100, 30, 57, 1),
	(101, 36, 70, 2),
	(102, 26, 82, 3),
	(103, 9, 32, 2),
	(104, 24, 14, 3),
	(105, 11, 35, 1),
	(106, 34, 54, 4),
	(107, 37, 67, 2),
	(108, 19, 65, 5),
	(109, 16, 12, 7),
	(110, 27, 71, 1),
	(111, 22, 60, 2),
	(112, 36, 21, 3),
	(113, 31, 41, 5),
	(114, 6, 72, 2),
	(115, 10, 58, 3),
	(116, 31, 13, 2),
	(117, 31, 1, 1),
	(118, 31, 80, 1),
	(119, 12, 30, 4),
	(120, 30, 47, 3),
	(121, 4, 63, 4),
	(122, 36, 18, 4),
	(123, 16, 45, 2),
	(124, 31, 58, 1),
	(125, 10, 41, 3),
	(126, 9, 37, 5),
	(127, 31, 21, 1),
	(128, 28, 22, 5),
	(129, 32, 45, 1),
	(130, 25, 25, 8),
	(131, 5, 83, 1),
	(132, 22, 12, 6),
	(133, 5, 64, 6),
	(134, 37, 13, 4),
	(135, 33, 72, 7),
	(136, 21, 12, 4),
	(137, 24, 67, 2),
	(138, 7, 20, 3),
	(139, 14, 60, 3),
	(140, 27, 75, 2),
	(141, 12, 20, 1),
	(142, 16, 77, 2),
	(143, 33, 74, 3),
	(144, 17, 21, 6),
	(145, 37, 34, 8),
	(146, 4, 60, 3),
	(147, 17, 30, 7),
	(148, 7, 8, 4),
	(149, 8, 73, 2),
	(150, 15, 67, 4),
	(151, 38, 60, 4),
	(152, 33, 68, 5),
	(153, 5, 17, 5),
	(154, 6, 81, 3),
	(155, 5, 29, 2),
	(156, 22, 16, 4),
	(157, 15, 10, 5),
	(158, 26, 18, 4),
	(159, 33, 26, 1);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;

-- Volcando estructura para tabla petrest.pedidos
CREATE TABLE IF NOT EXISTS `pedidos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla petrest.pedidos: ~39 rows (aproximadamente)
DELETE FROM `pedidos`;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` (`id`, `id_cliente`, `fecha`) VALUES
	(1, 8, '2023-04-06'),
	(2, 18, '2023-01-09'),
	(3, 2, '2023-01-14'),
	(4, 13, '2023-01-19'),
	(5, 11, '2023-02-13'),
	(6, 19, '2023-03-12'),
	(7, 12, '2023-01-10'),
	(8, 19, '2023-03-04'),
	(9, 19, '2023-04-19'),
	(10, 9, '2023-01-21'),
	(11, 17, '2023-04-26'),
	(12, 11, '2023-04-04'),
	(13, 16, '2023-02-22'),
	(14, 15, '2023-04-26'),
	(15, 4, '2023-03-08'),
	(16, 12, '2023-01-28'),
	(17, 12, '2023-01-28'),
	(18, 19, '2023-01-08'),
	(19, 4, '2023-03-11'),
	(20, 5, '2023-02-22'),
	(21, 16, '2023-01-08'),
	(22, 19, '2023-03-24'),
	(23, 11, '2023-01-16'),
	(24, 18, '2023-02-25'),
	(25, 16, '2023-03-28'),
	(26, 8, '2023-02-14'),
	(27, 15, '2023-03-11'),
	(28, 6, '2023-01-20'),
	(29, 10, '2023-01-12'),
	(30, 4, '2023-04-15'),
	(31, 2, '2023-02-24'),
	(32, 9, '2023-04-15'),
	(33, 17, '2023-03-07'),
	(34, 19, '2023-03-17'),
	(35, 8, '2023-03-09'),
	(36, 1, '2023-02-03'),
	(37, 8, '2023-02-21'),
	(38, 14, '2023-04-10'),
	(39, 14, '2023-04-03');
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;

-- Volcando estructura para tabla petrest.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(50) NOT NULL DEFAULT '0',
  `clave` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla petrest.usuarios: ~1 rows (aproximadamente)
DELETE FROM `usuarios`;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`id`, `usuario`, `clave`) VALUES
	(1, 'usuario', 'clave'),
	(3, 'Pareja1', 'rEf@ZO{TU'),
	(4, 'Pareja2', 'VMPB@KKiD'),
	(5, 'Pareja3', 'vjxQkUwIQ'),
	(6, 'Pareja4', 'jYgTmLMeZ'),
	(7, 'Pareja5', '{cVpA{|ba'),
	(8, 'Pareja6', 'HLGmxK@tB'),
	(9, 'Pareja7', 'wj{VrhvLe'),
	(10, 'Pareja8', 'oQAghFnyx'),
	(11, 'Pareja9', 'ImyUk@QnL'),
	(12, 'Pareja10', 'NCeLOKkhj'),
	(13, 'Pareja11', 'BkrnSyr{l'),
	(14, 'Pareja12', 'gIG{G@Dvb'),
	(15, 'Pareja13', 'aeVBOT}gM'),
	(16, 'Pareja14', '}JSRpzEdl'),
	(17, 'Pareja15', '{ptJJzMZz'),
	(18, 'Pareja16', '}kbWmffDm'),
	(19, 'Pareja17', 'lBJEaYQAA'),
	(20, 'Pareja18', '[i[hr}xbO'),
	(21, 'Pareja19', 'zfTEpynxL'),
	(22, 'Pareja20', '{+LuqEgtE'),
	(23, 'Pareja21', 'Yq@gQNcor');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
