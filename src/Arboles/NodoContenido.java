package Arboles;

public class NodoContenido {
    public int fila;
    public int columna;
    public String terminal;
    public String estado;
    public ListaTerminal lista;
    public NodoContenido siguiente;
    public NodoContenido abajo;
    
    public NodoContenido(int fila, int columna, String terminal){
        this.terminal = terminal;
        this.estado = ""; 
        this.fila = fila;
        this.columna = columna;
        this.lista = new ListaTerminal();
        this.siguiente = null;
        this.abajo = null;
    }
    
    
}
