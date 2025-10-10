package com.mycompany.logistica;

import com.mycompany.logistica.util.BaseDeDatos;


public class Logistica {

    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new VentanaConductores().setVisible(true));
        BaseDeDatos createDB = new BaseDeDatos();
        createDB.inicializar();
    }
}
