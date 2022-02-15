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
            if (this.primero == null){
                this.primero = this.ultimo = nuevo;
            }
            else if (Integer.parseInt(this.primero.nodo) < Integer.parseInt(nuevo.nodo) && this.primero.siguiente == null){
                this.primero.siguiente = nuevo;
                this.ultimo = nuevo;
            }
            else if (Integer.parseInt(this.primero.nodo) > Integer.parseInt(nuevo.nodo)){
                nuevo.siguiente = this.primero;
                this.primero = nuevo;
            }
            else{
                NodoSiguientes aux2 = this.primero;
                while(aux2 != null){
                    if (aux2 == this.ultimo && Integer.parseInt(aux2.nodo) < Integer.parseInt(nuevo.nodo)){
                        aux2.siguiente = nuevo;
                        this.ultimo = nuevo;
                        break;
                    }
                    else if (aux2 != this.ultimo && Integer.parseInt(aux2.siguiente.nodo) > Integer.parseInt(nuevo.nodo) && Integer.parseInt(aux2.nodo) < Integer.parseInt(nuevo.nodo)){
                        nuevo.siguiente = aux2.siguiente;
                        aux2.siguiente = nuevo;
                        break;
                    }

                    aux2 = aux2.siguiente;
                }
            }
        }
    }
    
    public NodoSiguientes buscar(String nodo){
        NodoSiguientes aux = this.primero;
        
        while(aux!=null){
            if(aux.nodo.equals(nodo)){
                return aux;
            }
            
            aux = aux.siguiente;
        }
        return null;
    }
    
    public void generarHTML(){
        NodoSiguientes aux = this.primero;
        String rutaActual = System.getProperty("user.dir");
        
        String html = "<table border=\"2\">\n<tr>\n<th colspan=\"2\">Hoja</th>\n<th>Siguientes</th>\n<tr>\n";
        
        while(aux != null){
            html += "</tr>\n<td>"+aux.lexema+"</td>\n<td>"+aux.nodo+"</td>\n<td>"+aux.listSiguientes+"</td>\n<tr>\n";
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
