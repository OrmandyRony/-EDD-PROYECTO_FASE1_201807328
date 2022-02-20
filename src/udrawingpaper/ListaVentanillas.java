/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawingpaper;

import java.util.Scanner;

/**
 *
 * @author orman
 */
public class ListaVentanillas {

    public Nodo head;
    public ListaImpresoras listaImpresoras;
    public Impresora impresoraColor = new Impresora(2);
    public Impresora impresoraBlancoNegro = new Impresora(1);
    public ColaRecepcion colaRecepcion = new ColaRecepcion();
    public ListaClientesEspera listaEspera = new ListaClientesEspera();
    public ListaClientesAtendidos listaClientesAtendidos = new ListaClientesAtendidos();
    boolean bandera5 = false;
    boolean bandera6 = true;
    boolean bandera7 = true;
    boolean ingreso = false;
    boolean ingreso2 = false;
    Scanner input = new Scanner(System.in);
    int pasos = 0;

    public int size = 0;

    public class Nodo {

        public Ventanilla ventanilla;
        public Nodo next = null;

        public Nodo(Ventanilla ventanilla) {
            this.ventanilla = ventanilla;
        }

    }

    public void crear() {
        size++;
        Ventanilla ventanilla = new Ventanilla(size);
        Nodo nuevoNodo = new Nodo(ventanilla);

        if (head == null) {
            head = nuevoNodo;
        } else {
            Nodo aux = head;
            while (aux.next != null) {
                aux = aux.next;
            }
            aux.next = nuevoNodo;
        }
    }

    public void realizarPasos() {
        Nodo aux = head;

        while (aux != null) {
            System.out.println("");
        }
    }

    public void ejecutarPaso() {
        sacarClienteListaespera(true);
        //Ingresa a la ventanilla y sale de la cola de recepción 
        if (bandera5) {
                ingreso = ingresarImagenesVentanilla(true);//revisar
            } 
        
        ingreso2 = ingresarVentanilla(ingreso);
        
        
        
        
        /**
        for (int i = 0; i < size; i++) {
            
        }
        */
        bandera5 = true;
        
        
        System.out.println("Bandera 6 : " + bandera6);
        if (bandera6) {
            imprimirImagenes(true);
        } else {
            bandera6 = true;
        }
        // Ingreso de imagenes a color en la ventanilla
        // Recorrer todas la ventanillas
        // Son 4 pasos
        

        

    }
    
 
    public boolean ingresarVentanilla(boolean imprimoPaso) {
        if (colaRecepcion.vacia()) {
            // validar si se puedo ingresar el cliente ----------------------------
            Nodo aux = head;
            while (aux.ventanilla.ocupada()) {
                aux = aux.next;
                if (aux == null) {
                    break;
                }
            }
            if (aux != null) {
                if (imprimoPaso) {
                    imprimirPaso();
                }

                Cliente cliente = colaRecepcion.top();
                cliente.paso();
                colaRecepcion.dequeue();
                aux.ventanilla.insertarCliente(cliente);
                System.out.println("El cliente " + cliente.nombre + " ingresa a"
                        + " la ventanilla " + aux.ventanilla.numero);
                return true;
            }
            
        }
        return false;

    }

    public boolean ingresarImagenesVentanilla(boolean imprimoPaso) {

        Nodo aux = head;
        boolean banderita = true;
        boolean ing = true;
        if (aux != null) {
            while (aux != null) {

                if (aux != null) {
                    //System.out.println("Estado ventanilla: " + aux.ventanilla.ocupada());
                    if (aux.ventanilla.ocupada()) {
                        ing = false;
                        if (aux.ventanilla.cliente.imagenColor > 0) {

                            if (imprimoPaso && banderita) {
                                imprimirPaso();
                                banderita = false;
                            }

                            //System.out.println("Entra");
                            aux.ventanilla.cliente.paso();
                            System.out.println("La ventanilla " + aux.ventanilla.numero + " recibe"
                                    + " una imagen a color del cliente " + aux.ventanilla.cliente.nombre);
                            aux.ventanilla.insertarImagen("Color", 2, aux.ventanilla.cliente.nombre);
                            aux.ventanilla.cliente.setImagenColor();
                            imprimirImagenes(false);
                            bandera6 = false;

                        } else if (aux.ventanilla.cliente.imagenBlancoNegro > 0) { // revisar
                            if (imprimoPaso) {
                                imprimirPaso();
                            }
                            //System.out.println("Vuleve a entrar");
                            aux.ventanilla.cliente.paso();
                            System.out.println("La ventanilla " + aux.ventanilla.numero + " recibe "
                                    + "una imagen a blanco y negro del cliente " + aux.ventanilla.cliente.nombre);
                            aux.ventanilla.insertarImagen("BlancoNegro", 1, aux.ventanilla.cliente.nombre);
                            aux.ventanilla.cliente.setImagenBlancoNegro();
                            imprimirImagenes(false);
                            bandera6 = false;

                        } else if (aux.ventanilla.cliente.imagenColor == 0 && aux.ventanilla.cliente.imagenBlancoNegro == 0) {

                            if (imprimoPaso) {
                                imprimirPaso();
                            }

                            imprimirImagenes(false);
                            System.out.println("El cliente " + aux.ventanilla.cliente.nombre
                                    + " es atendido e ingresa a la lista de espera y la ventanilla "
                                    + aux.ventanilla.numero + " envia las imagenes a las impresoras");

                            //Envio de pila de imagenes a la impresoras
                            while (aux.ventanilla.pilaImagenes.vacia()) {
                                Imagen auxImagen = aux.ventanilla.pilaImagenes.getTop();
                                //System.out.println(auxImagen.tipoImpresion);
                                if ("Color".equals(auxImagen.tipoImpresion)) {
                                    impresoraColor.colaImpresiones.enqueue(auxImagen);
                                } else {
                                    impresoraBlancoNegro.colaImpresiones.enqueue(auxImagen);
                                }
                                aux.ventanilla.pilaImagenes.pop();
                            }

                            listaEspera.insert(aux.ventanilla.sacarCliente());

                            ingresarVentanilla(false);
                            sacarClienteListaespera(false);
                            bandera6 = false;
                        }

                    }
                }
                aux = aux.next;
            }
        }
        return ing;

    }

    public void imprimirPaso() {
        pasos++;
        System.out.println("------------- PASO " + pasos + "---------------");
    }

    public void imprimirImagenes(boolean imprimoPaso) {
        boolean bandera = true;

        if (impresoraColor.colaImpresiones.vacia() || impresoraBlancoNegro.colaImpresiones.vacia()) {
            if (imprimoPaso) {
                imprimirPaso();
            }
            //Impresora a color
            if (impresoraColor.colaImpresiones.vacia()) {
                if (impresoraColor.colaImpresiones.imprimir()) {

                    Imagen imagenEspera = impresoraColor.colaImpresiones.getFrontImagen();
                    Cliente clienteColor = listaEspera.buscarCliente(imagenEspera.propietario);
                    clienteColor.listaImagenes.insert(imagenEspera);
                    System.out.println("Se completa la impresion de una imagen a color " + clienteColor.nombre);

                    impresoraColor.colaImpresiones.dequeue();
                    if (bandera) {
                        clienteColor.paso();
                        bandera = false;
                    }

                }
            }

            //Impresora a blanco y nego
            if (impresoraBlancoNegro.colaImpresiones.vacia()) {
                if (impresoraBlancoNegro.colaImpresiones.imprimir()) {

                    Imagen imagenEspera = impresoraBlancoNegro.colaImpresiones.getFrontImagen();

                    Cliente clienteBlancoNegro = listaEspera.buscarCliente(imagenEspera.propietario);
                    clienteBlancoNegro.listaImagenes.insert(imagenEspera);
                    System.out.println("Se completa la impresion de una imagen a blanco y negro del cliente " + clienteBlancoNegro.nombre);
                    impresoraBlancoNegro.colaImpresiones.dequeue();
                    if (bandera) {
                        clienteBlancoNegro.paso();
                    }
                    // Sacar cliente de lista de espera
                }
            }

            //ingresarImagenesVentanilla(false);

        }

    }

    public void sacarClienteListaespera(boolean imprimoPaso) {
        //Se saca al cliente de la lista de espera
        //System.out.println("Truena");
        Cliente clienteAtendido = listaEspera.sacarClienteListaEspera();
        if (clienteAtendido != null) {
            if (imprimoPaso) {
                imprimirPaso();
            }

            clienteAtendido.paso();
            System.out.println("El cliente " + clienteAtendido.nombre + " ya"
                    + " posee todas sus imágenes impresas y sale de la empresa\n"
                    + "registrando el tiempo total dentro de ella. El tiempo total es:\n"
                    + clienteAtendido.pasos);
            
            listaClientesAtendidos.insert(clienteAtendido);
            ingresarVentanilla(false);
            ingresarImagenesVentanilla(false);
            //imprimirImagenes(false);
        }

    }
    
    public void imprimirCliente(String nombre) {
        Cliente cliente = listaClientesAtendidos.buscarCliente(nombre);
        
        System.out.println("--------------------- Informacion del cliente ------------");
        System.out.println("Nombre: " + cliente.nombre);
        System.out.println("Cantidad de imagenes a color impresas: " + 
                cliente.cantidadImagenesColor);
        System.out.println("Cantidad de imagenes a blanco y negro: " +
                cliente.cantidadImagenesBlancoNegro);
        System.out.println("Cantidad total de imagenes impresas: " +
                cliente.totalImagenes);
        
    }
}
