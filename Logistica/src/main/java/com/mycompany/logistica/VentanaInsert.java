package com.mycompany.logistica;

import javax.swing.JFrame;

public class VentanaInsert extends JFrame {

    public VentanaInsert() {
        setTitle("Gestión de Conductores");
        setSize(650, 450); // Tamaño de la ventana
        setLocationRelativeTo(null); // Centrar en pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creamos el panel
        UI_Insert panel = new UI_Insert();

        // Le decimos al JFrame que el panel es su contenido
        setContentPane(panel);
    }
}
