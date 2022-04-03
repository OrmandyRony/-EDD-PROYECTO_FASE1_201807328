package udrawingpaperf2;

/**
 *
 * @author orman
 */
public class ListaNodoB {
    NodoB primero;
    NodoB ultimo;
    int size;
    
    ListaNodoB(){
        this.primero = null;
        this.ultimo = null;
        this.size = 0;
    }

    NodoB getPrimero(){
        return this.primero;
    }
    int getSize(){
        return this.size;
    }
    
    NodoB getUltimo(){
        return this.ultimo;
    }
    
    void setPrimero(NodoB prim){
        this.primero= prim;
    }
    
    boolean insertar(NodoB nuevo){
        if(this.primero == null){
            this.primero = nuevo;
            this.ultimo = nuevo;
            size ++;
            return true;
        }else{
            if(this.primero == this.ultimo){ 
                if(nuevo.dpi < this.primero.dpi){
                    nuevo.siguiente = this.primero;
                    this.primero.anterior = nuevo;
                    this.primero.izquierda = nuevo.derecha; 
                    this.primero = nuevo;
                    size++; 
                    return true;
                }else if(nuevo.dpi > this.ultimo.dpi){
                    this.ultimo.siguiente = nuevo;
                    nuevo.anterior = this.ultimo;
                    this.ultimo.derecha = nuevo.izquierda; 
                    this.ultimo = nuevo;
                    size++; 
                    return true;
                }else{
                    return false;
                }
            }else{ 
                if(nuevo.dpi < this.primero.dpi){
                    nuevo.siguiente = this.primero;
                    this.primero.anterior = nuevo;
                    this.primero.izquierda = nuevo.derecha; 
                    this.primero = nuevo;
                    size++; 
                    return true;
                }else if(nuevo.dpi > this.ultimo.dpi){
                    this.ultimo.siguiente = nuevo;
                    nuevo.anterior = this.ultimo;
                    this.ultimo.derecha = nuevo.izquierda; 
                    this.ultimo = nuevo;
                    size++; 
                    return true;
                }else{
                    NodoB pivote = this.primero;
                    while(pivote != null){
                        if(nuevo.dpi < pivote.dpi){
                            nuevo.siguiente = pivote;
                            nuevo.anterior = pivote.anterior;
                            
                            pivote.izquierda = nuevo.derecha;
                            pivote.anterior.derecha = nuevo.izquierda;
                            
                            pivote.anterior.siguiente = nuevo;
                            pivote.anterior = nuevo;
                            size++;
                            return true;
                        }else if(nuevo.dpi == pivote.dpi){
                            //System.out.println ("Ya existe este dpi");
                            return false;
                        }else{
                            pivote = pivote.siguiente;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    
    
}
