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
        
    public void ejecutarPaso() {
        System.out.println("Copia");
        imprimirPaso();
        // Sacar cliente de lista de espera
        sacarClienteListaespera(false);
        // Imprimir imagenes
        imprimirImagenes(false);
        
        // Ingreso de imagenes a la ventanilla 
        ingresarImagenesVentanilla(false);
        
        // Ingreso de cliente a la ventanilla 1 paso
        ingresarVentanilla(false);
        
        // Ingreso a la cola de recepcion clientes aleatorios
        ingresarClientesAleatorios();
 
        
    }

    public void imprimirImagenes(boolean imprimoPaso) {
        boolean bandera = true;
        // cambiar busqueda por id
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
                            //imprimirImagenes(false);
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
                            //imprimirImagenes(false);
                            bandera6 = false;

                        } else if (aux.ventanilla.cliente.imagenColor == 0 && aux.ventanilla.cliente.imagenBlancoNegro == 0) {

                            if (imprimoPaso) {
                                imprimirPaso();
                            }

                            //imprimirImagenes(false);
                            System.out.println("El cliente " + aux.ventanilla.cliente.nombre
                                    + " es atendido e ingresa a la lista de espera y la ventanilla\n "
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

                            //ingresarVentanilla(false);
                            
                            bandera6 = false;
                        }

                    }
                }
                aux = aux.next;
            }
        }
        return ing;

    }
    
    public void sacarClienteListaespera(boolean imprimoPaso) {
        //Se saca al cliente de la lista de espera
       
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
            //ingresarImagenesVentanilla(false);
            //imprimirImagenes(false);
        }

    }    
    
    public void imprimirPaso() {
        pasos++;
        System.out.println("------------- PASO " + pasos + "---------------");
    }

    
    public void imprimirCliente(String nombre) {
        Cliente cliente = listaClientesAtendidos.buscarCliente(nombre);
        
        if (cliente != null) {
            System.out.println("--------------------- Informacion del cliente ------------");
            System.out.println("Nombre: " + cliente.nombre);
            System.out.println("Cantidad de imagenes a color impresas: "
                    + cliente.cantidadImagenesColor);
            System.out.println("Cantidad de imagenes a blanco y negro: "
                    + cliente.cantidadImagenesBlancoNegro);
            System.out.println("Cantidad total de imagenes impresas: "
                    + cliente.totalImagenes);
        } else {
            System.out.println("El cliente no existe");
        }   
    }
    
    public void generarImgEstructuras() {
        colaRecepcion.graficarDot();
        impresoraColor.colaImpresiones.graficarDot("ImpresoraColor");
        impresoraBlancoNegro.colaImpresiones.graficarDot("ImpresoraBlancoNegro");
        listaEspera.graficarDot();
        listaClientesAtendidos.graficarDot();
    }
    
        public void ingresarClientesAleatorios() {
        String nombres[] = new String[10];
        nombres[0] = "Carlos";
        nombres[1] = "César";
        nombres[2] = "Giancarlo";
        nombres[3] = "Josue";
        nombres[4] = "Ricardo";
        nombres[5] = "Daniel";
        nombres[6] = "David";
        nombres[7] = "Jancarlo";
        nombres[8] = "Herzon";
        nombres[9] = "Nery";

        String apellidos[] = new String[10];
        apellidos[0] = "Barrera";
        apellidos[1] = "Caledrón";
        apellidos[2] = "Costantini";
        apellidos[3] = "Cun";
        apellidos[4] = "Darcón";
        apellidos[5] = "Gonzales";
        apellidos[6] = "López";
        apellidos[7] = "Lorenzana";
        apellidos[8] = "Matinez";
        apellidos[9] = "Mixtún";

        int numeroNombre = (int) (Math.random() * 10);
        int numeroApellido = (int) (Math.random() * 10);
        String nombre = nombres[numeroNombre] + " " + apellidos[numeroApellido];
        String id = Integer.toString((int)(Math.random()*9850));
        int imagenColor = (int) (Math.random() * 5);
        int imagenBlancoNegro = (int) (Math.random() * 3);

        int n = (int) (Math.random()*4);
        for (int i = 0; i < n; i++) {
            colaRecepcion.enqueue(id, nombre, imagenColor, imagenBlancoNegro);
        }

    }
    
}