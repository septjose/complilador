/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorverano;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author juanjesuspadrondiaz
 */
public class ArchivoAleatorio {

    RandomAccessFile archivo;
    //Propiedades propiedades;
    FuncionHash hash = new FuncionHash();
    boolean enTabla = false;
    ArrayList<String> arrayDatos = new ArrayList<String>();

    ;
    
    public void cargarArchivo() {
        try {
            archivo = new RandomAccessFile("Tabla", "rw");
        } catch (Exception e) {
        }
    }

    public void escribirElementos(String token, String tipo_dato, String longitud, String valor, String categoria, String identificador) {
        try {
            enTabla = buscarElemento(token);
            if (archivo.length() != 0) {
                if (enTabla) {

                    //System.out.println("Elemto ya en la tabla");
                } else {
                    //posicionar puntero al final del archivo
                    archivo.seek(archivo.length());
                    archivo.writeInt(hash.hash(token));
                    archivo.writeUTF(token);
                    archivo.writeUTF(tipo_dato);
                    archivo.writeUTF(longitud);
                    archivo.writeUTF(valor);
                    archivo.writeUTF(categoria);
                    CompiladorVerano.interfaz.tabla(new TablaSimbolos(token, tipo_dato, longitud, valor, categoria, identificador));
                }
            } else {
                //posicionar puntero al final del archivo
                archivo.seek(archivo.length());
                archivo.writeInt(hash.hash(token));
                archivo.writeUTF(token);
                archivo.writeUTF(tipo_dato);
                archivo.writeUTF(longitud);
                archivo.writeUTF(valor);
                archivo.writeUTF(categoria);
                CompiladorVerano.interfaz.tabla(new TablaSimbolos(token, tipo_dato, longitud, valor, categoria, identificador));
            }
        } catch (Exception e) {
            System.out.println("error de escritura");
        }
    }

    public boolean buscarElemento(String token) {
        try {
            archivo.seek(0);
            while (archivo.getFilePointer() < archivo.length()) { //getFilePointer() -> devuelve posicion en bytes
                if (archivo.readInt() == hash.hash(token)) {
                    return true;
                } else {
                    //recorre la distancia para llegar al siguiente elemento ingresado
                    //archivo.readInt();
                    archivo.readUTF();
                    archivo.readUTF();
                    archivo.readUTF();
                    archivo.readUTF();
                    archivo.readUTF();
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    public void cerrar() {
        try {
            archivo.close();
        } catch (Exception e) {
        }
    }

    public void limpiarArchivo() {
        try {
            new RandomAccessFile("Tabla", "rw").setLength(0);
        } catch (Exception e) {
        }
    }
}
