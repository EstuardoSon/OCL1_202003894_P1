package Arboles;


public class CambioNotacion {
    
    public ListaSiguientes tablaSiguientes;
    
    public CambioNotacion(String texto, String nombre){
        int largo = texto.length();
        tablaSiguientes = new ListaSiguientes(nombre);
        FormacionArbol pila = new FormacionArbol(nombre);
        
        int contHojas = 1;
        
        for(int i = largo-1; i>=0; i--){
            char caracter = texto.charAt(i);
            NodoArbol nuevo;
            
            if (((int) caracter) == 34){
                String cadena = "";
                
                for(int j = i-1; j>=0; j--){
                    if(((int)texto.charAt(j)) == 34 && ((int)texto.charAt(j-1)) == 92){
                        cadena += String.valueOf(texto.charAt(j-1))+String.valueOf(texto.charAt(j));
                        j=j-1;
                    }
                    else if(((int)texto.charAt(j)) == 34 && ((int)texto.charAt(j-1)) != 92){
                        i = j;
                        break;
                    }
                    else{
                        cadena += String.valueOf(texto.charAt(j));
                    }
                }
                
                nuevo = new NodoArbol(cadena, contHojas);
                nuevo.primeros.insertar(String.valueOf(contHojas), cadena);
                nuevo.ultimos.insertar(String.valueOf(contHojas), cadena);
                pila.insertar(nuevo);
            }
            
            else if (((int) caracter) == 125){
                String cadena = "";
                
                for(int j = i-1; j>=0; j--){
                    if(((int)texto.charAt(j)) != 123){
                        cadena = String.valueOf(texto.charAt(j))+cadena;
                    }
                    else{
                        i = j;
                        break;
                    }
                }
                
                nuevo = new NodoArbol(cadena, contHojas);
                nuevo.primeros.insertar(String.valueOf(contHojas), cadena);
                nuevo.ultimos.insertar(String.valueOf(contHojas), cadena);
                pila.insertar(nuevo);
            }
            
            else if ((((int) caracter) >= 97 && ((int) caracter) <= 122) || (((int) caracter) >= 65 && ((int) caracter) <= 90) || (((int) caracter) >= 48 && ((int) caracter) <= 57) | ((int) caracter) == 164 | ((int) caracter) == 165){
                nuevo = new NodoArbol(String.valueOf(caracter), contHojas);
                nuevo.primeros.insertar(String.valueOf(contHojas), String.valueOf(caracter));
                nuevo.ultimos.insertar(String.valueOf(contHojas), String.valueOf(caracter));
                pila.insertar(nuevo);
            }
            
            
            else if (((int) caracter) == 46){
                nuevo = new NodoArbol(String.valueOf(caracter), false, contHojas);
                nuevo.izquierda = pila.extraer();
                nuevo.derecha = pila.extraer();
                nuevo.primeros.insertarLista(nuevo.izquierda.primeros);
                nuevo.ultimos.insertarLista(nuevo.derecha.ultimos);
                
                if (nuevo.izquierda.anulable == true && nuevo.derecha.anulable == true){                    
                    nuevo.anulable = true;
                }
                if (nuevo.izquierda.anulable){
                    nuevo.primeros.insertarLista(nuevo.derecha.primeros); 
                }
                if (nuevo.derecha.anulable){
                    nuevo.ultimos.insertarLista(nuevo.izquierda.ultimos); 
                }
                
                NodoPU aux = nuevo.izquierda.ultimos.primero;
                while(aux != null){
                    this.tablaSiguientes.insertar(aux.Lexema, aux.noNodo, nuevo.derecha.primeros.imprimirLista());
                    aux = aux.siguiente;
                } 
                pila.insertar(nuevo);
            }
            
            else if (((int) caracter) == 42){
                nuevo = new NodoArbol(String.valueOf(caracter), true, contHojas);
                nuevo.izquierda = pila.extraer();
                nuevo.primeros.insertarLista(nuevo.izquierda.primeros);
                nuevo.ultimos.insertarLista(nuevo.izquierda.ultimos);
                
                NodoPU aux = nuevo.izquierda.ultimos.primero;
                while(aux != null){
                    this.tablaSiguientes.insertar(aux.Lexema, aux.noNodo, nuevo.izquierda.primeros.imprimirLista());
                    aux = aux.siguiente;
                } 
                
                pila.insertar(nuevo);
            }
            
            else if (((int) caracter) == 124){
                nuevo = new NodoArbol(String.valueOf(caracter), false, contHojas);
                nuevo.izquierda = pila.extraer();
                nuevo.derecha = pila.extraer();
                nuevo.primeros.insertarLista(nuevo.izquierda.primeros);
                nuevo.primeros.insertarLista(nuevo.derecha.primeros);
                nuevo.ultimos.insertarLista(nuevo.izquierda.ultimos);
                nuevo.ultimos.insertarLista(nuevo.derecha.ultimos);       
                
                if (nuevo.izquierda.anulable == true || nuevo.derecha.anulable == true){
                    nuevo.anulable = true;
                }
                pila.insertar(nuevo);
            }
            
            else if (((int) caracter) == 63){
                nuevo = new NodoArbol(String.valueOf(caracter), true, contHojas);
                nuevo.izquierda = pila.extraer();
                nuevo.primeros.insertarLista(nuevo.izquierda.primeros);
                nuevo.ultimos.insertarLista(nuevo.izquierda.ultimos);
                pila.insertar(nuevo);
            }
            
            else if (((int) caracter) == 43){
                nuevo = new NodoArbol(String.valueOf(caracter), false, contHojas);
                nuevo.izquierda = pila.extraer();
                nuevo.primeros.insertarLista(nuevo.izquierda.primeros);
                nuevo.ultimos.insertarLista(nuevo.izquierda.ultimos);
                
                if (nuevo.izquierda.anulable == true){
                    nuevo.anulable = true;
                }
                
                NodoPU aux = nuevo.izquierda.ultimos.primero;
                while(aux != null){
                    this.tablaSiguientes.insertar(aux.Lexema, aux.noNodo, nuevo.izquierda.primeros.imprimirLista());
                    aux = aux.siguiente;
                } 
                
                pila.insertar(nuevo);
            }
            
            contHojas ++;
        }
        
        NodoArbol nuevo = new NodoArbol(".", false, contHojas);
        nuevo.izquierda = pila.extraer();
        nuevo.derecha = new NodoArbol("#", false, contHojas+1);
        nuevo.derecha.primeros.insertar(String.valueOf(contHojas+1), "#");
        nuevo.derecha.ultimos.insertar(String.valueOf(contHojas+1), "#");
        nuevo.primeros.insertarLista(nuevo.izquierda.primeros);
        nuevo.ultimos.insertarLista(nuevo.derecha.ultimos);

        if (nuevo.izquierda.anulable == true && nuevo.derecha.anulable == true){
            nuevo.anulable = true;
        }
        
        if (nuevo.izquierda.anulable){
            nuevo.primeros.insertarLista(nuevo.derecha.primeros); 
        }
        
        if (nuevo.derecha.anulable){
            nuevo.ultimos.insertarLista(nuevo.izquierda.ultimos); 
        }
        
        NodoPU aux = nuevo.izquierda.ultimos.primero;
        while(aux != null){
            this.tablaSiguientes.insertar(aux.Lexema, aux.noNodo, nuevo.derecha.primeros.imprimirLista());
            aux = aux.siguiente;
        } 
        
        this.tablaSiguientes.insertar("#", String.valueOf(contHojas+1), "-");
        
        pila.raiz = nuevo;
        
        pila.crearArchivo();
        this.tablaSiguientes.imprimir();
        
    }
}
