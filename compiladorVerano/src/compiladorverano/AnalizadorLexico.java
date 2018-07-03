/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorverano;

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
     //GUI tabla = new GUI(); 
    //String codigo;

    AnalizadorLexico(String codigo) {
        String renglonCodigo[] = null;
        String tokenArray[] = null;
        renglonCodigo = codigo.split("\n");

        for (String renglon : renglonCodigo) {
            tokenArray = renglon.split(" ");
            numRenglonActual++;
            for (String token : tokenArray) {
                    analizar(token);
            }
            if (auxComentario) {
                auxComentario = false;
               // System.out.println("Es comentario");
            }
        }
    }

    public void analizar(String token) {

        Automatas automata = new Automatas();
        FuncionHash hash = new FuncionHash();
        Arch archivo = new Arch();
        TablaSimbolos tablaSimbolos;

        boolean tokenValido = false;

        if (archivo.Buscar(token, "src/compiladorVerano/PalabrasReservadas") && !auxCadena && !auxComentario) {
            tokenValido = true;
            hash.hash(token);
            
            if(token.equalsIgnoreCase("int"))
                        {
                            CompiladorVerano.interfaz.tabla( new TablaSimbolos(token, "entero", "4","" ,"PR"));
                        } 
                         if(token.equalsIgnoreCase("double"))
                            {
                                CompiladorVerano.interfaz.tabla( new TablaSimbolos(token, "decimal", "6","" ,"PR"));
                            }  
                          if(token.equalsIgnoreCase("float"))
                            {
                                CompiladorVerano.interfaz.tabla( new TablaSimbolos(token, "decimal", "6","" ,"PR"));       
                            }
                           if(token.equalsIgnoreCase("String"))
                        {
                            CompiladorVerano.interfaz.tabla( new TablaSimbolos(token, "Caadena", "16","" ,"PR"));
                        } 
            
            
            //System.out.println("Es PR" + token + hash.hash(token));
        }

        if (archivo.Buscar(token, "src/compiladorVerano/Operadores") && !auxCadena && !auxComentario) {
            tokenValido = true;
            CompiladorVerano.interfaz.tabla( new TablaSimbolos(token, "", "","" ,"OP"));
        }

        if (automata.AutomataValorInt(token) && !auxCadena && !auxComentario) {
            tokenValido = true;
            CompiladorVerano.interfaz.tabla( new TablaSimbolos(token, "Entero", "4","" ,"DI"));
        }

        if (!tokenValido && automata.AutomataValorDouble(token) && !auxCadena && !auxComentario) {
            tokenValido = true;
            CompiladorVerano.interfaz.tabla( new TablaSimbolos(token, "Flotante", "6","" ,"DI"));
        }

        if ((!tokenValido && automata.AutomataCadena(token, 0) == 1) || automata.AutomataCadena(token, 0) == 2 || auxCadena && !auxComentario) {
            
            System.out.println(auxTokenCadena);
            if (auxCadena) {
                auxTokenCadena = auxTokenCadena+" "+token;
                if (automata.AutomataCadena(token, 1) == 1) {
                    auxCadena = false;
                    tokenValido = true;
                    CompiladorVerano.interfaz.tabla( new TablaSimbolos(auxTokenCadena, "Cadena", "16","" ,"CAD"));
                    auxTokenCadena = "";
                } else {
                    if (automata.AutomataCadena(token, 1) == 0) {
                        auxCadena = false;
                    } else {
                        if (automata.AutomataCadena(token, 1) == 2) {
                            auxCadena = true;
                        }
                    }
                }
            }

            if (!tokenValido) {
               
                if (!auxCadena) {
                     auxTokenCadena = auxTokenCadena+" "+token;
                    if (automata.AutomataCadena(token, 0) == 2) {
                        auxCadena = true;
                    } else {

                        tokenValido = true;
                        auxCadena = false;
                       
                         CompiladorVerano.interfaz.tabla( new TablaSimbolos(auxTokenCadena, "Cadena", "16","" ,"CAD"));
                          auxTokenCadena = "";
                    }
                }
            }
        }

        if (!tokenValido && automata.AutomataIdentificador(token) && !auxCadena && !auxComentario) {
            tokenValido = true;
             CompiladorVerano.interfaz.tabla( new TablaSimbolos(token, "", "","" ,"ID"));
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
        
        
            /*if(!tokenValido && token.codePointAt(0)==59 && !auxCadena && !auxComentario)
                    {
                        tokenValido = true;
                        CompiladorVerano.interfaz.tabla( new TablaSimbolos(token, "", "","" ,"DEL"));
                    }*/
        
        
        if(!tokenValido && !auxCadena && !token.equalsIgnoreCase("")){
            error = "Error en la línea "+ numRenglonActual;
            CompiladorVerano.interfaz.error(error);
            //System.out.println("Error en linea "+ numRenglonActual);
        }
            


         
    }
}
