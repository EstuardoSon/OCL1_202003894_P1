
package Arboles;

public class NodoSiguientes {
    
    public String nodo;
    public String listSiguientes;
    public String lexema;
    public NodoSiguientes siguiente;
    
    public NodoSiguientes(String lexema, String noNodo, String siguientes){
        this.lexema = lexema;
        this.nodo = noNodo;
        this.listSiguientes = siguientes;
        this.siguiente = null;
    }
}
