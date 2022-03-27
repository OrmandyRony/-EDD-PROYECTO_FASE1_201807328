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
public class ArbolB {

    Pagina raiz;
    int orden;
    int altura;

    public ArbolB() {
        this.raiz = null;
        this.orden = 5;
        this.altura = 0;
    }

    public void insertarNodo(long dpi, String nombre, String password) {
        NodoB nuevo = new NodoB(dpi, new Cliente(nombre, password));
        if (this.raiz == null) {
            this.raiz = new Pagina();
            this.raiz.raiz = true;
            this.raiz = (Pagina) this.raiz.insertarPagina(nuevo);
            System.out.print("se inserto el valor " + nuevo.dpi + "\n");
        } else {
            if (this.altura == 0) { 
                // no se a dividido la raiz
                Object respuesta_insertar = this.raiz.insertarPagina(nuevo);
                if (respuesta_insertar instanceof Pagina) { 
                    // la raiz no se dividio 
                    this.raiz = (Pagina) respuesta_insertar;
                    System.out.print("se inserto el getValor " + nuevo.dpi + "\n");

                } else if (respuesta_insertar instanceof NodoB) { 
                    // la raiz se dividio
                    this.altura++;
                    this.raiz = new Pagina();
                    NodoB nuevo_raiz = (NodoB) respuesta_insertar;
                    this.raiz = (Pagina) this.raiz.insertarPagina(nuevo_raiz);
                }
            } else {
                // ya hay mas de una pagina, recorrer el arbol y ubicarse en la rama donde deveria de ir el nodo
                Object respuesta_insertar = insertarRecorrer(nuevo, this.raiz); // verificar que responde y si cambia la raiz

                if (respuesta_insertar instanceof NodoB) { 
                    // la raiz se dividio
                    this.altura++;
                    this.raiz = new Pagina();
                    this.raiz.insertarPagina((NodoB) respuesta_insertar);
                } else {
                    this.raiz = (Pagina) respuesta_insertar;
                }
            }
        }
    }

    private Object insertarRecorrer(NodoB nuevo, Pagina raizActual) {
        if (raizActual.esHoja(raizActual)) {
            Object respuesta = raizActual.insertarPagina(nuevo);
            return respuesta;
        } else {
            Object respuesta;
            if (nuevo.getDpi() < raizActual.claves.getPrimero().getDpi()) {
                respuesta = insertarRecorrer(nuevo, raizActual.claves.getPrimero().izquierda);

                if (respuesta instanceof NodoB) {
                    return raizActual.insertarPagina((NodoB) respuesta);
                } else {
                    raizActual.claves.getPrimero().izquierda = (Pagina) respuesta;
                    return raizActual;
                }
            } else if (nuevo.getDpi() > raizActual.claves.getUltimo().getDpi()) {
                respuesta = insertarRecorrer(nuevo, raizActual.claves.getUltimo().derecha);

                if (respuesta instanceof NodoB) {
                    return raizActual.insertarPagina((NodoB) respuesta);
                } else {
                    raizActual.claves.getPrimero().derecha = (Pagina) respuesta;
                    return raizActual;
                }

            } else {
                NodoB tmp = raizActual.claves.getPrimero();

                while (tmp != null) {
                    if (nuevo.dpi < tmp.dpi) {
                        respuesta = insertarRecorrer(nuevo, tmp.izquierda);
                        if (respuesta instanceof NodoB) {
                            return raizActual.insertarPagina((NodoB) respuesta);
                        } else {
                            tmp.izquierda = (Pagina) respuesta;
                            tmp.anterior.derecha = (Pagina) respuesta;
                            return raizActual;
                        }
                    } else if (nuevo.dpi == tmp.dpi) {
                        return raizActual;
                    } else {
                        tmp = tmp.siguiente;
                    }

                }
            }

            return this;
        }

    }

    public int getAltura() {
        return this.altura;
    }

}
