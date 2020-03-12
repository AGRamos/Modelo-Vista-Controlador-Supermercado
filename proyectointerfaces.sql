SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


CREATE TABLE `materiales` (
  `id_mat` int(3) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `descripcion` varchar(50) DEFAULT NULL,
  `pasillos_id_pas` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `pasillos` (
  `id_pas` int(3) NOT NULL,
  `descripcion` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `reponedores` (
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `fecha` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `rep_reponen_material` (
  `materiales_id_mat` int(3) NOT NULL,
  `reponedores_dni` varchar(9) NOT NULL,
  `cantidad` int(5) DEFAULT NULL,
  `fecha` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `usuarios` (
  `usuario` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`usuario`);

ALTER TABLE `materiales`
  ADD PRIMARY KEY (`id_mat`),
  ADD KEY `materiales_pasillos_fk` (`pasillos_id_pas`);

ALTER TABLE `pasillos`
  ADD PRIMARY KEY (`id_pas`);

ALTER TABLE `reponedores`
  ADD PRIMARY KEY (`dni`);

ALTER TABLE `rep_reponen_material`
  ADD PRIMARY KEY (`materiales_id_mat`,`reponedores_dni`, `fecha`),
  ADD KEY `rep_reponen_reponedores_fk` (`reponedores_dni`);

ALTER TABLE `materiales`
  ADD CONSTRAINT `materiales_pasillos_fk` FOREIGN KEY (`pasillos_id_pas`) REFERENCES `pasillos` (`id_pas`);

ALTER TABLE `rep_reponen_material`
  ADD CONSTRAINT `rep_reponen_materiales_fk` FOREIGN KEY (`materiales_id_mat`) REFERENCES `materiales` (`id_mat`),
  ADD CONSTRAINT `rep_reponen_reponedores_fk` FOREIGN KEY (`reponedores_dni`) REFERENCES `reponedores` (`dni`);
COMMIT;

insert into usuarios values ('admin', 'admin');
insert into usuarios values ('jose', 'jose');
insert into usuarios values ('luis', 'luis');
insert into usuarios values ('pepe', 'pepe');
insert into usuarios values ('antonio', 'antonio');
insert into usuarios values ('maria', 'maria');

insert into pasillos values (1, 'Pasillo de Conservas');
insert into pasillos values (2, 'Pasillo de Carne');
insert into pasillos values (3, 'Pasillo de Pescados');
insert into pasillos values (4, 'Pasillo de Bebidas');
insert into pasillos values (5, 'Pasillo de Verduras');

insert into materiales values (1, 'Aceitunas Garri L', 'Bote de aceitunas grandes.', 1);
insert into materiales values (2, 'Aceitunas Garri S', 'Bote de aceitunas pequeñas.', 1);
insert into materiales values (3, 'Aceitunas Garri M', 'Bote de aceitunas medianas.', 1);
insert into materiales values (4, 'Chuletas de Aguja', '500 Gr.', 2);
insert into materiales values (5, 'Filetes de Pollo', '1000 Gr.', 2);
insert into materiales values (6, 'Filetes de Lomo', '6 Unidades.', 2);
insert into materiales values (7, 'Atun ahumado', '1200 Gr.', 3);
insert into materiales values (8, 'Salmon ahumado', '1200 Gr.', 3);
insert into materiales values (9, 'Lubina fresca', '4 Unidades.', 3);
insert into materiales values (10, 'Coca Cola', '2l.', 4);
insert into materiales values (11, 'Fanta', 'Pack 6 latas.', 4);
insert into materiales values (12, 'Agua Mineral', 'Pack 6 botellas.', 4);
insert into materiales values (13, 'Patatas', 'Saco Freir', 5);
insert into materiales values (14, 'Calabacín', 'Malla', 5);
insert into materiales values (15, 'Ajos', '4 unidades.', 5);

insert into reponedores values ('31843506Y', 'Jose', '1980-02-06');
insert into reponedores values ('56257797G', 'Maria', '1981-01-02');
insert into reponedores values ('52438233G', 'Pepe', '1982-02-02');
insert into reponedores values ('59980929B', 'Pepita', '1983-01-06');
insert into reponedores values ('82126432X', 'Luisa', '1985-02-02');
insert into reponedores values ('85126188Z', 'Alejandro', '1987-01-02');
insert into reponedores values ('47335695Q', 'Daniel', '1998-02-06');

insert into rep_reponen_material values (1, '31843506Y', 100, '2019-03-06');
insert into rep_reponen_material values (2, '31843506Y', 16, '2019-05-06');
insert into rep_reponen_material values (3, '56257797G', 23, '2019-03-06');
insert into rep_reponen_material values (4, '56257797G', 27, '2019-02-06');
insert into rep_reponen_material values (5, '52438233G', 312, '2019-05-06');
insert into rep_reponen_material values (6, '52438233G', 34, '2019-03-06');
insert into rep_reponen_material values (7, '59980929B', 47, '2019-02-06');
insert into rep_reponen_material values (8, '59980929B', 49, '2019-02-06');
insert into rep_reponen_material values (9, '82126432X', 50, '2019-05-06');
insert into rep_reponen_material values (10, '82126432X', 52, '2019-02-06');
insert into rep_reponen_material values (11, '85126188Z', 16, '2019-03-06');
insert into rep_reponen_material values (12, '85126188Z', 63, '2019-05-06');
insert into rep_reponen_material values (13, '47335695Q', 47, '2019-02-06');
insert into rep_reponen_material values (14, '47335695Q', 71, '2019-03-06');
insert into rep_reponen_material values (15, '47335695Q', 700, '2019-05-06');