package com.mycompany.logistica;

import com.mycompany.logistica.util.BaseDeDatos;
import javax.swing.SwingUtilities;

public class Logistica {

    public static void main(String[] args) {
       /** SwingUtilities.invokeLater(() -> {
            new VentanaView().setVisible(true);
        });
        **/
       
       BaseDeDatos crearDb = new BaseDeDatos();
       
       
        crearDb.inicializar();
        
        
        
       
       
    }
}
