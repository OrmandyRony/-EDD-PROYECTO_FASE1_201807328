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
public class ListaClientesAtendidos {
    public Nodo head;
    public ListaClientesAtendidos listaOrdenar;
    public ListaClientesAtendidos listaOrdenarAscendente;
    
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
    
    public Nodo pop() {
        if (head != null) {
            Nodo tmp = head;
            head = head.next;
            return tmp;
        }
        return null;
    }
    
    public void topCincoColor () {
        ListaClientesAtendidos lista = new ListaClientesAtendidos();
        
        lista = copiarLista();
        Nodo tmp = lista.head;
        Nodo tmp2 = tmp.next;
        Nodo tmp3;
        
        while (tmp != null) {
            
            while (tmp2 != null) {
                if (tmp.cliente.cantidadImagenesColor > tmp2.cliente.cantidadImagenesColor) {
                    tmp3 = tmp;
                    tmp = tmp2;
                    tmp2 = tmp3;
                    System.out.println("Entro");
                }
                tmp2 = tmp2.next;
            }
            tmp = tmp.next;
        }
        
        
        int count = 0;
        
        Nodo aux = lista.head;
        
        while (aux != null) {
            System.out.println("Nombre: " + aux.cliente.nombre );
            
            count++;
            aux = aux.next;
            if (count == 5) {
                break;
            }
            
        }
        
    }
    
    public void topCincoBlancoNegro () {
        ListaClientesAtendidos lista = copiarLista();
        
        Nodo tmp = lista.head;
        Nodo tmp2 = tmp.next;
        Nodo tmp3;
        
        while (tmp != null) {
            System.out.println("Entro");
            while (tmp2 != null) {
                if (tmp.cliente.cantidadImagenesBlancoNegro < tmp2.cliente.imagenBlancoNegro) {
                    tmp3 = tmp;
                    tmp = tmp2;
                    tmp2 = tmp3;
                    System.out.println("Cambio");
                }
                tmp2 = tmp2.next;
            }
            tmp = tmp.next;
        }
        
        
        int count = 0;
        
        Nodo aux = lista.head;
        
        while (aux != null) {
            System.out.println("Nombre: " + aux.cliente.nombre );
            
            count++;
            aux = aux.next;
            if (count == 5) {
                break;
            }
            
        }
        
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public Nodo peek() {
        return head;
    }
    
    
    public ListaClientesAtendidos copiarLista () {
        ListaClientesAtendidos listaTmp = new ListaClientesAtendidos();
        Nodo aux = head;
        
        while (aux!= null) {
            listaTmp.insert(aux.cliente);
            aux = aux.next;
        }
        
        return listaTmp;
    }
    
    public Cliente buscarCliente(String nombre) {
        Nodo aux = this.head;
        
        while (aux != null) {
            if (aux.cliente.nombre.equals(nombre)) {
                return aux.cliente;
            }
            aux = aux.next;
        }
        
        return null;
    }
    
    public void clienteMasPasos() {
        Cliente cliente = this.head.cliente;
        Nodo aux = this.head.next;
        
        while (aux != null) {
            if (cliente.pasos < aux.cliente.pasos) {
                cliente = aux.cliente;
            }
            aux = aux.next;
        }
        
        System.out.println("----------------- Informacion Cliente ---------------");
        System.out.println("Nombre: " + cliente.nombre);
        System.out.println("Cantidad de imagenes a color impresas: "
                + cliente.cantidadImagenesColor);
        System.out.println("Cantidad de imagenes a blanco y negro: "
                + cliente.cantidadImagenesBlancoNegro);
        System.out.println("Cantidad total de imagenes impresas: "
                + cliente.totalImagenes);
        System.out.println("Cantidad de pasos: " + cliente.pasos);
    }
    
    
    public void graficarDot() {

        String conexiones = "";
        String nodos = "";
        String nombreEstructura = "Cola Clientes Atendidos";
        String cadena = "digraph G{\nlabel = \"" + nombreEstructura + "\";\nnode [shape=box];\n";

        Nodo aux = head;

        while (aux != null) {
            nodos += "N" + aux.hashCode() + "[label=\"" + aux.cliente.nombre + "\"];\n";
            if (aux.next != null) {
                conexiones += "N" + aux.next.hashCode() + " -> " + "N" + aux.hashCode() + ";\n";
            }
            aux = aux.next;
        }

        cadena += nodos + "{ rank = same " + conexiones + "\n}\n}";

        //System.out.println(cadena);
        try {
            String ruta = "grafica/ListaClientesAtendidos.txt";
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

            String fileInputPath = "grafica/ListaClientesAtendidos.txt";
            String fileOutputPath = "ListaClientesAtendidos.jpg";

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
            System.out.println("Grafica generada de la lista de atendidos");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
     
        }

    }
}
