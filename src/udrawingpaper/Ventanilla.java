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
public class Ventanilla {
    int numero;
    PilaImagenes pilaImagenes = new PilaImagenes();
    Cliente cliente;

    public Ventanilla(int numero) {
        this.numero = numero;
    }
    
    public void insertarCliente(Cliente cliente){
        this.cliente = cliente;
    }
    
   public boolean ocupada(){
       if (cliente != null) {
           return true;
       }
       return false;
   }
   
   public void insertarImagen(String tipo, int pasos, String propietario) {
       Imagen imagen = new Imagen(tipo, pasos, propietario);
       pilaImagenes.push(imagen);
   }

    public Cliente getCliente() {
        return cliente;
    }
   
    public void sacarCliente() {
        cliente = null;
    }
    
}
