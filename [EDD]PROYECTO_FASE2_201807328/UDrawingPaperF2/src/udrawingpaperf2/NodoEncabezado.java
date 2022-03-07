/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawingpaperf2;

import udrawingpaperf2.MatrizDispersaImagenes.NodoInterno;

/**
 *
 * @author orman
 */
public class NodoEncabezado {
    int posicion; 
    NodoEncabezado siguiente = null; 
    NodoEncabezado anterior = null;
    NodoInterno acceso = null;

    public NodoEncabezado(int posicion) {
        this.posicion = posicion;
    }
}
