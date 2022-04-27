

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    
    
    NodoB(long dpi, String nombre, String usuario, String correo, String telefono,
            String contrasena, String direccion, String idMunicipio){
        
        this.dpi = dpi;
        this.anterior = null;
        this.siguiente = null;
        this.izquierda = null;
        this.derecha = null;
        this.cliente = new Cliente(nombre, usuario, correo, telefono, contrasena,
            direccion, idMunicipio);
    }
    
 
    
    long getDpi(){
        return this.dpi;
    }

    
    void setDpi(long dpi){
        this.dpi = dpi;
    }
}
