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
    public int size = 0;
    
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
        size++;
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
        
        if (head.cliente.nombre.equals(propietario)) {
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
    
    public void mostrar() {
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
    
    public void eliminar() {
        
    }
    
    public Cliente sacarClienteListaEspera() {
        if (head != null) {
            if (head.cliente.listaImagenes.size == head.cliente.totalImagenes) {
                //System.out.println("Emtro");
                Cliente tmp;
                head.cliente.paso();
                tmp = head.cliente;
                if (size == 1) {
                    size--;
                    head = null;
                    return tmp;
                }
                //System.out.println("Se cambio de cabeza");
                head.next.next = head.next;
                head.next.prev = head.next;
                head = head.next;
                return tmp;

            } else {
                Nodo nodoTemporal = head.next;

                while (nodoTemporal != last.next || nodoTemporal != null) {
                    if (nodoTemporal.cliente.totalImagenes == nodoTemporal.cliente.listaImagenes.size) {
                        Cliente tmp;
                        nodoTemporal.cliente.paso();
                        tmp = nodoTemporal.cliente;
                        if (!nodoTemporal.equals(last)) {
                            nodoTemporal.prev.next = nodoTemporal.next;
                            nodoTemporal.next.prev = nodoTemporal.prev;
                            nodoTemporal = null;
                        } else {
                            last = last.prev;
                            nodoTemporal = null;
                        }

                        return tmp;

                    }

                    nodoTemporal = nodoTemporal.next;
                }
            }
            //System.out.println("Le mando null");
        }

        return null;
    }
}
