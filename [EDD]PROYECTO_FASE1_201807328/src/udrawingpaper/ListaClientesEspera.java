/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawingpaper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

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
                    //System.out.println("Cliente encontrado\n---------------");
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
                    last = null;
                    return tmp;
                }
                //System.out.println("Se cambio de cabeza");
                //head.next.next = head.next;
                head.next.prev = last;
                last.next = head.next;
                
                head = head.next;
                size--;
                return tmp;

            } else if (last.cliente.listaImagenes.size == last.cliente.totalImagenes) {
                Cliente tmp;
                tmp = last.cliente;
                last.prev.next = head;
                head.prev = last.prev;
                last = last.prev;
                
                size--;
                return tmp;
            } else if(!(head.next == head)) {
                Nodo nodoTemporal = head.next;

                while (nodoTemporal != last.next && nodoTemporal != null) {
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
                        size--;
                        return tmp;
                        
                    }

                    nodoTemporal = nodoTemporal.next;
                }
            }
            //System.out.println("Le mando null");
        }

        return null;
    }
    
    
    public void graficarDot() {

        String conexiones = "";
        String nodos = "";

        String cadena = "digraph G{\nlabel = \"Lista de clientes en espera \";\nnode [shape=box];\n";

        if (head == null) {
            System.out.println("Lista de espera esta vacia");
        } else {
            Nodo aux = head;

            do {
                nodos += "N" + aux.hashCode() + "[label=\"" + aux.cliente.nombre + "\"];\n";
                if (aux.next != null) {
                    conexiones += "N" + aux.hashCode() + " -> " + "N" + aux.next.hashCode() + ";\n";
                }
                aux = aux.next;
            } while (aux != last.next);
        }

        cadena += nodos + "{rank = same " + conexiones + "\n}\n}";

        //System.out.println(cadena);
        try {
            String ruta = "grafica/ListaClientesEspera.txt";
            String contenido = cadena;
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            String dotPath = "C:\\Program Files\\Graphviz\\bin\\dot.exe";

            String fileInputPath = "grafica/ListaClientesEspera.txt";
            String fileOutputPath = "ListaClientesEspera.jpg";

            String tParam = "-Tjpg";
            String tOParam = "-o";

            String[] cmd = new String[5];
            cmd[0] = dotPath;
            cmd[1] = tParam;
            cmd[2] = fileInputPath;
            cmd[3] = tOParam;
            cmd[4] = fileOutputPath;

            Runtime rt = Runtime.getRuntime();

            rt.exec(cmd);
            System.out.println("Grafica generada de la lista de espera");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }

    }
    
    

    
    
    
}
