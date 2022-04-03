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
     * @param numeroCapa es el id en el arbol
     * @param matriz
     * 
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
        } else  if (numeroCapa > tmp.numeroCapa) {
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
     * @return 
     */
    
    public String inOrden(){
        return inOrden(raiz);
    }
    
    public String inOrden(Nodo tmp){
        String capa = "";
        
        if (tmp != null) {
            capa += inOrden(tmp.izquierdo) + ",";
            capa += tmp.numeroCapa + ",";
            capa += inOrden(tmp.derecho) + ",";
            
        }
        return capa;
    }
    
    /**
     * Recorrido Preorden
     * Se realizan las siguientes operaciones recursivas:
     *  visita la raíz
     *  Atravesar el sub-árbol izquierdo
     *  Atraviese el sub-árbol
     * 
     */
    
    public String preOrden() {
        
        return preOrden(raiz);
    }
    
    public String preOrden(Nodo tmp) {
        String capa = "";
        if (tmp != null) {
            capa += "," + tmp.numeroCapa;
            capa += "," + preOrden(tmp.izquierdo);
            capa += "," + preOrden(tmp.derecho);
        }
        return capa;
    }
    
    
    public String postOrden() {
        
        return postOrden(raiz);
    }
    
    public String postOrden(Nodo tmp) {
        String capa = "";
        if (tmp != null) {
            capa += "," + postOrden(tmp.izquierdo);
            capa += "," + postOrden(tmp.derecho);
            capa += "," + tmp.numeroCapa;
        }
        return capa;
    }
    
    
    
    public MatrizDispersaPixeles searchPreOrden(int capa) {
        return  searchPreOrden(capa, raiz);
       
    }
    
    public MatrizDispersaPixeles searchPreOrden(int capa, Nodo tmp) {
        if (tmp != null) {
            if (tmp.numeroCapa == capa) {
                return tmp.capa;
            } else if (tmp.numeroCapa > capa) {
                return searchPreOrden(capa, tmp.izquierdo);
            } else if (tmp.numeroCapa < capa) {
                return searchPreOrden(capa, tmp.derecho);
            }
            
        }
        return null;
    }
    
    
    public void graficarCapas(String capas, String nombreImagen) {
        MatrizDispersaPixeles matrizTmp2 = new MatrizDispersaPixeles();
        MatrizDispersaPixeles matrizTmp;
        // Genera los nodos de las capas seleccionadas
        String capa = "";
        for (int j = 0; j < capas.length(); j++) {
                //System.out.println(imgs);
                
                if ('0' == capas.charAt(j)) {
                    capa += "0";
                } else if ('1' == capas.charAt(j)) {
                    capa += "1";
                } else if ('2' == capas.charAt(j)) {
                    capa += "2";
                } else if ('3' == capas.charAt(j)) {
                    capa += "3";
                } else if ('4' == capas.charAt(j)) {
                    capa += "4";
                } else if ('5' == capas.charAt(j)) {
                    capa += "5";
                } else if ('6' == capas.charAt(j)) {
                    capa += "6";
                } else if ('7' == capas.charAt(j)) {
                    capa += "7";
                } else if ('8' == capas.charAt(j)) {
                    capa += "8";
                } else if ('9' == capas.charAt(j)) {
                    capa += "9";
                }
                
                if (capas.charAt(j) == ',') {
                    //System.out.println("Se inserto busca la capa: " + capa);
                    int cap = Integer.parseInt(capa);
                    matrizTmp = this.searchPreOrden(cap);

                    if (matrizTmp != null) {
                        //System.out.println("Se encontro la capa");
                        NodoEncabezado tmp = matrizTmp.filas.primero;
                        //Obteniendo valores de la matriz seleccionada
                        

                        while (tmp != null) {
                            MatrizDispersaPixeles.NodoInterno piv = tmp.acceso;
                            while (piv != null) {
                                //System.out.println("x: " + piv.coordenadaX + " Y: " + piv.coordenadaY + " Hexa: " + piv.hexagecimal);
                                matrizTmp2.insert(piv.coordenadaX, piv.coordenadaY, piv.hexagecimal);
                                piv = piv.derecha;
                            }
                            tmp = tmp.siguiente;
                        }
                        
                }

                capa = "";
                matrizTmp = null;
            }
                
                if (capas.charAt(j) == ']') {
                    //System.out.println("Se inserto: " + capa);
                    if (!capa.equals("")) {
                        System.out.println("Se inserto busca la capa: " + capa);
                        int cap = Integer.parseInt(capa);
                        matrizTmp = this.searchPreOrden(cap);

                        if (matrizTmp != null) {
                            NodoEncabezado tmp = matrizTmp.filas.primero;
                            //Obteniendo valores de la matriz seleccionada
                            

                            while (tmp != null) {
                                MatrizDispersaPixeles.NodoInterno piv = tmp.acceso;
                                while (piv != null) {
                                     //System.out.println("x: " + piv.coordenadaX + " Y: " + piv.coordenadaY + " Hexa: " + piv.hexagecimal);
                                    matrizTmp2.insert(piv.coordenadaX, piv.coordenadaY, piv.hexagecimal);
                                    piv = piv.derecha;
                                }
                                tmp = tmp.siguiente;
                            }

                        }
                }
                    
                    capa = "";
                    matrizTmp = null;
                    break;
                }   
            }
            
        matrizTmp2.crearGrafo2(nombreImagen);
    }
    
    public String graficarNodos(MatrizDispersaPixeles matrizTmp) {
        String contenido = "";
        NodoEncabezado tmp = matrizTmp.filas.primero;

        //primero las cabeceras de filas.
        contenido += "\n//cabeceras columnas\n";
        while (tmp != null) {
            contenido += "\nF" + tmp.posicion + "[label=\"F" + tmp.posicion+ "" + "\" color=\"white\" style=\"filled\" ]";
            tmp = tmp.siguiente;
        }
        tmp = matrizTmp.columnas.primero;

        //primero las cabeceras de columnas.
        contenido += "\n//cabeceras filas\n";
        while (tmp != null) {
            contenido += "\nC" + tmp.posicion + "[label=\"C" + tmp.posicion + "" + "\" color=\"white\" style=\"filled\" ]";
            tmp = tmp.siguiente;
        }
        //ahora declaramos los valores dentro de la matriz;
        contenido += "\n//contenido matriz\n";
        tmp = matrizTmp.columnas.primero;
        
        while (tmp != null) {
            MatrizDispersaPixeles.NodoInterno piv = tmp.acceso;
            while (piv != null) {
                contenido += "\nN" + piv.coordenadaX + "_" + piv.coordenadaY + "[label=\"N" + piv.coordenadaX + "--" + piv.coordenadaY + "\" color=\"" + piv.hexagecimal + "\" style=\"filled\" ];";
                piv = piv.abajo;
            }
            tmp = tmp.siguiente;
        }
        contenido += "\n//Conexiones entre filas\n";
        
        tmp = matrizTmp.filas.primero;
        while (tmp != null) {
            if (tmp.siguiente != null) {
                contenido += "\nF" + tmp.posicion + "->F" + tmp.siguiente.posicion + "[dir=both];";
            }
            tmp = tmp.siguiente;
        }
        
        contenido += "\n//Conexiones entre columnas\n";
        tmp = matrizTmp.columnas.primero;
        while (tmp != null) {
            if (tmp.siguiente != null) {
                contenido += "\nC" + tmp.posicion + "->C" + tmp.siguiente.posicion + "[dir=both];";
            }
            tmp = tmp.siguiente;
        }
        
        //ahora a conectar las columnas con el contenido de la matriz.
        contenido += "\n//Concexiones fila-nodoInterno\n";
        tmp = matrizTmp.filas.primero;
        while (tmp != null) {
            MatrizDispersaPixeles.NodoInterno piv = tmp.acceso;
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
        tmp = matrizTmp.columnas.primero;
        while (tmp != null) {
            MatrizDispersaPixeles.NodoInterno piv = tmp.acceso;
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
        if (matrizTmp.columnas.primero != null) {
            contenido += "\npivote->" + "\nC" + matrizTmp.columnas.primero.posicion + "[dir=both];";
        }
        if (matrizTmp.filas.primero != null) {
            contenido += "\npivote->" + "\nF" + matrizTmp.filas.primero.posicion + "[dir=both];";
        }

        //los rank
        contenido += "\n//Los ranks";
        //primero pivote luego los de columnas
        contenido += "\n{ rank = same;pivote;";
        tmp = matrizTmp.filas.primero;
        while (tmp != null) {
            contenido += "\nF" +tmp.posicion + ";";
            tmp = tmp.siguiente;
        }
        contenido += "}";

        //ahora las filas
        tmp = matrizTmp.columnas.primero;
        while (tmp != null) {
            contenido += "\n{ rank = same;" + "C" +tmp.posicion + ";";
            MatrizDispersaPixeles.NodoInterno piv = tmp.acceso;
            
            while (piv != null) {
                contenido += "N" + piv.coordenadaX + "_" + piv.coordenadaY + ";";
                piv = piv.abajo;
            }
            contenido += "}\n";
            tmp = tmp.siguiente;
        }
        
        return contenido;
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
