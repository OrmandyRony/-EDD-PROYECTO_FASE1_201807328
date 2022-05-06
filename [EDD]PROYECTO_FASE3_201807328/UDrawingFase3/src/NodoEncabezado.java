/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author orman
 */
public class NodoEncabezado {
    
    Object dato = null;
    NodoEncabezado siguiente = null;
    NodoEncabezado anterior = null;
    NodoEncabezado derecho = null;
    NodoEncabezado izquierdo = null;
    
    public int posicion = -1;

    public NodoEncabezado(Object data) {
    this.dato = data;
    }

    
}
