/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawingpaperf2;

/**
 *
 * @author orman
 */
public class UDrawingPaperF2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MatrizDispersaImagenes matriz = new MatrizDispersaImagenes(0);
        System.out.println("Ingresando datos");
    
        matriz.insert(1, 1, "gold2");
        matriz.insert(1, 2, "gold2");
        matriz.insert(1, 3, "gold2");
        matriz.insert(1, 4, "gold2");
        matriz.insert(2, 1, "gold2");
        matriz.insert(2, 2, "gold2");
        matriz.insert(2, 3, "gold2");
        matriz.insert(2, 4, "gold2");
        matriz.insert(3, 1,"gold2");
        matriz.insert(3, 2, "gold2");
        matriz.insert(3, 3,"gold2");
        matriz.insert(3, 4, "gold2");
        matriz.insert(4, 1, "gold2");
        matriz.insert(4, 2, "gold2");
        matriz.insert(4, 3, "gold2");
        matriz.insert(4, 4, "gold2");;
      
        matriz.graficar("Prueba");
        System.out.println("Datos ingresados");
    }
    
}
