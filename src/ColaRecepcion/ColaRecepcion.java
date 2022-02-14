/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ColaRecepcion;

/**
 *
 * @author orman
 */
public class ColaRecepcion {
    public Nodo front;
    
    public class Nodo {
        public Cliente cliente;
        public Nodo next = null;

        public Nodo(Cliente cliente) {
            this.cliente = cliente;
        }
        
    }
    
    public void enqueue(String id, String nombre, int imagenColor, int imagenBlancoNegro){
        Cliente nuevoCliente = new Cliente(id, nombre, imagenColor, imagenBlancoNegro);
        Nodo nuevoNodo = new Nodo(nuevoCliente);
        
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
    
    public Cliente top() {
        return front.cliente;
    }
    
    public boolean vacia() {
        if (front == null) {
            return false;
        }
        return true;
    }
    
}
