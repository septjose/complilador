/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorverano;

import java.util.ArrayList;
import java.util.Stack;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author José Alberto
 */
public class GUI extends javax.swing.JFrame {
    DefaultTableModel model;
    ArrayList<String> token;
    
        private final String[][] Matriz
            = {{"", "class", "id", "begin", "end", ";", "{","}","if","(",")","else","while","for","++","--","entero","cadena","decimal","booleano","=","int","double","float","String","boolean","final","+","-","*","/","%","==","!=","<=","<",">=",">","&&","||"},
           {"programa", "class id cuerpo_programa", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"cuerpo_programa", "404", "404", "begin codigo end",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"codigo", "404", "proposicion_punto_y_coma codigo_prima_punto_y_coma", "404",  "", "404", "404", "", "proposicion_llave codigo_prima_llave", "404", "404", "404", "proposicion_llave codigo_prima_llave", "proposicion_llave codigo_prima_llave", "404", "404", "404", "404", "404", "404", "404", "proposicion_punto_y_coma codigo_prima_punto_y_coma", "proposicion_punto_y_coma codigo_prima_punto_y_coma", "proposicion_punto_y_coma codigo_prima_punto_y_coma", "proposicion_punto_y_coma codigo_prima_punto_y_coma", "proposicion_punto_y_coma codigo_prima_punto_y_coma", "proposicion_punto_y_coma codigo_prima_punto_y_coma", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"codigo_prima_punto_y_coma", "404", "404", "404",  "404", "; codigo", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"codigo_prima_llave", "404", "404", "404",  "404", "404", "404", "} codigo", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"proposicion_punto_y_coma", "404", "asignacion", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "declaracion", "declaracion", "declaracion", "declaracion", "declaracion", "declaracion", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"proposicion_llave", "404", "404", "404",  "404", "404", "404", "404", "decision", "404", "404", "404", "ciclo_while", "ciclo_for", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"decision", "404", "404", "404",  "404", "404", "404", "404", "if condicion_if cuerpo_if", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"condicion_if", "404", "404", "404",  "404", "404", "404", "404", "404", "( condicion )", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"cuerpo_if", "404", "404", "404",  "404", "404", "{ codigo cuerpo_if_2", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"cuerpo_if_2", "404", "404", "404",  "404", "404", "404", "} else cuerpo_else", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"cuerpo_else", "404", "404", "404",  "404", "404", "{ codigo", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"ciclo_for", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "for parentesis_for cuerpo_for", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"parentesis_for", "404", "404", "404",  "404", "404", "404", "404", "404", "sentencia )", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"sentencia", "404", "404", "404",  "404", "404", "404", "404", "404", "( declaracion_for sentencia_2", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"sentencia_2", "404", "condicion_for expresion_for", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "condicion_for expresion_for", "condicion_for expresion_for", "condicion_for expresion_for", "condicion_for expresion_for", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"declaracion_for", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "declaracion ;", "declaracion ;", "declaracion ;", "declaracion ;", "declaracion ;", "declaracion ;", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"condicion_for", "404", "condicion_for1 condicion_for2 ;", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "condicion_for1 condicion_for2 ;", "condicion_for1 condicion_for2 ;", "condicion_for1 condicion_for2 ;", "condicion_for1 condicion_for2 ;", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"condicion_for1", "404", "id", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "valor", "valor", "valor", "valor", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"condicion_for2", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "operRel condicion_for1", "operRel condicion_for1", "operRel condicion_for1", "operRel condicion_for1", "operRel condicion_for1", "operRel condicion_for1", "404", "404"},
           {"expresion_for", "404", "id incremento", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"incremento", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "++", "--", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"cuerpo_for", "404", "404", "404",  "404", "404", "{ codigo", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"ciclo_while", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "while condicion_while", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"condicion_while", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "condicion cuerpo_while2", "condicion cuerpo_while2", "condicion cuerpo_while2", "condicion cuerpo_while2", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"cuerpo_while", "404", "404", "404",  "404", "404", "{ codigo", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"cuerpo_while2", "404", "404", "404",  "404", "404", "404", "404", "404", "404", ") cuerpo_while", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"valor", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "entero", "cadena", "decimal", "booleano", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"asignacion", "404", "id = asignacion_prima", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"asignacion_prima", "404", "valores expresion", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "valores expresion", "valores expresion", "valores expresion", "valores expresion", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"valores", "404", "id", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "valor", "valor", "valor", "valor", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"expresion", "404", "404", "404",  "404", "", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "operAr expresion_prima", "operAr expresion_prima", "operAr expresion_prima", "operAr expresion_prima", "operAr expresion_prima", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"expresion_prima", "404", "expresion_prima2 expresion", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "expresion_prima2 expresion", "expresion_prima2 expresion", "expresion_prima2 expresion", "expresion_prima2 expresion", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"expresion_prima2", "404", "id", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "valor", "valor", "valor", "valor", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"tipo", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "int", "double", "float", "String", "boolean", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"declaracion", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "declaracion_2", "declaracion_2", "declaracion_2", "declaracion_2", "declaracion_2", "declaracion_1", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"declaracion_1", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "final declaracion_1_prima", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"declaracion_1_prima", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "tipo aux", "tipo aux", "tipo aux", "tipo aux", "tipo aux", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"declaracion_2", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "tipo aux", "tipo aux", "tipo aux", "tipo aux", "tipo aux", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"aux", "404", "id", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"operAr", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "+", "-", "*", "/", "%", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"operRel", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "==", "!=", "<=", "<", ">=", ">", "404", "404"},
           {"operLog", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "&&", "||"},
           {"condicion", "404", "condicion_prima condicion_prima2", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "condicion_prima condicion_prima2", "condicion_prima condicion_prima2", "condicion_prima condicion_prima2", "condicion_prima condicion_prima2", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"condicion_prima", "404", "id", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "valor", "valor", "valor", "valor", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"condicion_prima2", "404", "404", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "operRel condicion_compuesta", "operRel condicion_compuesta", "operRel condicion_compuesta", "operRel condicion_compuesta", "operRel condicion_compuesta", "operRel condicion_compuesta", "404", "404"},
           {"condicion_compuesta", "404", "condicion_prima condicion_compuesta_prima", "404",  "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "condicion_prima condicion_compuesta_prima", "condicion_prima condicion_compuesta_prima", "condicion_prima condicion_compuesta_prima", "condicion_prima condicion_compuesta_prima", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404"},
           {"condicion_compuesta_prima", "404", "condicion_prima condicion_compuesta_prima", "404",  "404", "404", "404", "404", "404", "404", "", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "404", "operLog condicion", "operLog condicion"}
            };
    
    

    /**
     * Creates new form Interfaz
     */
    public GUI() {
        initComponents();
        
        this.model = (DefaultTableModel) tblTAS.getModel();
        Object[] tblModel = new Object[this.Matriz.length];
            int i = 0;
        for (String [] string : this.Matriz) {
            i = 0;
            for (String string1 : string) {
                tblModel[i] = string;   
            tblModel[i] = string1;
            model.addRow(tblModel);
            tblTAS.setModel(model);
            i++;
            }

        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxtCuaderno = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSimbolos = new javax.swing.JTable();
        lbError = new javax.swing.JLabel();
        lbTabla = new javax.swing.JLabel();
        btnCompilar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtxtErrores = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblTAS = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblTraza = new javax.swing.JTable();
        lbTabla1 = new javax.swing.JLabel();
        lbTabla2 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jtxtCuaderno.setColumns(20);
        jtxtCuaderno.setRows(5);
        jScrollPane1.setViewportView(jtxtCuaderno);

        tblSimbolos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Token", "Tipo", "Longitud", "Valor", "Categoria", "Identificador"
            }
        ));
        jScrollPane3.setViewportView(tblSimbolos);
        if (tblSimbolos.getColumnModel().getColumnCount() > 0) {
            tblSimbolos.getColumnModel().getColumn(2).setHeaderValue("Longitud");
            tblSimbolos.getColumnModel().getColumn(3).setHeaderValue("Valor");
            tblSimbolos.getColumnModel().getColumn(4).setHeaderValue("Categoria");
            tblSimbolos.getColumnModel().getColumn(5).setHeaderValue("Identificador");
        }

        lbError.setText("                 Errores");

        lbTabla.setText("Tabla de Simbolos");

        btnCompilar.setText("Compilar");
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jtxtErrores.setColumns(20);
        jtxtErrores.setRows(5);
        jScrollPane4.setViewportView(jtxtErrores);

        tblTAS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Pila", "Entrada"
            }
        ));
        jScrollPane5.setViewportView(tblTAS);

        tblTraza.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Pila", "Entrada"
            }
        ));
        jScrollPane7.setViewportView(tblTraza);

        lbTabla1.setText("Traza");

        lbTabla2.setText("Tabla de análisis sintáctico");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbTabla)
                        .addGap(326, 326, 326))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane7)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane3)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(btnCompilar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(100, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(235, 235, 235)
                .addComponent(lbError, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(256, 256, 256)
                .addComponent(lbTabla2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbTabla1)
                .addGap(330, 330, 330))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbTabla)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbTabla2)
                    .addComponent(lbTabla1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCompilar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(112, 112, 112))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lbError)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        // TODO add your handling code here:
        jtxtErrores.setText("");
        ArchivoAleatorio aA = new ArchivoAleatorio();
        aA.limpiarArchivo();
        token = new ArrayList<>();
        String cabezera[] = new String[]{"Token", "Tipo", "Longitud", "Valor","Categoria","Identificador"}; // Nombre de las columnas de la tabla
        String datos[][] = {};
        DefaultTableModel modelo = new DefaultTableModel(datos, cabezera);  // Modelo de la tabla
        tblSimbolos.setModel(modelo);
        
        cabezera = null;
        cabezera = new String[]{"Pila", "Entrada"}; // Nombre de las columnas de la tabla
        modelo = new DefaultTableModel(datos, cabezera);  // Modelo de la tabla
        tblTraza.setModel(modelo);
        
        String codigo = jtxtCuaderno.getText();
        AnalizadorLexico analiadorLexico = new AnalizadorLexico(codigo);
        
    }//GEN-LAST:event_btnCompilarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        jtxtCuaderno.setText("");
        jtxtErrores.setText("");
        ArchivoAleatorio aA = new ArchivoAleatorio();
        aA.limpiarArchivo();
        String cabezera[] = new String[]{"Token", "Tipo", "Longitud", "Valor","Categoria","Identificador"}; // Nombre de las columnas de la tabla
        String datos[][] = {};
        DefaultTableModel modelo = new DefaultTableModel(datos, cabezera);  // Modelo de la tabla
        tblSimbolos.setModel(modelo);
        
        cabezera = null;
        cabezera = new String[]{"Pila", "Entrada"}; // Nombre de las columnas de la tabla
        modelo = new DefaultTableModel(datos, cabezera);  // Modelo de la tabla
        tblTraza.setModel(modelo);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        
        //AnalizadorSintactico analizadorSintactico = new AnalizadorSintactico();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalirActionPerformed

    public void tabla(TablaSimbolos registro) {
        this.model = (DefaultTableModel) tblSimbolos.getModel();
        Object[] tblModel = new Object[6];

        if(token.isEmpty() || !token.contains(registro.token))
        {
            token.add(registro.token);
            
            tblModel[0] = registro.getToken();
            tblModel[1] = registro.getTipo();
            tblModel[2] = registro.getLongitud();
            tblModel[3] = registro.getValor();
            tblModel[4] = registro.getCategoria();
            tblModel[5] = registro.getIdentificador();
            model.addRow(tblModel);
            tblSimbolos.setModel(model);
        }
    }
    
    public void tablaTraza(Stack<String> pila, ArrayList<String> entrada)
    {    
        Object renglon [] = new Object [2];
        renglon[0] = pila.toString();
        renglon[1] = entrada.toString();
        
        this.model = (DefaultTableModel) tblTraza.getModel();
        this.model.addRow(renglon);  
        
        tblTraza.setModel(model);
    }
    
    public void error(String error)
    {
        jtxtErrores.setText(jtxtErrores.getText()+"\n"+error);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCompilar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jtxtCuaderno;
    private javax.swing.JTextArea jtxtErrores;
    private javax.swing.JLabel lbError;
    private javax.swing.JLabel lbTabla;
    private javax.swing.JLabel lbTabla1;
    private javax.swing.JLabel lbTabla2;
    private javax.swing.JTable tblSimbolos;
    private javax.swing.JTable tblTAS;
    private javax.swing.JTable tblTraza;
    // End of variables declaration//GEN-END:variables

}
