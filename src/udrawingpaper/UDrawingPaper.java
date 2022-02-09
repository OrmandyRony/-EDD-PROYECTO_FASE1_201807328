/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawingpaper;

import ColaRecepcion.*;

/**
 *
 * @author ormandyRony
 */
public class UDrawingPaper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ColaRecepcion colaRecepcion = new ColaRecepcion();
        Cliente rony = new Cliente("1", "Rony", 10, 10);
        colaRecepcion.enqueue(rony);
    }
    
}
