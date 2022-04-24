/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USUARIO
 */
public class TablaHash {
    Mensajero claves[];
    int numeroClavesUsadas;
    int size = 37;

    public TablaHash() {
        this.claves = new Mensajero[this.size];
    }
    
    public void insert(long documentoPersonalIdentificacion, String nombre, 
            String apellido, String tipoLicencia, String genero, String telefono, 
            String dirección) {
        
        Mensajero nuevoMensajero = new Mensajero(documentoPersonalIdentificacion, nombre, 
            apellido, tipoLicencia, genero, telefono, dirección);
        
        int valorHash = funcionHash(documentoPersonalIdentificacion);
        
        if (claves[valorHash] == null) {
            claves[valorHash] =  nuevoMensajero;
        }
        
    }
    
    public int funcionHash(long dPI) {
        return (int) (dPI % this.size);
    }
    
}
