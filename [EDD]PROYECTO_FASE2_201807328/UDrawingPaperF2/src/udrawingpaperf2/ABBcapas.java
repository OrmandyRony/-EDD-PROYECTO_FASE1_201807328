package udrawingpaperf2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author orman
 */
public class ABBcapas {
    public Nodo raiz;
    
    public class Nodo {
        MatrizDispersaPixeles capa;
        int numeroCapa;
        Nodo izquierdo = null;
        Nodo derecho = null;

        public Nodo(MatrizDispersaPixeles capa, int numeroCapa) {
            this.capa = capa;
            this.numeroCapa = numeroCapa;
        }
        
    }
    
    /**
     * 
     * @param numeroCapa
     * @param matriz 
     * Este metodo inserta un nodo en el Arbol binario de busqueda de manera  
     * recursiva.
     * 
     */
    
    public void add(int numeroCapa, MatrizDispersaPixeles matriz) {
        
        if (raiz != null) {
            add(numeroCapa, matriz, raiz);
        } else {
            raiz = new Nodo(matriz, numeroCapa);
        }
    }
    
    public void add(int numeroCapa, MatrizDispersaPixeles matriz, Nodo tmp) {
        if (numeroCapa < tmp.numeroCapa) {
            if (tmp.izquierdo != null) {
                add(numeroCapa, matriz, tmp.izquierdo);
            } else {
                tmp.izquierdo = new Nodo(matriz, numeroCapa);
            }
        } else {
            if (tmp.derecho != null) {
                add(numeroCapa, matriz, tmp.derecho);
            } else {
                tmp.derecho = new Nodo(matriz, numeroCapa);
            }
        }
    }
    
    /**
     * Recorrido inOrden
     * Se realizan las siguientes operaciones recursivas:
     *  Atravesar el sub-árbol izquierdo
     *  visita la raíz
     *  Atraviese el sub-árbol
     * 
     */
    
    public void inOrden(){
        inOrden(raiz);
    }
    
    public void inOrden(Nodo tmp){
        if (tmp != null) {
            inOrden(tmp.izquierdo);
            System.out.println(tmp.numeroCapa);
            inOrden(tmp.derecho);
        }
    }
    
    /**
     * Recorrido Preorden
     * Se realizan las siguientes operaciones recursivas:
     *  visita la raíz
     *  Atravesar el sub-árbol izquierdo
     *  Atraviese el sub-árbol
     * 
     */
    
    public void preOrden() {
        preOrden(raiz);
    }
    
    public void preOrden(Nodo tmp) {
        if (tmp != null) {
            System.out.println(tmp.numeroCapa);
            preOrden(tmp.izquierdo);
            preOrden(tmp.derecho);
        }
    }
    
    public MatrizDispersaPixeles searchPreOrden(int capa) {
        return  searchPreOrden(capa, raiz);
       
    }
    
    public MatrizDispersaPixeles searchPreOrden(int capa, Nodo tmp) {
        if (tmp != null) {
            if (tmp.numeroCapa == capa) {
                return tmp.capa;
            }
            searchPreOrden(capa, tmp.izquierdo);
            searchPreOrden(capa, tmp.derecho);
        }
        return null;
    }
    
    /**
     * Recorrido Preorden
     * Se realizan las siguientes operaciones recursivas:
     *  visita la raíz
     *  Atravesar el sub-árbol izquierdo
     *  Atraviese el sub-árbol
     * 
     */
    
    public void postOrden() {
        inOrden(raiz);
    }
    
    public void postOrden(Nodo tmp) {
        if (tmp != null) {
            inOrden(tmp.izquierdo);
            inOrden(tmp.derecho);
            System.out.println(tmp.numeroCapa);
        }
    }
    
    public void graficoABB(String nombreImagen) {
        String graAbb = "digraph ABB {\n\tlabel= \"" + nombreImagen + "\";\n";
        graAbb += generarNodos(raiz) + "\n";
        graAbb += enlazar(raiz) + "\n }";
        
        try {
            String ruta = "grafica/" + nombreImagen + ".dot";
            String candena = graAbb;
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(candena);
            bw.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {

            String dotPath = "C:\\Program Files\\Graphviz\\bin\\dot.exe";

            String fileInputPath = "grafica/" + nombreImagen + ".dot";
            String fileOutputPath = nombreImagen + ".png";

            String tParam = "-Tpng";
            String tOParam = "-o";

            String[] cmd = new String[5];
            cmd[0] = dotPath;
            cmd[1] = tParam;
            cmd[2] = fileInputPath;
            cmd[3] = tOParam;
            cmd[4] = fileOutputPath;

            Process proceso = Runtime.getRuntime().exec(cmd);

            proceso.waitFor();
            System.out.println("Grafica generada");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

        }
    }
    
    public String generarNodos(Nodo tmp) {
        String nodos = "";
        
        if (tmp != null) {
            nodos = "\tnodo" + tmp.numeroCapa + "[label=\"" + tmp.numeroCapa + "\"]\n";
            nodos += generarNodos(tmp.izquierdo);
            nodos += generarNodos(tmp.derecho);
        }

        return nodos;
    }
    
    public String enlazar(Nodo tmp) {
        String enlaces = "";
        
        if (tmp !=  null) {
            enlaces += enlazar(tmp.izquierdo);
            enlaces += enlazar(tmp.derecho);
            
            if (tmp.izquierdo != null) { //Se valida si nodo no es hoja
                enlaces += "\tnodo" + tmp.numeroCapa + "->nodo" 
                        + tmp.izquierdo.numeroCapa +"\n";
                
            }
            
            if (tmp.derecho != null) {
               enlaces += "\tnodo"+ tmp.numeroCapa + "->nodo"+ tmp.derecho.numeroCapa +"\n";
            }
        }
        
        return enlaces;
    }
}
