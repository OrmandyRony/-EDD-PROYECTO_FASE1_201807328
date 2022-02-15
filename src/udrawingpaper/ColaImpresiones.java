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
    
    public boolean imprimir() {
        front.imagen.velocidad();
        return front.imagen.getPasos() == 0;
    }

    public Imagen getFrontImagen() {
        return front.imagen;
    }
    
    public void mostrar() {
        Nodo aux = front;
        while (aux != null) {
            System.out.println(aux.imagen);
            aux = aux.next;
        }
    }
    
    public boolean vacia() {
        return front != null;
    }
    
}
