/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USUARIO
 */
public class ListaEncabezado {
    
    NodoEncabezado head = null;
    int size = 0;
    double costoTotal = 0;
    int inicio = -1;
    int fin = -1;
    
    //componer
    public void insert(Object nuevo) {
        NodoEncabezado tmp = head;
        
        if (head == null) {
            head = new NodoEncabezado(nuevo);
            head.posicion = size;
            size++;
        } else {
            
            while (tmp.siguiente != null) {
                tmp = tmp.siguiente;
            }
            
            tmp.siguiente = new NodoEncabezado(nuevo);
            tmp.siguiente.posicion = (int) size;
            tmp.siguiente.anterior = tmp;
            size++;
        }
    }
    
    public void insert(NodoEncabezado nuevo) {
        NodoEncabezado tmp = head;
        
        if (tmp == null ) {
            head = nuevo;
        } else {
            while (tmp.siguiente != null) {
                tmp = tmp.siguiente;
            }
            
            tmp.siguiente = nuevo;
            nuevo.anterior = tmp;
        }
        
    }
    
    public void insertarRecorrido(long identificador) {
        NodoEncabezado tmp = head;
        NodoEncabezado nuevo = new NodoEncabezado(identificador);
        
        if (tmp == null) {
            head = nuevo;
        } else {
            nuevo.siguiente = head;
            head.anterior = nuevo;
            head = nuevo;
        }
        
    }
    
    public void detallesRecorrido() {
        NodoEncabezado uno = head;
        NodoEncabezado dos = uno.siguiente;
        
        while (dos != null) {
            System.out.println(">Pasar del nodo "+uno.dato+" al nodo "+dos.dato+" :::: coste => ");
            uno = dos;
            dos = dos.siguiente;
        }
        
        System.out.println("Costo Total: " + costoTotal);
    }
    
    public void insertarRecorrido2(long id){
        NodoEncabezado tmp = head;
        NodoEncabezado nuevo = new NodoEncabezado(id);
        
        if(tmp == null) {
            head = nuevo;
        } else {
            while(tmp.siguiente != null) {
                tmp = tmp.siguiente;
            }
            tmp.siguiente = nuevo;
            nuevo.anterior = tmp;
        }
    }

    
}
