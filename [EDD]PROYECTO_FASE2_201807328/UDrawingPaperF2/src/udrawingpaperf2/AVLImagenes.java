package udrawingpaperf2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author orman
 */
public class AVLImagenes {
    NodoImagen raiz = null;
    int size = 0;
    
    public class NodoImagen {
        int id;
        ABBcapas capas = new ABBcapas();
        NodoImagen izquierda = null;
        NodoImagen derecha = null;
        int altura;

        public NodoImagen(int id) {
            this.id = id;
        }
        
    }
    
    public void insert(int id) {
        size++;
        NodoImagen nuevaImagen = new NodoImagen(id);
        if (raiz != null) {
                raiz = insert(raiz, nuevaImagen);
        } else {
            raiz = nuevaImagen;
        }
    }
    
    public NodoImagen insert(NodoImagen raizActual, NodoImagen nuevaImagen) {
        if (raizActual != null) {
            if (raizActual.id > nuevaImagen.id) {
                raizActual.izquierda = insert(raizActual.izquierda, nuevaImagen);

                if (altura(raizActual.derecha) - altura(raizActual.izquierda) == -2) {
             
                    if (nuevaImagen.id < raizActual.izquierda.id) {
                        raizActual = rotacionIzquierda(raizActual);
                    } else {
                        
                        raizActual = rotacionIzquierdaDerecha(raizActual);
                    }
                }
            } else if (raizActual.id < nuevaImagen.id) {
                raizActual.derecha = insert(raizActual.derecha, nuevaImagen);

                if (altura(raizActual.derecha) - altura(raizActual.izquierda) == 2) {
                 
                    if (nuevaImagen.id > raizActual.derecha.id) {
                   
                        raizActual = rotacionDerecha(raizActual);
                    } else {
                        
                        raizActual = rotacionDerechaIzquierda(raizActual);
                    }
                }
            } else {
                System.out.println("No se pudo insertar");
            }

            raizActual.altura = alturaMaxima(altura(raizActual.derecha), altura(raizActual.izquierda)) + 1;
            return raizActual;
        } else {
           raizActual = nuevaImagen;
           return raizActual;
        }
    }
    
    public NodoImagen rotacionIzquierda(NodoImagen nodo) {
        NodoImagen tmp = nodo.izquierda;
        nodo.izquierda = tmp.derecha;
        tmp.derecha = nodo;
        nodo.altura = alturaMaxima(altura(nodo.derecha), altura(nodo.izquierda)) + 1;
        tmp.altura = alturaMaxima(nodo.altura, altura(nodo.izquierda)) + 1;
        return tmp;
    }
    
    public NodoImagen rotacionDerecha(NodoImagen nodo) {
        NodoImagen tmp = nodo.derecha;
        nodo.derecha = tmp.izquierda;
        tmp.izquierda = nodo;
        nodo.altura = alturaMaxima(altura(nodo.izquierda), altura(nodo.derecha)) + 1;
        tmp.altura = alturaMaxima(nodo.altura, altura(nodo.derecha)) + 1;
        return tmp;
    }
    
    public NodoImagen rotacionIzquierdaDerecha(NodoImagen nodo) {
        nodo.izquierda = rotacionDerecha(nodo.izquierda);
        NodoImagen tmp = rotacionIzquierda(nodo);
        return tmp;
    }
    
    public NodoImagen rotacionDerechaIzquierda(NodoImagen nodo) {
        nodo.derecha = rotacionIzquierda(nodo.derecha);
        NodoImagen tmp = rotacionDerecha(nodo);
        return tmp;
    }
    
    public int altura(NodoImagen tmp) {
        if (tmp != null) {
            return tmp.altura;
        } else {
            return -1;
        }
    }
    
    public int alturaMaxima(int h1, int h2) {
        if (h2 >= h1) {
            return h2;
        } else {
            return h1;
        }
    }
    
    /**
     * Busqueda en preorden retorna el ABB para insercción de datos
     * @param id
     * @return 
     */
    
    public ABBcapas searchPreOrden(int id) {
        if (raiz != null) {
           return searchPreOrden(id, this.raiz);
        } 
        return null;
    }
    
    public ABBcapas searchPreOrden(int id, NodoImagen tmp) {
        if (tmp != null) {
            if (id == tmp.id) {
                return tmp.capas;
            } else if (tmp.id > id) {
                //System.out.println("Izquierda " + tmp.izquierda);
                return searchPreOrden(id, tmp.izquierda);
                

            } else if (tmp.id < id) {
                //System.out.println("Derecha " + tmp.derecha);
                return searchPreOrden(id, tmp.derecha);
            } 
        }
        
        return null;
    }
    /*
    public ABBcapas searchPreOrden(int id) {
        return  searchPreOrden(id, raiz);
       
    }
    
    public ABBcapas searchPreOrden(int id, NodoImagen tmp) {
        if (tmp != null) {
            if (tmp.id == id) {
                return tmp.capas;
            }
            searchPreOrden(id, tmp.izquierda);
            searchPreOrden(id, tmp.derecha);
        }
        return null;
    }
*/
    
     public void graficoAvl() {
         String nombreImagen = "graficoAvl";
        String graAbb = "digraph Avl {\n\tlabel= \"" + nombreImagen + "\";\n";
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
            String fileOutputPath = "grafica/" + nombreImagen + ".png";

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
     
    public String generarNodos(NodoImagen tmp) {
        String nodos = "";
        
        if (tmp != null) {
            nodos = "\tnodo" + tmp.id + "[label=\"" + tmp.id + "\"]\n";
            nodos += generarNodos(tmp.izquierda);
            nodos += generarNodos(tmp.derecha);
        }

        return nodos;
    }
    
    public String enlazar(NodoImagen tmp) {
        String enlaces = "";
        
        if (tmp !=  null) {
            enlaces += enlazar(tmp.izquierda);
            enlaces += enlazar(tmp.derecha);
            
            if (tmp.izquierda != null) { //Se valida si nodo no es hoja
                enlaces += "\tnodo" + tmp.id + "->nodo" 
                        + tmp.izquierda.id +"\n";
                
            }
            
            if (tmp.derecha != null) {
               enlaces += "\tnodo"+ tmp.id + "->nodo"+ tmp.derecha.id +"\n";
            }
        }
        
        return enlaces;
    }
}
