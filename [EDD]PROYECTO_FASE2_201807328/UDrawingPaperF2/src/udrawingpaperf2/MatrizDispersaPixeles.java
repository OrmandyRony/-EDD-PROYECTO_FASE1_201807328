package udrawingpaperf2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


/**
 *
 * @author orman
 */
public class MatrizDispersaPixeles {
    int capa;
    ListaEncabezado filas = new ListaEncabezado("fila"); // Eje x
    ListaEncabezado columnas = new ListaEncabezado("columna"); // Eje y

    /*
    public MatrizDispersaPixeles(int capa) {
        this.capa = capa;
    }
    
    */
    
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
            String ruta = "grafica/matriz" + nombre + ".dot";

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

            String dotPath = "C:\\Program Files\\Graphviz\\bin\\neato.exe";

            String fileInputPath = "grafica/matriz" + nombre + ".dot";
            String fileOutputPath = "matriz" + nombre + ".jpg";

            String tParam = "neato -Tjpg ";
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
    
    public void graficarDot(String nombre) {
        
        String contenido = "digraph T{ \n node[shape=box fontname=\"Arial\" "
                + "fillcolor=\"white\" style=filled ] \n root[label = \""
                + "capa: " + this.capa  + "\", group=1]\n"
                + "label = \"Matriz dispersa\" \nfontname=\"Arial Black\" \nfontsize=\"15pt\" \n";
        
        NodoEncabezado pivote = this.filas.primero;
        
        while (pivote != null) {
            contenido += "F" + pivote.posicion + "[label=\"F" + pivote.posicion 
                    + "\",fillcolor=\"plum\",group=1];\n";
            pivote = pivote.siguiente;
        }
        
        pivote = this.filas.primero;
        
        while (pivote != null) {
            if (pivote.siguiente != null) {
                contenido += "F" + pivote.posicion + "->F" + pivote.siguiente.posicion + ";\n";
                contenido += "F" + pivote.siguiente.posicion + "->F" + pivote.posicion + ";\n";
            }
            pivote = pivote.siguiente;
        }
        
        NodoEncabezado pivoteY = this.columnas.primero;
       
        /*
        while (pivoteY != null) {
            contenido += "F" + pivote.posicion + "[label=\"F" + pivote.posicion + " \"\",fillcolor=\"plum\",group=1];\\n";
            pivote = pivote.siguiente;
        }
        */
        
       
        
        while (pivoteY != null) {
            int grupo = pivoteY.posicion + 1;
            
            contenido += "C" + pivoteY.posicion + "[label=\"C" + pivoteY.posicion 
                    + "\",fillcolor=\"powderblue\",group=" + grupo + "];\n";
   
            pivoteY = pivoteY.siguiente;
        }
        
        
        int cont = 0;
        pivoteY  = this.columnas.primero;
        while (pivoteY != null) {
            if (pivoteY.siguiente != null) {
                contenido += "C" + pivoteY.posicion + "->C" + pivoteY.siguiente.posicion+ ";\n";
                contenido += "C" + pivoteY.siguiente.posicion + "->C" + pivoteY.posicion+ ";\n";
            }
        
            pivoteY = pivoteY.siguiente;
        }
        
        
        
        pivoteY = this.columnas.primero;
        pivote = this.filas.primero;
        
        contenido += "root->F" + pivote.posicion + ";"
                + "\n root->C" + pivoteY.posicion + ";"
                + "\n {rank=same; root;";
        
        
        
        pivoteY = this.columnas.primero;
        while (pivoteY != null) {
            contenido += "C" + pivoteY.posicion + ";";
            pivoteY = pivoteY.siguiente;
        }
        
        contenido += "}\n";
        
        NodoEncabezado aux = this.filas.primero;
        NodoInterno aux2 = aux.acceso;
        
        while (aux != null) {
            cont++;
            while (aux2 != null) {
                contenido += "N"+ aux2.coordenadaX +"_"+ aux2.coordenadaY 
                        +"[label=\"" + aux2.hexagecimal + "\",group=\"" 
                        + (aux2.coordenadaY + 1) + "\", fillcolor=\"black\"];\n";
                aux2 = aux2.derecha;
            }
            
            aux = aux.siguiente;
            if (aux != null) {
                aux2= aux.acceso;
            }
        }
        
        aux = this.filas.primero;
        aux2 = aux.acceso;
        cont = 0;
        
        
        
        
        
        while (aux != null) {
            //System.out.println("Entro----------------------------------------");
            String rank = "{rank = same; F" + aux.posicion +";";
            cont = 0;
            
            while (aux2 != null) {
                if (cont == 0) {
                    contenido += "F"+ aux.posicion +"->N"+ aux2.coordenadaX +"_"+ aux2.coordenadaY +";\n";
                    contenido += "N"+ aux2.coordenadaX +"_"+ aux2.coordenadaY + "->F"
                            + aux.posicion+";\n";
                    cont++;
                }
                
                if (aux2.derecha != null) {
                    contenido +=  "N"+ aux2.coordenadaX +"_"+ aux2.coordenadaY +"->N"
                            +aux2.derecha.coordenadaX +"_"+ aux2.derecha.coordenadaY+";\n";
                    contenido +=  "N"+ aux2.derecha.coordenadaX +"_"+ aux2.derecha.coordenadaY +"->N"
                            +aux2.coordenadaX +"_"+ aux2.coordenadaY+";\n";
                }
                
                rank += "N" + aux2.coordenadaX + "_" +aux2.coordenadaY + ";";
                aux2 = aux2.derecha;
            }
            
            
            
            aux = aux.siguiente;
            if (aux != null) {
                aux2 = aux.acceso;
            }
            //System.out.println(rank);
            contenido += rank + "}\n";
        }
        
        
        aux = this.columnas.primero;
        aux2 = aux.acceso;
        cont = 0;
        
        while (aux != null) {
            cont = 0;
            contenido += "";
            
            while (aux2 != null) {
                if (cont == 0) {
                    contenido += "C"+ aux.posicion +"->N"+ aux2.coordenadaX +"_"+ aux2.coordenadaY +";\n";
                    contenido += "N"+ aux2.coordenadaX +"_"+ aux2.coordenadaY +"->C"+ aux.posicion+";\n";
                    cont++;
                }
                
                if (aux2.abajo != null) {
                    contenido +=  "N"+ aux2.abajo.coordenadaX +"_"+ aux2.abajo.coordenadaY +"->N"
                            +aux2.coordenadaX +"_"+ aux2.coordenadaY+";\n";
                    contenido +=  "N"+ aux2.coordenadaX +"_"+ aux2.coordenadaY +"->N"
                            +aux2.abajo.coordenadaX +"_"+ aux2.abajo.coordenadaY +";\n";
                }
         
                aux2 = aux2.abajo;
            }
            aux = aux.siguiente;
            
            if (aux != null) {
                aux2 = aux.acceso;
            }
            
        }
        contenido += "}\n";
   
        try {
            String ruta = "grafica/MatrizDisperza.txt";
            String candena = contenido;
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

            String fileInputPath = "grafica/MatrizDisperza.txt";
            String fileOutputPath = "MatrizDisperza.jpg";

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
            System.out.println("Grafica generada");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

        }
      
    }
    

    
        
    public void crearGrafo2() {

        //primero darle los valores a los nodos internos
        
        String conca = "";
        //Luego necesito recorrer los nodos
        String contenido = "digraph {\n"
                + "\n"
                + "rankdir = TB;\n"
                + "node [shape=rectangle, height=0.5, width=0.5];\n"
                + "graph[ nodesep = 0.5];\n";

        //horizontal = cabeceras de arriba; group = valor;
        //vertical = cabeceras de lado; group = 0
        //crear los nodos de las cabeceras;
        //pivote
        contenido += "pivote[ label = \"pivote\", width = 1.5, style = filled];";

        NodoEncabezado tmp = filas.primero;

        //primero las cabeceras de filas.
        contenido += "\n//cabeceras columnas\n";
        while (tmp != null) {
            contenido += "\nF" + tmp.posicion + "[label=\"F" + tmp.posicion+ "" + "\" color=\"white\" style=\"filled\" ]";
            tmp = tmp.siguiente;
        }
        tmp = columnas.primero;

        //primero las cabeceras de columnas.
        contenido += "\n//cabeceras filas\n";
        while (tmp != null) {
            contenido += "\nC" + tmp.posicion + "[label=\"C" + tmp.posicion + "" + "\" color=\"white\" style=\"filled\" ]";
            tmp = tmp.siguiente;
        }
        //ahora declaramos los valores dentro de la matriz;
        contenido += "\n//contenido matriz\n";
        tmp = columnas.primero;
        
        while (tmp != null) {
            NodoInterno piv = tmp.acceso;
            while (piv != null) {
                contenido += "\nN" + piv.coordenadaX + "_" + piv.coordenadaY + "[label=\"N" + piv.coordenadaX + "--" + piv.coordenadaY + "\" color=\"" + piv.hexagecimal + "\" style=\"filled\" ];";
                piv = piv.abajo;
            }
            tmp = tmp.siguiente;
        }
        contenido += "\n//Conexiones entre filas\n";
        
        tmp = filas.primero;
        while (tmp != null) {
            if (tmp.siguiente != null) {
                contenido += "\nF" + tmp.posicion + "->F" + tmp.siguiente.posicion + "[dir=both];";
            }
            tmp = tmp.siguiente;
        }
        
        contenido += "\n//Conexiones entre columnas\n";
        tmp = columnas.primero;
        while (tmp != null) {
            if (tmp.siguiente != null) {
                contenido += "\nC" + tmp.posicion + "->C" + tmp.siguiente.posicion + "[dir=both];";
            }
            tmp = tmp.siguiente;
        }
        
        //ahora a conectar las columnas con el contenido de la matriz.
        contenido += "\n//Concexiones fila-nodoInterno\n";
        tmp = filas.primero;
        while (tmp != null) {
            NodoInterno piv = tmp.acceso;
            contenido += "\nF" + tmp.posicion + "->N" + piv.coordenadaX + "_" + piv.coordenadaY + "[dir=both];";
            while (piv != null) {
                if (piv.derecha != null) {
                    contenido += "\nN" + piv.coordenadaX + "_" + piv.coordenadaY + "->N" + piv.derecha.coordenadaX + "_" + piv.derecha.coordenadaY + "[dir=both];";
                    
                }
                piv = piv.derecha;
            }
            tmp = tmp.siguiente;
        }
        
        //ahora a conectar las filas con el contenido de la matriz.
        
        contenido += "\n//Concexiones columna-NodoInterno\n";
        tmp = columnas.primero;
        while (tmp != null) {
            NodoInterno piv = tmp.acceso;
            contenido += "\nC" + tmp.posicion + "->N" + piv.coordenadaX + "_" + piv.coordenadaY + "[constraint=false,dir=both];";
            while (piv != null) {
                if (piv.abajo != null) {
                    contenido += "\nN" + piv.coordenadaX + "_" + piv.coordenadaY + "->N" + piv.abajo.coordenadaX + "_" + piv.abajo.coordenadaY + "[constraint=false,dir=both];";
                    
                }
                piv = piv.abajo;
            }
            tmp = tmp.siguiente;
        }
        //conectar a pivote con las raices.
        if (columnas.primero != null) {
            contenido += "\npivote->" + "\nC" + columnas.primero.posicion + "[dir=both];";
        }
        if (filas.primero != null) {
            contenido += "\npivote->" + "\nF" + filas.primero.posicion + "[dir=both];";
        }

        //los rank
        contenido += "\n//Los ranks";
        //primero pivote luego los de columnas
        contenido += "\n{ rank = same;pivote;";
        tmp = filas.primero;
        while (tmp != null) {
            contenido += "\nF" +tmp.posicion + ";";
            tmp = tmp.siguiente;
        }
        contenido += "}";

        //ahora las filas
        tmp = columnas.primero;
        while (tmp != null) {
            contenido += "\n{ rank = same;" + "C" +tmp.posicion + ";";
            NodoInterno piv = tmp.acceso;
            
            while (piv != null) {
                contenido += "N" + piv.coordenadaX + "_" + piv.coordenadaY + ";";
                piv = piv.abajo;
            }
            contenido += "}\n";
            tmp = tmp.siguiente;
        }

        contenido += "}";
        try {
            String ruta = "grafica/MatrizDisperza.dot";
            String candena = contenido;
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

            String fileInputPath = "grafica/MatrizDisperza.dot";
            String fileOutputPath = "MatrizDisperza.png";

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

    
}























