package udrawingpaperf2;

/**
 *
 * @author orman
 */
class Pagina {
    ListaNodoB claves;
    boolean raiz;
    int claves_max;
    int claves_min;
    int tam;
    
   Pagina(){
       claves = new ListaNodoB();
       raiz = false;
       claves_max = 4; // valores establecidos por el orden del arbol
       claves_min = 2;
       tam =0;
   }
    
    
   Object insertarPagina(NodoB nuevo){
       if(this.claves.insertar(nuevo)){
           this.tam = this.claves.getSize();
           
            if(this.tam < 5){
                return this;
            }else if(this.tam == 5){
                return dividirPag(this);
            } 
       }
       
       return null;
   }
   
   NodoB dividirPag(Pagina pag){
       //System.out.print("Entro");
       NodoB temp = pag.claves.getPrimero();
       for(int i =0; i<2; i++){
           temp = temp.siguiente;
       }
       NodoB primero = pag.claves.getPrimero();
       NodoB segundo = pag.claves.getPrimero().siguiente;
       NodoB tercero = temp.siguiente;
       NodoB cuarto = pag.claves.ultimo;
       
       primero.siguiente=null;
       primero.anterior = null;
       
       segundo.siguiente = null;
       segundo.anterior = null;
       
       tercero.siguiente = null;
       tercero.anterior = null;
       
       cuarto.siguiente = null;
       cuarto.anterior = null;

       temp.anterior = null;
       temp.siguiente = null;
       
       Pagina izquierda = new Pagina();
       izquierda.insertarPagina(primero);
       izquierda.insertarPagina(segundo);
       
       Pagina derecha = new Pagina();
       derecha.insertarPagina(tercero);
       derecha.insertarPagina(cuarto);
       
       temp.izquierda = izquierda;
       temp.derecha = derecha;
       
       return temp;
   }
   
   boolean esHoja(Pagina pag){
       if(pag.claves.getPrimero().izquierda == null){
           return true;
       }
      return false;
   }
}
