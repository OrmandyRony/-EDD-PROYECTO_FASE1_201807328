/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListaVentanillas;

import ColaRecepcion.Cliente;

/**
 *
 * @author orman
 */
public class ListaVentanillas {
    public Nodo head;
    public ListaImpresoras listaImpresoras;
    public int size = 0;
    
    public class Nodo {
        public Ventanilla ventanilla;
        public Nodo next = null;

        public Nodo(Ventanilla ventanilla) {
            this.ventanilla = ventanilla;
        }
        
    }
    
    public void crear() {
        size++;
        Ventanilla ventanilla = new Ventanilla(size); 
        Nodo nuevoNodo = new Nodo(ventanilla);
        
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
    
    public void simularIngreso(Cliente cliente) {
        Nodo aux = head;
        while (aux.ventanilla.cliente != null) {
            aux = aux.next;
        }
        aux.ventanilla.insertarCliente(cliente);
    }
    
    public void realizarPasos(){
        Nodo aux = head;
        
        while (aux != null) {
            aux.ventanilla.cliente.
        }
    }
}
