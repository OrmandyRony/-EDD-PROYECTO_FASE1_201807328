package udrawingpaperf2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


/**
 *
 * @author orman
 */
public class MatrizDispersaImagenes {
    int capa;
    ListaEncabezado filas = new ListaEncabezado("fila"); // Eje x
    ListaEncabezado columnas = new ListaEncabezado("columna"); // Eje y

    public MatrizDispersaImagenes(int capa) {
        this.capa = capa;
    }
    
    
    
    public class NodoInterno {
        String hexagecimal;
        int coordenadaX; // fila
        int coordenadaY; // columna
        NodoInterno arriba = null;
        NodoInterno abajo = null;
        NodoInterno izquierda = null;
        NodoInterno derecha = null;

        public NodoInterno( int coordenadaX, int coordenadaY, String hexagecimal) {
            this.hexagecimal = hexagecimal;
            this.coordenadaX = coordenadaX;
            this.coordenadaY = coordenadaY;
        }
        
        
    }
    
    public void insert(int posX, int posY, String hexagecimal) {
        NodoInterno nuevo = new NodoInterno(posX, posY, hexagecimal);
        
        NodoEncabezado nodoX = this.filas.getEncabezado(posX);
        NodoEncabezado nodoY = this.columnas.getEncabezado(posY);
        
        if (nodoX == null) {
            nodoX = new NodoEncabezado(posX);
            this.filas.insert(nodoX);
        }
        
        if (nodoY == null) {
            nodoY = new NodoEncabezado(posY);
            this.columnas.insert(nodoY);
        }
        
        
        // Insertar en fila
        if (nodoX.acceso == null) {
            nodoX.acceso = nuevo;
        } else {
            if (nuevo.coordenadaY < nodoX.acceso.coordenadaY) {
                nuevo.derecha = nodoX.acceso;
                nodoX.acceso.izquierda = nuevo;
                nodoX.acceso = nuevo;
            } else {
                NodoInterno tmp = nodoX.acceso;
                while (tmp != null) {
                    
                    if (nuevo.coordenadaY < tmp.coordenadaY) {
                        nuevo.derecha = tmp;
                        nuevo.izquierda = tmp.izquierda;
                        tmp.izquierda.derecha = nuevo;
                        tmp.izquierda = nuevo;
                        break;
                        
                    } else if (nuevo.coordenadaX == tmp.coordenadaX && nuevo.coordenadaY == tmp.coordenadaY) {
                        break;
                        
                    } else {
                        if (tmp.derecha == null) {
                            tmp.derecha = nuevo;
                            nuevo.izquierda= tmp;
                            break;
                        } else {
                            tmp = tmp.derecha;
                        }
                    }
                }
            }
        }
        
        // Insertar en columna
        if (nodoY.acceso == null) {
            nodoY.acceso = nuevo;
        } else {
            NodoInterno tmp2 = nodoY.acceso;
            while (tmp2 != null) {
                if (nuevo.coordenadaX < tmp2.coordenadaX) {
                    nuevo.abajo = tmp2;
                    nuevo.arriba = tmp2.arriba;
                    tmp2.arriba.abajo = nuevo;
                    tmp2.arriba = nuevo;
                    break;
                } else if (nuevo.coordenadaX == tmp2.coordenadaY && nuevo.coordenadaY == tmp2.coordenadaY) {
                    break;
                } else {
                    if (tmp2.abajo == null) {
                        tmp2.abajo = nuevo;
                        nuevo.arriba = tmp2;
                        break;
                    } else {
                        tmp2 = tmp2.abajo;
                    }
                }
                
            }
        }
        
    }
    
    public void graficar(String nombre) {
        String contenido = "digraph G{\n"
                + "    node[shape=box, width=0.7, height=0.7, fontname=\"Arial\", "
                + "fillcolor=\"white\", style=filled]\n"
                + "    edge[style = \"bold\"] node[label = \"capa:" + this.capa + "\" "
                + "fillcolor=\"darkolivegreen1\" pos = \"-1,1!\"]raiz;";
        
        contenido += "label = \"\n"
                + ""+ nombre + "\" \n"
                + "fontname=\"Arial Black\" \n"
                + "fontsize=\"25pt\" \n";

        // Graficar nodos encabezado
        NodoEncabezado pivote = this.filas.primero;
        int posX = 0;
        
        while (pivote != null) {
            contenido +=  "\nnode[label = \"F" + pivote.posicion + "\" "
                    + "fillcolor = \"azure3\"  pos=\"-1,-" + posX 
                    + "!\" shape=box]x" + pivote.posicion + ";";
            pivote = pivote.siguiente;
            posX += 1;
        }
        
        pivote = this.filas.primero;
        
        while (pivote.siguiente != null) {
            contenido += "\n\tx"+ pivote.posicion +"->x" + pivote.siguiente.posicion + ";";
            contenido += "\n\tx"+ pivote.posicion +"->x"+ pivote.siguiente.posicion +"[dir=back];";
            pivote = pivote.siguiente;
        }
        contenido += "\n\traiz->x" + this.filas.primero.posicion + ";";
        
        // Graficar nodos columna
        NodoEncabezado pivotey = this.columnas.primero;
        int posy = 0;
        while (pivotey != null) {
            contenido += "\n\tnode[label = \"C"+ pivotey.posicion 
                    + "\" fillcolor=\"azure3\" pos = \"" + posy  + ",1!\" shape=box]y"
                    +  pivotey.posicion + ";";
            pivotey = pivotey.siguiente;
            posy ++;
        }
        pivotey = this.columnas.primero;
        
        while (pivotey.siguiente != null) {
            contenido += "\n\ty" + pivotey.posicion + "->y" + pivotey.siguiente.posicion + ";";
            contenido += "\n\ty" + pivotey.posicion + "->y" + pivotey.siguiente.posicion + "[dir=back];";
            pivotey = pivotey.siguiente;
        }
           
        contenido += "\n\traiz->y" + this.columnas.primero.posicion + ";";
        
        // Graficando nodos internos
        pivote = this.filas.primero;
        posX = 0;
        while (pivote != null) {
            NodoInterno pivoteCelda = pivote.acceso;
          
            while (pivoteCelda != null) {
                NodoEncabezado pivoteY = this.columnas.primero;
                int posCeldaY = 0;
                
                while (pivoteY != null) {
                    if (pivoteY.posicion == pivoteCelda.coordenadaY) {
                        break;
                    }
                    posCeldaY ++;
                    pivoteY = pivoteY.siguiente;
                }
                
                contenido += "\n\tnode[label=\" \" fillcolor=\"" + pivoteCelda.hexagecimal + "\" pos=\"" +
                        posCeldaY + ",-" + posX + "!\" shape=box]i" + pivoteCelda.coordenadaX + "_" + pivoteCelda.coordenadaY + ";";
                pivoteCelda = pivoteCelda.derecha;
            }
            
            pivoteCelda = pivote.acceso;
            while (pivoteCelda != null) {
                if (pivoteCelda.derecha != null) {
                    contenido += "\n\ti" + pivoteCelda.coordenadaX + "_" 
                    + pivoteCelda.coordenadaY + "->i" + pivoteCelda.derecha.coordenadaX 
                    + "_"+ pivoteCelda.derecha.coordenadaY +";";
                    contenido += "\n\ti"+ pivoteCelda.coordenadaX +"_"+ pivoteCelda.coordenadaY +"->i" + pivoteCelda.derecha.coordenadaX + "_" 
                            + pivoteCelda.derecha.coordenadaY + "[dir=back];";
                  
                }
                pivoteCelda = pivoteCelda.derecha;
            }
            
            contenido += "\n\tx"+ pivote.posicion +"->i"+ pivote.acceso.coordenadaX +"_"+ pivote.acceso.coordenadaY +";";
            contenido += "\n\tx" + pivote.posicion + "->i"+ pivote.acceso.coordenadaX +"_"+ pivote.acceso.coordenadaY +"[dir=back];";
            pivote = pivote.siguiente;
            posX ++;
        }
        
        pivote = this.columnas.primero;
        
        while (pivote != null) {
            NodoInterno pivoteCelda = pivote.acceso;
            
            while (pivoteCelda != null) {
                if (pivoteCelda.abajo != null) {
                    contenido += "\n\ti" + pivoteCelda.coordenadaX + "_" + pivoteCelda.coordenadaY + "->i" 
                            + pivoteCelda.abajo.coordenadaX + "_" +  pivoteCelda.abajo.coordenadaY + ";";
                    contenido += "\n\ti" + pivoteCelda.coordenadaX + "_" + pivoteCelda.coordenadaY + "->i" 
                            +  pivoteCelda.abajo.coordenadaX + "_" +  pivoteCelda.abajo.coordenadaY + "[dir=back];";
                }
                pivoteCelda = pivoteCelda.abajo;
            }
            contenido += "\n\ty"+ pivote.posicion +"->i"+ pivote.acceso.coordenadaX +"_"+ pivote.acceso.coordenadaY +";";
            contenido += "\n\ty" + pivote.posicion + "->i"+ pivote.acceso.coordenadaX +"_"+ pivote.acceso.coordenadaY +"[dir=back];";
            pivote = pivote.siguiente;
        }
        
        contenido += "\n}";
        
         try {
            String ruta = "grafica/matriz " + nombre + ".txt";

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

            String fileInputPath = "grafica/matriz " + nombre + ".txt";
            String fileOutputPath = "matriz " + nombre + ".jpg";

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
            System.out.println("Matriz generada");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
     
        }

    }
        
    
    
}
