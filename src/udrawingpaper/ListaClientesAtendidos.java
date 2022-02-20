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
    
    public void topCinco () {
        ListaClientesAtendidos lista = copiarLista();
        
        while (!lista.isEmpty()) {
            Nodo nodoTmp = lista.pop();
            
            while (!listaOrdenar.isEmpty() && lista.peek().cliente.cantidadImagenesColor > nodoTmp.cliente.cantidadImagenesColor) {
                lista.insert(listaOrdenar.pop().cliente);
            }
            listaOrdenar.insert(nodoTmp.cliente);
        }
        
        int count = 0;
        
        Nodo aux = listaOrdenar.head;
        
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

        cadena += nodos + "{ rank = same" + conexiones + "\n}\n}";

        //System.out.println(cadena);
        try {
            String ruta = "grafica/graphviz.txt";
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

            String fileInputPath = "grafica/ColaImpresion.txt";
            String fileOutputPath = "ColaImpresion.jpg";

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
            System.out.println("Grafica generada de la cola de impresiones");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("Error en cola de recepción");
        }

    }
}
