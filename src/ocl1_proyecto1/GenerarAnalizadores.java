
package ocl1_proyecto1;

public class GenerarAnalizadores {
    public static void main(String []args){
        try{
            String  opcFlex[] = {"src/ocl1_proyecto1/Lexico.jflex","-d","src/ocl1_proyecto1"};
            JFlex.Main.generate(opcFlex);
            String opcCup [] ={"-destdir","src/ocl1_proyecto1","-parser","parser","src/ocl1_proyecto1/Sintactico.cup"};
            java_cup.Main.main(opcCup);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
