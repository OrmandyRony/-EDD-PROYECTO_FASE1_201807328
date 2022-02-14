/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ColaRecepcion;

import ListaVentanillas.ListaImagenes;

/**
 *
 * @author orman
 */
public class Cliente {
    String id;
    String nombre;
    int imagenColor;
    int imagenBlancoNegro;
    ListaImagenes listaImagenes = new ListaImagenes();
    int pasos = 0;

    public Cliente(String id, String nombre, int imagenColor, int imagenBlancoNegro) {
        this.id = id;
        this.nombre = nombre;
        this.imagenColor = imagenColor;
        this.imagenBlancoNegro = imagenBlancoNegro;
    }

    public Cliente() {
        
    }
    
    
}
