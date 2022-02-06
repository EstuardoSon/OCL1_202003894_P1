package ocl1_proyecto1;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Ventana extends JFrame{
    
    private JPanel panel = new JPanel();
    private JTextArea entrada = new JTextArea();
    private JTextArea salida = new JTextArea();
    
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
        
        JMenuItem guardar = new JMenuItem();
        guardar.setText("Guardar Archivo");
        guardar.addActionListener(new ActionListener (){

            @Override
            public void actionPerformed(ActionEvent e) {
                //Instrucciones para cargar Archivo
            }
        });
        menu.add(guardar);
        
        JMenuItem guardarC = new JMenuItem();
        guardarC.setText("Guardar Como...");
        guardarC.addActionListener(new ActionListener (){

            @Override
            public void actionPerformed(ActionEvent e) {
                //Instrucciones para cargar Archivo
            }
        });
        menu.add(guardarC);
        
        JMenuItem generarXML = new JMenuItem();
        generarXML.setText("Generar XML");
        generarXML.addActionListener(new ActionListener (){

            @Override
            public void actionPerformed(ActionEvent e) {
                //Instrucciones para cargar Archivo
            }
        });
        menu.add(generarXML);
        barraMenu.add(menu);
        panel.add(barraMenu);
    }
    
    private void crearLabels(){
        JLabel texto1 = new JLabel("Archivo de Entrada");
        texto1.setBounds(40, 90, 200, 20);
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
    
    private void crearBotones(){
        JButton automata = new JButton("Crear Automatas");
        automata.setBounds(40, 350, 150, 20);
        this.panel.add(automata);
        
        JButton analizar = new JButton("Analizar Entradas");
        analizar.setBounds(230, 350, 150, 20);
        this.panel.add(analizar);
        
        analizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                AnalizadorLexico lexico = new AnalizadorLexico(new BufferedReader(new StringReader(entrada.getText())));
                parser sintactico = new parser(lexico);
                try {
                    sintactico.parse();
                    for(int i = 0; i< sintactico.action_obj.listaConjuntos.size(); i++){
                        List atConjunto = (ArrayList) sintactico.action_obj.listaConjuntos.get(i);
                        System.out.println(atConjunto.get(0));
                    }
                    for(int i = 0; i< lexico.errores.size(); i++){
                        System.out.println(lexico.errores.get(i));
                    }
                    for(int i = 0; i< sintactico.errores.size(); i++){
                        System.out.println(sintactico.errores.get(i));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
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
                if (!arboles.exists()) {
                    if (arboles.mkdirs()) {
                        System.out.println("Directorio creado");
                    } else {
                        System.out.println("Error al crear directorio");
                    }
                }

                File afd = new File(directorioActual+"/Reportes_202003894/AFD_202003894");
                if (!arboles.exists()) {
                    if (arboles.mkdirs()) {
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
