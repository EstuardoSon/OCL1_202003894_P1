
package Arboles;

public class NodoCabecera {
    
    public String nombre = "";
    public String lexema;
    public int numero;
    public ListaEstado listaTransicion;
    public boolean estadoFinal = false;
    public NodoCabecera siguiente;
    public NodoCabecera abajo;
    
    public NodoCabecera(String lexema, int numero){
        this.numero = numero;
        this.listaTransicion = new ListaEstado();
        this.lexema = lexema;
        this.siguiente = null;
        this.abajo = null;
    }
    
    public NodoCabecera(String lexema, String estado, int numero){
        this.listaTransicion = new ListaEstado();
        this.numero = numero;
        this.nombre = estado;
        this.lexema = lexema;
        this.siguiente = null;
        this.abajo = null;
    }
}
