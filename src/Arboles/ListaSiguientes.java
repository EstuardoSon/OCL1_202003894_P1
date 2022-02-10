package Arboles;

import gui.ava.html.image.generator.HtmlImageGenerator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListaSiguientes {
    
    public NodoSiguientes primero;
    public NodoSiguientes ultimo;
    public String nombre;
    
    public ListaSiguientes(String nombre){
        this.primero = null;
        this.ultimo = null;
        this.nombre = nombre;
    }
    
    public void insertar(String lexema, String nodo, String siguientes){
        NodoSiguientes aux = this.buscar(nodo);
        if (aux!=null){
            aux.listSiguientes += ","+siguientes;
        }
        else{
            NodoSiguientes nuevo = new NodoSiguientes(lexema, nodo, siguientes);
            if(this.primero != null){
                this.ultimo.siguiente = nuevo;
                this.ultimo = nuevo;
            }
            else{
                this.primero = this.ultimo = nuevo;
            }
        }
    }
    
    public NodoSiguientes buscar(String nodo){
        NodoSiguientes aux = this.primero;
        
        while(aux!=null){
            if(aux.nodo == nodo){
                return aux;
            }
            
            aux = aux.siguiente;
        }
        return null;
    }
    
    public void imprimir(){
        NodoSiguientes aux = this.primero;
        String rutaActual = System.getProperty("user.dir");
        
        String html = "<table border=\"2\">\n<tr>\n<td colspan=\"2\">Hoja</td>\n<td>Siguientes</td>\n<tr>\n";
        
        while(aux != null){
            html += "</tr>\n<td>"+aux.lexema+"</td>\n<td>"+aux.nodo+"</td>\n<td>"+aux.listSiguientes+"</td>\n<tr>\n";
            System.out.println(aux.lexema+" "+aux.nodo+" "+aux.listSiguientes);
            aux = aux.siguiente;
        }
        html += "</table>";
        
        File archivo = new File(rutaActual+"/Reportes_202003894/Siguientes_202003894/"+this.nombre+".html");
        try {
            archivo.createNewFile();
            FileWriter escritor = new FileWriter(archivo);
            escritor.write(html);
            escritor.close();
        } catch (IOException ex) {
            Logger.getLogger(ListaSiguientes.class.getName()).log(Level.SEVERE, null, ex);
        }

        HtmlImageGenerator hig = new HtmlImageGenerator();
        hig.loadHtml(html);
        hig.saveAsImage(new File(rutaActual+"/Reportes_202003894/Siguientes_202003894/"+this.nombre+".png"));
    }
}
