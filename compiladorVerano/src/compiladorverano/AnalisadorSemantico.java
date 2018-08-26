/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorverano;

import java.util.ArrayList;

/**
 *
 * @author José Alberto
 */
public class AnalisadorSemantico {

    ArrayList<ArrayList<String>> expresionesEvaluar = new ArrayList<>();
    ArrayList<ArrayList<String>> declaraciones = new ArrayList<>();
    ArrayList<ArrayList<String>> expresiones = new ArrayList<>();
    ArrayList<ArrayList<String>> exp = new ArrayList<>();

    int contadorExpresion = 0;
    int contadorAsignacion = 0;

    String error = "";
    String idClase = "";

    ArrayList<String> tokens;
    ArrayList<TablaSimbolos> tablaSimbolos;

    boolean errorSemantico = false;
    boolean auxIdClass = false;

    AnalisadorSemantico(ArrayList<String> tokens, ArrayList<TablaSimbolos> tablaSimbolos) {
        tokens.removeIf(String::isEmpty);

        this.tokens = tokens;
        this.tablaSimbolos = tablaSimbolos;

        analizar();
        idClase = exp.get(0).get(0);
       
        

        expresiones.remove(0);
        exp.remove(0);
        

        expresionesEvaluar = expresionesAEvaluar(expresiones, declaraciones);
        //System.out.println(expresiones);

        for (ArrayList<String> array : expresionesEvaluar) {
            new Infija_Postfija(array, this.tablaSimbolos);
        }
        //System.out.println(exp);
        //System.out.println(expresionesEvaluar);
        declaracionMultiple(declaraciones);
        variableNoDeclarada(exp);

        //variableNoDeclarada(expresiones);
    }

    public void analizar() {
        String aux = "";
        while (!tokens.isEmpty()) {

            for (TablaSimbolos tablaSimbolo : tablaSimbolos) {

                if (tokens.get(0).equals(tablaSimbolo.getToken())) {
                    aux = tablaSimbolo.getCategoria();
                }
            }
            if (tokens.get(0).equalsIgnoreCase("int") || tokens.get(0).equalsIgnoreCase("double") || tokens.get(0).equalsIgnoreCase("boolean") || tokens.get(0).equalsIgnoreCase("String")) {
                contadorAsignacion++;
                this.declaraciones.add(new ArrayList<>());
                while (!tokens.get(0).equalsIgnoreCase(";")) {
                    this.declaraciones.get(contadorAsignacion - 1).add(tokens.get(0));
                    tokens.remove(0);

                }
            } else if (aux.equals("ID")) {
                auxIdClass = true;

                contadorExpresion++;
                this.expresiones.add(new ArrayList<>());
                this.exp.add(new ArrayList<>());
                while (!tokens.get(0).equalsIgnoreCase(")") && !tokens.get(0).equalsIgnoreCase(";") && !tokens.get(0).equalsIgnoreCase("begin")) {
                    this.expresiones.get(contadorExpresion - 1).add(tokens.get(0));
                    this.exp.get(contadorExpresion - 1).add(tokens.get(0));
                    tokens.remove(0);
                }
            } else {
                tokens.remove(0);
            }
        }
    }

    public void variableNoDeclarada(ArrayList<ArrayList<String>> expresiones) {
        while (!expresiones.isEmpty()) {

            for (TablaSimbolos tablaSimbolo : tablaSimbolos) {

                if (expresiones.get(0).get(0).equals(tablaSimbolo.getToken())) {

                    if (tablaSimbolo.getCategoria().equals("ID")) {
                        if (tablaSimbolo.getTipo().equals("")) {
                            errorSemantico = true;
                            //System.out.println("Error Semántico, variable " + expresiones.get(0).get(0) + " no declarada:  ");
                            error = "Error Semántico, variable " + expresiones.get(0).get(0) + " no declarada:  ";
                            CompiladorVerano.interfaz.error(error);
                        }

                    }
                    break;
                }

            }
            expresiones.get(0).remove(0);
            if (expresiones.get(0).isEmpty()) {
                expresiones.remove(0);
            }
        }
    }

    public void declaracionMultiple(ArrayList<ArrayList<String>> declaraciones) {
        ArrayList<String> variablesDeclaracion = new ArrayList<>();
        for (ArrayList<String> declaracione : declaraciones) {
            variablesDeclaracion.add(declaracione.get(1));
        }

        for (int i = 0; i < variablesDeclaracion.size(); i++) {
            for (int j = i + 1; j < variablesDeclaracion.size(); j++) {
                if (variablesDeclaracion.get(i).equals(variablesDeclaracion.get(j))) {
                    //System.out.println("Error Semántico, variable " + variablesDeclaracion.get(i) + " ya declarada");
                    error = "Error Semántico, variable " + variablesDeclaracion.get(i) + " ya declarada";
                    CompiladorVerano.interfaz.error(error);
                    break;
                }
            }
        }

    }

    public ArrayList<ArrayList<String>> expresionesAEvaluar(ArrayList<ArrayList<String>> expresiones, ArrayList<ArrayList<String>> declaraciones) {
        ArrayList<ArrayList<String>> expresionesUnidas = new ArrayList<>();
        ArrayList<ArrayList<String>> auxExpresionesUnidas = new ArrayList<>();
        ArrayList<String> temporal = new ArrayList<>();

        for (ArrayList<String> arrayList : expresiones) {
            expresionesUnidas.add(arrayList);
        }

        for (ArrayList<String> array : declaraciones) {
            if (array.contains("=")) {
                expresionesUnidas.add(new ArrayList<>());
                for (String string : array) {
                    if (!(string.equalsIgnoreCase("int") || string.equalsIgnoreCase("double") || string.equalsIgnoreCase("boolean") || string.equalsIgnoreCase("String"))) {
                        expresionesUnidas.get(expresionesUnidas.size() - 1).add(string);
                    }
                }
            }
        }
        //System.out.println(expresionesUnidas);

        int n = 0;
        for (ArrayList<String> array : expresiones) {
            if (array.contains("++") || array.contains("--")) {
                expresionesUnidas.add(new ArrayList<>());
                expresionesUnidas.get(expresionesUnidas.size() - 1).add(expresionesUnidas.get(n).get(0));
                expresionesUnidas.get(expresionesUnidas.size() - 1).add("=");
                expresionesUnidas.get(expresionesUnidas.size() - 1).add(expresionesUnidas.get(n).get(0));
                expresionesUnidas.get(expresionesUnidas.size() - 1).add("+");
                expresionesUnidas.get(expresionesUnidas.size() - 1).add("1");
            }
            n++;

        }

        for (ArrayList<String> array : expresionesUnidas) {
            if (array.contains("++") || array.contains("--")) {

            } else {
                auxExpresionesUnidas.add(array);
            }
        }

        //System.out.println(auxExpresionesUnidas);
        return auxExpresionesUnidas;
    }

    public static void main(String[] args) {
        //new AnalisadorSemantico();
    }

}
