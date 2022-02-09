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
public class ListaVentanillas {
    public Nodo head;
    
    public class Nodo {
        public Ventanilla ventanilla;
        public Nodo next = null;

        public Nodo(Ventanilla ventanilla) {
            this.ventanilla = ventanilla;
        }
        
    }
    
    public void insertar(Ventanilla ventanilla) {
        Nodo nuevoNodo = new Nodo(ventanilla);
        nuevoNodo.next = head;
        head = nuevoNodo;
    }
}
