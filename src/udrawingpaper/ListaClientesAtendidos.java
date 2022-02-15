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
public class ListaClientesAtendidos {
    public Nodo head;
    
    public class Nodo {
        Cliente cliente;
        Nodo next = null;

        public Nodo(Cliente cliente) {
            this.cliente = cliente;
        }
    }
    
    public void insert(Cliente cliente) {
        Nodo nuevoNodo = new Nodo(cliente);
       
        nuevoNodo.next = head;
        head = nuevoNodo;
       
    }
    
}
