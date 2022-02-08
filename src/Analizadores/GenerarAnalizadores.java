
package Analizadores;

public class GenerarAnalizadores {
    public static void main(String []args){
        try{
            String  opcFlex[] = {"src/Analizadores/Lexico.jflex","-d","src/Analizadores"};
            JFlex.Main.generate(opcFlex);
            String opcCup [] ={"-destdir","src/Analizadores","-parser","parser","src/Analizadores/Sintactico.cup"};
            java_cup.Main.main(opcCup);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
