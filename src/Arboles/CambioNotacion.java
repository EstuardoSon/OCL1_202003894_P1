package Arboles;


public class CambioNotacion {
    
    public CambioNotacion(String texto, String nombre){
        int largo = texto.length();
        Pila pila = new Pila(nombre);
        
        int contHojas = 1;
        
        for(int i = largo-1; i>=0; i--){
            char caracter = texto.charAt(i);
            
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
                
                pila.insertar(new NodoArbol(cadena, contHojas));
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
                
                pila.insertar(new NodoArbol(cadena, contHojas));
            }
            
            else if ((((int) caracter) >= 97 && ((int) caracter) <= 122) || (((int) caracter) >= 65 && ((int) caracter) <= 90) || (((int) caracter) >= 48 && ((int) caracter) <= 57) | ((int) caracter) == 164 | ((int) caracter) == 165){
                pila.insertar(new NodoArbol(String.valueOf(caracter), contHojas));
            }
            
            
            else if (((int) caracter) == 46){
                NodoArbol nuevo = new NodoArbol(String.valueOf(caracter), false, contHojas);
                nuevo.izquierda = pila.extraer();
                nuevo.derecha = pila.extraer();
                
                if (nuevo.izquierda.anulable == true && nuevo.derecha.anulable == true){
                    nuevo.anulable = true;
                }
                pila.insertar(nuevo);
            }
            
            else if (((int) caracter) == 42){
                NodoArbol nuevo = new NodoArbol(String.valueOf(caracter), true, contHojas);
                nuevo.izquierda = pila.extraer();
                pila.insertar(nuevo);
            }
            
            else if (((int) caracter) == 124){
                NodoArbol nuevo = new NodoArbol(String.valueOf(caracter), false, contHojas);
                nuevo.izquierda = pila.extraer();
                nuevo.derecha = pila.extraer();
                
                if (nuevo.izquierda.anulable == true || nuevo.derecha.anulable == true){
                    nuevo.anulable = true;
                }
                pila.insertar(nuevo);
            }
            
            else if (((int) caracter) == 63){
                NodoArbol nuevo = new NodoArbol(String.valueOf(caracter), true, contHojas);
                nuevo.izquierda = pila.extraer();
                pila.insertar(nuevo);
            }
            
            else if (((int) caracter) == 43){
                NodoArbol nuevo = new NodoArbol(String.valueOf(caracter), false, contHojas);
                nuevo.izquierda = pila.extraer();
                
                if (nuevo.izquierda.anulable == true){
                    nuevo.anulable = true;
                }
                pila.insertar(nuevo);
            }
            
            contHojas ++;
        }
        
        NodoArbol nuevo = new NodoArbol(".", false, contHojas);
        nuevo.izquierda = pila.extraer();
        nuevo.derecha = new NodoArbol("#", false, contHojas+1);

        if (nuevo.izquierda.anulable == true && nuevo.derecha.anulable == true){
            nuevo.anulable = true;
        }
        
        pila.raiz = nuevo;
        
        pila.crearArchivo();
    }
}
