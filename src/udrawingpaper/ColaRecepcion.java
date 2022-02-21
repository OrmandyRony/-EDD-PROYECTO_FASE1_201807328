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
public class ColaRecepcion {
    public Nodo front;
    
    public class Nodo {
        public Cliente cliente;
        public Nodo next = null;

        public Nodo(Cliente cliente) {
            this.cliente = cliente;
        }
        
    }
    
    public void enqueue(String id, String nombre, int imagenColor, int imagenBlancoNegro){
        Cliente nuevoCliente = new Cliente(id, nombre, imagenColor, imagenBlancoNegro);
        Nodo nuevoNodo = new Nodo(nuevoCliente);
        
        if (front == null) {
            front = nuevoNodo;
        } else {
            Nodo aux = front;
            
            while (aux.next != null) {
                aux = aux.next;
            }
            
            aux.next = nuevoNodo;
        }
    }
    
    public void dequeue() {
        if (front != null) {
            front = front.next;
        }
    }
    
    public Cliente top() {
        return front.cliente;
    }
    
    public boolean vacia() {
        return front != null;
    }
    
    public void graficarDot() {
        
        String conexiones = "";
        String nodos = "";
        String nombreEstructura = "Cola Recepcion";
        String cadena = "digraph G{\nlabel = \"" + nombreEstructura + "\";\nnode [shape=box];\n";
        Nodo aux = front;
        
        while (aux != null) {
            nodos += "N" +aux.hashCode() + "[label=\"" +aux.cliente.nombre +"\"];\n";
            if (aux.next != null) {
                conexiones+="N"+ aux.next.hashCode() + " -> "+"N"+ aux.hashCode() +";\n";
            }
            aux = aux.next;
        }
        
        cadena += nodos + "{ " + conexiones + "\n}\n}";
        
        //System.out.println(cadena);
        
        try {
            String ruta = "grafica/ColaRecepcion.txt";
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

        String fileInputPath = "grafica/ColaRecepcion.txt";
        String fileOutputPath = "ColaRecepcion.jpg";

        String tParam = "-Tjpg";
        String tOParam = "-o";

        String[] cmd = new String[5];
        cmd[0] = dotPath;
        cmd[1] = tParam;
        cmd[2] = fileInputPath;
        cmd[3] = tOParam;
        cmd[4] = fileOutputPath;

        Runtime rt = Runtime.getRuntime();

        rt.exec( cmd );
        System.out.println("Grafica de la cola generada");
      } catch (Exception ex) {
        ex.printStackTrace();
      } finally {
      }
        
        
    }
    
}
