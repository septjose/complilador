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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
       // ArrayList<TablaSimbolos> tablaSimbolos = new ArrayList<>();
       /*
        TablaSimbolos[] tablaSimbolos = new TablaSimbolos[4]; 
       for(int i=0; i<4; i++) { 
        tablaSimbolos[i] = new TablaSimbolos("hi", "hi", "", "", 0); 
        
        } 
       */
       
         
         //tablaSimbolos[0].categoria=new String("hola");
        //System.out.println(tablaSimbolos[0].token);
        
        /*FuncionHash fh = new FuncionHash();
        System.out.println(fh.hash("int"));
        
        AnalizadorLexico al=new AnalizadorLexico();
        boolean checar=al.Buscar("for");
        if (checar)
        {
            System.out.println("es PR");
        }*/
        
        
        /*
        String token;
        token = JOptionPane.showInputDialog("token");
        
        Automatas automata = new Automatas();
        FuncionHash hash = new FuncionHash();
        Archivo archivo = new Archivo();
        TablaSimbolos tablaSimbolos;
        
        boolean tokenValido = false;
                
                if(archivo.Buscar(token,"src/compiladorVerano/PalabrasReservadas"))
                    {
                        tokenValido = true;
                        hash.hash(token);
                        System.out.println("Es PR" + token + hash.hash(token));
                    }
                
                if(archivo.Buscar(token,"src/compiladorVerano/Operadores"))
                    {
                        tokenValido = true;
                        System.out.println("Es Op");
                    }
                    if(automata.AutomataValorInt(token))
                    {
                        tokenValido = true;
                        System.out.println("Es entero");
                    }
        
                    if(automata.AutomataValorInt(token))
                    {
                        tokenValido = true;
                        System.out.println("Es entero");
                    }
                    
                   if(!tokenValido && automata.AutomataValorDouble(token))
                    {
                        tokenValido = true;
                        System.out.println("Es decimal");
                    }
                   
                    if(!tokenValido && automata.AutomataCadena(token))
                    {
                        tokenValido = true;
                        System.out.println("Es cadena");
                    }
                    
                    if(!tokenValido && automata.AutomataIdentificador(token))
                    {
                        tokenValido = true;
                        System.out.println("Es identificador");
                    }
                    
                     if(!tokenValido && automata.AutomataComentarios(token))
                    {
                        tokenValido = true;
                        System.out.println("Es un comentario");
                    }
             /*        if(!tokenValido && token.codePointAt(0)==59);
                    {
                        tokenValido = true;
                        System.out.println("Es del");
                    }
            */
        
        GUI interfaz  = new GUI();
        interfaz.setVisible(true);
        
    }
    
}
