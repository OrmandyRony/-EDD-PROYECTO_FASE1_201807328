package udrawingpaperf2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author orman
 */
public class ArbolB {
    Pagina raiz;
    int orden;
    int altura;
    int maximo;
    int minimo;
    
    ArbolB(){
        this.raiz = null;
        this.orden = 5;
        this.maximo = 4;
        this.minimo = 2;
        this.altura = 0;
    }
    
    int getAltura(){
        return this.altura;
    }
    
    void insertarNodo(long dpi, String nombre, String password){ // recorrer el arbol para ver en que pagina tiene que insertar
        NodoB nuevo = new NodoB(dpi, nombre, password);
        if(this.raiz == null){
            this.raiz = new Pagina();
            this.raiz.raiz = true;
            this.raiz = (Pagina)this.raiz.insertarPagina(nuevo);
           
        }else{
            if(this.altura == 0){ // no se a dividido la raiz
                Object respuesta = this.raiz.insertarPagina(nuevo);
                if(respuesta instanceof Pagina){ 
                    this.raiz = (Pagina)respuesta; 
                   
                    
                }else if(respuesta instanceof NodoB){ 
                    this.altura++;
                    this.raiz = new Pagina();
                    NodoB nuevo_raiz = (NodoB)respuesta;
                    this.raiz = (Pagina)this.raiz.insertarPagina(nuevo_raiz);
                }
            }else{
                Object respuesta_insertar = insertarRecorrer(nuevo,this.raiz); 
                
                if(respuesta_insertar instanceof NodoB){ 
                    this.altura++;
                    this.raiz = new Pagina();
                    this.raiz.insertarPagina((NodoB) respuesta_insertar);
                }else{
                    
                    this.raiz= (Pagina)respuesta_insertar;
                }
            }
        }
    }
    
    Object insertarRecorrer(NodoB nuevo, Pagina raizActual){
        
        if(raizActual.esHoja(raizActual)){
          
            Object respuesta = raizActual.insertarPagina(nuevo);
            return respuesta;
        }else{ 
            Object respuesta;
            if( nuevo.getDpi() < raizActual.claves.getPrimero().getDpi()){ 
     
                respuesta = insertarRecorrer(nuevo,raizActual.claves.getPrimero().izquierda);
                if(respuesta instanceof NodoB){
                    return raizActual.insertarPagina((NodoB)respuesta);
                }else{
                    raizActual.claves.getPrimero().izquierda = (Pagina)respuesta;
                    return raizActual;
                }
            }else if(nuevo.getDpi() > raizActual.claves.getUltimo().getDpi()){ 
                respuesta = insertarRecorrer(nuevo,raizActual.claves.getUltimo().derecha);
                if(respuesta instanceof NodoB){
                  
                    return raizActual.insertarPagina((NodoB)respuesta);
                }else{
                    
                    raizActual.claves.getUltimo().derecha = (Pagina)respuesta;
                    return raizActual;
                }
            }else{
                NodoB pivote = raizActual.claves.getPrimero();
                
                while(pivote != null){
                    if(nuevo.dpi < pivote.dpi){
                        respuesta = insertarRecorrer(nuevo,pivote.izquierda);
                        if(respuesta instanceof NodoB){
                            return raizActual.insertarPagina((NodoB)respuesta);
                        }else{
                            pivote.izquierda = (Pagina)respuesta;
                            pivote.anterior.derecha = (Pagina) respuesta;
                            return raizActual;
                        }
                    }else if(nuevo.dpi == pivote.dpi){
                        return raizActual;
                    }else{
                        pivote = pivote.siguiente;
                    }
                }
            }
        }
        
       return this;
    }
    
    NodoB buscar(Pagina raizActual, long dpi){
        NodoB tmp = raizActual.claves.primero;
        while (tmp != null) {
            if (tmp.dpi == dpi) {
                return tmp;
            } 
            tmp = tmp.siguiente;
        }
        
        if (raizActual.esHoja(raizActual) == false) {
            if (dpi < raizActual.claves.primero.dpi) {
                return buscar(raizActual.claves.primero.izquierda, dpi);
            } else if (dpi > raizActual.claves.ultimo.dpi) {
                return buscar(raizActual.claves.ultimo.derecha, dpi);
            } else {
                tmp = raizActual.claves.primero;
                while (tmp != null) {
                    if (dpi < tmp.dpi) {
                        return buscar(tmp.izquierda, dpi);
                    } else {
                        tmp = tmp.siguiente;
                    }
                }
            }
        }
        
        return null;
    }
    
    Pagina buscarPagina(Pagina raizActual, long dpi){
        NodoB tmp = raizActual.claves.primero;
        while (tmp != null) {
            if (tmp.dpi == dpi) {
                return raizActual;
            } 
            tmp = tmp.siguiente;
        }
        
        if (raizActual.esHoja(raizActual) == false) {
            if (dpi < raizActual.claves.primero.dpi) {
                return buscarPagina(raizActual.claves.primero.izquierda, dpi);
            } else if (dpi > raizActual.claves.ultimo.dpi) {
                return buscarPagina(raizActual.claves.ultimo.derecha, dpi);
            } else {
                tmp = raizActual.claves.primero;
                while (tmp != null) {
                    if (dpi < tmp.dpi) {
                        return buscarPagina(tmp.izquierda, dpi);
                    } else {
                        tmp = tmp.siguiente;
                    }
                }
            }
        }
        
        return null;
    }
    
    boolean eliminar(long dpi) {
        Pagina pagina = buscarPagina(this.raiz, dpi);
        
        if (pagina != null) {
            
            if (pagina.esHoja(pagina)) { // Si es hoja caso 1 se puede eliminar el nodo
                if (pagina.claves.size > this.minimo) {
                    
                    if (pagina.claves.primero.dpi == dpi ) {
                        pagina.claves.primero = pagina.claves.primero.siguiente;
                        pagina.claves.primero.anterior = null;
                        return true;
                    }
                    
                    if (pagina.claves.ultimo.dpi == dpi) {
                        pagina.claves.ultimo = pagina.claves.ultimo.anterior;
                        pagina.claves.ultimo.siguiente = null;
                    }
                    
                    NodoB tmp = pagina.claves.primero.siguiente;
                    while (tmp != null) {
                        if (tmp.dpi == dpi) {
                            tmp.anterior.siguiente = tmp.siguiente;
                            tmp.siguiente.anterior = tmp.anterior;
                            tmp = null;
                            return true;
                        } 
                        tmp = tmp.siguiente;
                    }
                }
            }
                
            
        }
        return false;
    }
    
    void graficar() {
        String cadena = "digraph arbolB {\n";
        cadena += "rankr = TB; \n";
        cadena += "node[shape = box,fillcolor=\"azure2\" color=\"black\" style=\"filled\"];\n";
        cadena += graficarNodos(raiz);
        cadena += graficarEnlaces(raiz);
        cadena += "}\n";
        
        System.out.println(cadena);
        try {
            String ruta = "grafica/ArbolB.dot";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(cadena);
            bw.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {

            String dotPath = "C:\\Program Files\\Graphviz\\bin\\dot.exe";

            String fileInputPath = "grafica/ArbolB.dot";
            String fileOutputPath =  "grafica/ArbolB.png";

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
    
    String graficarNodos(Pagina raizActual){
        String cadena = "";
        
        if (raizActual.esHoja(raizActual)) {
            cadena += "node[shape=record label= \"<p0>";
            int contador = 0;
            NodoB aux = raizActual.claves.primero;
            
            while (aux != null) {
                contador++;
                cadena += "|{"+aux.dpi+"}|<p"+contador+"> ";
                aux = aux.siguiente;
            }
            
            cadena+="\"]" + raizActual.claves.primero.dpi + ";\n";
            return cadena;
            
        } else {
            cadena += "node[shape=record label= \"<p0>";
            int contador = 0;
            NodoB aux = raizActual.claves.primero;
            
            while (aux != null) {
                contador++;
                cadena += "|{" + aux.dpi + "}|<p" + contador + "> ";
                aux= aux.siguiente;
            }
            
            cadena += "\"]" + raizActual.claves.primero.dpi + ";\n";
            
            aux = raizActual.claves.primero;
            while(aux != null){
                cadena += this.graficarNodos(aux.izquierda);
                aux = aux.siguiente;
            }
            
            cadena += this.graficarNodos(raizActual.claves.ultimo.derecha);
            return cadena;
        }
     
    }
    
    String graficarEnlaces(Pagina raizActual){
        String cadena="";
        if(raizActual.esHoja(raizActual)){
            return "" + raizActual.claves.primero.dpi + ";\n";
        }else{
            
            NodoB aux = raizActual.claves.primero;
            int contador =0;
            long raizActualTxt = raizActual.claves.primero.dpi;
            while(aux != null){
                cadena += "\n" + raizActualTxt + ":p" + contador + "->" + this.graficarEnlaces(aux.izquierda);
                contador++;
                aux = aux.siguiente;
            }
            cadena += "\n" + raizActualTxt+":p"+contador+"->" + this.graficarEnlaces(raizActual.claves.ultimo.derecha);
            return cadena;
        }
    }
}
