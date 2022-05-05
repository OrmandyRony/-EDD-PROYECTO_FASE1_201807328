/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USUARIO
 */
public class NodoEncabezado {
    Object dato;
    NodoEncabezado siguiente = null;
    NodoEncabezado anterior = null;
    NodoEncabezado derecho = null;
    NodoEncabezado izquierdo = null;
    int posicion = -1;

    public NodoEncabezado(Object dato) {
        this.dato = dato;
    }
}
