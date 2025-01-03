package ajedrez.logica;

// Librerias importadas
import java.util.ArrayList;
import java.util.List;

public class Alfil extends Piece{
    // Constructor
    public Alfil(Team equipo, Position pos){
        super(equipo, pos);
    }
    @Override
    // Get Moves: Recibe un tablero y obtiene la lista de posiciones donde puede moverse un alfil.
    public List<Position> getMoves(Tablero t){
            List<Position> posiciones = new ArrayList<>();
            Position nextPosition;
            // Diagonal izquierda
            for (int r = actualPos.getRow() - 1, c = actualPos.getColumn() - 1; r >= 0; r--, c--){
                    nextPosition = new Position(r , c);
                    //System.out.println(nextPosition);
                    if (t.validPosition(nextPosition)){
                        if (t.getPiece(nextPosition) == null)
                            posiciones.add(nextPosition);
                        else if(validCapture(t, nextPosition)){
                            posiciones.add(nextPosition);
                            break;
                        }else{
                            break;
                        }
                    }else{
                        break;
                    }
            }
            
            // Diagonal derecha:
            for (int r = actualPos.getRow() - 1, c = actualPos.getColumn() + 1; c < 8; r--, c++){
                    nextPosition = new Position(r , c);
                    //System.out.println(nextPosition);
                    if (t.validPosition(nextPosition)){
                        if (t.getPiece(nextPosition) == null)
                            posiciones.add(nextPosition);
                        else if(validCapture(t, nextPosition)){
                            posiciones.add(nextPosition);
                            break;
                        }else{
                            break;
                        }
                    }else{
                        break;
                    }
            }

            // Diagonal izquierda inversa.
            for (int r = actualPos.getRow() + 1, c = actualPos.getColumn() - 1; r < 8; r++, c--){
                    nextPosition = new Position(r , c);
                    //System.out.println(nextPosition);
                    if (t.validPosition(nextPosition)){
                        if (t.getPiece(nextPosition) == null)
                            posiciones.add(nextPosition);
                        else if(validCapture(t, nextPosition)){
                            posiciones.add(nextPosition);
                            break;
                        }else{
                            break;
                        }
                    }else{
                        break;
                    }
            }
            
             // Diagonal derecha inversa.
            for (int r = actualPos.getRow() + 1, c = actualPos.getColumn() + 1; c < 8; r++, c++){
                    nextPosition = new Position(r , c);
                    //System.out.println(nextPosition);
                    if (t.validPosition(nextPosition)){
                        if (t.getPiece(nextPosition) == null)
                            posiciones.add(nextPosition);
                        else if(validCapture(t, nextPosition)){
                            posiciones.add(nextPosition);
                            break;
                        }else{
                            break;
                        }
                    }else{
                        break;
                    }
            }
                    
        return posiciones;
    }
        
    @Override
    // Get Path: Retorna la dirección de imagen de la pieza.
    public String getPath(){
        if (equipo.toString().equals("B"))
            return System.getProperty("user.dir") + "\\src\\main\\java\\ajedrez\\interfaz\\alfil_blanco.png";
        else
            return System.getProperty("user.dir") + "\\src\\main\\java\\ajedrez\\interfaz\\alfil_negro.png";
    }

    @Override
    // Get Type: Retorna el tipo de la pieza.
    public String getType(){
        return "A";
    }    

   @Override
   // To String: Método para obtener el tipo y el equipo de la pieza.
   public String toString(){
       if (equipo.name().equals("BLANCO"))
           return "AB";
       else
           return "AN";
   }    
}
