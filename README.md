CREATE DATABASE reparto;
USE reparto;

CREATE TABLE conductor (
  dni VARCHAR(9) PRIMARY KEY,
  nombre VARCHAR(100),
  telefono VARCHAR(15),
  direccion VARCHAR(150),
  salario DECIMAL(10,2),
  municipioResidencia VARCHAR(100)
);
