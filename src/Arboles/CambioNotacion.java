package Arboles;


public class CambioNotacion {
    
    public ListaCabecera columTransicion = new ListaCabecera();
    
    public CambioNotacion(String texto, String nombre){
        int largo = texto.length();
        FormacionArbol pila = new FormacionArbol(nombre);
        
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
                
                if (cadena.length() == 1 || cadena.equals("\\n") || cadena.equals("\\\"") | cadena.equals("\\\'")){
                    nuevo = new NodoArbol(cadena);
                    pila.insertar(nuevo);
                }
                
                else{
                    int contador = 0;
                    int j;
                    for(j = 0; j < cadena.length(); j++){
                        if (!String.valueOf(cadena.charAt(j)).equals("\\")){
                            nuevo = new NodoArbol(String.valueOf(cadena.charAt(j)));
                            pila.insertar(nuevo);
                            contador++;
                        }
                        else{
                            nuevo = new NodoArbol(String.valueOf(cadena.charAt(j)+String.valueOf(cadena.charAt(j+1))));
                            pila.insertar(nuevo);
                            j++;
                            contador++;
                        }
                    }   
                    for(int k = 0; k < contador-1; k++){
                        nuevo = new NodoArbol(".", false);
                        nuevo.izquierda = pila.extraer();
                        nuevo.derecha = pila.extraer();
                        pila.insertar(nuevo);
                    }
                }
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
                
                nuevo = new NodoArbol(cadena);
                pila.insertar(nuevo);
            }
            
            else if ((((int) caracter) >= 97 && ((int) caracter) <= 122) || (((int) caracter) >= 65 && ((int) caracter) <= 90) || (((int) caracter) >= 48 && ((int) caracter) <= 57) | ((int) caracter) == 164 | ((int) caracter) == 165){
                nuevo = new NodoArbol(String.valueOf(caracter));
                pila.insertar(nuevo);
            }
            
            
            else if (((int) caracter) == 46){
                nuevo = new NodoArbol(String.valueOf(caracter), false);
                nuevo.izquierda = pila.extraer();
                nuevo.derecha = pila.extraer();
                pila.insertar(nuevo);
            }
            
            else if (((int) caracter) == 42){
                nuevo = new NodoArbol(String.valueOf(caracter), true);
                nuevo.izquierda = pila.extraer();
                pila.insertar(nuevo);
            }
            
            else if (((int) caracter) == 124){
                nuevo = new NodoArbol(String.valueOf(caracter), false);
                nuevo.izquierda = pila.extraer();
                nuevo.derecha = pila.extraer(); 
                pila.insertar(nuevo);
            }
            
            else if (((int) caracter) == 63){
                nuevo = new NodoArbol(String.valueOf(caracter), true);
                nuevo.izquierda = pila.extraer();
                pila.insertar(nuevo);
            }
            
            else if (((int) caracter) == 43){
                nuevo = new NodoArbol(String.valueOf(caracter), false);
                nuevo.izquierda = pila.extraer();           
                pila.insertar(nuevo);
            }
        }
        
        NodoArbol nuevo = new NodoArbol(".", false);
        nuevo.izquierda = pila.extraer();
        nuevo.derecha = new NodoArbol("#");
        pila.raiz = nuevo;
        
        pila.numerarNodos(pila.raiz);
        pila.PrimUltSig(pila.raiz);
        pila.tablaSiguientes.insertar("#", String.valueOf(pila.contadorHojas-1), "-");
        
        pila.crearArchivo();
        pila.tablaSiguientes.imprimir();
        
        /*
        NodoSiguientes terminales = pila.tablaSiguientes.primero;
        
        while(terminales != null){
            this.columTransicion.insertar(terminales.lexema);
            terminales = terminales.siguiente;
        }
        
        System.out.println("\n\nTerminales");
        this.columTransicion.imprimir();
        */
    }
}
