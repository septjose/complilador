/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorverano;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author juanjesuspadrondiaz
 */
public class CompiladorVerano {
    static GUI interfaz = new GUI();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
        interfaz.setVisible(true);
        //new AnalizadorSintactico().analizar();
    }
}
