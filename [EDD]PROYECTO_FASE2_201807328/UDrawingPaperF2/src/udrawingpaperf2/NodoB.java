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
    
    
    NodoB(long dpi, String nombre, String password){
        this.dpi = dpi;
        this.anterior = null;
        this.siguiente = null;
        this.izquierda = null;
        this.derecha = null;
        this.cliente = new Cliente(nombre, password);
    }
    
    long getDpi(){
        return this.dpi;
    }

    
    void setDpi(long dpi){
        this.dpi = dpi;
    }
}
