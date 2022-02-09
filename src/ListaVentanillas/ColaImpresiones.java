/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListaVentanillas;

/**
 *
 * @author orman
 */
public class ColaImpresiones {
    public Nodo front;
    
    public class Nodo {
        public Imagen imagen;
        public Nodo next = null;

        public Nodo(Imagen imagen) {
            this.imagen = imagen;
        }
        
    }
    
    public void enqueue(Imagen imagen){
        Nodo nuevoNodo = new Nodo(imagen);
        
        if (front == null) {
            front = nuevoNodo;
        } else {
            Nodo aux = front;
            
            while (aux.next != null) {
                aux = aux.next;
            }
            
            aux.next = nuevoNodo;
        }
    }
    
    public void dequeue() {
        if (front != null) {
            front = front.next;
        }
    }
}
