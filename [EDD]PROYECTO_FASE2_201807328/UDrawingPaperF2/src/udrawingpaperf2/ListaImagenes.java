package udrawingpaperf2;

/**
 *
 * @author orman
 */
public class ListaImagenes {
    NodoImagenes head = null;
    
    public class NodoImagenes {
        NodoImagenes next = null;
        int id;

        public NodoImagenes(int id) {
            this.id = id;
        }
        
    }
    
    public void insert(int id) {
        NodoImagenes nuevasImagenes = new NodoImagenes(id);
        
        nuevasImagenes.next = head;
        head = nuevasImagenes;
    }
    
}
