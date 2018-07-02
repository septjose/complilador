/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorverano;


/**
 *
 * @author juanjesuspadrondiaz
 */

public class AnalizadorLexico {
    
    boolean auxCadena = false;
    boolean auxComentario = false;
    int numRenglonActual = 0;
    //String codigo;
    
        AnalizadorLexico(String codigo) {
        String renglonCodigo[] = null;
        String tokenArray[] = null;
        
        renglonCodigo = codigo.split("\n");
        
        for (String renglon : renglonCodigo) {
            tokenArray=renglon.split(" ");
            numRenglonActual++;            
            for (String token : tokenArray) {
                analizar(token);
            }
             if(auxComentario)
            {
                auxComentario=false;
                System.out.println("Es comentario");
            }
        }
    }
    
     public void analizar(String token){
         
        Automatas automata = new Automatas();
        FuncionHash hash = new FuncionHash();
        Archivo archivo = new Archivo();
        TablaSimbolos tablaSimbolos;
        
        boolean tokenValido = false;
                
                if(archivo.Buscar(token,"src/compiladorVerano/PalabrasReservadas") && !auxCadena && !auxComentario)
                    {
                        tokenValido = true;
                        hash.hash(token);
                        System.out.println("Es PR" + token + hash.hash(token));
                    }
                
                if(archivo.Buscar(token,"src/compiladorVerano/Operadores") && !auxCadena && !auxComentario)
                    {
                        tokenValido = true;
                        System.out.println("Es Op");
                    }
        
                    if(automata.AutomataValorInt(token) && !auxCadena && !auxComentario)
                    {
                        tokenValido = true;
                        System.out.println("Es entero");
                    }
                    
                   if(!tokenValido && automata.AutomataValorDouble(token) && !auxCadena && !auxComentario)
                    {
                        tokenValido = true;
                        System.out.println("Es decimal");
                    }
                   
                   
                   
                   
                   
                   
                   
                    if((!tokenValido && automata.AutomataCadena(token, 0)==1) || automata.AutomataCadena(token, 0)==2 || auxCadena && !auxComentario)
                    {
                        System.out.println(token);
                        System.out.println(automata.AutomataCadena(token, 0));
                        if(auxCadena){
                            
                            if(automata.AutomataCadena(token, 1)==1)
                            {
                                System.out.println("entro");
                                auxCadena = false;
                                tokenValido = true;
                            }
                            else
                                if(automata.AutomataCadena(token, 1)==0)
                                {
                                    auxCadena = false;
                                }
                                else 
                                    if(automata.AutomataCadena(token,1)==2)
                                        auxCadena = true;
                        }

                        
                        
                        if(!tokenValido)
                        if(!auxCadena){
                            System.out.println(automata.AutomataCadena(token, 0));
                            if(automata.AutomataCadena(token, 0)==2)
                            {
                                auxCadena = true;
                                System.out.println(automata.AutomataCadena(token, 0));
                            }
                            else{

                            tokenValido = true;
                            auxCadena = false;
                            System.out.println("Es cadena");                   
                            }
                        }
                        
                        
                        

                        
                        
                        
                        
                    }
                    
                    
                    if(!tokenValido && automata.AutomataIdentificador(token) && !auxCadena && !auxComentario)
                    {
                        tokenValido = true;
                        System.out.println("Es identificador");
                    }
                    
                     if((!tokenValido && automata.AutomataComentarios(token, 0) && !auxCadena) || auxComentario )
                    {
                        
                        System.out.println("enyra");
                        
                        if(auxComentario){
                            automata.AutomataComentarios(token, 1);
                        }
                        else
                            auxComentario=true;
                        tokenValido = true;
                           
                    }
                     
                     
                     
                     
                     
                     
             /*        if(!tokenValido && token.codePointAt(0)==59);
                    {
                        tokenValido = true;
                        System.out.println("Es del");
                    }
            */
    
}
}
