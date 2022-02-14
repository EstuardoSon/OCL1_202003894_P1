package Arboles;


public class TablaTransicion {
    
    public ListaCabecera columTransicion;
    public ListaCabecera filaTransicion;
    public ListaSiguientes tablaSiguiente;
    public int contadorEstado = 0;
    
    public TablaTransicion(ListaSiguientes listaSiguientes){
        this.columTransicion = new ListaCabecera();
        this.filaTransicion = new ListaCabecera();
        this.tablaSiguiente = listaSiguientes;
    }
    
    public void ingresarColumnas(NodoSiguientes primero){
        NodoSiguientes terminales = primero;
        
        while(terminales != null){
            this.columTransicion.insertar(terminales.lexema);
            terminales = terminales.siguiente;
        }
    }
    
    public void ingresarFila(String lista){
            this.filaTransicion.insertarFila(lista, this.contadorEstado);
            this.contadorEstado++;
    }
    
    public void llenarTabla(){
        NodoCabecera aux = this.filaTransicion.primero;
        
        while(aux != null){
            String terminalesTransicion [] = aux.lexema.split(",");
            
            for(int i = 0; i < terminalesTransicion.length; i++){
                NodoSiguientes terminal = this.tablaSiguiente.buscar(terminalesTransicion[i].strip());
                NodoCabecera columna = this.columTransicion.buscar(terminal.lexema);
                String transicionColumna [] = terminal.listSiguientes.split(",");
                columna.listaTransicion.insertarEnColumna(aux.numero, columna.numero, transicionColumna);
                aux.listaTransicion.insertarEnFila(aux.numero, columna.numero, transicionColumna);
            }
            
            aux = aux.abajo;
        }
    }
    
    public void imprimirFilas(){
        NodoCabecera aux = this.filaTransicion.primero;
        
        while ( aux != null ){
            System.out.println(aux.numero+" "+aux.nombre+" "+aux.lexema);
            
            NodoContenido aux2 = aux.listaTransicion.primero;
            while ( aux2 != null ){
                System.out.println( aux2.columna+" -> "+aux2.lista.imprimirLista());
                aux2 = aux2.siguiente;
            }
            
            aux = aux.abajo;
        }
    }
}
