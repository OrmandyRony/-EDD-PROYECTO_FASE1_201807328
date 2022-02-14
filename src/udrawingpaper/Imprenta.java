/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawingpaper;

import ColaRecepcion.Cliente;
import ColaRecepcion.ColaRecepcion;
import ListaVentanillas.Impresora;
import ListaVentanillas.ListaVentanillas;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author orman
 */
public class Imprenta {
    String nombre;
    ListaVentanillas listaVentanillas = new ListaVentanillas(); 
    ColaRecepcion colaRecepcion = new ColaRecepcion();
    Impresora impresoraColor = new Impresora("Color", 2);
    
    public void simulacion(){
        int paso = 0;
        while (colaRecepcion.vacia()) {
            paso++;
            for (int i = 0; i < listaVentanillas.size; i++) {
                //System.out.println("------------- PASO " + paso + "---------------");
                // Lleno de clientes a todas mis ventanillas
                Cliente cliente = colaRecepcion.top();
                listaVentanillas.simularIngreso(cliente);
                //System.out.println("El cliente ingresa a ventanilla"+ i+1);
            }
            
            
            colaRecepcion.dequeue();
        }
        System.out.println("Cola vacia");
    }
}
