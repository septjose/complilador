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
public class Automatas {

    public boolean AutomataIdentificador(String cadena) {

        int estado = 0;
        int i = 0;
        int caracter;
        boolean aceptado = false;
        String subtoken = "";

        while (i < cadena.length() && estado != 2 && subtoken.equals("")) {

            caracter = (int) (cadena.charAt(i));
            switch (estado) {

                case 0:
                    if ((caracter > 64 && caracter < 91) || (caracter > 96 && caracter < 123)) {
                        estado = 1;
                    } else {
                        estado = 2;
                    }
                    break;

                case 1:
                    if ((caracter > 64 && caracter < 91) || (caracter > 96 && caracter < 123) || (caracter > 47 && caracter < 58) || caracter == 95) {
                        estado = 1;
                    } else {

                        estado = 2;
                    }

                    break;

                case 2:
            }
            i++;
        }
        if (estado == 1) {

            //InsertarTablaSimbolos("5", cadena, "Identificador",contador);
            aceptado = true;
            //System.out.println("es");
        }

        return aceptado;
    }

    public boolean AutomataValorInt(String cadena) {
        int estado = 0;
        int i = 0;
        int caracter;

        boolean aceptado = false;

        while (i < cadena.length()) {
            caracter = (int) (cadena.charAt(i));
            switch (estado) {
                case 0:
                    if (caracter > 47 && caracter < 58) {
                        estado = 1;
                    } else {
                        estado = 2;
                    }
                    break;
                case 1:
                    if (caracter > 47 && caracter < 58) {
                        estado = 1;
                    } else {

                        estado = 2;

                    }
                    break;
                /*case 3:
                    aceptado = false;
                    break;*/
            }
            i++;
        }

        if (estado == 1) {
            //Insertar(new TablaSimbolos("" + cadena, 10, 10, "", linea));
            aceptado = true;
            //System.out.println("es");
        }
        return aceptado;
    }

    public boolean AutomataValorDouble(String cadena) {

        int estado = 0;
        int i = 0;
        int caracter;

        boolean aceptado = false;

        while (i < cadena.length()) {
            caracter = (int) (cadena.charAt(i));
            switch (estado) {
                case 0:
                    if (caracter > 47 && caracter < 58) {
                        estado = 1;
                    } else {
                        estado = 4;
                    }
                    break;

                case 1:
                    if (caracter > 47 && caracter < 58) {
                        estado = 1;
                    } else if (caracter == 46) {
                        estado = 2;
                    } else {
                        estado = 4;
                    }
                    break;

                case 2:
                    if (caracter > 47 && caracter < 58) {
                        estado = 3;
                    } else if (caracter == 46) {
                        estado = 4;
                    } else {
                        estado = 4;
                    }
                    break;

                case 3:
                    if (caracter > 47 && caracter < 58) {
                        estado = 3;
                    } else if (caracter == 46) {
                        estado = 4;
                    } else {
                        estado = 4;
                    }
                    break;
                case 4:
            }
            i++;
        }

        if (estado == 3) {
            //Insertar(new TablaSimbolos("" + cadena, 10, 11, "", linea));
            //          InsertarTablaSimbolos("120", cadena, "Double", contador);
            aceptado = true;
            //  System.out.println("es");
        }

        return aceptado;
    }

    public boolean AutomataComentarios(String cadena, int estado) {

        //int estado = 0;
        int i = 0;
        int caracter;

        boolean aceptado = false;
        String subtoken = "";

        while (i < cadena.length() && estado != 2 && subtoken.equals("")) {

            caracter = (int) (cadena.charAt(i));
            switch (estado) {

                case 0:
                    if (caracter == 35) {
                        estado = 1;
                    } else {
                        estado = 3;
                    }
                    break;

                case 1:
                    if (true) {
                        estado = 1;
                    }

                    break;

                // case 2:                  
            }
            i++;
        }
        if (estado == 1) {

            //InsertarTablaSimbolos("5", cadena, "Identificador",contador);
            aceptado = true;
            // System.out.println("es");
        }

        return aceptado;
    }

    public int AutomataCadena(String cadena, int estado) {

        //int estado = 0;     
        int i = 0;
        int caracter;

        int aceptado = 0;
        String subtoken = "";

        while (i < cadena.length() && estado != 3 && subtoken.equals("")) {

            caracter = (int) (cadena.charAt(i));
            switch (estado) {

                case 0:
                    if (caracter == 34) {
                        estado = 1;
                    } else {
                        estado = 3;
                    }
                    break;

                case 1:
                    if (caracter != 34) {
                        estado = 1;
                    } else if (caracter == 34) {
                        estado = 2;
                    } else {
                        estado = 3;
                    }

                    break;

                case 2:
                    if (caracter != 34) {
                        estado = 3;
                    } else if (caracter == 34) {
                        estado = 3;

                    } else {
                        estado = 2;
                    }

                    break;
                case 3:
                    aceptado = 0;
                    break;
            }
            i++;
        }
        if (estado == 2) {

            //InsertarTablaSimbolos("5", cadena, "Identificador",contador);
            aceptado = 1;
            //System.out.println("es");
        }

        if (estado == 1) {
            aceptado = 2;
        }

        return aceptado;
    }

}
