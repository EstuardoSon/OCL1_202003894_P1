package Arboles;

public class NodoPU {
    public String noNodo;
    public String Lexema;
    public NodoPU siguiente;
    
    public NodoPU(String noNodo, String Lexema){
        this.Lexema = Lexema;
        this.noNodo = noNodo;
        this.siguiente = null;
    }
}
