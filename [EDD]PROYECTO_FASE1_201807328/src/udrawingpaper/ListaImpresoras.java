/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawingpaper;

/**
 *
 * @author orman
 */
public class ListaImpresoras {
    public Nodo head;
    
    public class Nodo {
        public Impresora impresora;
        public Nodo next = null;

        public Nodo(Impresora impresora) {
            this.impresora = impresora;
        }
        
    }
    
    public void insertar(Impresora impresora) {
        Nodo nuevoNodo = new Nodo(impresora);
        nuevoNodo.next = head;
        head = nuevoNodo;
    }
}
