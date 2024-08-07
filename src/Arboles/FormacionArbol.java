package Arboles;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FormacionArbol {
    
    public Nodo primero, ultimo;
    public NodoArbol raiz = null;
    public String dot ="", dotAFN ="";
    public String nombre = "";
    public int contadorHojas = 1, contadorAFN = 0;
    public int contadorNodos = 1;
    
    public ListaSiguientes tablaSiguientes;
    public FormacionArbol(String nombre){
        this.primero = null;
        this.ultimo = null;
        this.nombre = nombre;
        tablaSiguientes = new ListaSiguientes(nombre);
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
            dot += "N"+aux.noNodo+"->";
            if(!aux.izquierda.hoja){
                dot+= "N"+aux.izquierda.noNodo+"\n";
            }else{
                dot+= aux.izquierda.noNodo+"\n";
            }
            this.recorrerArbol(nuevo.izquierda);
        }
        
        if(aux.hoja){
            if(aux.texto.equals("\\n")){
                dot += aux.noNodo+"[label=\"N\n\\\\n\nP: "+aux.primeros.imprimirLista()+"\nU: "+aux.ultimos.imprimirLista()+"\"];\n";
            }
            else if(aux.texto.equals("\\\"") || aux.texto.equals("\\\'")){
                dot += aux.noNodo+"[label=\"N\n\\\\"+aux.texto+"\nP: "+aux.primeros.imprimirLista()+"\nU: "+aux.ultimos.imprimirLista()+"\"];\n";
            }
            else{
                dot += aux.noNodo+"[label=\"N\n"+aux.texto+"\nP: "+aux.primeros.imprimirLista()+"\nU: "+aux.ultimos.imprimirLista()+"\"];\n";
            }
        }
        if(!aux.hoja){
            if (aux.anulable){
                dot += "N"+aux.noNodo+"[label=\"A\n"+aux.texto+"\nP: "+aux.primeros.imprimirLista()+"\nU: "+aux.ultimos.imprimirLista()+"\"];\n";
            }else{
                dot += "N"+aux.noNodo+"[label=\"N\n"+aux.texto+"\nP: "+aux.primeros.imprimirLista()+"\nU: "+aux.ultimos.imprimirLista()+"\"];\n";
            }
        }
            
        if(aux.derecha != null){
            dot += "N"+aux.noNodo+"->";
            if(!aux.derecha.hoja){
                dot+= "N"+aux.derecha.noNodo+"\n";
            }else{
                dot+= aux.derecha.noNodo+"\n";
            }
            this.recorrerArbol(nuevo.derecha);
        }
    }
    
    public void crearArchivo(){
        String dirActual = System.getProperty("user.dir");
        
        File filedot = new File(dirActual+"/Reportes_202003894/Arboles_202003894/"+ this.nombre +".dot");
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
                
                ProcessBuilder proceso = new ProcessBuilder("dot", "-Tpng","-o",dirActual+"/Reportes_202003894/Arboles_202003894/"+ this.nombre +".png",dirActual+"/Reportes_202003894/Arboles_202003894/"+ this.nombre +".dot");
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
    
    public void numerarNodos(NodoArbol nodo){
        
        if (nodo.izquierda != null){
            this.numerarNodos(nodo.izquierda);
        }
        
        if(nodo.hoja){
            nodo.noNodo = this.contadorHojas;
            this.contadorHojas ++;
        }
        if(!nodo.hoja){
            nodo.noNodo = this.contadorNodos;
            this.contadorNodos ++;
        }
        
        if (nodo.derecha != null){
            this.numerarNodos(nodo.derecha);
        }
    }
    
    public void PrimUltSig(NodoArbol nodo){
        if (nodo.izquierda != null){
            this.PrimUltSig(nodo.izquierda);
        }
        
        if (nodo.derecha != null){
            this.PrimUltSig(nodo.derecha);
        }
        
        
        if (".".equals(nodo.texto) && !nodo.hoja){
            nodo.primeros.insertarLista(nodo.izquierda.primeros);

            if (nodo.izquierda.anulable == true && nodo.derecha.anulable == true){                    
                nodo.anulable = true;
            }
            if (nodo.izquierda.anulable){
                nodo.primeros.insertarLista(nodo.derecha.primeros); 
            }
            if (nodo.derecha.anulable){
                nodo.ultimos.insertarLista(nodo.izquierda.ultimos); 
            }
            nodo.ultimos.insertarLista(nodo.derecha.ultimos);

            NodoPU aux = nodo.izquierda.ultimos.primero;
            while(aux != null){
                this.tablaSiguientes.insertar(aux.Lexema, aux.noNodo, nodo.derecha.primeros.imprimirLista());
                aux = aux.siguiente;
            }
        }
                
        else if ("?".equals(nodo.texto) && !nodo.hoja){
            nodo.primeros.insertarLista(nodo.izquierda.primeros);
            nodo.ultimos.insertarLista(nodo.izquierda.ultimos);
        }
            
        else if ("*".equals(nodo.texto) && !nodo.hoja){
            nodo.primeros.insertarLista(nodo.izquierda.primeros);
            nodo.ultimos.insertarLista(nodo.izquierda.ultimos);

            NodoPU aux = nodo.izquierda.ultimos.primero;
            while(aux != null){
                this.tablaSiguientes.insertar(aux.Lexema, aux.noNodo, nodo.izquierda.primeros.imprimirLista());
                aux = aux.siguiente;
            } 
        }
                
        else if ("+".equals(nodo.texto) && !nodo.hoja){
            nodo.primeros.insertarLista(nodo.izquierda.primeros);
            nodo.ultimos.insertarLista(nodo.izquierda.ultimos);

            if (nodo.izquierda.anulable == true){
                nodo.anulable = true;
            }

            NodoPU aux = nodo.izquierda.ultimos.primero;
            while(aux != null){
                this.tablaSiguientes.insertar(aux.Lexema, aux.noNodo, nodo.izquierda.primeros.imprimirLista());
                aux = aux.siguiente;
            }
        }
            
        else if ("|".equals(nodo.texto) && !nodo.hoja){
            nodo.primeros.insertarLista(nodo.izquierda.primeros);
            nodo.primeros.insertarLista(nodo.derecha.primeros);
            nodo.ultimos.insertarLista(nodo.izquierda.ultimos);
            nodo.ultimos.insertarLista(nodo.derecha.ultimos);       

            if (nodo.izquierda.anulable == true || nodo.derecha.anulable == true){
                nodo.anulable = true;
            }
        }
            
        else{
                nodo.primeros.insertar(String.valueOf(nodo.noNodo), nodo.texto);
                nodo.ultimos.insertar(String.valueOf(nodo.noNodo), nodo.texto);
        }
    }
    
    public void imprimirArbol(NodoArbol nodo){
        if (nodo.izquierda != null){
            this.imprimirArbol(nodo.izquierda);
        }
        System.out.println(nodo.texto);
        if (nodo.derecha != null){
            this.imprimirArbol(nodo.derecha);
        }
    }
    
    public void generarAFN(){
        
        this.dotAFN ="digraph G{\nrankdir = LR;\n" ;
        this.dotAFN +="Inicio[style=invis];\n";
        int arreglo [] = this.recorrerAFN(this.raiz.izquierda);
        this.dotAFN += "Inicio->"+(arreglo[0])+" [label=\"Inicio\"];\n";
        this.dotAFN += arreglo[1]+" [shape=doublecircle];\n";
        this.dotAFN += "}";
        
        String dirActual = System.getProperty("user.dir");
        
        File filedot = new File(dirActual+"/Reportes_202003894/AFND_202003894/"+ this.nombre +".dot");
        try {
            filedot.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(FormacionArbol.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                try (FileWriter escritor = new FileWriter(filedot)) {
                    escritor.write(this.dotAFN);
                }
                
                ProcessBuilder proceso = new ProcessBuilder("dot", "-Tpng","-o",dirActual+"/Reportes_202003894/AFND_202003894/"+ this.nombre +".png",dirActual+"/Reportes_202003894/AFND_202003894/"+ this.nombre +".dot");
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
    
    public int [] recorrerAFN(NodoArbol nodo){
        
        int [] datosIzquierda = new int [2];
        int [] datosDerecha = new int [2];
        if (nodo.izquierda != null && !nodo.izquierda.hoja){
           datosIzquierda = recorrerAFN(nodo.izquierda);
        }
        
        if (nodo.derecha != null && !nodo.derecha.hoja){
           datosDerecha = recorrerAFN(nodo.derecha);
        }
        
        int [] regreso = new int [2];
        regreso[0]= this.contadorAFN;

        
        if (nodo.texto.equals(".") && nodo.izquierda.hoja && nodo.derecha.hoja){
            
            this.dotAFN += contadorAFN+"[shape=circle];\n";
            this.dotAFN += (contadorAFN+1)+"[shape=circle];\n";
            this.dotAFN += (contadorAFN+2)+"[shape=circle];\n";
            
            String izq = "", der = "";
            if (nodo.izquierda.texto.equals("\\n")){ izq = "\\\\n"; }
            else if(nodo.izquierda.texto.equals("\\\"")){ izq = "\\\\\""; }
            else if(nodo.izquierda.texto.equals("\\\'")){ izq = "\\\\\'"; }
            else{izq = nodo.izquierda.texto;}
            if (nodo.derecha.texto.equals("\\n")){ der = "\\\\n"; }
            else if(nodo.derecha.texto.equals("\\\"")){ der = "\\\\\""; }
            else if(nodo.derecha.texto.equals("\\\'")){ der = "\\\\\'"; }
            else{der = nodo.derecha.texto;}
            
            this.dotAFN += contadorAFN+"->"+(contadorAFN+1)+" [label=\""+izq+"\"];\n";
            this.dotAFN += (contadorAFN+1)+"->"+(contadorAFN+2)+" [label=\""+der+"\"];\n";
            regreso[1] = contadorAFN+2;
            this.contadorAFN = contadorAFN+3;
        }
        
        else if (nodo.texto.equals("+") && nodo.izquierda.hoja){
            String izq = "";
            if (nodo.izquierda.texto.equals("\\n")){ izq = "\\\\n"; }
            else if(nodo.izquierda.texto.equals("\\\"")){ izq = "\\\\\""; }
            else if(nodo.izquierda.texto.equals("\\\'")){ izq = "\\\\\'"; }
            else{izq = nodo.izquierda.texto;}

            this.dotAFN += contadorAFN+"[shape=circle];\n";
            this.dotAFN +=(contadorAFN+1)+"[shape=circle];\n";
            this.dotAFN +=(contadorAFN+2)+"[shape=circle];\n";
            this.dotAFN +=(contadorAFN+3)+"[shape=circle];\n";
            this.dotAFN +=contadorAFN+"->"+(contadorAFN+1)+" [label=\"Epsilon\"];\n";
            this.dotAFN +=(contadorAFN+1)+"->"+(contadorAFN+2)+" [label=\""+izq+"\"];\n";
            this.dotAFN +=(contadorAFN+2)+"->"+(contadorAFN+3)+" [label=\"Epsilon\"];\n";
            this.dotAFN +=(contadorAFN+2)+"->"+(contadorAFN+1)+" [label=\"Epsilon\"];\n";
            regreso[1] = contadorAFN+3;
            this.contadorAFN = contadorAFN+4;
        }
        
        else if (nodo.texto.equals("*") && nodo.izquierda.hoja){
            String izq = "";
            if (nodo.izquierda.texto.equals("\\n")){ izq = "\\\\n"; }
            else if(nodo.izquierda.texto.equals("\\\"")){ izq = "\\\\\""; }
            else if(nodo.izquierda.texto.equals("\\\'")){ izq = "\\\\\'"; }
            else{izq = nodo.izquierda.texto;}
            
            this.dotAFN +=contadorAFN+"[shape=circle];\n";
            this.dotAFN +=(contadorAFN+1)+"[shape=circle];\n";
            this.dotAFN +=(contadorAFN+2)+"[shape=circle];\n";
            this.dotAFN +=(contadorAFN+3)+"[shape=circle];\n";
            this.dotAFN +=contadorAFN+"->"+(contadorAFN+1)+" [label=\"Epsilon\"];\n";
            this.dotAFN +=contadorAFN+"->"+(contadorAFN+3)+" [label=\"Epsilon\"];\n";
            this.dotAFN +=(contadorAFN+1)+"->"+(contadorAFN+2)+" [label=\""+izq+"\"];\n";
            this.dotAFN +=(contadorAFN+2)+"->"+(contadorAFN+3)+" [label=\"Epsilon\"];\n";
            this.dotAFN +=(contadorAFN+2)+"->"+(contadorAFN+1)+" [label=\"Epsilon\"];\n";
            regreso[1] = contadorAFN+3;
            this.contadorAFN = contadorAFN+4;
        }
        
        else if (nodo.texto.equals("?") && nodo.izquierda.hoja){
            String izq = "", der = "";
            if (nodo.izquierda.texto.equals("\\n")){ izq = "\\\\n"; }
            else if(nodo.izquierda.texto.equals("\\\"")){ izq = "\\\\\""; }
            else if(nodo.izquierda.texto.equals("\\\'")){ izq = "\\\\\'"; }
            else{izq = nodo.izquierda.texto;}
            
            this.dotAFN +=contadorAFN+"[shape=circle];\n";
            this.dotAFN +=(contadorAFN+1)+"[shape=circle];\n";
            this.dotAFN +=(contadorAFN+2)+"[shape=circle];\n";
            this.dotAFN +=(contadorAFN+3)+"[shape=circle];\n";
            this.dotAFN +=contadorAFN+"->"+(contadorAFN+1)+" [label=\"Epsilon\"];\n";
            this.dotAFN +=contadorAFN+"->"+(contadorAFN+3)+" [label=\"Epsilon\"];\n";
            this.dotAFN +=(contadorAFN+1)+"->"+(contadorAFN+2)+" [label=\""+izq+"\"];\n";
            this.dotAFN +=(contadorAFN+2)+"->"+(contadorAFN+3)+" [label=\"Epsilon\"];\n";
            regreso[1] = contadorAFN+3;
            this.contadorAFN = contadorAFN+4;
        }
        
        else if (nodo.texto.equals("|") && nodo.izquierda.hoja && nodo.derecha.hoja){
            String izq = "", der = "";
            if (nodo.izquierda.texto.equals("\\n")){ izq = "\\\\n"; }
            else if(nodo.izquierda.texto.equals("\\\"")){ izq = "\\\\\""; }
            else if(nodo.izquierda.texto.equals("\\\'")){ izq = "\\\\\'"; }
            else{izq = nodo.izquierda.texto;}
            if (nodo.derecha.texto.equals("\\n")){ der = "\\\\n"; }
            else if(nodo.derecha.texto.equals("\\\"")){ der = "\\\\\""; }
            else if(nodo.derecha.texto.equals("\\\'")){ der = "\\\\\'"; }
            else{der = nodo.derecha.texto;}
            
            this.dotAFN +=contadorAFN+"[shape=circle];\n";
            this.dotAFN +=(contadorAFN+1)+"[shape=circle];\n";
            this.dotAFN +=(contadorAFN+2)+"[shape=circle];\n";
            this.dotAFN +=(contadorAFN+3)+"[shape=circle];\n";
            this.dotAFN +=(contadorAFN+4)+"[shape=circle];\n";
            this.dotAFN +=(contadorAFN+5)+"[shape=circle];\n";
            this.dotAFN +=contadorAFN+"->"+(contadorAFN+1)+" [label=\"Epsilon\"];\n";
            this.dotAFN +=(contadorAFN+1)+"->"+(contadorAFN+2)+" [label=\""+izq+"\"];\n";
            this.dotAFN +=contadorAFN+"->"+(contadorAFN+3)+" [label=\"Epsilon\"];\n";
            this.dotAFN +=(contadorAFN+3)+"->"+(contadorAFN+4)+" [label=\""+der+"\"];\n";
            this.dotAFN +=(contadorAFN+2)+"->"+(contadorAFN+5)+" [label=\"Epsilon\"];\n";
            this.dotAFN +=(contadorAFN+4)+"->"+(contadorAFN+5)+" [label=\"Epsilon\"];\n";
            regreso[1] = contadorAFN+5;
            this.contadorAFN = contadorAFN+6;
        }
        
        else if (nodo.texto.equals("|") && (nodo.izquierda.hoja || nodo.derecha.hoja)){
            this.dotAFN +=contadorAFN+"[shape=circle];\n";
            this.dotAFN +=(contadorAFN+1)+"[shape=circle];\n";
            this.dotAFN +=(contadorAFN+2)+"[shape=circle];\n";
            this.dotAFN +=(contadorAFN+3)+"[shape=circle];\n";
            
            if(nodo.izquierda.hoja){
                String izq = "";
                if (nodo.izquierda.texto.equals("\\n")){ izq = "\\\\n"; }
                else if(nodo.izquierda.texto.equals("\\\"")){ izq = "\\\\\""; }
                else if(nodo.izquierda.texto.equals("\\\'")){ izq = "\\\\\'"; }
                else{izq = nodo.izquierda.texto;}
                
                this.dotAFN +=contadorAFN+"->"+(contadorAFN+1)+" [label=\"Epsilon\"];\n";
                this.dotAFN +=(contadorAFN+1)+"->"+(contadorAFN+2)+" [label=\""+izq+"\"];\n";
                this.dotAFN +=(contadorAFN+2)+"->"+(contadorAFN+3)+" [label=\"Epsilon\"];\n";
                this.dotAFN +=contadorAFN+"->"+(datosDerecha[0])+" [label=\"Epsilon\"];\n";
                this.dotAFN +=datosDerecha[1]+"->"+(contadorAFN+3)+" [label=\"Epsilon\"];\n";
            }
            else{
                String der = "";
                if (nodo.derecha.texto.equals("\\n")){ der = "\\\\n"; }
                else if(nodo.derecha.texto.equals("\\\"")){ der = "\\\\\""; }
                else if(nodo.derecha.texto.equals("\\\'")){ der = "\\\\\'"; }
                else{der = nodo.derecha.texto;}
                this.dotAFN +=contadorAFN+"->"+(contadorAFN+1)+" [label=\"Epsilon\"];\n";
                this.dotAFN +=(contadorAFN+1)+"->"+(contadorAFN+2)+" [label=\""+nodo.derecha.texto+"\"];\n";
                this.dotAFN +=(contadorAFN+2)+"->"+(contadorAFN+3)+" [label=\"Epsilon\"];\n";
                this.dotAFN +=contadorAFN+"->"+(datosIzquierda[0])+" [label=\"Epsilon\"];\n";
                this.dotAFN +=datosIzquierda[1]+"->"+(contadorAFN+3)+" [label=\"Epsilon\"];\n";
            }
            
            regreso[1] = contadorAFN+3;
            this.contadorAFN = contadorAFN+4;
        }
        
        else if(nodo.texto.equals(".") && (nodo.izquierda.hoja || nodo.derecha.hoja)){
            this.dotAFN +=contadorAFN+"[shape=circle];\n";
            if (nodo.izquierda.hoja){
                String izq = "";
                if (nodo.izquierda.texto.equals("\\n")){ izq = "\\\\n"; }
                else if(nodo.izquierda.texto.equals("\\\"")){ izq = "\\\\\""; }
                else if(nodo.izquierda.texto.equals("\\\'")){ izq = "\\\\\'"; }
                else{izq = nodo.izquierda.texto;}
                
                this.dotAFN +=contadorAFN+"->"+(datosDerecha[0])+" [label=\""+izq+"\"];\n";
                regreso[0] = contadorAFN;
                regreso[1] = datosDerecha[1];
            }else{
                String der = "";
                if (nodo.derecha.texto.equals("\\n")){ der = "\\\\n"; }
                else if(nodo.derecha.texto.equals("\\\"")){ der = "\\\\\""; }
                else if(nodo.derecha.texto.equals("\\\'")){ der = "\\\\\'"; }
                else{der = nodo.derecha.texto;}
                
                this.dotAFN +=(datosIzquierda[1])+"->"+(contadorAFN)+" [label=\""+der+"\"];\n";
                regreso[0] = datosIzquierda[0];
                regreso[1] =  contadorAFN;
            }
            this.contadorAFN = contadorAFN+1;
        }
        
        else if (nodo.texto.equals("+")){
            this.dotAFN +=contadorAFN+"[shape=circle];\n";
            this.dotAFN +=(contadorAFN+1)+"[shape=circle];\n";
            this.dotAFN +=contadorAFN+"->"+(datosIzquierda[0])+" [label=\"Epsilon\"];\n";
            this.dotAFN +=(datosIzquierda[1])+"->"+(contadorAFN+1)+" [label=\"Epsilon\"];\n";
             this.dotAFN +=datosIzquierda[1]+"->"+datosIzquierda[0]+" [label=\"Epsilon\"];\n";
            regreso[1] = contadorAFN+1;
            this.contadorAFN = contadorAFN+2;
        }
        
        else if (nodo.texto.equals("*")){
            this.dotAFN +=contadorAFN+"[shape=circle];\n";
            this.dotAFN +=(contadorAFN+1)+"[shape=circle];\n";
            this.dotAFN +=contadorAFN+"->"+(datosIzquierda[0])+" [label=\"Epsilon\"];\n";
            this.dotAFN +=(datosIzquierda[1])+"->"+(contadorAFN+1)+" [label=\"Epsilon\"];\n";
            this.dotAFN +=datosIzquierda[1]+"->"+datosIzquierda[0]+" [label=\"Epsilon\"];\n";
            this.dotAFN +=(contadorAFN)+"->"+(contadorAFN+1)+" [label=\"Epsilon\"];\n";
            regreso[1] = contadorAFN+1;
            this.contadorAFN = contadorAFN+2;
        }
        
        else if (nodo.texto.equals("?")){
            this.dotAFN +=contadorAFN+"[shape=circle];\n";
            this.dotAFN +=(contadorAFN+1)+"[shape=circle];\n";
            this.dotAFN +=contadorAFN+"->"+(datosIzquierda[0])+" [label=\"Epsilon\"];\n";
            this.dotAFN +=(datosIzquierda[1])+"->"+(contadorAFN+1)+" [label=\"Epsilon\"];\n";
            this.dotAFN +=(contadorAFN)+"->"+(contadorAFN+1)+" [label=\"Epsilon\"];\n";
            regreso[1] = contadorAFN+1;
            this.contadorAFN = contadorAFN+2;
        }
        
        else if (nodo.texto.equals("|")){
            this.dotAFN +=contadorAFN+"[shape=circle];\n";
            this.dotAFN +=(contadorAFN+1)+"[shape=circle];\n";
            this.dotAFN +=contadorAFN+"->"+(datosIzquierda[0])+" [label=\"Epsilon\"];\n";
            this.dotAFN +=contadorAFN+"->"+(datosDerecha[0])+" [label=\"Epsilon\"];\n";
            this.dotAFN +=(datosIzquierda[1])+"->"+(contadorAFN+1)+" [label=\"Epsilon\"];\n";
            this.dotAFN +=(datosDerecha[1])+"->"+(contadorAFN+1)+" [label=\"Epsilon\"];\n";
            regreso[1] = contadorAFN+1;
            this.contadorAFN = contadorAFN+2;
        }
        
        else if(nodo.texto.equals(".")){
            this.dotAFN = this.dotAFN.replaceAll("\n"+datosDerecha[0]+"-", "\n"+datosIzquierda[1]+"-");
            this.dotAFN = this.dotAFN.replaceAll("\n"+datosDerecha[0]+"[^0-9-]", "\n"+datosIzquierda[1]+"[");
            this.dotAFN = this.dotAFN.replaceAll(">"+datosDerecha[0]+" [^0-9-]", ">"+datosIzquierda[1]+" [");
            regreso[0] = datosIzquierda[0];
            regreso[1] = datosDerecha[1];
            this.contadorAFN = contadorAFN+2;
        }
        
        return regreso;
    }
}
