/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawingpaper;

/**
 *
 * @author orman
 */
public class Cliente {
    String id;
    String nombre;
    int imagenColor;
    int imagenBlancoNegro;
    int totalImagenes;
    ListaImagenes listaImagenes = new ListaImagenes();
    int pasos = 0;

    public Cliente(String id, String nombre, int imagenColor, int imagenBlancoNegro) {
        this.id = id;
        this.nombre = nombre;
        this.imagenColor = imagenColor;
        this.imagenBlancoNegro = imagenBlancoNegro;
        this.totalImagenes = imagenColor + imagenBlancoNegro;
    }

    public Cliente() {
        
    }
    
    public void paso() {
        pasos++;
    }

    public void setImagenBlancoNegro() {
        this.imagenBlancoNegro--;
    }

    public void setImagenColor() {
        this.imagenColor--;
    }
    
    
    
}
