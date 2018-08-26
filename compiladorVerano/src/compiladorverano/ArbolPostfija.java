/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorverano;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 *
 * @author José Alberto
 */
public class ArbolPostfija {

    /**
     * @param args the command line arguments
     */
    ArbolPostfija(ArrayList<String>entrada, ArrayList<String>entrada2) {
        // TODO code application logic here
        Stack<Arbol> pila = new Stack<>();
        
        
        //ArrayList<String> entrada;
        //ArrayList<String> entrada2;
       
        
        boolean error = false;
        //String [] datos = {"123","12","3","+","*","4","6","2","+","*","+"};
        
        //String [] datos = {"5", "3", "+", "6", "+","2","+","x","="};
        //String [] tipo = {"Entero", "Entero", "+", "Entero", "+","Entero","+","Decimal","="};
        
        //String [] datos = {"y", "7.1", "1", "+", "="};
        //String [] tipo = {"Cadena", "Cadena", "Cadena", "+", "="};
        
        //String [] datos = {"y", "x", "="};
        //String [] tipo = {"Booleano", "Booleano", "="};
        
        //String [] datos = {"y", "x", "+"};
        //String [] tipo = {"Entero", "Entero", "+"};
        
        //String [] datos = {"x", "4", ">","7","2",">","||"};
        //String [] tipo = {"Entero", "Entero", ">","Entero","Entero",">","||"};
        
        //String [] datos = {"x","y","u","*","+","y","k","/","1","/","j","*","1","-","="};
        //String [] datos = {"x","y","+","k","w","/","z","/","="};
       
        
        //entrada = new ArrayList<>(Arrays.asList(datos));
        //entrada2 = new ArrayList<>(Arrays.asList(tipo));
        
        String elemento;
        String elemento2;
        Arbol e = null;
        
        while(!entrada.isEmpty() && !error)
        {
            elemento = entrada.get(0);
            entrada.remove(0);
            
            elemento2 = entrada2.get(0);
            entrada2.remove(0);
            
            switch (pertenece(elemento))
            {
                case 1:
                    pila.push(new Arbol(elemento,elemento2));
                    break;
                case 2:
                    error = true;
                    
                    break;
                case 3:
                    
                    if(pila.size()<2)
                    {
                        error = true;
                    }
                    else
                    {                        
                        Arbol a1 = null;
                        Arbol a2 = null;
                        
                        a1 = pila.pop();
                        a2 = pila.pop();

                        pila.push(new Arbol(new Arbol(elemento,elemento2), a2, a1));
                    }
 
                    break;
            }
        }
        
        if(pila.empty() || pila.size()>1)
        {
            error = true;
        }
        else
        {
            e = pila.pop();
        }
        
       // e.imprimirEntre();
        e.imprimirPost();
        
    }
    
    public int pertenece(String elemento)
    {
        int p = 1;
        if (elemento.equals("%")  || elemento.equals("*")  || elemento.equals("/")  || elemento.equals("+")  || elemento.equals("-") ||
            elemento.equals("==") || elemento.equals(">=") || elemento.equals("<=") || elemento.equals("!=") || elemento.equals(">") || 
            elemento.equals("<")  || elemento.equals("||") || elemento.equals("&&") || elemento.equals("="))
            p = 3;

        if (elemento.equals(")"))
            p = 2;

        return p;
    
    }
    
    public static void main(String [] args)
    {
        //new ArbolPostfija();
    }
    
}