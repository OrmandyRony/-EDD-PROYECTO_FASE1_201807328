/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawingpaperf2;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author orman
 */
public class Menu {
    ABBcapas abb = new ABBcapas();
    
    
    Scanner input = new Scanner(System.in);
    
    public void menuPrincipal() throws IOException, ParseException {
        int opcionPrincipal;
        String ruta = "";
        
        do {
            System.out.println("------------Menu :D --------\n"
                + "1. Carga matriz\n"
                + "2. Graficar capa\n"
                + "3. Acerca de\n"
                + "4. Salir");
              opcionPrincipal = input.nextInt();
              
            if (opcionPrincipal == 1) {
                input.skip("\n");
                System.out.println("Ingrese la ruta del archivo");
                ruta = input.nextLine();
                cargaMasiva(ruta);
            } else if (opcionPrincipal == 2) {
                input.skip("\n");
                System.out.println("Ingrese el numero de capa");
                int capa = input.nextInt();
                MatrizDispersaPixeles matrizTmp = abb.searchPreOrden(capa);
                if (matrizTmp != null) {
                    matrizTmp.crearGrafo2();
                } else {
                    System.out.println("La matriz es nula");
                }
            } else if (opcionPrincipal == 3) {
                System.out.println("Graficando capas.");
                abb.graficoABB("capas");
            }
        } while (opcionPrincipal != 4);
        
        
    }
    
    
    
    public void cargaMasiva(String ruta) throws IOException, ParseException {
            File doc = new File(ruta);
            
            BufferedReader obj = new BufferedReader(new FileReader(doc));
            
            String strng;
            ruta = "";
           
            while ((strng = obj.readLine()) != null) {
                ruta += strng;
            }
            
            JSONParser parser = new JSONParser();
            Object obj1 = parser.parse(ruta);
            JSONArray json = (JSONArray) obj1;
            int numeroCapa;
            
           
            for (int i = 0; i < json.size(); i++) {
               
                JSONObject object = (JSONObject) json.get(i);
                String capa =  object.get("id_capa").toString();
                numeroCapa = Integer.parseInt(capa);
                JSONArray tmp = (JSONArray) object.get("pixeles");
                System.out.println(tmp);
                MatrizDispersaPixeles matriz = new MatrizDispersaPixeles();
                for (int j = 0; j < tmp.size(); j++) {
                    
                    JSONObject object2 = (JSONObject) tmp.get(j);
                    int fila = Integer.parseInt((object2.get("fila").toString()));
                    int columna = Integer.parseInt((object2.get("columna").toString()));
                    String hexagecimal = object2.get("color").toString();
                    

                    matriz.insert(columna, fila, hexagecimal);

                }
                
                abb.add(numeroCapa, matriz);
                matriz = null;
            }
        
        
        
        /*
        Gson gson = new Gson();
        try {
            System.out.println("Haber");
            Reader lector = Files.newBufferedReader(Paths.get(ruta));
            System.out.println("despues");
            System.out.println(lector);
            //Reader lector = Files.newBufferedReader(Paths.get(ruta));
            //System.out.println(lector);
            Map<?, ?> sim_diccionario = gson.fromJson(lector, Map.class);
            System.out.println(sim_diccionario);
            System.out.println("Por acá");
            //System.out.println(sim_diccionario.getClass().getSimpleName());
            for (Map.Entry<?, ?> entry : sim_diccionario.entrySet()) {
                //System.out.println(entry.getKey() + ":" + entry.getValue());
                System.out.println("Entro");
                Map tmp = (Map) entry.getValue();
                System.out.println(tmp);
                
                /*
                String id = ((String) tmp.get("id_capa")).trim();
                String nombre = ((String) tmp.get("fila")).trim();
                System.out.println(id);
                System.out.println(nombre);
*/
                //int imagenColor = Integer.parseInt(((String) tmp.get("img_color")).trim());
                //int imagenBlancoNegro = Integer.parseInt(((String) tmp.get("img_bw")).trim());
                //Primero creo un cliente

                //listaVentanillas.colaRecepcion.enqueue(id, nombre, imagenColor, imagenBlancoNegro);
            /*    
            }
            
            System.out.println("Datos ingresados.");
            return true;
        } catch (Exception e) {
            System.out.println("Error al ingresar el archivo");
            return false;
        }
        */
    }
    
}
