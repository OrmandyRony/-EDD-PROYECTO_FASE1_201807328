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
public class ListaImagenes {
    public Nodo head;
    
    public class Nodo {
        public Imagen imagen;
        public Nodo next = null;

        public Nodo(Imagen imagen) {
            this.imagen = imagen;
        }
        
    }
    
    public void insert(Imagen imagen) {
        Nodo nuevoNodo = new Nodo(imagen);
        
        if (head == null) {
            head = nuevoNodo;
        } else {
            Nodo aux = head;
            
            while (aux.next != null) {
                aux = aux.next;
            }
            
            aux.next = nuevoNodo;
        }
    }
}
