package Arboles;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Pila {
    
    public Nodo primero, ultimo;
    public NodoArbol raiz = null;
    public String dot ="";
    public String nombre = "";
    
    public Pila(String nombre){
        this.primero = null;
        this.ultimo = null;
        this.nombre = nombre;
    }
    
    public void insertar(NodoArbol nodo){
        Nodo nuevo = new Nodo(nodo);
        
        if (this.primero != null){
            this.ultimo.siguiente = nuevo;
            nuevo.anterior = this.ultimo;
            this.ultimo = nuevo;
            
        }else {
            this.primero = this.ultimo = nuevo;
        }
    }
    
    public NodoArbol extraer(){
        Nodo aux = this.ultimo;
        if (this.primero == this.ultimo){
            this.primero = null;
        }
        else{
            this.ultimo.anterior.siguiente = null;
        }
        this.ultimo = this.ultimo.anterior;
        aux.anterior = null;
        return aux.contenido;
    }
    
    public void recorrer(){
        Nodo aux = this.primero;
        
        while(aux!=null){
            System.out.println(aux.contenido.texto);
            aux = aux.siguiente;
        }
    }
    
    public String generarDot(){
        this.dot = "digraph G{\n";
        
        this.recorrerArbol(this.raiz);
        
        this.dot += "}\n";
        return this.dot;
    }
    
    public void recorrerArbol(NodoArbol nuevo){
        NodoArbol aux = nuevo;
        
        if(aux.izquierda != null){
            dot += aux.noNodo+"->"+aux.izquierda.noNodo+"\n";
            this.recorrerArbol(nuevo.izquierda);
        }
        
        if (aux.anulable){
            dot += aux.noNodo+"[label=\"A\n"+aux.texto+"\"];\n";
        }else{
            dot += aux.noNodo+"[label=\"N\n"+aux.texto+"\"];\n";
        }
            
        if(aux.derecha != null){
            dot += aux.noNodo+"->"+aux.derecha.noNodo+"\n";
            this.recorrerArbol(nuevo.derecha);
        }
    }
    
    public void crearArchivo(){
        String dirActual = System.getProperty("user.dir");
        
        File filedot = new File(dirActual+"/Reportes_202003894/Arboles_202003894/"+ this.nombre +".dot");
        try {
            filedot.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Pila.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                try (FileWriter escritor = new FileWriter(filedot)) {
                    escritor.write(this.generarDot());
                }
                
                ProcessBuilder proceso = new ProcessBuilder("dot", "-Tpng","-o",dirActual+"/Reportes_202003894/Arboles_202003894/"+ this.nombre +".png",dirActual+"/Reportes_202003894/Arboles_202003894/"+ this.nombre +".dot");
                proceso.redirectErrorStream(true);
                proceso.start();
                } catch (IOException ex) {
                    Logger.getLogger(Pila.class.getName()).log(Level.SEVERE, null, ex);
                } 
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
