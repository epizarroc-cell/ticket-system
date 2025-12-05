

-- Crear base de datos si no existe
CREATE DATABASE IF NOT EXISTS ticket_system;
USE ticket_system;


CREATE TABLE IF NOT EXISTS t_usuarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    telefono VARCHAR(20),
    rol ENUM('administrador', 'estudiante', 'funcionario') NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS t_departamentos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) UNIQUE NOT NULL,
    descripcion VARCHAR(255),
    extension VARCHAR(50)
);


CREATE TABLE IF NOT EXISTS t_tickets (
    id INT PRIMARY KEY AUTO_INCREMENT,
    asunto VARCHAR(200) NOT NULL,
    descripcion TEXT NOT NULL,
    estado ENUM('Nuevo', 'En progreso', 'Resuelto') DEFAULT 'Nuevo',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario_id INT NOT NULL,
    departamento_id INT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES t_usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (departamento_id) REFERENCES t_departamentos(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS t_palabras_tecnicas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    palabra VARCHAR(100) UNIQUE NOT NULL,
    categoria VARCHAR(50) NOT NULL
);


CREATE TABLE IF NOT EXISTS t_palabras_emocionales (
    id INT PRIMARY KEY AUTO_INCREMENT,
    palabra VARCHAR(100) UNIQUE NOT NULL,
    emocion VARCHAR(50) NOT NULL
);



-- Insertar departamentos por defecto
INSERT INTO t_departamentos (nombre, descripcion, extension) VALUES
('Sistemas', 'Departamento de tecnología y soporte técnico', 'ext. 100'),
('Recursos Humanos', 'Gestión de personal y nómina', 'ext. 200'),
('Biblioteca', 'Servicios bibliotecarios y préstamos', 'ext. 300'),
('Registro', 'Gestión de matrícula y registros académicos', 'ext. 400'),
('Financiero', 'Gestión de pagos y finanzas estudiantiles', 'ext. 500');

-- Insertar usuario administrador por defecto
INSERT INTO t_usuarios (nombre, correo, contrasena, telefono, rol) VALUES
('Administrador Principal', 'admin@ucenfotec.ac.cr', 'placeholder', '8888-8888', 'administrador');

-- Insertar palabras técnicas por defecto (solo las esenciales)
INSERT INTO t_palabras_tecnicas (palabra, categoria) VALUES
('wifi', 'Redes'),
('internet', 'Redes'),
('conexion', 'Redes'),
('impresora', 'Impresoras'),
('imprimir', 'Impresoras'),
('papel', 'Impresoras'),
('software', 'Software'),
('hardware', 'Hardware'),
('usuario', 'Cuentas'),
('contraseña', 'Cuentas'),
('login', 'Cuentas');

-- Insertar palabras emocionales por defecto (solo las esenciales)
INSERT INTO t_palabras_emocionales (palabra, emocion) VALUES
('enojado', 'Frustración'),
('frustrado', 'Frustración'),
('urgente', 'Urgencia'),
('importante', 'Urgencia'),
('gracias', 'Positivo'),
('excelente', 'Positivo'),
('problema', 'Negativo'),
('mal', 'Negativo'),
('consultar', 'Neutral');



-- Ver tablas creadas
SHOW TABLES;

-- Ver datos insertados
SELECT 'Usuarios' AS Tabla, COUNT(*) AS Cantidad FROM t_usuarios
UNION ALL
SELECT 'Departamentos', COUNT(*) FROM t_departamentos
UNION ALL
SELECT 'Palabras Técnicas', COUNT(*) FROM t_palabras_tecnicas
UNION ALL
SELECT 'Palabras Emocionales', COUNT(*) FROM t_palabras_emocionales;

SELECT * FROM t_palabras_emocionales

SELECT * FROM t_palabras_tecnicas

SELECT * FROM t_usuarios

SELECT * FROM t_departamentos

SELECT * FROM t_tickets


INSERT INTO t_usuarios (nombre, correo, contrasena, telefono, rol) VALUES
('Funcionario Ejemplo', 'funcionario@ucenfotec.ac.cr', 'placeholder', '7777-7777', 'funcionario');

-- Insertar usuario estudiante de ejemplo
INSERT INTO t_usuarios (nombre, correo, contrasena, telefono, rol) VALUES
('Estudiante Ejemplo', 'estudiante@ucenfotec.ac.cr', 'placeholder', '6666-6666', 'estudiante');