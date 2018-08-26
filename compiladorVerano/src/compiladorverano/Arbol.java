/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorverano;

import java.util.Arrays;
import java.util.Stack;

/**
 *
 * @author José Alberto
 */
public class Arbol {

    Stack<String> pila = new Stack<>();
    Nodo raiz;
    String error;
    String exp;
    String exp1= "";

    String[][] repo
            = {
                {"Entero+Entero", "Entero"},
                {"Entero+Decimal", "Decimal"},
                {"Decimal+Entero", "Decimal"},
                {"Decimal+Decimal", "Decimal"},
                
                {"Cadena+Cadena","Cadena"},
                
                {"Entero-Entero", "Entero"},
                {"Entero-Decimal", "Decimal"},
                {"Decimal-Entero", "Decimal"},
                {"Decimal-Decimal", "Decimal"},
                    
                {"Entero*Entero", "Entero"},
                {"Entero*Decimal", "Decimal"},
                {"Decimal*Entero", "Decimal"},
                {"Decimal*Decimal", "Decimal"},
                    
                {"Entero/Entero", "Entero"},
                {"Entero/Decimal", "Decimal"},
                {"Decimal/Entero", "Decimal"},
                {"Decimal/Decimal", "Decimal"},
                    
                {"Entero%Entero", "Entero"},
                {"Entero%Decimal", "Decimal"},
                {"Decimal%Entero", "Decimal"},
                {"Decimal%Decimal", "Decimal"},
                
                {"Entero=Entero", ""},
                {"Decimal=Entero", ""},
                {"Decimal=Decimal", ""},
                {"Booleano=Booleano", ""},
                {"Cadena=Cadena", ""},
                
                {"Entero>Entero", "Booleano"},
                {"Entero>Decimal", "Booleano"},
                {"Decimal>Entero", "Booleano"},
                {"Decimal>Decimal", "Booleano"},
                
                {"Entero<Entero", "Booleano"},
                {"Entero<Decimal", "Booleano"},
                {"Decimal<Entero", "Booleano"},
                {"Decimal<Decimal", "Booleano"},
                    
                {"Entero<=Entero", "Booleano"},
                {"Entero<=Decimal", "Booleano"},
                {"Decimal<=Entero", "Booleano"},
                {"Decimal<=Decimal", "Booleano"},
                
                {"Entero>=Entero", "Booleano"},
                {"Entero>=Decimal", "Booleano"},
                {"Decimal>=Entero", "Booleano"},
                {"Decimal>=Decimal", "Booleano"},
                
                
                {"Entero==Entero", "Booleano"},
                {"Entero==Decimal", "Booleano"},
                {"Decimal==Entero", "Booleano"},
                {"Decimal==Decimal", "Booleano"},
                {"Booleano==Booleano", "Booleano"},
                {"Cadena==Cadena", "Booleano"},
                
                {"Entero!=Entero", "Booleano"},
                {"Entero!=Decimal", "Booleano"},
                {"Decimal!=Entero", "Booleano"},
                {"Decimal!=Decimal", "Booleano"},
                {"Booleano!=Booleano", "Booleano"},
                {"Cadena!=Cadena", "Booleano"},
                
                {"Booleano||Booleano", "Booleano"},
                {"Booleano&&Booleano", "Booleano"}
            };
    
    int numfilas = this.repo.length; //número de las filas
    //int numcolumnas = this.repo[0].length;//número de las columnas
    boolean errorSintactico = false;

    Arbol(String valor) {
        this.raiz = new Nodo(valor);
    }

    Arbol(Nodo raiz) {
        this.raiz = raiz;
    }

    Arbol(String valor, String nombre) {
        this.raiz = new Nodo(valor, nombre);
    }

    //Arbol(String padre, Arbol izq, Arbol der) {
    Arbol(Arbol padre, Arbol izq, Arbol der) {
        //this.raiz = new Nodo(padre);
        this.raiz = padre.getRaiz();
        this.raiz.setDer(der.getRaiz());
        this.raiz.setIzq(izq.getRaiz());

    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    private void imprimirEntre(Nodo reco) {
        if (reco != null) {
            imprimirEntre(reco.getIzq());
            exp=reco.dato+" ";
            exp1 = exp1 + reco.dato+" ";
            System.out.print(reco.dato + " ");
            imprimirEntre(reco.getDer());
        }
    }

    public void imprimirEntre() {
        imprimirEntre(raiz);
        //System.out.println();
    }

    private void imprimirPost(Nodo reco) {
        if (reco != null) {
            imprimirPost(reco.izq);
            imprimirPost(reco.der);
            //System.out.print(reco.dato + " ");

            if (!errorSintactico) {
                comprobarCompatibilidad(reco.nombre);
            }

            // calcular(reco.dato);
        }
    }

    public void imprimirPost() {
        imprimirPost(raiz);
        //System.out.println();
        
        if(!errorSintactico){
            
        }
            //System.out.println("verdadero");
        else
        {
            //System.out.println("falso");
            imprimirEntre(raiz);
            error = "Error Semántico, definicion de datos incorrectos en:  "+ exp1+";";
            CompiladorVerano.interfaz.error(error);
        }
        //System.out.println(pila.peek());
    }

    private void imprimirPre(Nodo reco) {
        if (reco != null) {
           // System.out.print(reco.dato + " ");
            imprimirPre(reco.izq);
            imprimirPre(reco.der);
        }
    }

    public void imprimirPre() {
        imprimirPre(raiz);
        System.out.println();
    }

    public void calcular(String elemento) {
        int resultado = 0;
        int elemento1;
        int elemento2;
        String operadores = "+-*/%";

        if (operadores.contains(elemento)) {
            // System.out.println(pila);
            elemento1 = Integer.parseInt(pila.pop());
            elemento2 = Integer.parseInt(pila.pop());

            switch (elemento) {
                case "+":
                    resultado = elemento1 + elemento2;
                    pila.push(resultado + "");
                    break;
                case "-":
                    resultado = elemento1 - elemento2;
                    pila.push(resultado + "");
                    break;
                case "*":
                    resultado = elemento1 * elemento2;
                    pila.push(resultado + "");
                    break;
                case "/":
                    resultado = elemento1 / elemento2;
                    pila.push(resultado + "");
                    break;
                case "%":
                    resultado = elemento1 % elemento2;
                    pila.push(resultado + "");
                    break;
            }
        } else {
            pila.push(elemento);
        }
    }

    public void comprobarCompatibilidad(String tipo) {
        //String resultado;
        String elemento1;
        String elemento2;
        String[] operadores = {"+","-","*","/","%","=",">","<",">=","<=","==","!=","||","&&"};
        if (Arrays.asList(operadores).contains(tipo)) {
            // System.out.println(pila);
            elemento1 = pila.pop();
            elemento2 = pila.pop();
            comprobarExistenciaReglaSemantica(elemento1, tipo, elemento2);
            
        } else 
            pila.push(tipo);
        //System.out.println(pila);
    }

    public void comprobarExistenciaReglaSemantica(String elemento1, String tipo, String elemento2) {
        for (int i = 0; i < this.numfilas; i++) {
            if (this.repo[i][0].equals(elemento2 + tipo + elemento1)) {
                //System.out.println("");
                if(!tipo.equals("="))
                    pila.push(repo[i][1]);
                //if(i == this.numfilas-1)
                    //System.out.println("verdadeo");
                errorSintactico = false;
                break;

            } else {
                errorSintactico = true;
                //if (i == this.numfilas - 1) {
                   // System.out.println("falso");
                //}
            }
        }
    }
}
