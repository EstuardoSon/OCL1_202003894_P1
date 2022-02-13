
package Arboles;

public class NodoCabecera {
    
    public String lexema;
    public NodoCabecera siguiente;
    
    public NodoCabecera(String lexema){
        this.lexema = lexema;
        this.siguiente = null;
    }
}
