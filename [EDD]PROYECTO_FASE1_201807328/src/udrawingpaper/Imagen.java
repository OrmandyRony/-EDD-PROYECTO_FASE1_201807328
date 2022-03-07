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
public class Imagen {
    String tipoImpresion; 
    String propietario;
    int pasos;

    public Imagen(String tipoImpresion, int pasos, String propietario) {
        this.tipoImpresion = tipoImpresion;
        this.pasos = pasos;
        this.propietario = propietario;
    }
    
    public void velocidad() {
        this.pasos--;
    }

    public int getPasos() {
        return pasos;
    }
    
    
}
