/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawingpaperf2;

/**
 *
 * @author orman
 */
public class ListaEncabezado {
    NodoEncabezado primero = null;
    NodoEncabezado ultimo = null;
    String tipo;

    public ListaEncabezado(String tipo) {
        this.tipo = tipo;
    }
    
    public void insert(NodoEncabezado nuevoEncabezado) {
        // Insercción en orden
        if (this.primero == null) {
            this.primero = nuevoEncabezado;
            this.ultimo = nuevoEncabezado;
        } else {
            if (nuevoEncabezado.posicion < this.primero.posicion) {
                nuevoEncabezado.siguiente = this.primero;
                this.primero.anterior = nuevoEncabezado;
                this.primero = nuevoEncabezado;
            } else if (nuevoEncabezado.posicion > this.ultimo.posicion) {
                this.ultimo.siguiente = nuevoEncabezado;
                nuevoEncabezado.anterior = this.ultimo;
                this.ultimo = nuevoEncabezado;
            } else {
                NodoEncabezado tmp = this.primero;
                while (tmp != null) {
                    if (nuevoEncabezado.posicion < tmp.posicion) {
                        nuevoEncabezado.siguiente = tmp;
                        nuevoEncabezado.anterior = tmp.anterior;
                        tmp.anterior.siguiente = nuevoEncabezado;
                        tmp.anterior = nuevoEncabezado;
                        break;
                    } else if (nuevoEncabezado.posicion > tmp.posicion) {
                        tmp = tmp.siguiente;
                    }
                }
            }
        }
    }
    
    public NodoEncabezado getEncabezado(int posicion) {
        NodoEncabezado tmp = this.primero;
        while (tmp != null) {
            if (posicion == tmp.posicion) {
                return tmp;
            }
            tmp = tmp.siguiente;
        }
        return null;
    }
}
