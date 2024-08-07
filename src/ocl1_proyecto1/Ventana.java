package ocl1_proyecto1;
import Analizadores.*;
import Arboles.*;
import static ocl1_proyecto1.OCL1_Proyecto1.ListaConjuntos;
import static ocl1_proyecto1.OCL1_Proyecto1.ListaExpresiones;
import static ocl1_proyecto1.OCL1_Proyecto1.ListaVerificaciones;
import static ocl1_proyecto1.OCL1_Proyecto1.ListaErrores;
import static ocl1_proyecto1.OCL1_Proyecto1.salida;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Ventana extends JFrame{
    
    private JPanel panel = new JPanel();
    private JTextArea entrada = new JTextArea();
    private JTextArea salida = new JTextArea();
    private JLabel imagen = new JLabel();
    private List arboles;
    private String nombreArchivo = "", direccionArchivo = "";
        
    public Ventana(){
        this.generarDirectorios();
        
        this.setSize(800, 600);
        this.setTitle("OLC1 Proyecto 1");
        this.panel.setLayout(null);
        this.panel.setBackground(Color.GRAY);
        
        this.crearMenuArchivo();
        this.crearLabels();
        this.configurarTextArea();
        this.crearBotones();
        this.visualizadorImagenes();
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(this.panel);
    }
    
    private void crearMenuArchivo(){
        JMenuBar barraMenu = new JMenuBar();
        JMenu menu= new JMenu("Archivo");
        menu.setFont(new Font("Arial", 0,16));
        barraMenu.setBounds(40, 40, 65, 20);
        
        JMenuItem abrir = new JMenuItem();
        abrir.setText("Abrir Archivo");
        abrir.addActionListener(new ActionListener (){

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser abrirArchivo = new JFileChooser();
                FileFilter filtro = new FileNameExtensionFilter("Archivos EXP (.exp)", "EXP"); 
                abrirArchivo.setFileFilter(filtro);
                int seleccion = abrirArchivo.showOpenDialog(abrirArchivo);
                
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                String ruta = abrirArchivo.getSelectedFile().getAbsolutePath();
                
                //Guardar la dirección y nombre del Archivo
                nombreArchivo = abrirArchivo.getSelectedFile().getName().replace(".exp", "");
                direccionArchivo = abrirArchivo.getSelectedFile().getAbsolutePath();

                Scanner contenido = null;
                String escribir = "";
                try {
                    File f = new File(ruta);
                    contenido = new Scanner(f);
                    while (contenido.hasNext()) {
                        escribir += contenido.nextLine()+"\n";
                    }
                } catch (FileNotFoundException a) {
                    System.out.println(a.getMessage());
                } finally {
                    if (contenido != null) {
                        entrada.setText(escribir);
                        contenido.close();
                    }
                }
            } else {
                System.out.println("No se ha seleccionado ningún fichero");
            }
            }
        });
        menu.add(abrir);
        
        JMenuItem crear = new JMenuItem();
        crear.setText("Nuevo Archivo");
        crear.addActionListener(new ActionListener (){

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser abrirArchivo = new JFileChooser();
                FileFilter filtro = new FileNameExtensionFilter("Archivos EXP (.exp)", "EXP"); 
                abrirArchivo.setFileFilter(filtro);
                
                int seleccion = abrirArchivo.showOpenDialog(abrirArchivo);
                
                if (seleccion == JFileChooser.APPROVE_OPTION) {

                    //Guardar la dirección y nombre del Archivo
                    nombreArchivo = abrirArchivo.getSelectedFile().getName().replace(".exp", "");
                    direccionArchivo = abrirArchivo.getSelectedFile().getAbsolutePath();
                    
                    File file = new File(direccionArchivo);
                    try {
                        file.createNewFile();
                        entrada.setText("");
                    } catch (IOException ex) {
                        Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    System.out.println("No se ha seleccionado ningún fichero");
                }
            }
        });
        menu.add(crear);
        
        JMenuItem guardar = new JMenuItem();
        guardar.setText("Guardar Archivo");
        guardar.addActionListener(new ActionListener (){

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter escribir = new FileWriter(direccionArchivo);
                    escribir.write(entrada.getText());
                    escribir.close();
                } catch (IOException ex) {
                    Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        menu.add(guardar);
        
        JMenuItem guardarC = new JMenuItem();
        guardarC.setText("Guardar Como...");
        guardarC.addActionListener(new ActionListener (){

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser abrirArchivo = new JFileChooser();
                FileFilter filtro = new FileNameExtensionFilter("Archivos EXP (.exp)", "EXP"); 
                abrirArchivo.setFileFilter(filtro);
                
                int seleccion = abrirArchivo.showOpenDialog(abrirArchivo);
                
                if (seleccion == JFileChooser.APPROVE_OPTION) {

                    //Guardar la dirección y nombre del Archivo
                    nombreArchivo = abrirArchivo.getSelectedFile().getName().replace(".exp", "");
                    direccionArchivo = abrirArchivo.getSelectedFile().getAbsolutePath();
                    
                    File file = new File(direccionArchivo);
                    
                    try {
                        file.createNewFile();
                        FileWriter escritor = new FileWriter(file);
                        escritor.write(entrada.getText());
                        escritor.flush();
                        escritor.close();
                        
                    } catch (IOException ex) {
                        Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    System.out.println("No se ha seleccionado ningún fichero");
                }
            }
        });
        menu.add(guardarC);
        
        barraMenu.add(menu);
        panel.add(barraMenu);
    }
    
    private void crearLabels(){
        JLabel texto1 = new JLabel("Archivo de Entrada");
        texto1.setBounds(40, 80, 200, 20);
        texto1.setFont(new Font("Arial", 0,16));
        
        JLabel texto2 = new JLabel("Salida");
        texto2.setBounds(40,375, 200, 20);
        texto2.setFont(new Font("Arial", 0,16));
        
        this.panel.add(texto1);
        this.panel.add(texto2);
    }
    
    private void configurarTextArea(){
        this.entrada.setBounds(0, 0, 340, 220);
        JScrollPane scroll = new JScrollPane(this.entrada);
        scroll.setBounds(40, 110, 340, 220);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        this.panel.add(scroll);
        
        
        this.salida.setBounds(0, 0, 710, 110);
        JScrollPane scroll2 = new JScrollPane(this.salida);
        scroll2.setBounds(40, 395, 710, 110);;
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        this.panel.add(scroll2);
    }
    
    private void visualizadorImagenes(){
        this.imagen.setBounds(0, 0, 340, 220);
        JScrollPane scroll = new JScrollPane(this.imagen);
        scroll.setBounds(405, 110, 340, 220);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        this.panel.add(scroll);
    }
    
    private void crearBotones(){
        JButton automata = new JButton("Crear Automatas");
        automata.setBounds(40, 350, 150, 20);
        this.panel.add(automata);
        
        automata.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                arboles = new ArrayList<>();
                String textoSalida = "";
                for(int i=0; i< ListaExpresiones.size(); i++){
                    List lista = (ArrayList) ListaExpresiones.get(i);
                    try{
                        CambioNotacion cambio = new CambioNotacion(((String) lista.get(1)),((String) lista.get(0)));
                        arboles.add(new ArrayList<>(){{add(String.valueOf(lista.get(0))); add(cambio);}});
                        textoSalida += "Automatas creados correctamente con la expresion "+String.valueOf(lista.get(0))+"\n";
                    }catch(Exception a){
                        System.out.println(a.toString());
                        textoSalida += "La expresion regular "+String.valueOf(lista.get(0))+" posee un error\n";
                    }
                }
                salida.setText(textoSalida);
            }
        });
        
        JButton analizarE = new JButton("Analizar Entradas");
        analizarE.setBounds(230, 350, 150, 20);
        this.panel.add(analizarE);
        
        analizarE.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                salida.setText("");
                JSONArray listaAceptados = new JSONArray();
                
                for (int i = 0; i < ListaVerificaciones.size(); i++){
                    List verificacion = (List) ListaVerificaciones.get(i);
                    String nombreArbol = String.valueOf(verificacion.get(0));
                    String frase = String.valueOf(verificacion.get(1));
                    
                    CambioNotacion arbol = null;
                    for (int j = 0; j < arboles.size(); j++){
                        List datosArbol = (List) arboles.get(j);
                        
                        if (nombreArbol.equals(String.valueOf(datosArbol.get(0)))){
                            arbol = (CambioNotacion) datosArbol.get(1);
                            break;
                        }
                    }
                    
                    if (arbol != null){
                        if  (arbol.tablaTransicion.recorrer(frase)){
                            salida.append(frase+" si cumple con la expresión de "+ nombreArbol+"\n");
                            JSONObject obj = new JSONObject();
                            obj.put("Valor", frase);
                            obj.put("ExpresionRegular", nombreArbol);
                            obj.put("Resultado", "Cadena Válida");
                            
                            listaAceptados.add(obj);
                        }else{
                            salida.append(frase+" no cumple con la expresión de "+ nombreArbol+"\n");
                        }
                    }
                }
                
                try {

                    String rutaActual = System.getProperty("user.dir");
                    FileWriter file = new FileWriter(rutaActual+"/Reportes_202003894/Salidas_202003894/"+nombreArchivo+".json");
                    file.write(listaAceptados.toJSONString());
                    file.flush();
                    file.close();

                } catch (IOException d) {
                    d.printStackTrace();

                }
            }
        });
        
        JButton analizar = new JButton("Analizar Archivo");
        analizar.setBounds(230, 80, 150, 20);
        this.panel.add(analizar);
        
        analizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ListaConjuntos = new ArrayList<List>();
                ListaErrores = new ArrayList<List>();
                ListaVerificaciones = new ArrayList<List>();
                ListaExpresiones = new ArrayList<List>();
                
                String directorioActual = System.getProperty("user.dir");
                AnalizadorLexico lexico = new AnalizadorLexico(new BufferedReader(new StringReader(entrada.getText())));
                parser sintactico = new parser(lexico);
                try {
                    sintactico.parse();

                    FileWriter archivo = new FileWriter(directorioActual+"/Reportes_202003894/Errores_202003894/"+nombreArchivo+".html"); 
                    archivo.write("<!DOCTYPE html>\n<head>\n<title>Errores</title>\n</head>\n<body>\n<table border='2'>\n<tr>\n<th>#</th>\n<th>Tipo de Error</th>\n<th>Descripcion</th>"
                            + "\n<th>Linea</th>\n<th>Columna</th>\n</tr>\n");
                    
                    int contador = 1;
                    for(int i = 0; i< ListaErrores.size(); i++){
                        List atErrores = (ArrayList) ListaErrores.get(i);
                        archivo.write("\n<tr>\n<td>"+contador+"</td>\n<td>"+atErrores.get(0)+"</td>\n<td>"+atErrores.get(1)+"</td>"
                            + "\n<td>"+atErrores.get(2)+"</td>\n<td>"+atErrores.get(3)+"</td>\n</tr>\n");
                        contador++;
                    }
                    
                    archivo.write("</table>\n</body>");
                    archivo.close();
                    salida.setText("RESULTADO DEL ANALISIS\n"+OCL1_Proyecto1.salida);
                    
                } catch (Exception ex) {
                    salida.setText("Error en el archivo de entrada");
                    ex.printStackTrace();
                }
            }
        });
        
        JButton verImagen = new JButton("Visualizar Imagen");
        verImagen.setBounds(500, 350, 150, 20);
        this.panel.add(verImagen);
        
        verImagen.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String rutaActual = System.getProperty("user.dir");
                JFileChooser abrirArchivo = new JFileChooser(rutaActual+"/Reportes_202003894");
                FileFilter filtro = new FileNameExtensionFilter("Archivos PNG (.png)", "PNG"); 
                abrirArchivo.setFileFilter(filtro);
                int seleccion = abrirArchivo.showOpenDialog(abrirArchivo);
                
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    String ruta = abrirArchivo.getSelectedFile().getAbsolutePath();
                    imagen.setIcon(new ImageIcon(ruta));
                }
            }
        });
    }
    
    private void generarDirectorios(){
        String directorioActual = System.getProperty("user.dir");
        File reportes = new File(directorioActual+"/Reportes_202003894");
        if (!reportes.exists()) {
            if (reportes.mkdirs()) {
                System.out.println("Directorio creado");
                File arboles = new File(directorioActual+"/Reportes_202003894/Arboles_202003894");
                if (!arboles.exists()) {
                    if (arboles.mkdirs()) {
                        System.out.println("Directorio creado");
                    } else {
                        System.out.println("Error al crear directorio");
                    }
                }

                File afnd = new File(directorioActual+"/Reportes_202003894/AFND_202003894");
                if (!afnd.exists()) {
                    if (afnd.mkdirs()) {
                        System.out.println("Directorio creado");
                    } else {
                        System.out.println("Error al crear directorio");
                    }
                }

                File afd = new File(directorioActual+"/Reportes_202003894/AFD_202003894");
                if (!afd.exists()) {
                    if (afd.mkdirs()) {
                        System.out.println("Directorio creado");
                    } else {
                        System.out.println("Error al crear directorio");
                    }
                }

                File siguientes = new File(directorioActual+"/Reportes_202003894/Siguientes_202003894");
                if (!siguientes.exists()) {
                    if (siguientes.mkdirs()) {
                        System.out.println("Directorio creado");
                    } else {
                        System.out.println("Error al crear directorio");
                    }
                }

                File transiciones = new File(directorioActual+"/Reportes_202003894/Transiciones_202003894");
                if (!transiciones.exists()) {
                    if (transiciones.mkdirs()) {
                        System.out.println("Directorio creado");
                    } else {
                        System.out.println("Error al crear directorio");
                    }
                }

                File errores = new File(directorioActual+"/Reportes_202003894/Errores_202003894");
                if (!errores.exists()) {
                    if (errores.mkdirs()) {
                        System.out.println("Directorio creado");
                    } else {
                        System.out.println("Error al crear directorio");
                    }
                }

                File salidas = new File(directorioActual+"/Reportes_202003894/Salidas_202003894");
                if (!salidas.exists()) {
                    if (salidas.mkdirs()) {
                        System.out.println("Directorio creado");
                    } else {
                        System.out.println("Error al crear directorio");
                    }
                }
            } else {
                System.out.println("Error al crear directorio");
            }
        }
    }
}
