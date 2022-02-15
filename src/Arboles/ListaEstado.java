package Arboles;

public class ListaEstado {

    public NodoContenido primero;
    public NodoContenido ultimo;
    
    public ListaEstado(){
        this.primero = null;
        this.ultimo = null;
    }
    
    public void insertarEnFila(int fila, int columna, String Terminal, String [] terminales){
        NodoContenido aux = this.buscarEnFila(columna);
        if(aux != null){
            for(int i = 0; i < terminales.length ; i++){
                aux.lista.insertar(Integer.parseInt(terminales[i]));
            }
            return;
        }
        else{
            NodoContenido nuevo = new NodoContenido(fila, columna, Terminal);
            for(int i = 0; i < terminales.length ; i++){
                nuevo.lista.insertar(Integer.parseInt(terminales[i]));
            }
            if (this.primero == null){
                this.primero = this.ultimo = nuevo;
            }
            else if (this.primero.columna < nuevo.columna && this.primero.siguiente == null){
                this.primero.siguiente = nuevo;
                this.ultimo = nuevo;
            }
            else if (this.primero.columna > nuevo.columna){
                nuevo.siguiente = this.primero;
                this.primero = nuevo;
            }
            else{
                NodoContenido aux2 = this.primero;
                while(aux2 != null){
                    if (aux2 == this.ultimo && aux2.columna < nuevo.columna){
                        aux2.siguiente = nuevo;
                        this.ultimo = nuevo;
                        break;
                    }
                    else if (aux2 != this.ultimo && aux2.siguiente.columna > nuevo.columna && aux2.columna < nuevo.columna){
                        nuevo.siguiente = aux2.siguiente;
                        aux2.siguiente = nuevo;
                        break;
                    }

                    aux2 = aux2.siguiente;
                }
            }
        }
    }
    
    public void insertarEnColumna(int fila, int columna, String Terminal, String [] terminales){
        NodoContenido aux = this.buscarEnColumna(fila);
        if(aux != null){
            for(int i = 0; i < terminales.length ; i++){
                aux.lista.insertar(Integer.parseInt(terminales[i]));
            }
            return;
        }
        else{
            NodoContenido nuevo = new NodoContenido(fila, columna, Terminal);
            for(int i = 0; i < terminales.length ; i++){
                nuevo.lista.insertar(Integer.parseInt(terminales[i]));
            }
            if (this.primero == null){
                this.primero = this.ultimo = nuevo;
            }
            else if (this.primero.fila < nuevo.fila && this.primero.abajo == null){
                this.primero.abajo = nuevo;
                this.ultimo = nuevo;
            }
            else if (this.primero.fila > nuevo.fila){
                nuevo.abajo = this.primero;
                this.primero = nuevo;
            }
            else{
                NodoContenido aux2 = this.primero;
                while(aux2 != null){
                    if (aux2 == this.ultimo && aux2.fila < nuevo.fila){
                        aux2.abajo = nuevo;
                        this.ultimo = nuevo;
                        break;
                    }
                    else if (aux2 != this.ultimo && aux2.abajo.fila > nuevo.fila && aux2.fila < nuevo.fila){
                        nuevo.abajo = aux2.abajo;
                        aux2.abajo = nuevo;
                        break;
                    }

                    aux2 = aux2.abajo;
                }
            }
        }
    }
    
    public NodoContenido buscarEnFila(int columna){
        NodoContenido aux = this.primero;
        
        while(aux!=null){
            if(aux.columna == columna){
                return aux;
            }
            
            aux = aux.siguiente;
        }
        return null;
    }
    
    public NodoContenido buscarEnColumna(int fila){
        NodoContenido aux = this.primero;
        
        while(aux!=null){
            if(aux.fila == fila){
                return aux;
            }
            
            aux = aux.abajo;
        }
        return null;
    }
}
