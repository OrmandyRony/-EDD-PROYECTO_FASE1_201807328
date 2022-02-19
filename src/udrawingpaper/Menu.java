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
