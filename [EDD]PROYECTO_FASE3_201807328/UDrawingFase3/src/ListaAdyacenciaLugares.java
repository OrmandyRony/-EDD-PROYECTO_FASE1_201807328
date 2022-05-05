/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author USUARIO
 * Donde lugar es el municipio con el departamento al que pertenece y las rutas son las calles con las 
 * que conecta con otros municipios
 */
public class ListaAdyacenciaLugares {
    Nodo head = null;
    ListaRutaLugares listaRutaMensajero = new ListaRutaLugares();
    
    public class Nodo {
        Lugar lugar;
        Nodo next = null;

        public Nodo(Lugar lugar) {
            this.lugar = lugar;
        }
        
    }
    
    public void insert(int id, String departamento, String nombre, 
            Boolean existeSucursal) {
            Lugar newLugar = new Lugar(id, departamento, nombre, existeSucursal);
            Nodo newNodo = new Nodo(newLugar);
        
        if (head == null) {
            head = newNodo;
        } else {
            newNodo.next = head;
            head = newNodo;
        }
    }
    
    public Lugar search(int id) {
        Nodo aux = head;
        while (aux != null) {
            if (aux.lugar.id == id) {
                return aux.lugar;
            }
            aux = aux.next;
        }
        return null;
    }
    
    public boolean vacia() {
        return head != null;
    }
    
    public void costoUniforme(int idInicio, int idFinal) {
        // Test 17 al 14
        // Buscar inicio
        Lugar lugarInicio = search(idInicio);
        Lugar lugarFinal = search(idFinal);
        
        transferirDatos(lugarInicio, 0);
        
        while (listaRutaMensajero.head != null) {
            ListaRutaLugares.Nodo nodoActual = listaRutaMensajero.pop();
            
            if (nodoActual.lugarRecorrido.id == lugarInicio.id) {
                
            }
        }

    }
    
    public void transferirDatos(Lugar lugar, int peso) {
        listaRutaMensajero.insert(lugar.id, peso);
        ListaRutas auxListaRutas = lugar.listaRutas;
        
        ListaRutas.NodoRuta nodoRuta = auxListaRutas.head;
        
        while (nodoRuta != null) {
            listaRutaMensajero.insert(nodoRuta.ruta.finalR, nodoRuta.ruta.peso + peso);
        }
    }    
    public void graficar() {
        String listaAdyacencia = "digraph ListaAdyacencia {\n" +
        "	nodesep=.05;\n" +
        "	rankdir=LR;\n" +
        "	node [shape=record,width=.1,height=.1];\n";
        
        String rutas = "";
        String municipios = "\tmunicipios[label = \"";;
        String conexiones = "";
        
        Nodo aux = head;
        
        while (aux != null) {
            municipios += "<municipio" + aux.lugar.id + ">" + aux.lugar.id + "|";
            ListaRutas.NodoRuta auxNodo = aux.lugar.listaRutas.head;
            
            if (auxNodo != null) {
                rutas += "\truta" + auxNodo.ruta.finalR + aux.lugar.id +"[label = \"{<n> " 
                        + auxNodo.ruta.finalR + " | " + auxNodo.ruta.peso  + " | <p> }\"];\n";
                
                conexiones += "\tmunicipios:municipio" +  aux.lugar.id + "->" + "ruta" 
                        + auxNodo.ruta.finalR+ aux.lugar.id + ":n;\n";
                
                while (auxNodo.next != null) {
                    rutas += "\truta" + auxNodo.next.ruta.finalR + aux.lugar.id + "[label = \"{<n> " 
                        + auxNodo.next.ruta.finalR + " | " + auxNodo.next.ruta.peso  + " | <p> }\"];\n";
                    
                    conexiones += "\truta" +  auxNodo.ruta.finalR + aux.lugar.id + ":p->" + "ruta" 
                        + auxNodo.next.ruta.finalR + aux.lugar.id + ":n;\n";
                    auxNodo = auxNodo.next;
                   
                } 
            }
            
           aux = aux.next;     
                
        }
        
        municipios += "\t\",height=2.5];\n"
                + "\tnode [width = 1.5];\n";
        
        listaAdyacencia += municipios + rutas + conexiones + "\n}";
        
        Generador generador = new Generador();
        generador.imagen("Admin/ListaAdyacencia.txt", listaAdyacencia, "Admin/ListaAdyacencia");
        
    }
    
    public void graficarGrafo() {
        String grafoRutas = "digraph grafo {\n" +
            "\trankdir=\"LR\"\n";
        
        Nodo auxMunicipio = head;
        String municipios = "";
        String conexiones = "";
        
        while (auxMunicipio != null) {
            municipios += "\tmunicipio" + auxMunicipio.lugar.id + "[label= \"" + auxMunicipio.lugar.id  + "\"];\n";
            
            ListaRutas.NodoRuta auxNodo = auxMunicipio.lugar.listaRutas.head;
                
            while (auxNodo != null) {

                conexiones += "\tmunicipio" +  auxMunicipio.lugar.id + " -> " + "municipio" 
                    + auxNodo.ruta.finalR + "[label=\"" + auxNodo.ruta.peso + "\"]\n";
                auxNodo = auxNodo.next;

            } 
            auxMunicipio = auxMunicipio.next;
        }
        
        grafoRutas += municipios + conexiones + "}\n";
        Generador generador = new Generador();
        generador.imagen("Admin/GrafoRutas.txt", grafoRutas, "Admin/GrafoRutas");
    }
    
    
}
