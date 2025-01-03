package ajedrez.logica;

// Librerias Importadas
import ajedrez.control.Control;
import java.util.ArrayList;
import java.util.List;

public class Rey extends Piece{
    // Constructor
    public Rey(Team equipo, Position pos){
        super(equipo, pos);
    }
    
    @Override
     // Get Moves: Recibe un tablero y obtiene la lista de posiciones donde puede moverse un rey.
    public List<Position> getMoves(Tablero t){
        List<Position> posiciones = new ArrayList<>();
        Position nextPosition;
        
        // Moverse en los alrededores.
        for (int r = actualPos.getRow() - 1; r <= actualPos.getRow() + 1; r++){
            for(int c = actualPos.getColumn() - 1; c <= actualPos.getColumn() + 1; c++){
                nextPosition = new Position(r, c);
                if (t.validPosition(nextPosition)){
                    if (validCapture(t, nextPosition) || t.getPiece(nextPosition) == null)
                        posiciones.add(nextPosition);
                }
            }
        }            
        return posiciones;
    }
    
    @Override
     // Get Path: Retorna la dirección de imagen de la pieza.
    public String getPath(){
        if (equipo.toString().equals("B"))
            return System.getProperty("user.dir") + "\\src\\main\\java\\ajedrez\\interfaz\\rey_blanco.png";
        else
            return System.getProperty("user.dir") + "\\src\\main\\java\\ajedrez\\interfaz\\rey_negro.png";
    }

    @Override
    // Get Type: Retorna el tipo de la pieza.
    public String getType(){
        return "K";
    }    
    
   @Override
   // To String: Método para obtener el tipo y el equipo de la pieza.
   public String toString(){
       if (equipo.name().equals("BLANCO"))
           return "KB";
       else
           return "KN";
   }    
}
