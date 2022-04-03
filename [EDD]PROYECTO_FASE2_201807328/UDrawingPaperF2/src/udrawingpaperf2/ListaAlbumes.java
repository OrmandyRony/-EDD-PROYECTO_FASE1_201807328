/*
 * Lista doblemente enlazada
 */
package udrawingpaperf2;

/**
 *
 * @author orman
 */
public class ListaAlbumes {
    public NodoAlbum head;
    public NodoAlbum last;
    int size = 0;
    
    public class NodoAlbum {
        ListaImagenes listaImagenes;
        String nombre;
        NodoAlbum next = null;
        NodoAlbum prev = null;

        public NodoAlbum(ListaImagenes listaImagenes, String nombre) {
            this.listaImagenes = listaImagenes;
            this.nombre = nombre;
        }
        
    }
    
    public void insert(ListaImagenes listaImagenes, String nombre) {
        size++;
        NodoAlbum nuevoAlbum = new NodoAlbum(listaImagenes, nombre);
        
        if (head == null) {
            head = nuevoAlbum;
            last = nuevoAlbum;
        } else {
            nuevoAlbum.prev = last;
            last.next = nuevoAlbum;
            last = nuevoAlbum;
        
        }
    }
    
    
}
