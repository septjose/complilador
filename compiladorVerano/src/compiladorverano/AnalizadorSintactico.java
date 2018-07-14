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
public class AnalizadorSintactico {

    Stack<String> pila = new Stack<>();
    ArrayList<ArrayList<String>> lista = new ArrayList<>();
    public String[][] Matriz
            = {{"", "id", "+", "*", "(", ")", "$"},
            {"E", "T Ep", null, null, "T Ep", null, null},
            {"Ep", null, "+ T Ep", null, null, "", ""},
            {"T", "F Tp", null, null, "F Tp", null, null},
            {"Tp", null, "", "* F Tp", null, "", ""},
            {"F", "id", null, null, "(E)", null, null}
            };
    
    int numfilas = Matriz.length; //número de las filas
    int numcolumnas = Matriz[0].length;//número de las columnas

    String terminales[];

    public void analizar() {
        
        cargarLista();
        this.pila.push("$");
        this.pila.push(this.Matriz[1][0]);
        System.out.println(this.pila);

        while(!this.pila.lastElement().equals("$")){
         
            //if(terminal(this.pila.lastElement()) || this.pila.lastElement().equals("$"))
                
            if (this.pila.lastElement().equals(this.lista.get(0).get(0))) {
                //System.out.println("entro");
                removerDePila();
                System.out.println(this.pila);
                
            } else {
                sustituirEnPila(this.pila.lastElement(), this.lista.get(0).get(0));
                System.out.println(this.pila);
            }
        }   
    }

    public void cargarLista() {
        this.lista.add(new ArrayList<>());
        lista.get(0).add("id");
        lista.get(0).add("+");
        lista.get(0).add("id");
        lista.get(0).add("*");
        lista.get(0).add("id");
        lista.get(0).add("$");
    }

    public void removerDePila() {       
        this.pila.pop();
        this.lista.get(0).remove(0);
 
        if(this.lista.get(0).isEmpty())
            this.lista.remove(0);
    }

    public void sustituirEnPila(String renglon, String columna) {
        String aCadena[];

        aCadena = buscarEnTablaAnalisisSintactico(renglon, columna).split(" ");
        this.pila.pop();

        for (int i = aCadena.length - 1; i >= 0; i--) {
            if(!aCadena[i].equals(""))
                pila.push(aCadena[i]);
        }
    }

    public String buscarEnTablaAnalisisSintactico(String renglon, String columna) {
        int i, j;
        String elementos = "";

        for (i = 0; i < this.numfilas; i++) {
            if (renglon.equals(this.Matriz[i][0])) {
                break;
            }
        }

        for (j = 0; j < this.numcolumnas; j++) {
            if (columna.equals(this.Matriz[0][j])) {
                break;
            }
        }

        elementos = this.Matriz[i][j];
        return elementos;
    }
    
    public boolean terminal(String x)
    {
        boolean terminal = false;
        
        for(int i = 0; i < this.numcolumnas; i++)
        {
            this.terminales[i] = this.Matriz[0][1];
        }
        terminal = Arrays.asList(this.terminales).contains(x);
        
        return terminal;
    }

    public static void main(String[] args) {
        new AnalizadorSintactico().analizar();

    }
}