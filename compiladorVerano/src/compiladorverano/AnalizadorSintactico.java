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

    private final Stack<String> pila = new Stack<>();
    private ArrayList<ArrayList<String>> entrada = new ArrayList<>();

    private final String[][] Matriz
            = {{"", "id", "+", "*", "(", ")", "$"},
            {"E", "T Ep", "404", "404", "T Ep", "404", "404"},
            {"Ep", "404", "+ T Ep", "404", "404", "", ""},
            {"T", "F Tp", "404", "404", "F Tp", "404", "404"},
            {"Tp", "404", "", "* F Tp", "404", "", ""},
            {"F", "id", "404", "404", "(E)", "404", "404"}
            };

    private final int numfilas;     private final int numcolumnas;
    private int renglon;            private int columna;

    private final String terminales[];

    AnalizadorSintactico(ArrayList<ArrayList<String>> entrada) {
        this.entrada = entrada;
        this.entrada.removeIf(ArrayList::isEmpty);
        System.out.println(entrada);
        this.numfilas = this.Matriz.length; //número de las filas
        this.numcolumnas = this.Matriz[0].length;//número de las columnas
        this.terminales = new String[this.numcolumnas];
        System.arraycopy(this.Matriz[0], 0, this.terminales, 0, this.numcolumnas);

        analizar();
    }

    private void analizar() {
        //cargarLista();

        this.pila.push("$");
        this.pila.push(this.Matriz[1][0]);
        //System.out.println(this.pila + "-------------------------" + this.entrada.get(0));
        CompiladorVerano.interfaz.tablaTraza(pila, entrada.get(0));
        while (!this.pila.lastElement().equals("$")) {
            if (terminal(this.pila.lastElement()) || this.pila.lastElement().equals("$")) {
                if (this.pila.lastElement().equals(this.entrada.get(0).get(0))) {
                    removerDePila();
                    //System.out.println(this.pila + "-------------------------" + this.entrada.get(0));
                    CompiladorVerano.interfaz.tablaTraza(pila, entrada.get(0));
                } else {
                    System.out.println("ERROR");
                    break;
                }
            } else if (existeInterseccionEnMatriz(this.pila.lastElement(), this.entrada.get(0).get(0))) {
                sustituirEnPila();
                //System.out.println(this.pila + "-------------------------" + this.entrada.get(0));
                CompiladorVerano.interfaz.tablaTraza(pila, entrada.get(0));
            } else {
                System.out.println("ERROR");
                break;
            }
        }
    }

    private void cargarLista() {
        this.entrada.add(new ArrayList<>());
        entrada.get(0).add("id");
        entrada.get(0).add("+");
        entrada.get(0).add("id");
        entrada.get(0).add("*");
        entrada.get(0).add("id");
        entrada.get(0).add("$");
    }

    private void removerDePila() {
        this.pila.pop();
        this.entrada.get(0).remove(0);

        if (this.entrada.get(0).isEmpty()) {
            this.entrada.remove(0);
        }
    }

    private boolean existeInterseccionEnMatriz(String x, String a) {
        boolean existe = false;

        for (this.renglon = 0; this.renglon < this.numfilas; this.renglon++) {
            if (x.equals(this.Matriz[this.renglon][0])) {
                break;
            }
        }

        for (this.columna = 0; this.columna < this.numcolumnas; this.columna++) {
            if (a.equals(this.Matriz[0][this.columna])) {
                break;
            }
        }
        if (!this.Matriz[this.renglon][this.columna].equals("404")) {
            existe = true;
        }
        return existe;
    }

    private void sustituirEnPila() {
        String aCadena[];

        aCadena = this.Matriz[this.renglon][this.columna].split(" ");
        this.pila.pop();

        for (int i = aCadena.length - 1; i >= 0; i--) {
            if (!aCadena[i].equals("")) {
                pila.push(aCadena[i]);
            }
        }
    }

    private boolean terminal(String x) {
        boolean terminal = false;

        terminal = Arrays.asList(this.terminales).contains(x);
        return terminal;
    }

    public static void main(String[] args) {
        //new AnalizadorSintactico().analizar();

    }
}