
import java.io.File;



/**
 *
 * @author orman
 */
public class Admin {
    String nombre = "admin";
    String password = "#EDD2022";
    ArbolB arbolBClientes = new ArbolB();
    TablaHash tablaHashMensajeros = new TablaHash();
    ListaAdyacenciaLugares listaAdyacencia = new ListaAdyacenciaLugares();

    public Admin() {
        generarCarpetaAdmin();
    }
    
    
    
    public void generarCarpetaAdmin(){
        File dir = new File("Admin");
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                
            }
        }
    }
    
}
