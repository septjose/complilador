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
 * @author juanjesuspadrondiaz
 */

public class AnalizadorLexico {
    
    public boolean Buscar(String cadena)
    {
            boolean bandera=false;
         try
        {
            FileReader lector = new FileReader("src/compiladorVerano/PalabrasReservadas");
            BufferedReader bw = new BufferedReader(lector);           
            
        
            String line;
            
                        
            line = bw.readLine();

            while(line!=null)
            {                           
            //    tokenArray = line.split(" ");
                //System.out.println(line);
                line = bw.readLine();
                if(cadena.equalsIgnoreCase(line))
                {
                    bandera= true;
                    break;
                }
                
            }
            bw.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al leer el archivo");
        }
        return bandera;
    }
    
    public boolean AutomataDigito(String token)
    {
        
        return true;
    }
    
    
    
}
