package ajedrez.logica;

// Librerias importadas
import java.util.ArrayList;
import java.util.List;

public class Caballo extends Piece{
    // Constructor
    public Caballo(Team equipo, Position pos){
        super(equipo, pos);
    }
    
    @Override
    // Get Moves: Recibe un tablero y obtiene la lista de posiciones donde puede moverse un caballo.
    public List<Position> getMoves(Tablero t){
        List<Position> posiciones = new ArrayList<>();
        Position nextPosition;
        
        // Movimiento en "L".
        for(int r= actualPos.getRow() - 2; r <= actualPos.getRow() + 2; r++){
            if (r != actualPos.getRow()){
                int moveValue = r == actualPos.getRow() -2 || r == actualPos.getRow() +2? 1:2;
                for (int i = -1; i <= 1;i+=2){
                    nextPosition = new Position(r, actualPos.getColumn() + moveValue * i);
                    //System.out.println("actual position: " + nextPosition);
                    if (t.validPosition(nextPosition)){ // Posición válida.
                        if (t.getPiece(nextPosition) == null) // Espacio vacío.
                            posiciones.add(nextPosition);
                        else if(validCapture(t, nextPosition)){ // Posición es pieza enemiga.
                            posiciones.add(nextPosition);
                        }
                    }                       
                }
            }
        }
     return posiciones;
    }

    @Override
    // Get Path: Retorna la dirección de imagen de la pieza.
    public String getPath(){
        if (equipo.toString().equals("B"))
            return System.getProperty("user.dir") + "\\src\\main\\java\\ajedrez\\interfaz\\caballo_blanco.png";
        else
            return System.getProperty("user.dir") + "\\src\\main\\java\\ajedrez\\interfaz\\caballo_negro.png";
    }

    @Override
    // Get Type: Retorna el tipo de la pieza.
    public String getType(){
        return "C";
    }
    
   @Override
   // To String: Método para obtener el tipo y el equipo de la pieza.
   public String toString(){
       if (equipo.name().equals("BLANCO"))
           return "CB";
       else
           return "CN";
   }    
}
