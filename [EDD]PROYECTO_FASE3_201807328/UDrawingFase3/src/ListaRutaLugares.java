/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USUARIO
 */
public class ListaRutaLugares {
    Nodo head;
    
    public class Nodo {
        LugarRecorrido lugarRecorrido;
        Nodo next = null;

        public Nodo(LugarRecorrido lugarRecorrido) {
            this.lugarRecorrido = lugarRecorrido;
        }
        
    }
    
    public void insert (int id, int peso) {
        LugarRecorrido lugarNuevo = new LugarRecorrido(id, peso);
        Nodo nuevoNodo = new Nodo(lugarNuevo);
        
        if (head == null) {
            head = nuevoNodo;
        } else {
            Nodo aux = head;
            // Se busca el ultimo nodo
            while (aux.next != null) {
                aux = aux.next;
            }
            
            aux.next = nuevoNodo;
            
        }
        
    }
    
    public Nodo pop() {
        if (head != null) {
            Nodo tmp = head;
            head = head.next;
            return tmp;
        }
        return null;
    }
    
    public void sort() {
        Nodo comprobar = head;
        Nodo aux = head;
        LugarRecorrido lugar;
        
        if (comprobar.next != null && aux != null) {
            Nodo i = this.head;
            while (i != null) {
                Nodo j = i.next;
                while (j != null) {
                    if (i.lugarRecorrido.peso > j.lugarRecorrido.peso) {
                        lugar = i.lugarRecorrido;
                        i.lugarRecorrido = j.lugarRecorrido;
                        j.lugarRecorrido = lugar;
                        
                    }
                    j = j.next;
                }
                i = i.next;
            }
        }
        
    }

    public void graficar() {
        String conexiones = "";
        String nodos = "";
        String nombreEstructura = "RutaMensajero";
        String cadena = "digraph rutaMensajero{\nlabel = \"" + nombreEstructura 
                + "\";\nnode [shape=box];\n";
        
        Nodo aux = head;
        
        while (aux != null) {
            nodos += "N" + aux.lugarRecorrido.id + "[label=\"" 
                    + aux.lugarRecorrido.nombre + "\"];\n";
            if (aux.next != null) {
                conexiones += "N" + aux.next.lugarRecorrido.id + " -> " + "N" + 
                        aux.lugarRecorrido.id + ";\n";
            }
            aux = aux.next;
        }
        
        cadena += nodos + "{ rank = same " + conexiones + "\n}\n}";
        
        Generador generador = new Generador();
        generador.imagen("Admin/RecorridoMensajero.txt", cadena, "Admin/RecorridoMensajero");
    }
}
