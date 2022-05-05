/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USUARIO
 */
public class Matriz {
    ListaEncabezado listaVertices = new ListaEncabezado();
    ListaEncabezado listaRecorridos = new ListaEncabezado();
    int size = 0;
    
   
    public void insertarVertice(int id) {
          Encabezado tmp = new Encabezado();
          tmp.id = id;
          tmp.posicion = size;
          size++;
          NodoEncabezado nuevo = new NodoEncabezado(tmp);
          listaVertices.insert(nuevo);

    }

        // Ensamblar
    public void rellenar() {
        NodoEncabezado tmp = listaVertices.head;
        
        while (tmp != null) {
            Encabezado encabezado = (Encabezado) tmp.dato;
            NodoEncabezado aux = null;
            
            for (int i = 0; i <size; i++) {
                NodoInterno nodoInternoAux = new NodoInterno(encabezado.posicion, i);
                NodoEncabezado nuevoNodo = new NodoEncabezado(nodoInternoAux);
                
                if (i == 0) {
                    tmp.derecho = nuevoNodo;
                    nuevoNodo.izquierdo = tmp;
                    aux = nuevoNodo;
                } else {
                    aux.derecho = nuevoNodo;
                    nuevoNodo.izquierdo = aux;
                    aux = nuevoNodo;
                }
            }
            tmp = tmp.siguiente;
        }
        
        pesosIniciales();
        recorridosIniciales();
        
    }
   
    public void insertarPesos(int id1, int id2, double peso) {
        NodoEncabezado nodoEncabezadoAux = listaVertices.head;
        int posicion1 = -1;
        int posicion2 = -1;
        
        while (nodoEncabezadoAux != null) {
            Encabezado encabezadoAux = (Encabezado) nodoEncabezadoAux.dato;
            
            if (encabezadoAux.id == id1 ) {
                posicion1 = encabezadoAux.posicion;
            } else if (encabezadoAux.id == id2) {
                posicion2 = encabezadoAux.posicion;
            }
            
            nodoEncabezadoAux = nodoEncabezadoAux.siguiente;
            
            if (posicion1 != -1 && posicion2 != -1) {
                break;
            }
            
        }
        
        if (posicion1 != -1 && posicion2 != -1) {
            NodoEncabezado fila = listaVertices.head;
            
            for (int i = 0; i < posicion1; i++) {
                fila = fila.siguiente;
            }
            
            NodoEncabezado celd = fila.derecho;
            for (int i = 0; i < posicion2; i++) {
                celd = celd.derecho;
            }
            
            
            NodoInterno nodoInternoAux = (NodoInterno) celd.dato;
            nodoInternoAux.valor = peso;
            
            fila = listaVertices.head;
            
            for (int i = 0; i < posicion2; i++) {
                fila = fila.siguiente;
            }
            celd = fila.derecho;
            for (int i = 0; i < posicion1; i++) {
                celd = celd.derecho;
            }
            
            nodoInternoAux = (NodoInterno) celd.dato;
            nodoInternoAux.valor = peso;
            nodoInternoAux.costo = peso;
        }
        
        
        
    }
    
    //asignar pesos iniciales
    public void pesosIniciales() {
        NodoEncabezado tmp = listaVertices.head;
        int contador = 0;
        
        while (tmp != null) {
            NodoEncabezado aux = tmp.derecho;
            
            for (int i = 0; i < contador; i++) {
                if (aux != null && aux.derecho != null) {
                    aux = aux.derecho;
                }
            }
            NodoInterno nodoInternoAux = (NodoInterno) aux.dato;
            nodoInternoAux.valor = 0;
            tmp = tmp.siguiente;
            contador++;
        }
        
        
    }
    
    
    
    public void recorridosIniciales() {
        NodoEncabezado tmp = listaVertices.head;
        
        while (tmp != null) {
            NodoEncabezado aux = tmp.derecho;
            Encabezado encabezado = (Encabezado) tmp.dato;
            int fila = encabezado.posicion;
            NodoEncabezado aux2 = listaVertices.head;
            
            for (int i = 0; i < size; i++) {
                if (aux != null) {
                    NodoInterno nodoInterno = (NodoInterno) aux.dato;
                    Encabezado encabezado2 = (Encabezado) aux2.dato;
                    if (nodoInterno.columna != fila) {
                        nodoInterno.clave = encabezado2.id;
                    }
                    aux = aux.derecho;
                    
                }
                
                if (aux2 != null) {
                    aux2 = aux2.siguiente;
                }
            }
            tmp = tmp.siguiente;
            
        }
    }
    
    public void imprimirMatriz1() {
        System.out.println("-------------- Primer Matriz -------------------");
        NodoEncabezado nodoEncabezadoAux = listaVertices.head;
        
        while (nodoEncabezadoAux != null) {
            Encabezado encabezado = (Encabezado) nodoEncabezadoAux.dato;
            System.out.println("\t" + encabezado.id);
            nodoEncabezadoAux = nodoEncabezadoAux.siguiente;
        }
        
        System.out.println("");
        
        nodoEncabezadoAux = listaVertices.head;
        
        while (nodoEncabezadoAux != null) {
            NodoEncabezado nodoAux = nodoEncabezadoAux.derecho;
            Encabezado encabezado = (Encabezado) nodoEncabezadoAux.dato;
            System.out.println(encabezado.id);
            
            while (nodoAux != null) {
                NodoInterno nodoInterno = (NodoInterno) nodoAux.dato;
                if (nodoInterno.valor != Double.POSITIVE_INFINITY) {
                    System.out.println("\t" + nodoInterno.valor);
                } else {
                    System.out.println("\ti");
                }
                nodoAux = nodoAux.derecho;
            }
            System.out.println("");
            nodoEncabezadoAux = nodoEncabezadoAux.siguiente;
        }
        System.out.println("\n");
    }
    
    public void imprimirMatriz2() {
        System.out.println("---------------------- Matriz 2 -----------------");
        NodoEncabezado nodoEncabezadoAux = listaVertices.head;
        
        while (nodoEncabezadoAux != null) {
            Encabezado encabezado = (Encabezado) nodoEncabezadoAux.dato;
            System.out.println("\t" + encabezado.id);
            nodoEncabezadoAux = nodoEncabezadoAux.siguiente;
        }
        
        System.out.println("");
        
        nodoEncabezadoAux = listaVertices.head;
        
        while (nodoEncabezadoAux != null) {
            NodoEncabezado nodoAux = nodoEncabezadoAux.derecho;
            Encabezado encabezado = (Encabezado) nodoEncabezadoAux.dato;
            System.out.println(encabezado.id);
            
            while (nodoAux != null) {
                NodoInterno nodoInterno = (NodoInterno) nodoAux.dato;
                if (nodoInterno.valor != Double.POSITIVE_INFINITY) {
                    System.out.println("\t" + nodoInterno.clave);
                } else {
                    System.out.println("\ti");
                }
                nodoAux = nodoAux.derecho;
            }
            System.out.println("");
            nodoEncabezadoAux = nodoEncabezadoAux.siguiente;
        }
        System.out.println("\n");
        
    }
    
    
    public void imprimirMatriz3() {
        System.out.println("------------------------- Matriz 3 -------------------");
        
        NodoEncabezado nodoEncabezadoAux = listaVertices.head;
        
        while (nodoEncabezadoAux != null) {
            Encabezado encabezado = (Encabezado) nodoEncabezadoAux.dato;
            System.out.println("\t" + encabezado.id);
            nodoEncabezadoAux = nodoEncabezadoAux.siguiente;
        }
        
        System.out.println("");
        
        nodoEncabezadoAux = listaVertices.head;
        
        while (nodoEncabezadoAux != null) {
            NodoEncabezado nodoAux = nodoEncabezadoAux.derecho;
            Encabezado encabezado = (Encabezado) nodoEncabezadoAux.dato;
            System.out.println(encabezado.id);
            
            while (nodoAux != null) {
                NodoInterno nodoInterno = (NodoInterno) nodoAux.dato;
                if (nodoInterno.valor != Double.POSITIVE_INFINITY) {
                    System.out.println("\t" + nodoInterno.costo);
                } else {
                    System.out.println("\ti");
                }
                nodoAux = nodoAux.derecho;
            }
            System.out.println("");
            nodoEncabezadoAux = nodoEncabezadoAux.siguiente;
        }
        System.out.println("\n");
    }
    
    
    public void algoritmoFloyd() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                NodoEncabezado nodoEncabezado1 = buscarNodoEncabezado(i, j);
                
                for (int k = 0; k < size; k++) {
                    System.out.println("Intentando operar");
                    NodoEncabezado nodoEncabezado2 = buscarNodoEncabezado(k, j);
                    NodoInterno nodoInternoA = (NodoInterno) nodoEncabezado1.dato;
                    NodoInterno nodoInternoB = (NodoInterno) nodoEncabezado2.dato;
                    
                    if (nodoInternoA.fila != nodoInternoB.fila && nodoInternoA.columna != nodoInternoB.columna) {
                        if (nodoEncabezado1 != null && nodoEncabezado2 != null) {
                            NodoEncabezado identificador = listaVertices.head;
                            
                            for (int l = 0; l < i; l++) {
                                identificador = identificador.siguiente;
                            }
                            
                            Encabezado id = (Encabezado) identificador.dato;
                            operatoria(nodoEncabezado1, nodoEncabezado2, id.id);
                                   
                        }
                    }
                }
            }
        }
    }
    
    public NodoEncabezado buscarNodoEncabezado(int fila, int columna) {
        NodoEncabezado nodoEncabezadoAux = listaVertices.head;
        
        for (int i = 0; i < fila; i++) {
            if (nodoEncabezadoAux != null) {
                nodoEncabezadoAux = nodoEncabezadoAux.siguiente;
            }
        }
        
        nodoEncabezadoAux = nodoEncabezadoAux.derecho;
        for (int i = 0; i < columna; i++) {
            if (nodoEncabezadoAux != null) {
                nodoEncabezadoAux = nodoEncabezadoAux.derecho;
            }
        }
        
        if (nodoEncabezadoAux != null) {
            NodoInterno nodoInterno = (NodoInterno) nodoEncabezadoAux.dato;
            return nodoEncabezadoAux;
        }
        
        return null;
    }
    
    
    public void operatoria(NodoEncabezado nodoEncabezado1, NodoEncabezado nodoEncabezado2, int identificador) {
        NodoInterno uno = (NodoInterno) nodoEncabezado1.dato;
        NodoInterno dos = (NodoInterno) nodoEncabezado2.dato;
        
        if (uno.valor != Double.POSITIVE_INFINITY && dos.valor != Double.POSITIVE_INFINITY) {
            double resultado = uno.valor + dos.valor;
            
            NodoEncabezado buscado = buscarNodoEncabezado(dos.fila, uno.columna);
            
            if (buscado != null) {
                
                NodoInterno nodoInterno = (NodoInterno) buscado.dato;
                
                if (nodoInterno.valor > resultado) {
                    imprimirMatriz2();
                    nodoInterno.valor = resultado;
                    
                    buscado = buscarNodoEncabezado(nodoInterno.fila, nodoInterno.columna);
                    NodoInterno nodoCambio = (NodoInterno) buscado.dato;
                    nodoCambio.clave = identificador;
                }
                
            }
        }
    }
    
    public void calcularRuta(int inicio, int finalV) {
        
        if (inicio != finalV) {
            NodoEncabezado tmp = listaRecorridos.head;
            boolean existe = false;
            
            while (tmp != null) {
                Recorrido temporal = (Recorrido) tmp.dato;
                
                if (temporal.inicio == inicio && temporal.fin == finalV) {
                    existe = true;
                }
                tmp = tmp.siguiente;
            }
            
            if (existe) {
                Recorrido recorridoB = (Recorrido) tmp.dato;
                recorridoB.listaDatos.detallesRecorrido();
            } else {
                Recorrido nuevoRecorrido = new Recorrido();
                nuevoRecorrido.inicio = inicio;
                nuevoRecorrido.fin = finalV;
                nuevoRecorrido.listaDatos.inicio = inicio;
                nuevoRecorrido.listaDatos.fin = finalV;
                
                NodoEncabezado nodoInicio = buscarId(inicio);
                NodoEncabezado nodoFin  = buscarId(finalV);
                
                if (nodoInicio != null && nodoFin != null) {
                    
                    NodoEncabezado aux = nodoInicio.derecho;
                    
                    Encabezado destino = (Encabezado) nodoFin.dato;
                    
                    for (int i = 0; i < destino.posicion; i++) {
                        aux = aux.derecho;
                    }
                    
                    NodoInterno nodoInterno = (NodoInterno) aux.dato;
                    nuevoRecorrido.pesoTotal = nodoInterno.valor;
                    nuevoRecorrido.listaDatos.costoTotal = nodoInterno.valor;
                    
                    NodoInterno nodoInterno1 = (NodoInterno) aux.dato;
                    boolean bucle = true;
                    
                    if (nodoInterno1.clave == destino.id) {
                        bucle = false;
                    }
                    
                    Encabezado principio = (Encabezado) nodoInicio.dato;
                    aux = nodoInicio.derecho;
                    
                    int auxInicio = (int) principio.posicion;
                    int auxFinal = (int) destino.posicion;
                    
                    if (auxInicio < auxFinal) {
                        nuevoRecorrido.listaDatos.insertarRecorrido(destino.id);
                    } else {
                        nuevoRecorrido.listaDatos.insertarRecorrido(principio.id);
                    }
                    
                    while (bucle) {
                        if (auxInicio < auxFinal) {
                            for (int i = 0; i < destino.posicion; i++) {
                                aux = aux.derecho;
                            }
                            
                            nodoInterno1 = (NodoInterno) aux.dato;
                        
                            if (nodoInterno1.clave == destino.id) {
                                nuevoRecorrido.listaDatos.insertarRecorrido(principio.id);
                                nuevoRecorrido.listaDatos.detallesRecorrido();
                                listaRecorridos.insert(nuevoRecorrido);
                                break;

                            } else {
                                nuevoRecorrido.listaDatos.insertarRecorrido((long) nodoInterno1.clave);
                                NodoEncabezado nuevo = buscarId( (long) nodoInterno1.clave);
                                destino = (Encabezado) nuevo.dato;
                                aux = aux.derecho;
                            }
                        } else {
                            
                            for (int i = 0; i < destino.posicion; i++) {
                                aux = aux.derecho;
                            }
                            
                            nodoInterno1 = (NodoInterno) aux.dato;
                            
                            if (nodoInterno1.clave == destino.id) {
                                nuevoRecorrido.listaDatos.insertarRecorrido2((long) nodoInterno1.clave);
                                nuevoRecorrido.listaDatos.detallesRecorrido();
                                this.listaRecorridos.insert(nuevoRecorrido);
                                break;
                            } else {
                                nuevoRecorrido.listaDatos.insertarRecorrido2((long) nodoInterno1.clave);
                                nodoInicio = buscarId((long) nodoInterno1.clave);
                                aux = nodoInicio.derecho;
                            }
                        }
                        
                        
                    } 
                    
                } else {
                    System.out.println("No hay que hacer otro calculo");
                }
                
            }
        }
        
       
        
    }
    
    
     public NodoEncabezado buscarId(long id) {
            NodoEncabezado tmp = listaVertices.head;
            
            while (tmp != null) {
                Encabezado temporal = (Encabezado) tmp.dato;
                
                if (temporal.id == id) {
                    return tmp;
                }
                
                tmp = tmp.siguiente;
            }
            
            return null;
    }
    
    
    
    
    
    
    
    
    
}
