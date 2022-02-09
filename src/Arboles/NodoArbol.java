package Arboles;

public class NodoArbol {
    
    public NodoArbol izquierda, derecha;
    public boolean anulable, hoja;
    public int noNodo;
    public String texto;
    public ListaPU ultimos;
    public ListaPU primeros;
    
    public NodoArbol(String contenido, int noNodo){
        this.anulable = false;
        this.izquierda = null;
        this.hoja = true;
        this.noNodo = noNodo;
        this.derecha = null;
        this.texto = contenido;
        this.primeros= new ListaPU();
        this.ultimos= new ListaPU();
    }
    
    public NodoArbol(String contenido, boolean anulable, int noNodo){
        this.anulable = anulable;
        this.izquierda = null;
        this.noNodo = noNodo;
        this.derecha = null;
        this.texto = contenido;
        this.primeros = new ListaPU();
        this.ultimos = new ListaPU();
    }
}
