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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juanjesuspadrondiaz
 */
public class ArchivoAleatorio {
     RandomAccessFile archivo;
    //Propiedades propiedades;
    FuncionHash hash = new FuncionHash();
    boolean enTabla = false;
    ArrayList<String> arrayDatos = new ArrayList<String>();;
    
    public void cargarArchivo(){
        try {
            archivo = new RandomAccessFile("Tabla", "rw");
        } catch (Exception e) {
        }
    }
    /*
        String = x -> cantidad de elementos + 2 bytes
        int -> 4 bytes
        */
    public void escribirElementos(String token, String tipo_dato, String longitud, String valor, String categoria){
        try {
            enTabla = buscarElemento(token);
            if(archivo.length() != 0){
                if(enTabla){
                    //hasta el momento no hace nada, eso sera en el sintactico
                    //System.out.println("Elemto ya en la tabla");
                }else{
                    //posicionar puntero al final del archivo
                    archivo.seek(archivo.length());
                    archivo.writeInt(hash.hash(token));
                    archivo.writeUTF(token);
                    archivo.writeUTF(tipo_dato);
                    archivo.writeUTF(longitud);
                    archivo.writeUTF(valor);
                    archivo.writeUTF(categoria);
                }
            }else{
                //posicionar puntero al final del archivo
                    archivo.seek(archivo.length());
                    archivo.writeInt(hash.hash(token));
                    archivo.writeUTF(token);
                    archivo.writeUTF(tipo_dato);
                    archivo.writeUTF(longitud);
                    archivo.writeUTF(valor);
                    archivo.writeUTF(categoria);
            }
        } catch (Exception e) {
            System.out.println("error de escritura");
        }
    }
    public boolean buscarElemento(String token){
        try {
            archivo.seek(0);
            while(archivo.getFilePointer() < archivo.length()){ //getFilePointer() -> devuelve posicion en bytes
                if(archivo.readInt() == hash.hash(token)){
                    return true;
                }else{
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
    
    public void imprimirConsola(){
        try {
            archivo.seek(0);
            while(archivo.getFilePointer() < archivo.length()){ //getFilePointer() -> devuelve posicion en bytes
                System.out.println(archivo.readInt()+" "+archivo.readUTF()+" "+archivo.readUTF()+" "+archivo.readUTF()+" "+archivo.readUTF()+" "+archivo.readUTF());
                /*System.out.println(archivo.readUTF());
                System.out.println(archivo.readUTF());
                System.out.println(archivo.readUTF());
                System.out.println(archivo.readUTF());
                System.out.println(archivo.readUTF());*/
            }
        } catch (Exception e) {
        }
    }
    
    public void cerrar(){
        try {
            archivo.close();
        } catch (Exception e) {
        }
    }
    
    public void cargarDatos(){
        try {
            archivo.seek(0);
            while(archivo.getFilePointer() < archivo.length()){ //getFilePointer() -> devuelve posicion en bytes
                //System.out.println(archivo.readInt()+" "+archivo.readUTF()+" "+archivo.readUTF()+" "+archivo.readUTF()+" "+archivo.readUTF()+" "+archivo.readUTF());
                /*System.out.println(archivo.readUTF());
                System.out.println(archivo.readUTF());
                System.out.println(archivo.readUTF());
                System.out.println(archivo.readUTF());
                System.out.println(archivo.readUTF());*/
                archivo.readInt();
                getArrayDatos(archivo.readUTF());
                getArrayDatos(archivo.readUTF());
                getArrayDatos(archivo.readUTF());
                getArrayDatos(archivo.readUTF());
                getArrayDatos(archivo.readUTF());
                
            }
        } catch (Exception e) {
        }
    }
    
    public void limpiarArchivo(){
        try {
            new RandomAccessFile("Tabla","rw").setLength(0); 
        } catch (Exception e) {
        }
    }
    
    public ArrayList getArrayDatos(String datos){
        
        arrayDatos.add(datos);
        
        return arrayDatos;
    }
}