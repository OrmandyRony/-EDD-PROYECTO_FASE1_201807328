/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawingpaper;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author orman
 */
public class Menu {
    Scanner input = new Scanner(System.in);
    ListaVentanillas listaVentanillas = new ListaVentanillas();
    
    public void menuPrincipal() throws IOException {    
        int opcionPrincipal;
        int opcionMenuSecundario = 0;
        boolean bandera1 = false;
        boolean bandera2 = false;
        String ruta = "";
   
        int ventanillas;
        
        
        do {
            System.out.println("------------Menu--------\n"
                + "1. Cantidad de ventanillas\n"
                + "2. Carga masiva de clientes\n"
                + "3. Acerca de\n"
                + "4. Salir");
                opcionPrincipal = input.nextInt();
                
            if (opcionPrincipal == 1) {
                System.out.println("-------------------------");
                System.out.println("Ingrese la cantidad de ventanillas: ");
                ventanillas = input.nextInt();
                crearVentanillas(ventanillas);
                
                bandera2 = true;
            }
            
            if (opcionPrincipal == 2) {
                System.out.println("-------------------------");
                System.out.println("Ingrese la ruta del archivo: ");
                
                input.skip("\n");
                ruta = input.nextLine();
                
                bandera1 = cargaMasiva(ruta);
            }
            
            if (opcionPrincipal == 3) {
                acercaDe();
            }
            
            while (bandera2 && bandera1 && (opcionMenuSecundario != 4)) {
                System.out.println("--------------------- Sub menu ------------");
                System.out.println("1. Ejecutar paso\n"
                                    + "2. Reportes\n"
                                    + "3. Estado en memoria de las estrucuturas\n"
                                    + "4. Salir del submenu\n"
                                    + "Ingrese una opcion: ");
                
                if (opcionMenuSecundario == 1) {
                    listaVentanillas.ejecutarPaso();
                    
                } else if (opcionMenuSecundario == 2) {
                    int opcionReportes = 0;
                    
                    do {
                        System.out.println("------------------- Reportes -----------");
                        System.out.println("1. Top 5 de clientes de la lista de clientes atendidos\n "
                                + "con mayor cantidad de imagenes a color\n"
                                + "2. Top 5 de clientes con menor cantidad de imagenes en blanco y negro\n"
                                + "3. Buscar cliente atendido\n"
                                + "4. Información del cliente que más pasos estuvo en el sistema\n"
                                + "5. Salir\n"
                                + "---------------------------------------------\n"
                                + "Ingrese una opcion");
                        System.out.print("$");

                        opcionReportes = input.nextInt();
                        
                        if (opcionReportes == 1) {
                            listaVentanillas.listaClientesAtendidos.topCincoColor();
                        } else if (opcionReportes == 2) {
                            listaVentanillas.listaClientesAtendidos.topCincoBlancoNegro();
                        } else if (opcionReportes == 3) {
                            System.out.println("Ingrese el nombre del cliente:");
                            System.out.print("&");
                            String nombre = input.nextLine();
                            listaVentanillas.imprimirCliente(nombre);
                        } else {
                            System.out.println("Opcion invalida");
                        }
                        
                    } while (opcionReportes != 5);
                    
                } else if (opcionMenuSecundario == 3) {
                    System.out.println("1. Graficos de vizualizacion");
                    System.out.println("2. Datos a generar");
                }
                
               
                opcionMenuSecundario = input.nextInt();
            }
                
        } while (opcionPrincipal != 4);
        
    }
    
    public boolean cargaMasiva(String ruta) throws IOException {
        
        
        Gson gson = new Gson();
        try {
        
            Reader lector = Files.newBufferedReader(Paths.get(ruta));
            
            //Reader lector = Files.newBufferedReader(Paths.get(ruta));
            //System.out.println(lector);
            Map<?, ?> sim_diccionario = gson.fromJson(lector, Map.class);

            //System.out.println(sim_diccionario.getClass().getSimpleName());
            for (Map.Entry<?, ?> entry : sim_diccionario.entrySet()) {
                //System.out.println(entry.getKey() + ":" + entry.getValue());
                Map tmp = (Map) entry.getValue();
                String id = ((String) tmp.get("id_cliente")).trim();
                String nombre = ((String) tmp.get("nombre_cliente")).trim();

                int imagenColor = Integer.parseInt(((String) tmp.get("img_color")).trim());
                int imagenBlancoNegro = Integer.parseInt(((String) tmp.get("img_bw")).trim());
                //Primero creo un cliente

                listaVentanillas.colaRecepcion.enqueue(id, nombre, imagenColor, imagenBlancoNegro);
                
            }
            
            System.out.println("Datos ingresados.");
            return true;
        } catch (Exception e) {
            System.out.println("Error al ingresar el archivo");
            return false;
        }
    }
    
    public void crearVentanillas(int numeroVentanillas) {
        for (int i = 0; i < numeroVentanillas; i++) {
            listaVentanillas.crear();
        }
        System.out.println("Ventanilas creadas");
    }
    
    public void acercaDe() {
        System.out.println("------------ Estudiante -------------------");
        System.out.println("Rony Ormandy Ortíz Alvarez");
        System.out.println("Carnet: 201807328");
    }
    
}
