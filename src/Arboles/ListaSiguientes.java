package Arboles;

public class ListaSiguientes {
    
    public NodoSiguientes primero;
    public NodoSiguientes ultimo;
    
    public ListaSiguientes(){
        this.primero = null;
        this.ultimo = null;
    }
    
    public void insertar(String lexema, String nodo, String siguientes){
        NodoSiguientes aux = this.buscar(nodo);
        if (aux!=null){
            aux.listSiguientes += ","+siguientes;
        }
        else{
            NodoSiguientes nuevo = new NodoSiguientes(lexema, nodo, siguientes);
            if(this.primero != null){
                this.ultimo.siguiente = nuevo;
                this.ultimo = nuevo;
            }
            else{
                this.primero = this.ultimo = nuevo;
            }
        }
    }
    
    public NodoSiguientes buscar(String nodo){
        NodoSiguientes aux = this.primero;
        
        while(aux!=null){
            if(aux.nodo == nodo){
                return aux;
            }
            
            aux = aux.siguiente;
        }
        return null;
    }
    
    public void imprimir(){
        NodoSiguientes aux = this.primero;
        
        while(aux != null){
            System.out.println(aux.lexema+" "+aux.nodo+" "+aux.listSiguientes);
            aux = aux.siguiente;
        }
    }
}
