/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListaVentanillas;

import ColaRecepcion.Cliente;

/**
 *
 * @author orman
 */
public class Ventanilla {
    int numero;
    PilaImagenes pilaImangenes = new PilaImagenes();
    Cliente cliente = new Cliente();

    public Ventanilla(int numero) {
        this.numero = numero;
    }
    
    public void insertarCliente(Cliente cliente){
        this.cliente = cliente;
    }
    
   
    
}
