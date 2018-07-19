/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorverano;

import java.io.FileReader;
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
    

/*    private final String[][] Matriz
          = {{"", "id", "+", "*", "(", ")", "$"},
            {"E", "T Ep", "404", "404", "T Ep", "404", "404"},
            {"Ep", "404", "+ T Ep", "404", "404", "", ""},
            {"T", "F Tp", "404", "404", "F Tp", "404", "404"},
            {"Tp", "404", "", "* F Tp", "404", "", ""},
            {"F", "id", "404", "404", "(E)", "404", "404"}
            };*/
    
     private final String[][] Matriz
            = {{"", "class", "id", "begin", "end", ";", "{","}","if","(",")","else","while","for","++","--","entero","cadena","decimal","booleano","=","int","double","float","String","boolean","final","+","-","*","/","%","==","!=","<=","<",">=",">","&&","||"},
            {"programa", "class id cuerpo_programa", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
            {"cuerpo_programa", "404", "404", "begin codigo end",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"codigo", "404", "proposicion_punto_y_coma codigo_prima_punto_y_coma", "404",  "", "404", "404", "", "proposicion_llave codigo_prima_llave", "404", "404", "404", "proposicion_llave codigo_prima_llave", "proposicion_llave codigo_prima_llave", "404", "404", "404", "404", "404", "404", "404", "proposicion_punto_y_coma codigo_prima_punto_y_coma", "proposicion_punto_y_coma codigo_prima_punto_y_coma", "proposicion_punto_y_coma codigo_prima_punto_y_coma", "proposicion_punto_y_coma codigo_prima_punto_y_coma", "proposicion_punto_y_coma codigo_prima_punto_y_coma", "proposicion_punto_y_coma codigo_prima_punto_y_coma", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"codigo_prima_punto_y_coma", "404", "404", "404",  "404", "; codigo", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"codigo_prima_llave", "404", "404", "404",  "404", "404", "404", "} codigo", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"proposicion_punto_y_coma", "404", "asignacion", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "declaracion", "declaracion", "declaracion", "declaracion", "declaracion", "declaracion", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"proposicion_llave", "404", "404", "404",  "404", "404", "404", "404", "decision", "404", "404", "404", "ciclo_while", "ciclo_for", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"decision", "404", "404", "404",  "404", "404", "404", "404", "if condicion_if cuerpo_if", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"condicion_if", "404", "404", "404",  "404", "404", "404", "404", "404", "( condicion )", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"cuerpo_if", "404", "404", "404",  "404", "404", "{ codigo cuerpo_if_2", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"cuerpo_if_2", "404", "404", "404",  "404", "404", "404", "} else cuerpo_else", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"cuerpo_else", "404", "404", "404",  "404", "404", "{ codigo", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"ciclo_for", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "for parentesis_for cuerpo_for", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"parentesis_for", "404", "404", "404",  "404", "404", "404", "404", "404", "sentencia )", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"sentencia", "404", "404", "404",  "404", "404", "404", "404", "404", "( declaracion_for sentencia_2", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"sentencia_2", "404", "condicion_for expresion_for", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "condicion_for expresion_for", "condicion_for expresion_for", "condicion_for expresion_for", "condicion_for expresion_for", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"declaracion_for", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "declaracion ;", "declaracion ;", "declaracion ;", "declaracion ;", "declaracion ;", "declaracion ;", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"condicion_for", "404", "condicion_for1 condicion_for2 ;", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "condicion_for1 condicion_for2 ;", "condicion_for1 condicion_for2 ;", "condicion_for1 condicion_for2 ;", "condicion_for1 condicion_for2 ;", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"condicion_for1", "404", "id", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "valor", "valor", "valor", "valor", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"condicion_for2", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "operRel condicion_for1", "operRel condicion_for1", "operRel condicion_for1", "operRel condicion_for1", "operRel condicion_for1", "operRel condicion_for1", "404", "404"},
           {"expresion_for", "404", "id incremento", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"incremento", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "++", "--", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"cuerpo_for", "404", "404", "404",  "404", "404", "{ codigo", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"ciclo_while", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "while condicion_while", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"condicion_while", "404", "404", "404",  "404", "404", "404", "404", "404", "cond_while cuerpo_while2", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"cond_while", "404", "404", "404",  "404", "404", "404", "404", "404", "( condicion", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"cuerpo_while", "404", "404", "404",  "404", "404", "{ codigo", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"cuerpo_while2", "404", "404", "404",  "404", "404", "404", "404", "404", "404", ") cuerpo_while", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"valor", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "entero", "cadena", "decimal", "booleano", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"asignacion", "404", "id = asignacion_prima", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"asignacion_prima", "404", "valores expresion", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "valores expresion", "valores expresion", "valores expresion", "valores expresion", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"valores", "404", "id", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "valor", "valor", "valor", "valor", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"expresion", "404", "404", "404",  "404", "", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "operAr expresion_prima", "operAr expresion_prima", "operAr expresion_prima", "operAr expresion_prima", "operAr expresion_prima", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"expresion_prima", "404", "expresion_prima2 expresion", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "expresion_prima2 expresion", "expresion_prima2 expresion", "expresion_prima2 expresion", "expresion_prima2 expresion", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"expresion_prima2", "404", "id", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "valor", "valor", "valor", "valor", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"tipo", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "int", "double", "float", "String", "boolean", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"declaracion", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "declaracion_2", "declaracion_2", "declaracion_2", "declaracion_2", "declaracion_2", "declaracion_1", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"declaracion_1", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "final declaracion_1_prima", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"declaracion_1_prima", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "tipo aux", "tipo aux", "tipo aux", "tipo aux", "tipo aux", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"declaracion_2", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "tipo aux", "tipo aux", "tipo aux", "tipo aux", "tipo aux", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"aux", "404", "id", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"operAr", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "+", "-", "*", "/", "%", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"operRel", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "==", "!=", "<=", "<", ">=", ">", "404", "404"},
           {"operLog", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "&&", "||"},
           {"condicion", "404", "condicion_prima condicion_prima2", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "condicion_prima condicion_prima2", "condicion_prima condicion_prima2", "condicion_prima condicion_prima2", "condicion_prima condicion_prima2", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"condicion_prima", "404", "id", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "valor", "valor", "valor", "valor", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"condicion_prima2", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "operRel condicion_compuesta", "operRel condicion_compuesta", "operRel condicion_compuesta", "operRel condicion_compuesta", "operRel condicion_compuesta", "operRel condicion_compuesta", "404", "404"},
           {"condicion_compuesta", "404", "condicion_prima condicion_compuesta_prima", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "condicion_prima condicion_compuesta_prima", "condicion_prima condicion_compuesta_prima", "condicion_prima condicion_compuesta_prima", "condicion_prima condicion_compuesta_prima", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"condicion_compuesta_prima", "404", "condicion_prima condicion_compuesta_prima", "404",  "404", "404", "404", "404", "404", "404", "", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "operLog condicion", "operLog condicion"}
            };
    private final int numfilas;     private final int numcolumnas;
    private int renglon;            private int columna;

    private final String terminales[];

    

    AnalizadorSintactico(ArrayList <ArrayList <String>> entrada) {
        //System.exit(0);
        this.entrada = entrada;
        this.entrada.removeIf(ArrayList::isEmpty);
        //System.out.println(entrada);
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
                    //System.out.println("ERROR");
                    CompiladorVerano.interfaz.error("Error Sintactico");
                    
                    break;
                }
            } else if (existeInterseccionEnMatriz(this.pila.lastElement(), this.entrada.get(0).get(0))) {
                sustituirEnPila();
                //System.out.println(this.pila + "-------------------------" + this.entrada.get(0));
                CompiladorVerano.interfaz.tablaTraza(pila, entrada.get(0));
            } else {
                CompiladorVerano.interfaz.error("Error Sintactico");
                break;
            }
        }
    }

    private void cargarLista() {
        this.entrada.add(new ArrayList<>());
        entrada.get(0).add("class");
        entrada.get(0).add("id");
        entrada.get(0).add("begin");
        entrada.get(0).add("end");
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
        System.out.println();
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
   

   /* public static void main(String[] args) {
        new AnalizadorSintactico();
    }*/
}





//ejecutable
//boton de reinicio

//nombre de la persona
//fecha de consulta
//diagnostico
//reconendacion