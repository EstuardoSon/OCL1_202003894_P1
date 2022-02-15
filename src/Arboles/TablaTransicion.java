package Arboles;

import gui.ava.html.image.generator.HtmlImageGenerator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TablaTransicion {
    
    public ListaCabecera columTransicion;
    public ListaCabecera filaTransicion;
    public ListaSiguientes tablaSiguiente;
    public String nombre;
    public int contadorEstado = 0;
    
    public TablaTransicion(ListaSiguientes listaSiguientes, String nombre){
        this.nombre = nombre;
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
    
    public String ingresarFila(String lista){
        return this.filaTransicion.insertarFila(lista);
    }
    
    public void llenarTabla(){
        NodoCabecera aux = this.filaTransicion.primero;
        
        while(aux != null){
            String terminalesTransicion [] = aux.lexema.split(",");
            
            for(int i = 0; i < terminalesTransicion.length; i++){
                NodoSiguientes terminal = this.tablaSiguiente.buscar(terminalesTransicion[i].strip());
                NodoCabecera columna = this.columTransicion.buscar(terminal.lexema);
                String transicionColumna [] = terminal.listSiguientes.split(",");
                if (!"-".equals(transicionColumna[0])){
                    columna.listaTransicion.insertarEnColumna(aux.numero, columna.numero, columna.lexema, transicionColumna);
                    aux.listaTransicion.insertarEnFila(aux.numero, columna.numero, columna.lexema, transicionColumna);
                }else{
                    aux.estadoFinal = true;
                }
            }
            
           
            NodoContenido aux2 = aux.listaTransicion.primero;
            while(aux2 != null){
                aux2.estado = this.ingresarFila(aux2.lista.imprimirLista());
                aux2 = aux2.siguiente;
            }
            
            aux = aux.abajo;
        }
    }
    
    public void htmlTabla(){
        NodoCabecera aux = this.filaTransicion.primero;
        String cadena = "<table border=\"2\">\n<tr>\n<th rowspan=\"2\">Estado</th>\n<th colspan=\""+this.columTransicion.ultimo.numero+"\">Terminales</th>\n</tr>\n<tr>\n";
        
        NodoCabecera columna = this.columTransicion.primero;
        
        while(columna != null){
            cadena += "<th>"+columna.lexema+"</th>\n";
            
            columna = columna.siguiente;
        }
        cadena += "</tr>\n";
        
        while ( aux != null ){
            cadena += "<tr>\n<td>"+aux.nombre+"{"+aux.lexema+"}</td>\n";
            
            NodoContenido aux2 = aux.listaTransicion.primero;
            for(int i = 1; i < (this.columTransicion.ultimo.numero + 1); i++){
                if(aux2 != null && i == aux2.columna){
                    cadena += "<td>"+aux2.estado+"</td>\n";
                    aux2 = aux2.siguiente;
                }
                else if(i == this.columTransicion.ultimo.numero && aux.estadoFinal){
                    cadena += "<td>*</td>\n";
                }
                else{
                    cadena += "<td></td>\n";
                }
            }
            cadena += "</tr>\n";
            aux = aux.abajo;
        }
        cadena += "</table>";
        
        String rutaActual = System.getProperty("user.dir");
        File archivo = new File(rutaActual+"/Reportes_202003894/Transiciones_202003894/"+this.nombre+".html");
        try {
            archivo.createNewFile();
            FileWriter escritor = new FileWriter(archivo);
            escritor.write(cadena);
            escritor.close();
        } catch (IOException ex) {
            Logger.getLogger(ListaSiguientes.class.getName()).log(Level.SEVERE, null, ex);
        }

        HtmlImageGenerator hig = new HtmlImageGenerator();
        hig.loadHtml(cadena);
        hig.saveAsImage(new File(rutaActual+"/Reportes_202003894/Transiciones_202003894/"+this.nombre+".png"));
    }
    
    public String generarDot(){
        NodoCabecera aux = this.filaTransicion.primero;
        String cadena = "digraph G{\nrankdir = LR;\ninicio[style=\"invis\"];inicio->S0 [label=\"inicio\"];\n";
        
        while ( aux != null ){
            if (aux.estadoFinal){
                cadena += aux.nombre+"[label=\""+aux.nombre+"\"shape=doublecircle];\n";
            }else{
                cadena += aux.nombre+"[label=\""+aux.nombre+"\"shape=circle];\n";
            }
            
            NodoContenido aux2 = aux.listaTransicion.primero;
            while ( aux2 != null ){
                if(aux2.terminal.equals("\\n")){
                    cadena += aux.nombre+" -> "+aux2.estado+"[label=\"\\\\n\"];\n";
                }else if(aux2.terminal.equals("\\\"") || aux2.terminal.equals("\\\'")){
                    cadena += aux.nombre+" -> "+aux2.estado+"[label=\"\\\\"+aux2.terminal+"\"];\n";
                }
                else{
                    cadena += aux.nombre+" -> "+aux2.estado+"[label=\""+aux2.terminal+"\"]\n";
                }
                aux2 = aux2.siguiente;
            }
            
            aux = aux.abajo;
        }
        cadena += "}";
        return cadena;
    }
    
    public void crearArchivo(){
        String dirActual = System.getProperty("user.dir");
        
        File filedot = new File(dirActual+"/Reportes_202003894/AFD_202003894/"+ this.nombre +".dot");
        try {
            filedot.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(FormacionArbol.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                try (FileWriter escritor = new FileWriter(filedot)) {
                    escritor.write(this.generarDot());
                }
                
                ProcessBuilder proceso = new ProcessBuilder("dot", "-Tpng","-o",dirActual+"/Reportes_202003894/AFD_202003894/"+ this.nombre +".png",dirActual+"/Reportes_202003894/AFD_202003894/"+ this.nombre +".dot");
                proceso.redirectErrorStream(true);
                proceso.start();
                }  catch (IOException ex) {
                    Logger.getLogger(FormacionArbol.class.getName()).log(Level.SEVERE, null, ex);
                } 
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
