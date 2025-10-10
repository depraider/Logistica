/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.logistica;

import javax.swing.JFrame;

/**
 *
 * @author jefra
 */
public class VentanaView extends JFrame{
    
    
    public VentanaView() {
        setTitle("Gestión de Conductores");
        setSize(590, 490); // Tamaño de la ventana
        setLocationRelativeTo(null); // Centrar en pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        UI_View panel = new UI_View();

        // Le decimos al JFrame que el panel es su contenido
        setContentPane(panel);
    }
    
    
}

    
