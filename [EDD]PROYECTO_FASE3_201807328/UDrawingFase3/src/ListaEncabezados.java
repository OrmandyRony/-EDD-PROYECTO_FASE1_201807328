/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author orman
 */
public class ListaEncabezados {
    long size = 0;
    NodoEncabezado heap = null;
    long costoTotal = 0;
    long inicio = -1;
    long fin = -1;
    
    
    public void insertar(Object nuevo) {
        NodoEncabezado tmp = heap;
        if (tmp == null) {
            heap = new NodoEncabezado(nuevo);
            heap.posicion = (int)size;
            size++;
        } else {
            while (tmp.siguiente != null) {
                tmp = tmp.siguiente;
            }
            tmp.siguiente = new NodoEncabezado(nuevo);
            tmp.siguiente.posicion = (int)size;
            tmp.siguiente.anterior = tmp;
            size++;
        }
    }
    
    public void insertar(NodoEncabezado nuevo){
        NodoEncabezado tmp = heap;
        if(tmp == null){
            heap = nuevo;
        }
        else{
            while(tmp.siguiente != null){
                tmp = tmp.siguiente;
            }
            tmp.siguiente = nuevo;
            nuevo.anterior = tmp;
        }
    }
    
    public String detallesRecorrido(){
        System.out.println("\n=================================DETALLE DE :: RECORRIDO DE "+inicio+" A "+fin+"==========================");
        String cadena = "Inicio: " + inicio +
                "\nFinal: " + fin;
        
        NodoEncabezado uno = heap;
        NodoEncabezado dos = uno.siguiente;
        while(dos != null ){
            System.out.println("" + uno.dato + " al nodo " + dos.dato + " :::: coste => ");
            cadena += "\n" + uno.dato + "->" + dos.dato + "->";
            uno = dos;
            dos = dos.siguiente;
        }
        
        cadena += costoTotal;
        System.out.println("COSTE TOTAL : "+costoTotal);
        return cadena;
    }
    
    public void insertarRecorrido(long identificador){
        NodoEncabezado tmp = heap;
        NodoEncabezado nuevo = new NodoEncabezado(identificador);
        //mejor hacemos una copia de los datos del NodoEncabezado y no usar el apuntador para no perder los datos
        if(tmp == null){
            heap = nuevo;
        }
        else{
            //lo vamos a agregar de manera inversa es decir se van a ir guardando como una pila
            nuevo.siguiente = heap;
            heap.anterior = nuevo;
            heap = nuevo;
        }
    }
    
    public void insertarRecorrido2(long identificador){
        NodoEncabezado tmp = heap;
        NodoEncabezado nuevo = new NodoEncabezado(identificador);
        //mejor hacemos una copia de los datos del NodoEncabezado y no usar el apuntador para no perder los datos
        if(tmp == null){
            heap = nuevo;
        }
        else{
            while(tmp.siguiente != null){
                tmp = tmp.siguiente;
            }
            tmp.siguiente = nuevo;
            nuevo.anterior = tmp;
        }
    }
}
