
package Arboles;

public class ListaCabecera {
    
    public NodoCabecera primero;
    public NodoCabecera ultimo;
    public int contadorTerminal = 1;
    
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
            NodoCabecera nuevo = new NodoCabecera(lexema, this.contadorTerminal);
            if (primero != null){
                this.ultimo.siguiente = nuevo;
                this.ultimo = nuevo;
            }
            else{
                this.primero = this.ultimo = nuevo;
            }
            this.contadorTerminal++;
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
    
    public void insertarFila(String lexema, int noEstado){
        NodoCabecera aux = this.buscar(lexema);
        if (aux!=null){
            return;
        }
        else{
            NodoCabecera nuevo = new NodoCabecera(lexema, "S"+String.valueOf(noEstado), (noEstado+1));
            if (primero != null){
                this.ultimo.abajo = nuevo;
                this.ultimo = nuevo;
            }
            else{
                this.primero = this.ultimo = nuevo;
            }
        }
    }
    
    public NodoCabecera buscarFila(String nodo){
        NodoCabecera aux = this.primero;
        
        while(aux!=null){
            if(aux.lexema.equals(nodo)){
                return aux;
            }
            
            aux = aux.abajo;
        }
        return null;
    }
    
    public void imprimir(){
        NodoCabecera aux = this.primero;
        
        while(aux != null){
            System.out.println(aux.numero+" "+aux.lexema);
            
            aux = aux.siguiente;
        }
    }
}
