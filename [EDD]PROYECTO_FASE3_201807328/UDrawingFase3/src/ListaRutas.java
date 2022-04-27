/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USUARIO
 */
public class ListaRutas {
    NodoRuta head = null;
    
    public class NodoRuta {
        Ruta ruta;
        NodoRuta next = null;

        public NodoRuta(Ruta ruta) {
            this.ruta = ruta;
        }
        
    }
    
    public void insert(int inicioR, int finalR, int peso) {
        Ruta nuevaRuta = new Ruta(inicioR, finalR, peso);
        NodoRuta nuevoNodo = new NodoRuta(nuevaRuta);
        
        if (head == null) {
           head = nuevoNodo;
        } else {
            nuevoNodo.next = head;
            head = nuevoNodo;
        }
    }
}
