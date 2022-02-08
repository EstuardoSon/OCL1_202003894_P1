package Arboles;


public class Nodo {
    
    public NodoArbol contenido;
    public Nodo siguiente, anterior;
    
    public Nodo(NodoArbol nodo){
        this.contenido = nodo;
        this.siguiente = null;
        this.anterior = null;
    }
    
}
