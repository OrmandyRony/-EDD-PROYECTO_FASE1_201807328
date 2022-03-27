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
public class ListaNodoB {
    NodoB primero;
    NodoB ultimo;
    int size;

    public ListaNodoB() {
        this.primero = null;
        this.ultimo = null;
        this.size = 0;
    }
    

    public NodoB getPrimero() {
        return this.primero;
    }

    public int getSize() {
        return this.size;
    }

    public NodoB getUltimo() {
        return this.ultimo;
    }

    public void setPrimero(NodoB primero) {
        this.primero = primero;
    }
    
    public boolean insertar(NodoB nuevo) {
        if (this.primero == null) {
            this.primero = nuevo;
            this.ultimo = nuevo;
            size++;
            return true;
        } else {
            if (this.primero == this.ultimo) { 
                // Solo hay un nodo 
                if (nuevo.dpi < this.primero.dpi) {
                    nuevo.siguiente = this.primero;
                    this.primero.anterior = nuevo;
                    // Cambia los punteros a las paginas
                    this.primero.izquierda = nuevo.derecha; 
                    this.primero = nuevo;
                    size++;
                    return true;
                } else if (nuevo.dpi > this.ultimo.dpi) {
                    this.ultimo.siguiente = nuevo;
                    nuevo.anterior = this.ultimo;
                    // Cambia los punteros a las paginas 
                    this.ultimo.derecha = nuevo.izquierda; 
                    this.ultimo = nuevo;
                    size++;
                    return true;
                } else {
                    System.out.println("Ya hay un nodo registrado");
                    return false;
                }
            } else { 
                // hay mas de un nodo
                if (nuevo.dpi < this.primero.dpi) {
                    nuevo.siguiente = this.primero;
                    this.primero.anterior = nuevo;
                    // Cambia los punteros a las paginas
                    this.primero.izquierda = nuevo.derecha; 
                    this.primero = nuevo;
                    size++;
                    return true;
                } else if (nuevo.dpi > this.ultimo.dpi) {
                    this.ultimo.siguiente = nuevo;
                    nuevo.anterior = this.ultimo;
                    // Cambia los punteros a las paginas 
                    this.ultimo.derecha = nuevo.izquierda; 
                    this.ultimo = nuevo;
                    size++;
                    return true;
                } else {
                    NodoB pivote = this.primero;
                    while (pivote != null) {
                        if (nuevo.dpi < pivote.dpi) {
                            nuevo.siguiente = pivote;
                            nuevo.anterior = pivote.anterior;
                            // Cambia los punteros a las paginas
                            pivote.izquierda = nuevo.derecha;
                            pivote.anterior.derecha = nuevo.izquierda;
                            
                            pivote.anterior.siguiente = nuevo;
                            pivote.anterior = nuevo;
                            size++;
                            return true;
                        } else if (nuevo.dpi == pivote.dpi) {
                            System.out.println("El valor ya existe");
                            return false;
                        } else {
                            pivote = pivote.siguiente;
                        }
                    }
                }
            }
        }
        return false;
    }
}
