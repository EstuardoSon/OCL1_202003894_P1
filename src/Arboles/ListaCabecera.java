
package Arboles;

public class ListaCabecera {
    
    public NodoCabecera primero;
    public NodoCabecera ultimo;
    
    public ListaCabecera(){
        this.primero = null;
        this.ultimo = null;
    }
    
    public void insertar(String lexema){
        NodoCabecera aux = this.buscar(lexema);
        if (aux!=null){
            return;
        }
        else{
            NodoCabecera nuevo = new NodoCabecera(lexema);
            if (primero != null){
                this.ultimo.siguiente = nuevo;
                this.ultimo = nuevo;
            }
            else{
                this.primero = this.ultimo = nuevo;
            }
        }
    }
    
    public NodoCabecera buscar(String nodo){
        NodoCabecera aux = this.primero;
        
        while(aux!=null){
            if(aux.lexema.equals(nodo)){
                return aux;
            }
            
            aux = aux.siguiente;
        }
        return null;
    }
    
    public void imprimir(){
        NodoCabecera aux = this.primero;
        
        while(aux != null){
            System.out.println(aux.lexema);
            
            aux = aux.siguiente;
        }
    }
}
