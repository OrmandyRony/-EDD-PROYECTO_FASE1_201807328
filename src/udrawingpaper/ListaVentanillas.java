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
public class ListaVentanillas {
    public Nodo head;
    public ListaImpresoras listaImpresoras;
    public Impresora impresoraColor = new Impresora(2);
    public Impresora impresoraBlancoNegro = new Impresora(1);
    public ColaRecepcion colaRecepcion = new ColaRecepcion();
    public ListaClientesEspera listaEspera = new ListaClientesEspera();
    
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
    
    public void simularIngreso(Cliente cliente) {
        Nodo aux = head;
        while (aux.ventanilla.ocupada()) {
            aux = aux.next;
        }
        aux.ventanilla.insertarCliente(cliente);
        System.out.println("El cliente " + cliente.nombre + " ingresa a"
                + " la ventanilla " + aux.ventanilla.numero);
    }
    
    public void realizarPasos(){
        Nodo aux = head;
        
        while (aux != null) {
            System.out.println("");
        }
    }
    
    public void simulacionImprenta(){
        // Consejo: intentar ir recorriendo cada ventanilla despues de cada paso
         int paso = 0;
         Cliente cliente;
        while (colaRecepcion.vacia()) {
            paso++;
            
            System.out.println("------------- PASO " + paso + "---------------");
            // Lleno de clientes a todas mis ventanillas
            cliente = colaRecepcion.top();
            System.out.println(cliente);
            
            // Ingreso ventanilla
            Nodo aux = head;
            while (aux.ventanilla.ocupada()) {
                aux = aux.next;
            }
            
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
            cliente = colaRecepcion.top();
            aux.ventanilla.insertarCliente(cliente);
            colaRecepcion.dequeue();
            
            System.out.println("El cliente " + cliente.nombre + " ingresa a"
                    + " la ventanilla " + aux.ventanilla.numero);
            
            System.out.println("La ventanilla " + aux.ventanilla.numero + " envia las"
                    + " imagenes del cliente a sus respectivas cola de impresión");
            
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
            
           
            
            
            
            //System.out.println("El cliente ingresa a ventanilla"+ i+1);
            
            
            
            
            /*
            for (int i = 0; i < listaVentanillas.size; i++) {
               
                System.out.println("------------- PASO " + paso + "---------------");
                // Lleno de clientes a todas mis ventanillas
                Cliente cliente = colaRecepcion.top();
                System.out.println(cliente);
                listaVentanillas.simularIngreso(cliente); // Ingreso ventanilla
                
                
                //System.out.println("El cliente ingresa a ventanilla"+ i+1);
                
                colaRecepcion.dequeue();
                paso++;
            }*/
            
            
        }
        System.out.println("Cola vacia");
    }
}
