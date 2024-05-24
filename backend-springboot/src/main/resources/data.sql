--COLECCIONES
INSERT INTO colecciones (nombre_coleccion) VALUES('Slime');
INSERT INTO colecciones (nombre_coleccion) VALUES('Exfoliantes');

--PRODUCTOS
INSERT INTO productos (nombre_producto, precio_producto, tamaño_producto, beneficios_producto, imagen_producto, id_coleccion)
VALUES('Bubalú', 19.99, 100, 'Un producto beneficioso para la piel.', '/img/Bubalú.jpg', 1);
INSERT INTO productos (nombre_producto, precio_producto, tamaño_producto, beneficios_producto, imagen_producto, id_coleccion)
VALUES('Mango Tropical', 25.99, 150, 'Un producto beneficioso para la piel.', '/img/MangoTropical.jpg', 1);
INSERT INTO productos (nombre_producto, precio_producto, tamaño_producto, beneficios_producto, imagen_producto, id_coleccion)
VALUES('Fresa Salvaje', 15.99, 50, 'Un producto beneficioso para la piel.', '/img/FresaSalvaje.jpg', 1);
INSERT INTO productos (nombre_producto, precio_producto, tamaño_producto, beneficios_producto, imagen_producto, id_coleccion)
VALUES('Helado de Fresa', 26.99, 200, 'Un producto beneficioso para la piel.', '/img/HeladoDeFresa.jpg', 2);
INSERT INTO productos (nombre_producto, precio_producto, tamaño_producto, beneficios_producto, imagen_producto, id_coleccion)
VALUES('Coco', 18.99, 100, 'Un producto beneficioso para la piel.', '/img/Coco.jpg', 2);
INSERT INTO productos (nombre_producto, precio_producto, tamaño_producto, beneficios_producto, imagen_producto, id_coleccion)
VALUES('Naranja Tropical', 19.99, 100, 'Un producto beneficioso para la piel.', '/img/NaranjaTropical', 2);

--USUARIOS
INSERT INTO usuarios(nombre_usuario, apellido_usuario, contrasena_usuario, correo_usuario, fecha_registro)
VALUES ('Martín', 'Kong', 'abc', 'martin@correo.com', current_date());
INSERT INTO usuarios(nombre_usuario, apellido_usuario, contrasena_usuario, correo_usuario, fecha_registro)
VALUES ('Lolita', 'Souza', '123', 'lolita@correo.com', current_date());
INSERT INTO usuarios(nombre_usuario, apellido_usuario, contrasena_usuario, correo_usuario, fecha_registro)
VALUES ('Carmen', 'Da Silva', 'hola112', 'camuchita@correo.com', current_date());
INSERT INTO usuarios(nombre_usuario, apellido_usuario, contrasena_usuario, correo_usuario, fecha_registro)
VALUES ('Alma', 'Gozo', '123aa', 'alma@correo.com', current_date());
INSERT INTO usuarios(nombre_usuario, apellido_usuario, contrasena_usuario, correo_usuario, fecha_registro)
VALUES ('Mónica', 'Romero', 'kemiras', 'monica@correo.com', current_date());

--REVIEWS