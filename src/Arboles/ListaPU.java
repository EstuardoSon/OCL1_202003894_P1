package Arboles;

public class ListaPU {
    
    public NodoPU primero;
    public NodoPU ultimo;
    
    public ListaPU(){
        this.primero = null;
        this.ultimo = null;
    }
    
    public void insertar(String noNodo, String lexema){
        NodoPU nuevo = new NodoPU(noNodo, lexema);
        
        if (this.primero != null){
            this.ultimo.siguiente = nuevo;
            this.ultimo = nuevo;
        }
        else{
            this.primero = this.ultimo = nuevo;
        }
    }
    
    public void insertarLista(ListaPU lista){
        NodoPU aux = lista.primero;
        
        while(aux != null){
            this.insertar(aux.noNodo, aux.Lexema);
            aux = aux.siguiente;
        }
    }
    
    public String imprimirLista(){
        NodoPU aux = this.primero;
        String cadena = "";
        
        while (aux != null){
            if (aux == this.primero){
                cadena += aux.noNodo;
            }else{
                cadena += ","+aux.noNodo;
            }
            
            aux = aux.siguiente;
        }
        
        return cadena;
    }
}
