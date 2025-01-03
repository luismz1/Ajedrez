package ajedrez.logica;

// Librerias importadas
import java.io.Serializable;
import java.util.List;

public abstract class Piece implements Serializable {
    // Atributos protegidos,
    protected Team equipo;
    protected Position actualPos;
    protected boolean moved;
    
    // Constructor
    public Piece(Team equipo, Position position) {
        this.equipo = equipo;
        actualPos = position;
        moved = false;
    }
        
    // Get Equipo: Método para retornar el equipo de la pieza.
    public String getEquipo() {
        return equipo.toString();
    }
    
    // Set Moved: Vuelve el estado de la pieza a movido.
    public void setMoved(){
        moved = true;
    }
    
    // Set Position: Cambia la posición actual de una pieza.
    public void setPosition(Position position) {
        actualPos = position;
    }
    
    // Valid Capture: Verifica si una captura es válida.
    public boolean validCapture(Tablero t, Position p) {
        // La posición enemiga debe cumplir con los estandares del tablero.
        if (t.validPosition(p)) {
            //  La posición enemiga no puede ser de una pieza nula.
            if (t.getPiece(p) != null) {
                // La posición enemiga no puede ser del mismo equipo que el de la pieza.
                if (!t.getPiece(actualPos).getEquipo().equals(t.getPiece(p).getEquipo()))
                    return true;
            }
        }
        return false;
    }
    
    // Métodos abstractos.
    
    // Get Moves: Recibe un tablero y obtiene la lista de posiciones donde puede moverse una pieza.
    public abstract List<Position> getMoves(Tablero t);
    // Get Path: Retorna la dirección de imagen de la pieza.
    public abstract String getPath();
    // Get Type: Retorna el tipo de la pieza.
    public abstract String getType();
    
}
