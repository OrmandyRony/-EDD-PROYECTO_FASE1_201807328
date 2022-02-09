/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ColaRecepcion;

/**
 *
 * @author orman
 */
public class Cliente {
    String id;
    String nombre;
    int imagenColor;
    int imagenBlancoNegro;

    public Cliente(String id, String nombre, int imagenColor, int imagenBlancoNegro) {
        this.id = id;
        this.nombre = nombre;
        this.imagenColor = imagenColor;
        this.imagenBlancoNegro = imagenBlancoNegro;
    }
    
    
}
