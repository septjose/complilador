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
public class AnalizadorLexico {

    boolean auxCadena = false;
    String auxTokenCadena = "";
    boolean auxComentario = false;
    int numRenglonActual = 0;
    String error = "";
    String renglonesCodigo[] = null;
    String tokenArray[] = null;
    int i = 0;
    
    ArchivoAleatorio aA = new ArchivoAleatorio();
    ArrayList<ArrayList<String>> entrada = new ArrayList<>();
    //GUI tabla = new GUI(); 
    //String codigo;

    AnalizadorLexico(String codigo) {
        codigo = codigo.replace("\t" ," ");
        renglonesCodigo = codigo.split("\n");

        for (String renglon : renglonesCodigo) {
            this.entrada.add(new ArrayList<>());
            tokenArray = renglon.split(" ");
            numRenglonActual++;
            i = 0;
            for (String token : tokenArray) {
                analizar(token);
                i++;
            }
            auxCadena = false;
            if (auxComentario) {
                auxComentario = false;
                // System.out.println("Es comentario");
            }
        }
        
        entrada.get(numRenglonActual-1).add("$");
        new AnalizadorSintactico(entrada);
    }

    public void analizar(String token) {

        Automatas automata = new Automatas();
        FuncionHash hash = new FuncionHash();
        Arch archivo = new Arch();
        //TablaSimbolos tablaSimbolos;
        String tipo = "";
        String identificador = "";
        String longitud = "";

        boolean tokenValido = false;

        if (archivo.Buscar(token, "src/Archivos/PalabrasReservadas") && !auxCadena && !auxComentario) {
            tokenValido = true;
            hash.hash(token);

            if (token.equalsIgnoreCase("int") || token.equalsIgnoreCase("double") || token.equalsIgnoreCase("float") || token.equalsIgnoreCase("String") || token.equalsIgnoreCase("boolean")) 
            {
                if (token.equalsIgnoreCase("int")) {
                    CompiladorVerano.interfaz.tabla(new TablaSimbolos(token, "", "4", "", "PR", ""));
                    aA.cargarArchivo();
                    aA.escribirElementos(token, "", "4", "", "PR", "");
                    entrada.get(numRenglonActual-1).add("int");
                }
                if (token.equalsIgnoreCase("double")) {
                    CompiladorVerano.interfaz.tabla(new TablaSimbolos(token, "", "6", "", "PR", ""));
                    aA.cargarArchivo();
                    aA.escribirElementos(token, "", "6", "", "PR", "");
                    entrada.get(numRenglonActual-1).add("double");
                }
                if (token.equalsIgnoreCase("float")) {
                    CompiladorVerano.interfaz.tabla(new TablaSimbolos(token, "", "6", "", "PR", ""));
                    aA.cargarArchivo();
                    aA.escribirElementos(token, "", "6", "", "PR", "");
                    entrada.get(numRenglonActual-1).add("float");
                }
                if (token.equalsIgnoreCase("String")) {
                    CompiladorVerano.interfaz.tabla(new TablaSimbolos(token, "", "16", "", "PR", ""));
                    aA.cargarArchivo();
                    aA.escribirElementos(token, "", "16", "", "PR", "");
                    entrada.get(numRenglonActual-1).add("String");
                }
                if (token.equalsIgnoreCase("boolean")) {
                    CompiladorVerano.interfaz.tabla(new TablaSimbolos(token, "", "2", "", "PR", ""));
                    aA.cargarArchivo();
                    aA.escribirElementos(token, "", "16", "", "PR", "");
                    entrada.get(numRenglonActual-1).add("boolean");
                }
                if (token.equalsIgnoreCase("true")) {
                    CompiladorVerano.interfaz.tabla(new TablaSimbolos(token, "booleano", "2", "", "PR", ""));
                    aA.cargarArchivo();
                    aA.escribirElementos(token, "", "16", "", "PR", "");
                    entrada.get(numRenglonActual-1).add("booleano");
                }
                if (token.equalsIgnoreCase("false")) {
                    CompiladorVerano.interfaz.tabla(new TablaSimbolos(token, "booleano", "2", "", "PR", ""));
                    aA.cargarArchivo();
                    aA.escribirElementos(token, "", "16", "", "PR", "");
                    entrada.get(numRenglonActual-1).add("booleano");
                }
            } else 
                {
                    CompiladorVerano.interfaz.tabla(new TablaSimbolos(token, "", "", "", "PR", ""));
                    entrada.get(numRenglonActual-1).add(token);
                    //System.out.println(token);
                    aA.cargarArchivo();
                    aA.escribirElementos(token, "", "", "", "PR", "");
                }   
                //aA.cargarArchivo();
                //aA.escribirElementos(token, "", "", "", "PR", "");
                
            //System.out.println("Es PR" + token + hash.hash(token));
        }

        if (archivo.Buscar(token, "src/Archivos/Operadores") && !auxCadena && !auxComentario) {
            tokenValido = true;
            CompiladorVerano.interfaz.tabla(new TablaSimbolos(token, "", "", "", "OP", ""));
            aA.cargarArchivo();
            aA.escribirElementos(token, "", "", "", "OP", "");
            entrada.get(numRenglonActual-1).add(token);
        }
        if (archivo.Buscar(token, "src/Archivos/OperadoresLogicos") && !auxCadena && !auxComentario) {
            tokenValido = true;
            CompiladorVerano.interfaz.tabla(new TablaSimbolos(token, "", "", "", "OL", ""));
            aA.cargarArchivo();
            aA.escribirElementos(token, "", "", "", "OP", "");
            entrada.get(numRenglonActual-1).add(token);
        }
        
        if (archivo.Buscar(token, "src/Archivos/Agrupacion") && !auxCadena && !auxComentario) {
            tokenValido = true;
            CompiladorVerano.interfaz.tabla(new TablaSimbolos(token, "", "", "", "OA", ""));
            aA.cargarArchivo();
            aA.escribirElementos(token, "", "", "", "OP", "");
            entrada.get(numRenglonActual-1).add(token);
        }
        
        if (archivo.Buscar(token, "src/Archivos/Asignacion") && !auxCadena && !auxComentario) {
            tokenValido = true;
            CompiladorVerano.interfaz.tabla(new TablaSimbolos(token, "", "", "", "OP", ""));
            aA.cargarArchivo();
            aA.escribirElementos(token, "", "", "", "OP", "");
            entrada.get(numRenglonActual-1).add(token);
        }
        
        if (archivo.Buscar(token, "src/Archivos/OperadoresRelaciones") && !auxCadena && !auxComentario) {
            tokenValido = true;
            CompiladorVerano.interfaz.tabla(new TablaSimbolos(token, "", "", "", "OP", ""));
            aA.cargarArchivo();
            aA.escribirElementos(token, "", "", "", "OP", "");
            entrada.get(numRenglonActual-1).add(token);
        }

        if (automata.AutomataValorInt(token) && !auxCadena && !auxComentario) {
            tokenValido = true;
            CompiladorVerano.interfaz.tabla(new TablaSimbolos(token, "Entero", "4", "", "DI", ""));
            aA.cargarArchivo();
            aA.escribirElementos(token, "Entero", "4", "", "DI", "");
            entrada.get(numRenglonActual-1).add("entero");
        }

        if (!tokenValido && automata.AutomataValorDouble(token) && !auxCadena && !auxComentario) {
            tokenValido = true;
            CompiladorVerano.interfaz.tabla(new TablaSimbolos(token, "Decimal", "6", "", "DI", ""));
            aA.cargarArchivo();
            aA.escribirElementos(token, "Decimal", "6", "", "DI", "");
            entrada.get(numRenglonActual-1).add("decimal");
        }

        if ((!tokenValido && automata.AutomataCadena(token, 0) == 1) || automata.AutomataCadena(token, 0) == 2 || auxCadena && !auxComentario) {

            boolean bandera = false;
            //System.out.println(auxTokenCadena);
            if (auxCadena) {
                auxTokenCadena = auxTokenCadena + " " + token;
                if (automata.AutomataCadena(token, 1) == 1) {
                    auxCadena = false;
                    tokenValido = true;
                    CompiladorVerano.interfaz.tabla(new TablaSimbolos(auxTokenCadena, "Cadena", "16", "", "CAD", ""));
                    aA.cargarArchivo();
                    aA.escribirElementos(auxTokenCadena, "Cadena", "16", "", "CAD", "");
                    entrada.get(numRenglonActual-1).add("cadena");
                    auxTokenCadena = "";
                } else if (automata.AutomataCadena(token, 1) == 0) {
                    auxCadena = false;
                    bandera = true;

                } else if (automata.AutomataCadena(token, 1) == 2) {
                   // System.out.println("Entra");
                    //System.out.println(i);
                    //System.out.println(tokenArray.length);

                    if (tokenArray.length - 1 == i) {
                        //System.out.println("entrooooooooooo");
                        //System.out.println(token);
                        tokenValido = false;
                        auxCadena = true;
                        bandera = true;

                        error = "Error en la línea " + numRenglonActual;
                        CompiladorVerano.interfaz.error(error);
                        auxTokenCadena = "";
                    } else {
                        auxCadena = true;
                    }
                }
            }
            if (!bandera) {
                if (!tokenValido) {

                    if (!auxCadena) {
                        auxTokenCadena = auxTokenCadena + " " + token;
                        if (automata.AutomataCadena(token, 0) == 2) {

                            //System.out.println("Entra");
                            //System.out.println(i);
                            //System.out.println(tokenArray.length);
                            if (tokenArray.length - 1 == i) {
                                //System.out.println("yrah");
                                tokenValido = false;
                                auxCadena = false;
                                auxTokenCadena = "";
                            } else {
                                auxCadena = true;
                            }

                        } else {
                            tokenValido = true;
                            auxCadena = false;

                            CompiladorVerano.interfaz.tabla(new TablaSimbolos(auxTokenCadena, "Cadena", "16", "", "CAD", ""));
                            aA.cargarArchivo();
                            aA.escribirElementos(auxTokenCadena, "Cadena", "16", "", "CAD", "");
                            entrada.get(numRenglonActual-1).add("cadena");
                            auxTokenCadena = "";
                        }
                    }
                }
            }
        }

        if (!tokenValido && automata.AutomataIdentificador(token) && !auxCadena && !auxComentario) {
            tokenValido = true;

            if (i != 0) {
                if (tokenArray[i - 1].equalsIgnoreCase("int")) {

                    tipo = "Entero";
                    longitud = "4";
                }
                if (tokenArray[i - 1].equalsIgnoreCase("double")) {
                    tipo = "Decimal";
                    longitud = "6";
                }
                if (tokenArray[i - 1].equalsIgnoreCase("float")) {
                    tipo = "Decimal";
                    longitud = "6";
                }
                if (tokenArray[i - 1].equalsIgnoreCase("String")) {
                    tipo = "Cadena";
                    longitud = "16";
                }
               if (tokenArray[i - 1].equalsIgnoreCase("boolean")) {
                    tipo = "Booleano";
                    longitud = "16";
                }
            }

            if (i > 1) {
                if (tokenArray[i - 2].equalsIgnoreCase("final")) {
                    identificador = "Constante";

                } else {
                    identificador = "Variable";
                }
            } else {
                identificador = "Variable";
            }

            //System.out.println(tokenArray[i-1]);
            CompiladorVerano.interfaz.tabla(new TablaSimbolos(token, tipo, longitud, "", "ID", identificador));
            aA.cargarArchivo();
            aA.escribirElementos(token, tipo, longitud, "", "ID", "");
            entrada.get(numRenglonActual-1).add("id");
            //System.out.println(identificador);
        }

        if ((!tokenValido && automata.AutomataComentarios(token, 0) && !auxCadena) || auxComentario) {

            //System.out.println("enyra");
            if (auxComentario) {
                automata.AutomataComentarios(token, 1);
            } else {
                auxComentario = true;
            }
            tokenValido = true;
        }

        if (!tokenValido && token.equalsIgnoreCase(";") && !auxCadena && !auxComentario) {
            tokenValido = true;
            CompiladorVerano.interfaz.tabla(new TablaSimbolos(token, "", "", "", "DEL", ""));
            aA.cargarArchivo();
            aA.escribirElementos(token, "", "", "", "DEL", "");
            entrada.get(numRenglonActual-1).add(token);
        }

        if (!tokenValido && !auxCadena && !token.equalsIgnoreCase("")) {
            error = "Error en la línea: " + numRenglonActual +"; Token: " +token;
            CompiladorVerano.interfaz.error(error);
            //System.out.println("Error en linea "+ numRenglonActual);
        }
    }
}
