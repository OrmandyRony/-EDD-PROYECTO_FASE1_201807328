/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USUARIO
 */
public class NodoInterno {
    int columna;
    int fila;
    
    public double clave = Double.POSITIVE_INFINITY;
    double costo = -1;
    double valor = Double.POSITIVE_INFINITY;
    

    public NodoInterno(int columna, int fila) {
        this.columna = columna;
        this.fila = fila;
    }   

}
