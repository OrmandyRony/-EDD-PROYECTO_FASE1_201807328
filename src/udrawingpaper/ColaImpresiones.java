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
public class ColaImpresiones {
    public Nodo front;
    
    public class Nodo {
        public Imagen imagen;
        public Nodo next = null;

        public Nodo(Imagen imagen) {
            this.imagen = imagen;
        }
        
    }
    
    public void enqueue(Imagen imagen){
        Nodo nuevoNodo = new Nodo(imagen);
        
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
    
    public boolean imprimir() {
        front.imagen.velocidad();
        return front.imagen.getPasos() == 0;
    }

    public Imagen getFrontImagen() {
        return front.imagen;
    }
    
    public void mostrar() {
        Nodo aux = front;
        while (aux != null) {
            System.out.println(aux.imagen);
            aux = aux.next;
        }
    }
    
    public boolean vacia() {
        return front != null;
    }
    
    public void graficarDot(String tipoImpresora) {

        String conexiones = "";
        String nodos = "";
        String cadena = "digraph G{\nlabel = \"" + tipoImpresora + "\";\nnode [shape=box];\n";

        Nodo aux = front;

        while (aux != null) {
            nodos += "N" + aux.hashCode() + "[label=\"" + aux.imagen.propietario + "\"];\n";
            if (aux.next != null) {
                conexiones += "N" + aux.next.hashCode() + " -> " + "N" + aux.hashCode() + ";\n";
            }
            aux = aux.next;
        }

        cadena += nodos + "{rank = same " + conexiones + "\n}\n}";

        //System.out.println(cadena);
        try {
            String ruta = "grafica/ColaImpresion"+ tipoImpresora+ ".txt";
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

            String fileInputPath = "grafica/ColaImpresion"+ tipoImpresora+ ".txt";
            String fileOutputPath = "ColaImpresion"+ tipoImpresora+ ".jpg";

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
        }

    }
}
