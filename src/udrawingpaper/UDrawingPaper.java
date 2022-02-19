/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawingpaper;

import java.io.IOException;






/**
 *
 * @author ormandyRony
 */
public class UDrawingPaper {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    
    
    public static void main(String[] args) throws IOException {
        
        Menu menu = new Menu();
        menu.menuPrincipal();
        /*
        ListaVentanillas listaVentanillas = new ListaVentanillas();
        listaVentanillas.simulacionImprenta2();
        */
        /*  
        Scanner input = new Scanner(System.in);
        
        Imprenta imprenta = new Imprenta();
    
        
      
        
        int opcion;
        
        do {
            do {
            System.out.println("------------Menu--------\n"
                + "1. Cantidad de ventanillas\n"
                + "2. Carga masiva de clientes\n"
                + "3. Ejecutar paso\n"
                + "4. Reportes\n"
                + "5. Acerca de\n"
                + "6. Salir\n"
                + "Ingrese una opcion: ");
            opcion = input.nextInt();
            
            } while (opcion < 1 || opcion > 6);
            
            if (opcion == 1) {
                
                System.out.println("Ingrese la cantidad de ventanillas:");
                int n = input.nextInt();
                for (int i = 0; i < n; i++) {
                    imprenta.listaVentanillas.crear();
                }
            } else if (opcion == 2) {
                
                System.out.println("\nIngresa la ruta de tu archivo con los clientes.");
                String ruta = "C:\\Users\\orman\\Documents\\NetBeansProjects\\UDrawingPaper\\cliente.json";
                Reader lector = Files.newBufferedReader(Paths.get(ruta));
                //instrucciones del oediv
                Gson gson = new Gson();
                Map<?,?> sim_diccionario = gson.fromJson(lector,Map.class);
                System.out.println(sim_diccionario.getClass().getSimpleName());
                for (Map.Entry<?, ?> entry : sim_diccionario.entrySet()) {
                    System.out.println(entry.getKey() + ":" + entry.getValue());
                    Map tmp = (Map)entry.getValue();
                    String id = ((String)tmp.get("id_cliente")).trim();
                    String nombre = ((String)tmp.get("nombre_cliente")).trim();

                    int imagenColor = Integer.parseInt(((String)tmp.get("img_color")).trim());
                    int imagenBlancoNegro = Integer.parseInt(((String)tmp.get("img_bw")).trim());
                    //Primero creo un cliente
                    
                    imprenta.listaVentanillas.colaRecepcion.enqueue(id, nombre, imagenColor, imagenBlancoNegro);

                }
            } else if (opcion == 3) {
                imprenta.simulacion();
            } else if (opcion == 4) {
                
            } else if (opcion == 5) {
                System.out.println("############### Estudiante ##################");
                System.out.println("Rony Ormandy Ortíz Alvarez");
                System.out.println("Carnet: 201807328");
            }
            
        } while (opcion != 3);
        
        System.out.println("Ejecutar paso");
        
       
        */
      
        
        
        
    }
    
    
}
