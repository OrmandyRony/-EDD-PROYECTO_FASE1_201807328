/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawingpaper;

import java.io.IOException;
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
    
    
    
    public void realizarPasos(){
        Nodo aux = head;
        
        while (aux != null) {
            System.out.println("");
        }
    }
    
    public void simulacionImprenta2() throws IOException {
        int opcion = 0;
        int paso = 0;
        Cliente cliente;
        
      
                
                if (colaRecepcion.vacia()) {
                    paso++;
                    System.out.println("------------- PASO " + paso + "---------------");
                    cliente = colaRecepcion.top();
                    
                    Nodo aux = head;
                    while (aux.ventanilla.ocupada()) {
                        aux = aux.next;
                    }
                    
                    //llega el cliente a a la ventanilla
                    aux.ventanilla.insertarCliente(cliente);
                    colaRecepcion.dequeue();
                    
                    System.out.println("El cliente " + cliente.nombre + " ingresa a"
                        + " la ventanilla " + aux.ventanilla.numero);
                    
                    // Imprimir imagenes
                    if (impresoraBlancoNegro.colaImpresiones.vacia()) {
                        if (impresoraBlancoNegro.colaImpresiones.imprimir()) {
                            System.out.println("Se completa la impresion de una imagena a blanco y negro");
                            Imagen imagenEspera = impresoraBlancoNegro.colaImpresiones.getFrontImagen();
                            System.out.println(imagenEspera.propietario);
                            Cliente clienteLista = listaEspera.buscarCliente(imagenEspera.propietario);
                            clienteLista.listaImagenes.insert(imagenEspera);
                            impresoraBlancoNegro.colaImpresiones.dequeue();
                        }
                    }

                    if (impresoraColor.colaImpresiones.vacia()) {
                        if (impresoraColor.colaImpresiones.imprimir()) {
                            System.out.println("Se completa la impresion de una imagena a color");
                            Imagen imagenEspera = impresoraColor.colaImpresiones.getFrontImagen();
                            Cliente clienteLista = listaEspera.buscarCliente(imagenEspera.propietario);
                            clienteLista.listaImagenes.insert(imagenEspera);
                            impresoraColor.colaImpresiones.dequeue();
                        }
                    }
                    
                    System.out.println("Ingresando imagenes a la pila");
                    
                    // Ingresan imagenes a color en la pila
                    for (int i = 0; i < cliente.imagenColor; i++) {
                        paso++;
                        System.out.println("------------- PASO " + paso + "---------------");
                        aux.ventanilla.insertarImagen("Color", 2, cliente.nombre);
                        System.out.println("La ventanilla " + aux.ventanilla.numero + " a "
                                + "recibido una imagen a color");

                        if (impresoraBlancoNegro.colaImpresiones.vacia()) {
                            if (impresoraBlancoNegro.colaImpresiones.imprimir()) {
                                System.out.println("Se completa la impresion de una imagena a blanco y negro");
                                Imagen imagenEspera = impresoraBlancoNegro.colaImpresiones.getFrontImagen();
                                System.out.println(imagenEspera.propietario);
                                Cliente clienteLista = listaEspera.buscarCliente(imagenEspera.propietario);
                                clienteLista.listaImagenes.insert(imagenEspera);
                                impresoraBlancoNegro.colaImpresiones.dequeue();
                            }
                        }

                        if (impresoraColor.colaImpresiones.vacia()) {
                            if (impresoraColor.colaImpresiones.imprimir()) {
                                System.out.println("Se completa la impresion de una imagena a color");
                                Imagen imagenEspera = impresoraColor.colaImpresiones.getFrontImagen();
                                Cliente clienteLista = listaEspera.buscarCliente(imagenEspera.propietario);
                                clienteLista.listaImagenes.insert(imagenEspera);
                                impresoraColor.colaImpresiones.dequeue();
                            }
                        }
                    }
                    
                    // Ingresar imagenes a blanco y negro en la pila
                    for (int i = 0; i < cliente.imagenBlancoNegro; i++) {
                        paso++;
                        System.out.println("------------- PASO " + paso + "---------------");
                        aux.ventanilla.insertarImagen("BlancoNegro", 1, cliente.nombre);
                        System.out.println("La ventanilla " + aux.ventanilla.numero + " a "
                                + "recibido una imagen a blanco y negro");

                        if (impresoraBlancoNegro.colaImpresiones.vacia()) {
                            System.out.println("Error");
                            if (impresoraBlancoNegro.colaImpresiones.imprimir()) {
                                System.out.println("Se completa la impresion de una imagena a blanco y negro");
                                Imagen imagenEspera = impresoraBlancoNegro.colaImpresiones.getFrontImagen();
                                System.out.println(imagenEspera.propietario);
                                Cliente clienteLista = listaEspera.buscarCliente(imagenEspera.propietario);
                                clienteLista.listaImagenes.insert(imagenEspera);
                                impresoraBlancoNegro.colaImpresiones.dequeue();
                            }
                        }
                        System.out.println("    ");
                        if (impresoraColor.colaImpresiones.vacia()) {
                            if (impresoraColor.colaImpresiones.imprimir()) {
                                System.out.println("Se completa la impresion de una imagena a color");
                                Imagen imagenEspera = impresoraColor.colaImpresiones.getFrontImagen();
                                Cliente clienteLista = listaEspera.buscarCliente(imagenEspera.propietario);
                                clienteLista.listaImagenes.insert(imagenEspera);
                                impresoraColor.colaImpresiones.dequeue();
                            }
                        }
                    }
                    
                    // Envio de cliente a la lista de espera
                    paso++;
                    System.out.println("------------- PASO " + paso + "---------------");

                    // Dos clientes
              
                    System.out.println("El cliente " + cliente.nombre + " es atendido e ingresa a"
                            + " la lista de espera");

                    if (impresoraBlancoNegro.colaImpresiones.vacia()) {
                        if (impresoraBlancoNegro.colaImpresiones.imprimir()) {
                            System.out.println("Se completa la impresion de una imagena a blanco y negro");
                            Imagen imagenEspera = impresoraBlancoNegro.colaImpresiones.getFrontImagen();
                            System.out.println(imagenEspera.propietario);
                            Cliente clienteLista = listaEspera.buscarCliente(imagenEspera.propietario);
                            clienteLista.listaImagenes.insert(imagenEspera);
                            impresoraBlancoNegro.colaImpresiones.dequeue();
                        }
                    }

                    if (impresoraColor.colaImpresiones.vacia()) {
                        if (impresoraColor.colaImpresiones.imprimir()) {
                            System.out.println("Se completa la impresion de una imagena a color");
                            Imagen imagenEspera = impresoraColor.colaImpresiones.getFrontImagen();
                            Cliente clienteLista = listaEspera.buscarCliente(imagenEspera.propietario);
                            clienteLista.listaImagenes.insert(imagenEspera);
                            impresoraColor.colaImpresiones.dequeue();
                        }
                    }
                    
                    // Envio de imagenes a la cola de impresion
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
                    
                    listaEspera.insert(aux.ventanilla.getCliente());
                    aux.ventanilla.sacarCliente();
                    
                    if (colaRecepcion.vacia()) {
                        cliente = colaRecepcion.top();
                        aux.ventanilla.insertarCliente(cliente);
                        colaRecepcion.dequeue();
                    }
                    
                    if (aux.ventanilla.ocupada()) {
                        System.out.println("El cliente " + cliente.nombre + " ingresa a"
                            + " la ventanilla " + aux.ventanilla.numero);
                         System.out.println("La ventanilla " + aux.ventanilla.numero + " envia las"
                            + " imagenes del cliente a sus respectivas cola de impresión");
                    }
                   

                   

                    aux.ventanilla.sacarCliente();
                    
                    //-------------------------------------------------------------------------
                    paso++;
                    System.out.println("------------- PASO " + paso + "---------------");
                    // Imprimir imagenes

                    if (impresoraBlancoNegro.colaImpresiones.vacia()) {
                        if (impresoraBlancoNegro.colaImpresiones.imprimir()) {
                            System.out.println("Se completa la impresion de una imagena a blanco y negro");
                            Imagen imagenEspera = impresoraBlancoNegro.colaImpresiones.getFrontImagen();
                            System.out.println(imagenEspera.propietario);
                            Cliente clienteLista = listaEspera.buscarCliente(imagenEspera.propietario);
                            clienteLista.listaImagenes.insert(imagenEspera);
                            impresoraBlancoNegro.colaImpresiones.dequeue();
                        }
                    }

                    if (impresoraColor.colaImpresiones.vacia()) {
                        if (impresoraColor.colaImpresiones.imprimir()) {
                            System.out.println("Se completa la impresion de una imagena a color");
                            Imagen imagenEspera = impresoraColor.colaImpresiones.getFrontImagen();
                            Cliente clienteLista = listaEspera.buscarCliente(imagenEspera.propietario);
                            clienteLista.listaImagenes.insert(imagenEspera);
                            impresoraColor.colaImpresiones.dequeue();
                        }
                    }
                    System.out.println("Ingresando imagenesa la pila");
                    // Ingresar imagenes a color

                    //System.out.println("LISTA DE ESPERA");
                    //listaEspera.mostrar();
                    for (int i = 0; i < cliente.imagenColor; i++) {
                        paso++;
                        System.out.println("------------- PASO " + paso + "---------------");
                        aux.ventanilla.insertarImagen("Color", 2, cliente.nombre);
                        System.out.println("La ventanilla " + aux.ventanilla.numero + " a "
                                + "recibido una imagen a color");
                        if (impresoraBlancoNegro.colaImpresiones.vacia()) {
                            if (impresoraBlancoNegro.colaImpresiones.imprimir()) {
                                System.out.println("Se completa la impresion de una imagena a blanco y negro");
                                Imagen imagenEspera = impresoraBlancoNegro.colaImpresiones.getFrontImagen();
                                System.out.println(imagenEspera.propietario);
                                Cliente clienteLista = listaEspera.buscarCliente(imagenEspera.propietario);
                                clienteLista.listaImagenes.insert(imagenEspera);
                                impresoraBlancoNegro.colaImpresiones.dequeue();
                            }
                        }

                        if (impresoraColor.colaImpresiones.vacia()) {
                            if (impresoraColor.colaImpresiones.imprimir()) {
                                System.out.println("Se completa la impresion de una imagena a color");
                                Imagen imagenEspera = impresoraColor.colaImpresiones.getFrontImagen();
                                Cliente clienteLista = listaEspera.buscarCliente(imagenEspera.propietario);
                                clienteLista.listaImagenes.insert(imagenEspera);
                                impresoraColor.colaImpresiones.dequeue();
                            }
                        }
                    }

                    // Ingresar imagenes a blanco y negro
                    for (int i = 0; i < cliente.imagenBlancoNegro; i++) {
                        paso++;
                        System.out.println("------------- PASO " + paso + "---------------");
                        aux.ventanilla.insertarImagen("BlancoNegro", 1, cliente.nombre);
                        System.out.println("La ventanilla " + aux.ventanilla.numero + " a"
                                + "recibido una imagen a blanco y negro");
                        if (impresoraBlancoNegro.colaImpresiones.vacia()) {
                            if (impresoraBlancoNegro.colaImpresiones.imprimir()) {
                                System.out.println("Se completa la impresion de una imagena a blanco y negro");
                                Imagen imagenEspera = impresoraBlancoNegro.colaImpresiones.getFrontImagen();
                                System.out.println(imagenEspera.propietario);
                                Cliente clienteLista = listaEspera.buscarCliente(imagenEspera.propietario);
                                clienteLista.listaImagenes.insert(imagenEspera);
                                impresoraBlancoNegro.colaImpresiones.dequeue();
                            }
                        }

                        if (impresoraColor.colaImpresiones.vacia()) {
                            if (impresoraColor.colaImpresiones.imprimir()) {
                                System.out.println("Se completa la impresion de una imagena a color");
                                Imagen imagenEspera = impresoraColor.colaImpresiones.getFrontImagen();
                                Cliente clienteLista = listaEspera.buscarCliente(imagenEspera.propietario);
                                clienteLista.listaImagenes.insert(imagenEspera);
                                impresoraColor.colaImpresiones.dequeue();
                            }
                            
                            
                        }
                    }
                    
                    

                    // Envio de cliente a la lista de espera
                    paso++;

                    //-------------------------------------------------------------------------
                    aux.ventanilla.sacarCliente();
            } else {
                    while (impresoraColor.colaImpresiones.vacia() || impresoraBlancoNegro.colaImpresiones.vacia()) {
                        paso++;
                        System.out.println("------------- PASO " + paso + "---------------");
                        if (impresoraColor.colaImpresiones.vacia()) {
                            if (impresoraColor.colaImpresiones.imprimir()) {
                            
                                System.out.println("Se completa la impresion de una imagena a color");
                                Imagen imagenEspera = impresoraColor.colaImpresiones.getFrontImagen();
                                Cliente clienteLista = listaEspera.buscarCliente(imagenEspera.propietario);
                                clienteLista.listaImagenes.insert(imagenEspera);
                                impresoraColor.colaImpresiones.dequeue();
                            }
                        }
                        
                        
                        if (impresoraBlancoNegro.colaImpresiones.vacia()) {
                            if (impresoraBlancoNegro.colaImpresiones.imprimir()) {
                                System.out.println("Se completa la impresion de una imagena a blanco y negro");
                                Imagen imagenEspera = impresoraBlancoNegro.colaImpresiones.getFrontImagen();
                                System.out.println(imagenEspera.propietario);
                                Cliente clienteLista = listaEspera.buscarCliente(imagenEspera.propietario);
                                clienteLista.listaImagenes.insert(imagenEspera);
                                impresoraBlancoNegro.colaImpresiones.dequeue();
                            }
                        }
                        
                    }
                    
                   
                    
                    
                
                System.out.println("La cola esta vacia");
                }
                
        
        
    }
    
    public void ejecutarPaso() {
        //Ingresa a la ventanilla y sale de la cola de recepción 
        ingresarVentanilla(true);
        if (bandera5) {
            ingresarImagenesVentanilla(true);
        } else {
            bandera5 = true;
        }
        
        
        // Ingreso de imagenes a color en la ventanilla
        // Recorrer todas la ventanillas
        System.out.println("Bandera 6 : " + bandera6);
        if (bandera6) {
            imprimirImagenes(true);
        } else {
            bandera6 = true;
        }
            
        
        
        sacarClienteListaespera(true); 
        
        
        
      
        
        
        
        

    }
    
    public void ingresarVentanilla(boolean imprimoPaso) {
        if (colaRecepcion.vacia()) {
            // validar si se puedo ingresar el cliente ----------------------------
            Nodo aux = head;
            while (aux.ventanilla.ocupada()) {
                aux = aux.next;
                if (aux  == null) {
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
            }
            
        }
        
        
    }

    public void ingresarImagenesVentanilla(boolean imprimoPaso) {
        Nodo aux = head;
        if (aux != null) {
            while (!aux.ventanilla.ocupada()) {
                aux = aux.next;
                if (aux == null) {
                    break;
                }
            }
        }
        
        
        if (aux != null) {
            if (aux.ventanilla.cliente.imagenColor > 0) {
                if (imprimoPaso) {
                    imprimirPaso();
                }
                
                aux.ventanilla.cliente.paso();
                System.out.println("La ventanilla " + aux.ventanilla.numero + " recibe"
                        + " una imagen a color del cliente " + aux.ventanilla.cliente.nombre);
                aux.ventanilla.insertarImagen("Color", 2, aux.ventanilla.cliente.nombre);
                aux.ventanilla.cliente.setImagenColor();
                imprimirImagenes(false);
            } else if (aux.ventanilla.cliente.imagenBlancoNegro > 0) {
                imprimirPaso();
                aux.ventanilla.cliente.paso();
                System.out.println("La ventanilla " + aux.ventanilla.numero + " recibe "
                        + "una imagen a blanco y negro del cliente " + aux.ventanilla.cliente.nombre);
                aux.ventanilla.insertarImagen("BlancoNegro", 1, aux.ventanilla.cliente.nombre);
                aux.ventanilla.cliente.setImagenBlancoNegro();
                imprimirImagenes(false);
            } else if (aux.ventanilla.cliente.imagenColor == 0 && aux.ventanilla.cliente.imagenBlancoNegro == 0) {
                imprimirPaso();
                System.out.println("El cliente " + aux.ventanilla.cliente.nombre + 
                        " es atendido e ingresa a la lista de espera y la ventanilla " 
                        + aux.ventanilla.numero + " envia las imagenes a las impresoras" );
      
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
                //imprimirImagenes(false);
                ingresarVentanilla(false);
                bandera6 = false;
            }
        }

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
            
            ingresarImagenesVentanilla(false);
            
            
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
        }
        
    }
    
}

