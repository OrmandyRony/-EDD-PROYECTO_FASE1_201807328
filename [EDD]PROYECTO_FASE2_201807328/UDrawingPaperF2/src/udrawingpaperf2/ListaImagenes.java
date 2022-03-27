/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawingpaperf2;

/**
 *
 * @author orman
 */
public class ListaImagenes {
    NodoImagenes head = null;
    
    public class NodoImagenes {
        NodoImagenes next = null;
        int id;

        public NodoImagenes(int id) {
            this.id = id;
        }
        
    }
    
    public void insert(int id) {
        NodoImagenes nuevasImagenes = new NodoImagenes(id);
        
        nuevasImagenes.next = head;
        head = nuevasImagenes;
    }
    
}
