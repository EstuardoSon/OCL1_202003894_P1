package Arboles;

public class ListaTerminal {
    
    public NodoTerminal primero;
    public NodoTerminal ultimo; 
    
    public ListaTerminal(){
        this.primero = null;
        this.ultimo = null;
    }
    
    public void insertar(int numero){
        NodoTerminal nuevo = new NodoTerminal(numero);
        if (this.primero == null){
            this.primero = this.ultimo = nuevo;
        }
        else if (this.primero.numero < nuevo.numero && this.primero.siguiente == null){
            this.primero.siguiente = nuevo;
            this.ultimo = nuevo;
        }
        else if (this.primero.numero > nuevo.numero){
            nuevo.siguiente = this.primero;
            this.primero = nuevo;
        }
        else{
            NodoTerminal aux = this.primero;
            while(aux != null){
                if (aux == this.ultimo && aux.numero < nuevo.numero){
                    aux.siguiente = nuevo;
                    this.ultimo = nuevo;
                    break;
                }
                else if (aux != this.ultimo && aux.siguiente.numero > nuevo.numero && aux.numero < nuevo.numero){
                    nuevo.siguiente = aux.siguiente;
                    aux.siguiente = nuevo;
                    break;
                }

                aux = aux.siguiente;
            }
        }
    }
    
    public String imprimirLista(){
        NodoTerminal aux = this.primero;
        String cadena = "";
        
        while(aux != null){
            if(aux == this.primero){
                cadena += aux.numero;
            } else{
                cadena += ","+aux.numero;
            }
            
            aux = aux.siguiente;
        }
        
        return cadena;
    }
}
