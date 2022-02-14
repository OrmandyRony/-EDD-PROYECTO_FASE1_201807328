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
public class ListaClientesEspera {
    public Nodo head;
    public Nodo last;
    
    public class Nodo {
        Cliente cliente;
        Nodo next;
        Nodo prev;

        public Nodo(Cliente cliente) {
            this.cliente = cliente;
            this.next = null;
            this.prev = null;
        }
        
    }
    
    public void insert(String id, String nombre, int imagenColor, int imagenBlancoNegro) {
        Cliente cliente = new Cliente(id, nombre, imagenColor, imagenBlancoNegro);
        Nodo nuevoNodo = new Nodo(cliente);
        
        if (head == null) {
            head = nuevoNodo;
            last = nuevoNodo;
        } else {
         
            Nodo nodoAux = head;
            
            nuevoNodo.prev = last;
            nuevoNodo.next = nuevoNodo;
            last = nuevoNodo;
            head.prev = nuevoNodo;
           
        }
    }
}
