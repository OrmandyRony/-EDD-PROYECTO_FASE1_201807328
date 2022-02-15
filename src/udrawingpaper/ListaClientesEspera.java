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
    
    public void insert(Cliente cliente) {
        Nodo nuevoNodo = new Nodo(cliente);
        
        if (head == null) {
            head = nuevoNodo;
            last = nuevoNodo;
        } else {
            nuevoNodo.prev = last;
            last.next = nuevoNodo;
            last = nuevoNodo;
            head.prev = nuevoNodo;
           
        }
    }
    
    public Cliente buscarCliente(String propietario) {
        System.out.println("BUSCANDO");
        if (head.cliente.nombre.equals(propietario)) {
            System.out.println("Es la cabeza");
            return head.cliente;
        } else {
            Nodo aux = head.next;
            
            while (aux != last.next || aux != null) {
             
                if (aux.cliente.nombre.equals(propietario)) {
                    System.out.println("Cliente encontrado\n---------------");
                    return aux.cliente;
                }
                aux = aux.next;
            }
            System.out.println("No se puedo encontrar");
            return null;
        }
    }
    
    public void mostrar(){
        if (head == null) {
            System.out.println("Lista de espera vacia");
        } else {
            Nodo aux = head;

            do {
                System.out.println(aux.cliente.nombre);
                aux = aux.next;
            } while (aux != last.next);
        }
            
        
    }
    
}
