
package ajedrez.control;

// Librerias importadas
import java.util.ArrayList;
import java.util.List;

public class DataVerificator {
    // Int to Letter: Recibe como parámetro un número y lo transforma a la letra correspondiente.
   public static  String IntToLetter(int letterValue){
       return switch(letterValue){
           case 0->"A";
           case 1->"B";
           case 2->"C";
           case 3->"D";
           case 4->"E";
           case 5->"F";
           case 6->"G";
           default->"H";
       };
   }
   
   // Box Position Values: Recibe una casilla y retorna una lista de enteros con las posiciones correspondientes a la casilla.
   public static List<Integer> boxPositionValues(String valueBox){
       ArrayList<Integer> values = new ArrayList<>();
       values.add(8 - Integer.parseInt(valueBox.substring(1, 2)));
       values.add(DataVerificator.LetterToInt(valueBox.substring(0, 1)));
       return values;
   }
   
   // Letter To Int: Recibe como parámetro una letra y la transforma al número correspondiente.
   public static int LetterToInt(String letterValue){
        return switch(letterValue){
           case "A"->0;
           case "B"->1;
           case "C"->2;
           case "D"->3;
           case "E"->4;
           case "F"->5;
           case "G"->6;
           default->7;
       };      
   }  
}
