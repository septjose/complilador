/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorverano;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author José Alberto
 */
public class Infija_Postfija {

    Stack<String> pila = new Stack<>();
    ArrayList<String> cola = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    Infija_Postfija(ArrayList<String> entrada, ArrayList<TablaSimbolos> tablaSimbolos) {
        // TODO code application logic here
        Stack<String> pila = new Stack<>();
        //ArrayList<String> entrada;
        ArrayList<String> salida = new ArrayList<>();
        ArrayList<String> nombre = new ArrayList<>();
        boolean error = false;
        String elemento;

       // String expresion = JOptionPane.showInputDialog("teclea");
        //entrada = new ArrayList<>(Arrays.asList(expresion.split(" ")));
        entrada.removeIf(String::isEmpty);
        //System.out.println(entrada);

        //System.out.println(entrada);
        while (!entrada.isEmpty() && !error) {
            elemento = entrada.get(0);
            //System.out.println(precedencia(elemento));
            entrada.remove(0);

            switch (precedencia(elemento)) {

                case 10: // número o variable
                    salida.add(elemento);
                    break;
                case 1:  // paréntesis izquierdo
                    pila.push(elemento);
                    break;
                case 2: // paréntesis derecho
                    while (!pila.empty() && !pila.peek().equals("(")) {
                        salida.add(pila.pop());
                    }
                    if (pila.peek().equals("(")) {
                        pila.pop();
                    } else {
                        error = true;
                    }
                    break;
                case 3:  //operador =
                case 4:  //operador ||  &&
                case 5:  //operador == >= <= != > <
                case 6: //operador  +  -
                case 7: //operador  *  /
               // case 8: //operador  ^
                    while (!pila.empty() && (precedencia(pila.peek()) >= precedencia(elemento))) {
                        salida.add(pila.pop());
                    }
                    pila.push(elemento);

                    break;
            }
        }

        if (error) {
            System.out.println("error");
        }

        while (!pila.empty()) {
            salida.add(pila.pop());
        }

        //System.out.println(salida);
        
        /*for (TablaSimbolos t: tablaSimbolos) {
            System.out.println(t.token+"-----"+ t.tipo);
        }*/
        CompiladorVerano.interfaz.tabla(new TablaSimbolos("1", "Entero", "4", "", "DI", ""));
        tablaSimbolos.add(new TablaSimbolos("1", "Entero", "4", "", "DI", ""));
        
        String[] operadores = {"+","-","*","/","%","=",">","<",">=","<=","==","!=","||","&&"};
        for(int i = 0; i<salida.size();i++)
        {
            if(!Arrays.asList(operadores).contains(salida.get(i)))
            {
                for (TablaSimbolos t : tablaSimbolos) {
                    if(salida.get(i).equals(t.token))
                    {
                        nombre.add(t.tipo);
                        break;
                    }
                }
            }
            else
                nombre.add(salida.get(i));
        }
        
        //System.out.println(salida);
        //System.out.println(nombre);
        
        new ArbolPostfija(salida,nombre);
    }

    public static int precedencia(String elemento) {
        int p = 10;
        //if (elemento.equals("^"))
          //  p = 8;
        if (elemento.equals("*") || elemento.equals("/") || elemento.equals("%"))
            p = 7;
        if (elemento.equals("+") || elemento.equals("-")) 
            p = 6;    
        if (elemento.equals("==") || elemento.equals(">=") || elemento.equals("<=") || elemento.equals("!=") || elemento.equals(">") || elemento.equals("<"))
            p = 5;
        if (elemento.equals("||") || elemento.equals("&&"))
            p = 4;
        if (elemento.equals("="))
            p = 3;
        if (elemento.equals(")"))
            p = 2;
        if (elemento.equals("("))
            p = 1;

        return p;
    }

    public static boolean esNumero(String elemento) {
        boolean numero;

        try {
            Integer.parseInt(elemento);
            numero = true;
        } catch (NumberFormatException excepcion) {
            numero = false;
        }

        return numero;
    }
    
    
    public static void main(String [] args)
    {
        
        //String [] entrada = {"k","=","g","+","k"};
        //new Infija_Postfija(new ArrayList<>(Arrays.asList(entrada)));
    }

}
