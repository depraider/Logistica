package com.logistica.demo.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDeDatos {

    private static final String URL_BASE = "jdbc:mariadb://localhost:3306/";
    private static final String DB_NAME = "reparto";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public void inicializar() {
        try {
            crearBaseDeDatosSiNoExiste();
            crearTablasSiNoExisten();
            System.out.println("Base de datos y tablas listas.");
        } catch (SQLException e) {
            System.out.println("Error al inicializar la base de datos: " + e.getMessage());
        }
    }

    private static void crearBaseDeDatosSiNoExiste() throws SQLException {
        try (Connection con = DriverManager.getConnection(URL_BASE, USER, PASSWORD); Statement st = con.createStatement()) {
            String sql = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
            st.executeUpdate(sql);
        }
    }

    // Crea las tablas necesarias
    private static void crearTablasSiNoExisten() throws SQLException {
        String urlDB = URL_BASE + DB_NAME;
        try (Connection con = DriverManager.getConnection(urlDB, USER, PASSWORD); Statement st = con.createStatement()) {

            // conductor
            String sqlConductor = """
                CREATE TABLE IF NOT EXISTS conductor (
                    dni VARCHAR(20) PRIMARY KEY,
                    nombre VARCHAR(100) NOT NULL,
                    telefono VARCHAR(20),
                    direccion VARCHAR(150),
                    salario DOUBLE,
                    municipioResidencia VARCHAR(100)
                )
                """;
            st.executeUpdate(sqlConductor);

            System.out.println("Tabla 'conductor' verificada o creada.");

// tabla vehiculo
            String sqlVehiculo = """
    CREATE TABLE IF NOT EXISTS vehiculo (
        matricula VARCHAR(20) PRIMARY KEY,
        modelo VARCHAR(100) NOT NULL,
        potencia DOUBLE,
        tipo VARCHAR(50)
    )
    """;
            st.executeUpdate(sqlVehiculo);

            System.out.println("Tabla 'vehiculo' verificada o creada.");

//  entrega
            String sqlEntrega = """
    CREATE TABLE IF NOT EXISTS entrega (
        dniConductor VARCHAR(20) NOT NULL,
        codigoPaquete VARCHAR(50) NOT NULL,
        estado VARCHAR(50) NOT NULL,
        matriculaVehiculo VARCHAR(50) NOT NULL,
                    
        fechaEntrega DATE,
        PRIMARY KEY (dniConductor, codigoPaquete),
        FOREIGN KEY (dniConductor) REFERENCES conductor(dni)
            ON UPDATE CASCADE
            ON DELETE CASCADE
    )
    """;
            st.executeUpdate(sqlEntrega);

            System.out.println("Tabla 'entrega' verificada o creada.");

// paquete
            String sqlPaquete = """
    CREATE TABLE IF NOT EXISTS paquete (
        codigoPaquete VARCHAR(50) PRIMARY KEY,
        descripcion VARCHAR(200),
        destinatario VARCHAR(100),
        direccionEntrega VARCHAR(150),
        municipio VARCHAR(100)
    )
    """;
            st.executeUpdate(sqlPaquete);

            System.out.println("Tabla 'paquete' verificada o creada.");

// municipio
String sqlMunicipio = """
CREATE TABLE IF NOT EXISTS municipio (
    codigoMunicipio VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    provincia VARCHAR(100) NOT NULL
)
""";
st.executeUpdate(sqlMunicipio);

System.out.println("Tabla 'municipio' verificada o creada.");


        }
    }
}
