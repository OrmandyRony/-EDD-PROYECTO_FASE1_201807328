/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListaVentanillas;

/**
 *
 * @author orman
 */
public class Impresora {
    public String tipo; //Color o BlancoNegro
    public int paso;
    public ColaImpresiones colaImpresiones;

    public Impresora(String tipo, int paso) {
        this.tipo = tipo;
        this.paso = paso;
   
    }
    
    
}
