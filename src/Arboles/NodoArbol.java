package Arboles;

public class NodoArbol {
    
    public NodoArbol izquierda, derecha;
    public boolean anulable, hoja, estadoFinal = false;
    public int noNodo;
    public String texto;
    public ListaPU ultimos;
    public ListaPU primeros;
    
    public NodoArbol(String contenido){
        this.anulable = false;
        this.izquierda = null;
        this.hoja = true;
        this.noNodo = 0;
        this.derecha = null;
        this.texto = contenido;
        this.primeros= new ListaPU();
        this.ultimos= new ListaPU();
    }
    
    public NodoArbol(String contenido, boolean anulable){
        this.anulable = anulable;
        this.izquierda = null;
        this.hoja = false;
        this.noNodo = 0;
        this.derecha = null;
        this.texto = contenido;
        this.primeros = new ListaPU();
        this.ultimos = new ListaPU();
    }
}
