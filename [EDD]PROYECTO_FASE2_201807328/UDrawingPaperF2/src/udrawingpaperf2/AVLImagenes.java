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
public class AVLImagenes {
    NodoImagen raiz = null;
    
    public class NodoImagen {
        int id;
        ABBcapas capas = new ABBcapas();
        NodoImagen izquierda = null;
        NodoImagen derecha = null;
        int altura;

        public NodoImagen(int id) {
            this.id = id;
        }
        
    }
    
    public void insert(int id) {
        NodoImagen nuevaImagen = new NodoImagen(id);
        if (raiz != null) {
                raiz = insert(raiz, nuevaImagen);
        } else {
            raiz = nuevaImagen;
        }
    }
    
    public NodoImagen insert(NodoImagen raizActual, NodoImagen nuevaImagen) {
        if (raizActual != null) {
            if (raizActual.id > nuevaImagen.id) {
                raizActual.izquierda = insert(raizActual.izquierda, nuevaImagen);

                if (altura(raizActual.derecha) - altura(raizActual.izquierda) == -2) {
             
                    if (nuevaImagen.id < raizActual.izquierda.id) {
                        
                        raizActual = rotacionIzquierda(raizActual);
                    } else {
                        
                        raizActual = rotacionIzquierdaDerecha(raizActual);
                    }
                }
            } else if (raizActual.id < nuevaImagen.id) {
                raizActual.derecha = insert(raizActual.derecha, nuevaImagen);

                if (altura(raizActual.derecha) - altura(raizActual.izquierda) == 2) {
                 
                    if (nuevaImagen.id > raizActual.derecha.id) {
                   
                        raizActual = rotacionDerecha(raizActual);
                    } else {
                        
                        raizActual = rotacionDerechaIzquierda(raizActual);
                    }
                }
            } else {
                System.out.println("No se pudo insertar");
            }

            raizActual.altura = alturaMaxima(altura(raizActual.derecha), altura(raizActual.izquierda)) + 1;
            return raizActual;
        } else {
           raizActual = nuevaImagen;
           return raizActual;
        }
    }
    
    public NodoImagen rotacionIzquierda(NodoImagen nodo) {
        NodoImagen tmp = nodo.izquierda;
        nodo.izquierda = tmp.derecha;
        tmp.derecha = nodo;
        nodo.altura = alturaMaxima(altura(nodo.derecha), altura(nodo.izquierda)) + 1;
        tmp.altura = alturaMaxima(nodo.altura, altura(nodo.izquierda)) + 1;
        return tmp;
    }
    
    public NodoImagen rotacionDerecha(NodoImagen nodo) {
        NodoImagen tmp = nodo.derecha;
        nodo.derecha = tmp.izquierda;
        tmp.izquierda = nodo;
        nodo.altura = alturaMaxima(altura(nodo.izquierda), altura(nodo.derecha)) + 1;
        tmp.altura = alturaMaxima(nodo.altura, altura(nodo.derecha)) + 1;
        return tmp;
    }
    
    public NodoImagen rotacionIzquierdaDerecha(NodoImagen nodo) {
        nodo.izquierda = rotacionDerecha(nodo.izquierda);
        NodoImagen tmp = rotacionIzquierda(nodo);
        return tmp;
    }
    
    public NodoImagen rotacionDerechaIzquierda(NodoImagen nodo) {
        nodo.derecha = rotacionIzquierda(nodo.derecha);
        NodoImagen tmp = rotacionDerecha(nodo);
        return tmp;
    }
    
    public int altura(NodoImagen tmp) {
        if (tmp != null) {
            return tmp.altura;
        } else {
            return -1;
        }
    }
    
    public int alturaMaxima(int h1, int h2) {
        if (h2 >= h1) {
            return h2;
        } else {
            return h1;
        }
    }
}
