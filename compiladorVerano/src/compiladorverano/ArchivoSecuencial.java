/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorVerano;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joseAlberto
 */
public class ArchivoSecuencial {
    
    private static RandomAccessFile file;
    private static int numeroRegistros;
    private static final int tamaño = 80;

    public ArchivoSecuencial(String ruta){
        try {
            File archivo = new File(ruta);        
        
            file = new RandomAccessFile(archivo, "rw");
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }       
    }
    
    public static void cerrar(){
        try {
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean setToken(int i, TablaSimbolos token){
        try {
            file.seek(i*tamaño);
            file.writeUTF(token.getToken());
            file.writeUTF(token.getTipoDato());
            file.writeUTF(token.getLongitud());
            file.writeUTF(token.getValor());
            file.writeUTF(token.getCategoria());
        } catch (IOException e) {
            System.out.println(e);
        }
        return true;
    }
    
    public static TablaSimbolos getToken(int i){
        
        try {
            file.seek(i*tamaño);
            return new TablaSimbolos(file.readUTF(), file.readUTF(), file.readUTF(), file.readUTF(), file.readUTF());
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
}