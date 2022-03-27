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
    public static void main(String[] args){
        // TODO code application logic here
        ArbolB arbolB = new ArbolB();
        
        arbolB.insertarNodo(1, "Aux EDD", "edd1s2022");
        arbolB.insertarNodo(2, "Aux DD", "edd1s2022");
        arbolB.insertarNodo(3, "AuxDD", "edd1s2022");
        arbolB.insertarNodo(4, "AuxDD", "edd1s2022");
        arbolB.insertarNodo(5, "AuxD", "edd1s2022");
        arbolB.insertarNodo(6, "Aux ED", "edd1s2022");
        arbolB.insertarNodo(7, "Aux EDD", "edd1s2022");
        arbolB.insertarNodo(8, "Aux EDD", "edd1s2022");
        arbolB.insertarNodo(9, "Aux EDD", "edd1s2022");
        System.out.println("Ternuba");
        
        /*
        arbolB.insertarNodo(1234567890123L, "Aux EDD", "edd1s2022");
        arbolB.insertarNodo(1234567890178L, "Aux EDD", "edd1s2022");
        arbolB.insertarNodo(1234567890145L, "Aux EDD", "edd1s2022");
        arbolB.insertarNodo(12345678901138L, "Aux EDD", "edd1s2022");
        arbolB.insertarNodo(12345678901530L, "Aux EDD", "edd1s2022");
        arbolB.insertarNodo(1234567890143L, "Aux EDD", "edd1s2022");
        arbolB.insertarNodo(1234567890149L, "Aux EDD", "edd1s2022");
        arbolB.insertarNodo(1234567890128L, "Aux EDD", "edd1s2022");
        arbolB.insertarNodo(1234567890163L, "Aux EDD", "edd1s2022");
        arbolB.insertarNodo(1234567890127L, "Aux EDD", "edd1s2022");
        arbolB.insertarNodo(1234567890183L, "Aux EDD", "edd1s2022");
        */
        
                          //9223372036854775807
        /*
        Menu menu = new Menu();
        menu.menuPrincipal();
        */
        /*
        
  // Function to split the BST
  // into two Balanced BST
  static void splitBST(node root, int k)
  {
 
    // Print the original BST
    System.out.print("Original BST : ");
    if (root != null) {
      inorderTrav(root);
    }
    else {
      System.out.print("null");
    }
    System.out.println();
 
    // Store the size of BST1
    int numNode = sizeOfTree(root);
 
    // Take auxiliary array for storing
    // The inorder traversal of BST1
    int []inOrder = new int[numNode + 1];
    index = 0;
 
    // Function call for storing
    // inorder traversal of BST1
    storeInorder(root, inOrder);
 
    // Function call for getting
    // splitting index
    int splitIndex
      = getSplittingIndex(inOrder,
                          k);
 
    node root1 = null;
    node root2 = null;
 
    // Creation of first Balanced
    // Binary Search Tree
    if (splitIndex != -1)
      root1 = createBST(inOrder, 0,
                        splitIndex);
 
    // Creation of Second Balanced
    // Binary Search Tree
    if (splitIndex != (index - 1))
      root2 = createBST(inOrder,
                        splitIndex + 1,
                        index - 1);
 
    // Print two Balanced BSTs
    System.out.print("First BST : ");
    if (root1 != null) {
      inorderTrav(root1);
    }
    else {
      System.out.print("null");
    }
    System.out.println();
 
    System.out.print("Second BST : ");
    if (root2 != null) {
      inorderTrav(root2);
    }
    else {
      System.out.print("null");
    }
  }
        
        
        
        */
        
        
        
        
        //String root = "C:\\Users\\orman\\Documents\\NetBeansProjects\\UDrawingPaper\\EntradasFase2\\capas.json";
        
        /*
        MatrizDispersaPixeles matriz = new MatrizDispersaPixeles(0);
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
        else if (opcionPrincipal == 2) {
                input.skip("\n");
                System.out.println("Ingrese el numero de capa");
                int [] capas = new int [2];
                
                for (int i = 0; i < capas.length; i++) {
                    capas[i] = input.nextInt(); 
                }
                
                for (int i = 0; i < 10; i++) {
                    
                }
                MatrizDispersaPixeles matrizTmp = abb.searchPreOrden(capa);
                if (matrizTmp != null) {
                    matrizTmp.crearGrafo2();
                } else {
                    System.out.println("La matriz es nula");
                }
            }
        
        
*/
    }
    
}
