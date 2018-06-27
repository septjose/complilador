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
public class CompiladorVerano {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        FuncionHash fh = new FuncionHash();
        System.out.println(fh.hash("int"));
        
        AnalizadorLexico al=new AnalizadorLexico();
        boolean checar=al.Buscar("for");
        if (checar)
        {
            System.out.println("es PR");
        }
        
                
    }
    
}
