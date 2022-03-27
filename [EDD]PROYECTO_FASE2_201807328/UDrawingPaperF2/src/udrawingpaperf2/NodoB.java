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
public class NodoB {
    long dpi;
    Cliente cliente;
    
    NodoB siguiente;
    NodoB anterior;
    Pagina izquierda;
    Pagina derecha;

    public NodoB(long dpi, Cliente cliente) {
        this.dpi = dpi;
        this.cliente = cliente;
        this.anterior = null;
        this.siguiente = null;
        this.izquierda = null;
        this.derecha = null;
        
    }
    

    public long getDpi() {
        return this.dpi;
    }

    public void setDpi(long dpi) {
        this.dpi = dpi;
    }
    
}
