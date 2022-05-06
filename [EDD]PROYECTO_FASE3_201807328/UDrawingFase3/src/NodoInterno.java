/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author orman
 */
public class NodoInterno {
    
    long columna = 0; 
    long fila = 0;
    double valor = Double.POSITIVE_INFINITY;
    double clave = Double.POSITIVE_INFINITY;
    double costoIndi = -1;

    public NodoInterno(long fila,long columna) {
        this.columna = columna;
        this.fila = fila;
    }

}
