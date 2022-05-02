
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USUARIO
 */
public class TablaHash {
    Mensajero claves[];
    int numeroClavesUsadas = 0;
    int size = 37;

    public TablaHash() {
        this.claves = new Mensajero[this.size];
    }
    
    public void insert(long documentoPersonalIdentificacion, String nombre, 
            String apellido, String tipoLicencia, String genero, String telefono, 
            String dirección) {
        
        this.numeroClavesUsadas++;
        
        Mensajero nuevoMensajero = new Mensajero(documentoPersonalIdentificacion, nombre, 
            apellido, tipoLicencia, genero, telefono, dirección);
        
        int valorHash = funcionHash(documentoPersonalIdentificacion);
        
        if (claves[valorHash] == null) {
            claves[valorHash] =  nuevoMensajero;
        } else { 
            // Ocurrio una colision
            boolean encontro = false;
            int i = 1;
            while (!encontro) {
                int valorHash2 = funcionHash2(documentoPersonalIdentificacion, i);
                int dobleHash = (valorHash2); // formula con doble dispersion (valorHash + valorHash2) % this.size
                if (dobleHash < this.size) {
                    if (this.claves[dobleHash] == null) {
                        claves[dobleHash] =  nuevoMensajero;
                        encontro = true;
                    } 
                } else {
                    System.out.println("Error el hash2 es mayor");
                    break;
                }
                
                i++;
            }   
        }
        
        if (porcentajeOcupacion() >= 75) {  
                this.rehashing();
                
        }
        
    }
    
    /**
     * 
     * @param dPI
     * @return 
     * 
     * La funcion Hash(llv) = llv mod M
     * donde: 
     * M es igual a 37 como valor inicial
     * llv es la llave
     * 
     */
    public int funcionHash(long dPI) {
        return (int) (dPI % this.size);
    }
    
    
    public int funcionHash2(long dPI, int i) {
        return (int) (dPI % 7 + 1 * i);
    }
    
    /**
     * 
     * @return 
     * El porcentaje maximo de ocupacion es del 75%
     */
    
    public double porcentajeOcupacion() {
        double porcentaje = (((double) this.numeroClavesUsadas /(double) this.size ) * 100);
        return porcentaje;
    }
    
    
    public void rehashing() {
        Mensajero aux[] = new Mensajero[this.size];
        aux = this.claves;
        this.size = busquedaNumeroPrimo(); 
        this.claves = new Mensajero[this.size];
        this.numeroClavesUsadas = 0;
        for (int i = 0; i < aux.length; i++) {
            if (aux[i] != null) {
                this.insert(aux[i].documentoPersonalIdentificacion, aux[i].nombre, aux[i].apellido, 
                        aux[i].tipoLicencia, aux[i].genero, aux[i].telefono, aux[i].dirección);
            }
        }
        
    }
    
    public int busquedaNumeroPrimo() {
        int nuevoSize = this.size;
        boolean esPrimo = false;
        
        while (esPrimo == false) {
            nuevoSize++;
            int cont = 0;
            
            for (int i = nuevoSize; i > 0; i--) {
                if (nuevoSize % i == 0) {
                    cont++;
                }
            }
            if (cont == 2) {
                esPrimo = true;
            }
        }
        
        return nuevoSize;
    }
    
    public void Graficar() {
        String tablaHash = "digraph TablaHash {\n" +
"	nodesep=.05;\n" +
"	rankdir=LR;\n" +
"	node [shape=record,width=.1,height=.1];\n";
        
        String registros = "";
        String conexiones = "";
        String nodos = "\tindices [label = \"";
        
        for (int i = 0; i < this.size; i++) {
            nodos += "<indice" + i + "> "+ i +" | ";
            
            if (this.claves[i] == null) {
                
            } else {
                registros +=  "\tregistro" + i + "[label = \"{<n> " + this.claves[i].documentoPersonalIdentificacion 
                        + "| " + this.claves[i].nombre + " " +this.claves[i].apellido + " | "
                        + " | " + this.claves[i].genero + " | " + this.claves[i].dirección + "|" + 
                        this.claves[i].telefono + " | <p> }\"];\n";
                
                conexiones += "\tindices:indice" + i + "->" + "registro" + i + ":n;\n";
            }
        }
        
        nodos += "\t\",height=2.5];\n"
                + "\tnode [width = 1.5];\n";
        
        tablaHash += nodos + registros + conexiones + "\n}";
        
        Generador generador = new Generador();
        generador.imagen("Admin/TablaHash.txt", tablaHash, "Admin/TablaHash");
     

    }
    

    
}
