/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorverano;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author José Alberto
 */
public class Arch {
        public boolean Buscar(String cadena, String ruta) {
        boolean bandera = false;
        try {
            FileReader lector = new FileReader(ruta);
            BufferedReader bw = new BufferedReader(lector);
            String line;
            line = bw.readLine();
            while (line != null) {

                if (cadena.equalsIgnoreCase(line)) {
                    bandera = true;
                    break;
                }
                line = bw.readLine();
            }
            bw.close();
        } catch (Exception e) {
            System.out.println("Error al leer el archivo");
        }
        return bandera;
    }

}
