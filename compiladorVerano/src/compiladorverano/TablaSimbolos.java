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
public class TablaSimbolos {
    
    String token,tipo,valor,categoria;
    String longitud;
    
    
    public TablaSimbolos(String token, String tipo, String longitud, String valor, String categoria) {
        this.token = token;
        this.tipo = tipo;
        this.valor = valor;
        this.categoria = categoria;
        this.longitud = longitud;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
    
    
    
}
