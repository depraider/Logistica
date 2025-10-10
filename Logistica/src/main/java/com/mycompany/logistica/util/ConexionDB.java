package com.mycompany.logistica.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:mariadb://localhost:3306/reparto";
    private static final String USER = "root";  // cambia si tienes otro usuario
    private static final String PASSWORD = "";  // pon tu contraseña si la tienes

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar con la base de datos: " + e.getMessage());
            return null;
        }
    }
}
