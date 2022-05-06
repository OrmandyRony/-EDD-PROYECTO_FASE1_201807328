/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author orman
 */
public class Matriz {

    ListaEncabezados listaVertices = new ListaEncabezados();
    ListaEncabezados listaRecorridos = new ListaEncabezados();

    public long size = 0;

    public void insertarEncabezado(long identificador) {
        if (revision(identificador)) {
            Encabezado tmp = new Encabezado();
            tmp.identificador = identificador;
            tmp.posicion = size;
            NodoEncabezado nuevo = new NodoEncabezado(tmp);
            listaVertices.insertar(nuevo);
            size++;
        }
    }

    public boolean revision(long identificador) {
        NodoEncabezado tmp = listaVertices.heap;
        while (tmp != null) {
            
            Encabezado temporal = (Encabezado) tmp.dato;
            if (temporal.identificador == identificador) {
                return false;
            }
            tmp = tmp.siguiente;
        }
        return true;
    }

    
    public void rellenar() {
        NodoEncabezado tmp = listaVertices.heap;
        while (tmp != null) {
            Encabezado cab = (Encabezado) tmp.dato;
            NodoEncabezado referencia = null;
            for (int i = 0; i < size; i++) {
                NodoInterno temporal = new NodoInterno(cab.posicion, i);
                NodoEncabezado nuevo = new NodoEncabezado(temporal);
                if (i == 0) {
                    tmp.derecho = nuevo;
                    nuevo.izquierdo = tmp;
                    referencia = nuevo;

                } else {
                    referencia.derecho = nuevo;
                    nuevo.izquierdo = referencia;
                    referencia = nuevo;

                }
            }
            tmp = tmp.siguiente;
        }
        asignarPesosIniciales();
        asignarRecorridosIniciales();
    }

    public NodoEncabezado buscarId(long identificador) {
        NodoEncabezado tmp = listaVertices.heap;
        while (tmp != null) {
            Encabezado temporal = (Encabezado) tmp.dato;
            if (temporal.identificador == identificador) {
                return tmp;
            }
            tmp = tmp.siguiente;
        }

        return null;
    }

    public void asignarPesosIniciales() {
        NodoEncabezado tmp = listaVertices.heap;
        long contador = 0;
        while (tmp != null) {
            NodoEncabezado referencia = tmp.derecho;
            for (int i = 0; i < contador; i++) {
                if (referencia != null && referencia.derecho != null) {
                    referencia = referencia.derecho;
                }
            }
            NodoInterno temporal = (NodoInterno) referencia.dato;
            temporal.valor = 0;
            contador++;
            tmp = tmp.siguiente;
        }
    }

    public void asignarRecorridosIniciales() {
        NodoEncabezado tmp = listaVertices.heap;
        while (tmp != null) {
            NodoEncabezado temporal = tmp.derecho;
            Encabezado cab = (Encabezado) tmp.dato;
            long fila = cab.posicion;
            NodoEncabezado tmp2 = listaVertices.heap;
            for (int i = 0; i < size; i++) {
                if (temporal != null) {
                    NodoInterno celd = (NodoInterno) temporal.dato;
                    Encabezado cab2 = (Encabezado) tmp2.dato;
                    if (celd.columna != fila) {
                        celd.clave = cab2.identificador;
                    }
                    temporal = temporal.derecho;
                }

                if (tmp2 != null) {
                    tmp2 = tmp2.siguiente;
                }
            }
            tmp = tmp.siguiente;
        }
    }

    public void asignarPesos(long identificador_A, long identificador_B, double peso) {
        //primero buscar sus posiciones
        NodoEncabezado tmp = listaVertices.heap;
        long posicionA = -1;
        long posicionB = -1;
        while (tmp != null) {
            Encabezado temporal = (Encabezado) tmp.dato;
            if (temporal.identificador == identificador_A) {
                posicionA = temporal.posicion;
            } else if (temporal.identificador == identificador_B) {
                posicionB = temporal.posicion;
            }
            tmp = tmp.siguiente;
            if (posicionA != -1 && posicionB != -1) {
                break;
            }
        }

        if (posicionA != -1 && posicionB != -1) {

            NodoEncabezado fila = listaVertices.heap;
            for (int i = 0; i < posicionA; i++) {
                fila = fila.siguiente;
            }
            NodoEncabezado celd = fila.derecho;
            for (int i = 0; i < posicionB; i++) {
                celd = celd.derecho;
            }
            NodoInterno temporal = (NodoInterno) celd.dato;
            temporal.valor = peso;
            temporal.costoIndi = peso;

            fila = listaVertices.heap;
            for (int i = 0; i < posicionB; i++) {
                fila = fila.siguiente;
            }
            celd = fila.derecho;
            for (int i = 0; i < posicionA; i++) {
                celd = celd.derecho;
            }
            temporal = (NodoInterno) celd.dato;
            temporal.valor = peso;
            temporal.costoIndi = peso;

        }
    }

    public NodoEncabezado buscarPorCordenadas(long fila, long columna) {
        NodoEncabezado tmp = listaVertices.heap;
        for (int i = 0; i < fila; i++) {
            if (tmp != null) {
                tmp = tmp.siguiente;
            }
        }
        tmp = tmp.derecho;
        for (int i = 0; i < columna; i++) {
            if (tmp != null) {
                tmp = tmp.derecho;
            }
        }
        if (tmp != null) {
            NodoInterno cel = (NodoInterno) tmp.dato;
            return tmp;
        } else {
            return null;
        }
    }

    public void algoritmo() {

        for (int i = 0; i < size; i++) { // filas
            for (int j = 0; j < size; j++) { // columnas

                NodoEncabezado primero = buscarPorCordenadas(i, j);
                for (int l = 0; l < size; l++) {
                    System.out.println("\n\n===================intentado operar============================");
                    NodoEncabezado segundo = buscarPorCordenadas(l, i);
                    NodoInterno celA = (NodoInterno) primero.dato;
                    NodoInterno celB = (NodoInterno) segundo.dato;
                    if (celA.fila != celB.fila && celA.columna != celB.columna) {

                        if (primero != null && segundo != null) {

                            NodoEncabezado identificador = listaVertices.heap;
                            for (int k = 0; k < i; k++) {
                                identificador = identificador.siguiente;
                            }
                            Encabezado id = (Encabezado) identificador.dato;
                            operatoria(primero, segundo, id.identificador);
                        }
                    }
                    System.out.println("==========================================================\n\n");
                }

            }
        }
    }

    public void operatoria(NodoEncabezado primero, NodoEncabezado segundo, long identificador) {

        NodoInterno uno = (NodoInterno) primero.dato;
        NodoInterno dos = (NodoInterno) segundo.dato;
        if (uno.valor != Double.POSITIVE_INFINITY && dos.valor != Double.POSITIVE_INFINITY) {
            double resultado = uno.valor + dos.valor;

            NodoEncabezado buscado = buscarPorCordenadas(dos.fila, uno.columna);
            if (buscado != null) {

                NodoInterno celd = (NodoInterno) buscado.dato;
                if (celd.valor > resultado) {
                    
                    celd.valor = resultado;

                    
                    buscado = buscarPorCordenadas(celd.fila, celd.columna);
                    NodoInterno c_cam = (NodoInterno) buscado.dato;
                    c_cam.clave = identificador;
                }
            }
        }
    }

    public String obtenerRuta(long identificadorA, long identificadorB) {

        if (identificadorA != identificadorB) {
            NodoEncabezado tmp = listaRecorridos.heap;
            boolean encontrado = false;
            while (tmp != null) {
                Recorrido temporal = (Recorrido) tmp.dato;
                if (temporal.inicio == identificadorA && temporal.fin == identificadorB) {

                    encontrado = true;
                    break;
                }
                tmp = tmp.siguiente;
            }
            if (encontrado) {
                System.out.println("Este camino ya habia sido analizado y se encontro el nodo...");
                Recorrido recorrido_buscado = (Recorrido) tmp.dato;
                recorrido_buscado.lista_datos.detallesRecorrido();
            } else {
                
                Recorrido nuevo_recorrido = new Recorrido();
                nuevo_recorrido.inicio = identificadorA;
                nuevo_recorrido.fin = identificadorB;
                nuevo_recorrido.lista_datos.inicio = identificadorA;
                nuevo_recorrido.lista_datos.fin = identificadorB;

                NodoEncabezado inicio = buscarId(identificadorA);
                NodoEncabezado fin = buscarId(identificadorB);
                if (inicio != null && fin != null) {
                    
                    NodoEncabezado ir = inicio.derecho;

                    Encabezado destino = (Encabezado) fin.dato;

                    for (int i = 0; i < destino.posicion; i++) {
                        ir = ir.derecho;
                    }

                    NodoInterno cel = (NodoInterno) ir.dato;
                    nuevo_recorrido.pesoTotal = cel.valor;
                    nuevo_recorrido.lista_datos.costoTotal = (long) cel.valor;

                    
                    NodoInterno celd = (NodoInterno) ir.dato;
                    boolean bucle = true;
                    
                    if (celd.clave == destino.identificador) {
                        bucle = false;
                    }

                    Encabezado principio = (Encabezado) inicio.dato;
                    ir = inicio.derecho;
                    int aux_inicio = (int) principio.posicion;
                    int aux_final = (int) destino.posicion;
                    
                    
                    
                    if (aux_inicio < aux_final) {
                        nuevo_recorrido.lista_datos.insertarRecorrido(destino.identificador);
                    } else {
                        nuevo_recorrido.lista_datos.insertarRecorrido(principio.identificador);
                    }
                    
                    while (bucle) {

                        if (aux_inicio < aux_final) {
                            
                            for (int i = 0; i < destino.posicion; i++) {
                                ir = ir.derecho;
                            }

                            celd = (NodoInterno) ir.dato;

                            if (celd.clave == destino.identificador) {
                                nuevo_recorrido.lista_datos.insertarRecorrido((long) principio.identificador);
                                nuevo_recorrido.lista_datos.detallesRecorrido();
                                listaRecorridos.insertar(nuevo_recorrido);
                                return nuevo_recorrido.lista_datos.detallesRecorrido();
                            } else {

                                nuevo_recorrido.lista_datos.insertarRecorrido((long) celd.clave);
                                NodoEncabezado nuevo = buscarId((long) celd.clave);
                                destino = (Encabezado) nuevo.dato;
                                ir = inicio.derecho;
                            }
                        } else {

                            for (int i = 0; i < destino.posicion; i++) {
                                ir = ir.derecho;
                            }

                            celd = (NodoInterno) ir.dato;

                            if (celd.clave == destino.identificador) {
                                nuevo_recorrido.lista_datos.insertarRecorrido2((long) celd.clave);
                                
                                listaRecorridos.insertar(nuevo_recorrido);
                                return nuevo_recorrido.lista_datos.detallesRecorrido();
                            } else {
  
                                nuevo_recorrido.lista_datos.insertarRecorrido2((long) celd.clave);
                                inicio = buscarId((long) celd.clave);
                                ir = inicio.derecho;
                            }
                        }
                    }
                }
            }
        } else {
           return "F";
        }
        return null;

    }

}
