/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawingpaperf2;

/**
 *
 * @author orman
 */
class Pagina {
    ListaNodoB claves;
    boolean raiz;
    int clavesMax;
    int clavesMin;
    int size;
    

    public Pagina() {
        claves = new ListaNodoB();
        raiz = false;
        clavesMax = 4;
        clavesMin = 2;
        size = 0;
    }
    
    
    public Object insertarPagina(NodoB nuevoNodo) {
        
        if (this.claves.insertar(nuevoNodo)) {
            this.size = claves.getSize();
            if (this.size < 5) {
                return this;
            } else if (this.size == 5) {
                return dividirPagina(this);
            }
        }
        
        return null;
    }
    
    public NodoB dividirPagina(Pagina pagina){
        NodoB tmp = pagina.claves.getPrimero();
        
        for (int i = 0; i < clavesMin; i++) { // esto sive para llegar a la mitad
            tmp = tmp.siguiente;
        }
        // pasar valores de la pagina a nodos independientes
        NodoB primero = pagina.claves.getPrimero();
        NodoB segundo = pagina.claves.getPrimero().siguiente; 
        NodoB tercero = tmp.siguiente;
        NodoB cuarto = pagina.claves.ultimo;
        
        primero.siguiente = null;
        primero.anterior = null;
        
        segundo.siguiente = null;
        segundo.anterior = null;
        
        tercero.siguiente = null;
        tercero.anterior = null;

        cuarto.siguiente = null;
        cuarto.anterior = null;
        
        tmp.siguiente = null;
        tmp.anterior = null;
        
        // Cración de neuvas paginas
        Pagina paginaIzquierda = new Pagina();
        paginaIzquierda.insertarPagina(primero);
        paginaIzquierda.insertarPagina(segundo);
        
        Pagina paginaDerecha = new Pagina();
        paginaIzquierda.insertarPagina(tercero);
        paginaIzquierda.insertarPagina(cuarto);
        
        tmp.izquierda = paginaIzquierda;
        tmp.derecha = paginaDerecha;
                
        return tmp;
    }
    
    public boolean esHoja(Pagina pagina) {
        System.out.println(pagina);
        if (pagina == null) {
            return false;
        }
        
        System.out.println(pagina.claves);
        if (pagina.claves == null) {
            return false;
        }
        
        System.out.println("Get primero " + pagina.claves.getPrimero());
        if (pagina.claves.getPrimero() == null) {
            return true;
        }
        
        if (pagina.claves.getPrimero().izquierda == null) {
            return true;
        }
        return false;
    }
}
