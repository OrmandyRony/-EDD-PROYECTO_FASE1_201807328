/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author orman
 */
public class Mensajero {
    long documentoPersonalIdentificacion;
    String nombre;
    String apellido;
    String tipoLicencia;
    String genero;
    String telefono;
    String dirección;

    public Mensajero(long documentoPersonalIdentificacion, String nombre, 
            String apellido, String tipoLicencia, String genero, String telefono, 
            String dirección) {
        this.documentoPersonalIdentificacion = documentoPersonalIdentificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoLicencia = tipoLicencia;
        this.genero = genero;
        this.telefono = telefono;
        this.dirección = dirección;
    }

    
    
    
}
