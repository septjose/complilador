/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorverano;

import javax.swing.JOptionPane;

/**
 *
 * @author juanjesuspadrondiaz
 */
public class FuncionHash {
    
    public int hash(String cadena)
    {

       long suma=0;
       int posicionRegistro=0;
       
       for(int i=0;i<cadena.length();i++)
       {
            //System.out.println(cadena.charAt(i) + " = " + cadena.codePointAt(i));
            if(cadena.length()==1)
            {
               suma=cadena.codePointAt(i);
            }else{
                suma=suma+(cadena.codePointAt(i)*(i+1));
            }         
       }
       posicionRegistro= (int) (suma%1001);
        
        return posicionRegistro;
    }
          
    
}
