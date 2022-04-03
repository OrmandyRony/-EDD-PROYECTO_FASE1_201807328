package udrawingpaperf2;

/**
 *
 * @author orman
 */
public class Cliente {
    String nombre;
    String password;
    ListaAlbumes listaAlbumes = new ListaAlbumes();
    AVLImagenes avlImagenes = new AVLImagenes();

    public Cliente(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }
}
