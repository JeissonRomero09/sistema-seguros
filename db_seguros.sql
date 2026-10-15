-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-10-2026 a las 02:51:26
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_seguros`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asegurado`
--

CREATE TABLE `asegurado` (
  `identificacion` varchar(20) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `correo_electronico` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `asegurado`
--

INSERT INTO `asegurado` (`identificacion`, `nombre`, `correo_electronico`) VALUES
('1010555', 'Carlos Pérez', 'carlos.perez@email.com'),
('2020666', 'Ana Gómez', 'ana.gomez@email.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seguro`
--

CREATE TABLE `seguro` (
  `numero` varchar(20) NOT NULL,
  `fecha_expedicion` date NOT NULL,
  `valor` double NOT NULL,
  `estado` varchar(20) NOT NULL,
  `asegurado_id` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `seguro`
--

INSERT INTO `seguro` (`numero`, `fecha_expedicion`, `valor`, `estado`, `asegurado_id`) VALUES
('1', '2026-10-09', 100, 'Activo', '2020666'),
('3', '2026-10-09', 100, 'Activo', '2020666'),
('VEH-001', '2026-03-20', 850000, 'Activo', '1010555'),
('VEH-002', '2026-05-10', 1200000, 'Pendiente', '2020666'),
('VEH-0034', '2026-05-23', 123, 'Activo', '1010555'),
('VIDA-00', '2026-01-15', 1500000, 'Activo', '1010555'),
('VIDA-001', '2026-01-15', 1500000, 'Activo', '1010555');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seguro_vehiculo`
--

CREATE TABLE `seguro_vehiculo` (
  `numero` varchar(20) NOT NULL,
  `marca` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `seguro_vehiculo`
--

INSERT INTO `seguro_vehiculo` (`numero`, `marca`) VALUES
('VEH-001', 'Mazda'),
('VEH-002', 'Chevrolet'),
('VEH-0034', 'mazda');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seguro_vida`
--

CREATE TABLE `seguro_vida` (
  `numero` varchar(20) NOT NULL,
  `nombre_beneficiario` varchar(100) NOT NULL,
  `parentesco` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `seguro_vida`
--

INSERT INTO `seguro_vida` (`numero`, `nombre_beneficiario`, `parentesco`) VALUES
('1', 'juan', 'hermano'),
('3', 'juan', 'hermano'),
('VIDA-00', 'María Pérez', 'Hija'),
('VIDA-001', 'María Pérez', 'Hija');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `asegurado`
--
ALTER TABLE `asegurado`
  ADD PRIMARY KEY (`identificacion`);

--
-- Indices de la tabla `seguro`
--
ALTER TABLE `seguro`
  ADD PRIMARY KEY (`numero`),
  ADD KEY `fk_seguro_asegurado` (`asegurado_id`);

--
-- Indices de la tabla `seguro_vehiculo`
--
ALTER TABLE `seguro_vehiculo`
  ADD PRIMARY KEY (`numero`);

--
-- Indices de la tabla `seguro_vida`
--
ALTER TABLE `seguro_vida`
  ADD PRIMARY KEY (`numero`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `seguro`
--
ALTER TABLE `seguro`
  ADD CONSTRAINT `fk_seguro_asegurado` FOREIGN KEY (`asegurado_id`) REFERENCES `asegurado` (`identificacion`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `seguro_vehiculo`
--
ALTER TABLE `seguro_vehiculo`
  ADD CONSTRAINT `fk_vehiculoseguro_seguro` FOREIGN KEY (`numero`) REFERENCES `seguro` (`numero`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `seguro_vida`
--
ALTER TABLE `seguro_vida`
  ADD CONSTRAINT `fk_vidaseguro_seguro` FOREIGN KEY (`numero`) REFERENCES `seguro` (`numero`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
