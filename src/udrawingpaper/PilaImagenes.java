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
public class PilaImagenes {
    public Nodo top; 
            
    public class Nodo {
        public Imagen imagen;
        public Nodo next = null;

        public Nodo(Imagen imagen) {
            this.imagen = imagen;
        }

    }
    
    public void push(Imagen imagen) {
        Nodo nuevoNodo = new Nodo(imagen);
        nuevoNodo.next = top;
        top = nuevoNodo;
    }
    
    public void pop() {
        if (top != null) {
            top = top.next;
        }
    }
    
    public boolean vacia() {
        if (top == null) {
            return false;
        }
        return true;
    }

    public Imagen getTop() {
        return top.imagen;
    }
    
    
}
