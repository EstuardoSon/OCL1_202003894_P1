
package Arboles;

public class ListaCabecera {
    
    public NodoCabecera primero;
    public NodoCabecera ultimo;
    public int contadorTerminal = 1;
    public int contadorEstado = 0;
    
    public ListaCabecera(){
        this.primero = null;
        this.ultimo = null;
    }
    
    public void insertar(String lexema){
        NodoCabecera aux = this.buscar(lexema);
        if (aux!=null){
            return;
        }
        else{
            NodoCabecera nuevo = new NodoCabecera(lexema, this.contadorTerminal);
            if (primero != null){
                this.ultimo.siguiente = nuevo;
                this.ultimo = nuevo;
            }
            else{
                this.primero = this.ultimo = nuevo;
            }
            this.contadorTerminal++;
        }
    }
    
    public NodoCabecera buscar(String nodo){
        NodoCabecera aux = this.primero;
        
        while(aux!=null){
            if(aux.lexema.equals(nodo)){
                return aux;
            }
            
            aux = aux.siguiente;
        }
        return null;
    }
    
    public String insertarFila(String lexema){
        NodoCabecera aux = this.buscarFila(lexema);
        if (aux!=null){
            return aux.nombre;
        }
        else{
            NodoCabecera nuevo = new NodoCabecera(lexema, "S"+String.valueOf(this.contadorEstado), (this.contadorEstado+1));
            if (primero != null){
                this.ultimo.abajo = nuevo;
                this.ultimo = nuevo;
            }
            else{
                this.primero = this.ultimo = nuevo;
            }
            this.contadorEstado++;
            return nuevo.nombre;
        }
    }
    
    public NodoCabecera buscarFila(String nodo){
        NodoCabecera aux = this.primero;
        
        while(aux!=null){
            if(aux.lexema.equals(nodo)){
                return aux;
            }
            
            aux = aux.abajo;
        }
        return null;
    }
    
    public void imprimir(){
        NodoCabecera aux = this.primero;
        
        while(aux != null){
            System.out.println(aux.numero+" "+aux.lexema);
            
            aux = aux.siguiente;
        }
    }
}
