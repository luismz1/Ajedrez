package ajedrez.logica;

// Librerias importadas
import java.util.ArrayList;
import java.util.List;

public class Peon extends Piece {
    // Constructor
    public Peon(Team equipo, Position pos) {
        super(equipo, pos);
    }

    @Override
    // Get Moves: Recibe un tablero y obtiene la lista de posiciones donde puede moverse un peón.
    public List<Position> getMoves(Tablero t) {
        Piece actualPiece;
        Position nextPosition;

        List<Position> posiciones = new ArrayList<>();
        int doubleMove = equipo.toString().equals("B") ? -2 : 2;
        int startRow = equipo.toString().equals("B") ? 6 : 1;
        
        // Movimiento de inicio.
        nextPosition = new Position(actualPos.getRow() + doubleMove, actualPos.getColumn());
        if (t.validPosition(nextPosition)) {
            actualPiece = t.getPiece(nextPosition);
            if (actualPos.getRow() == startRow && actualPiece == null)
                posiciones.add(nextPosition);
        }

        int generalMove = equipo.toString().equals("B") ? -1 : 1;
        int deniedRow = equipo.toString().equals("B") ? 0 : 7;

        // Diagonal izquierda.
        nextPosition = new Position(actualPos.getRow() + generalMove, actualPos.getColumn() - 1);
        if (validCapture(t, nextPosition)) {
            if (actualPos.getRow() != deniedRow && actualPos.getColumn() != 0)
                posiciones.add(nextPosition);
        }

        // Diagonal derecha.
        nextPosition = new Position(actualPos.getRow() + generalMove, actualPos.getColumn() + 1);
        if (validCapture(t, nextPosition)) {
            if (actualPos.getRow() != deniedRow && actualPos.getColumn() != 7)
                posiciones.add(nextPosition);
        }

        // Moverse una casilla.
        nextPosition = new Position(actualPos.getRow() + generalMove, actualPos.getColumn());
        if (t.validPosition(nextPosition)) {
            actualPiece = t.getPiece(nextPosition);
            if (actualPos.getRow() != deniedRow && actualPiece == null)
                posiciones.add(nextPosition);
        }

        // Captura al paso
        Position peonCapturableAlPaso = t.getPeonCapturableAlPaso();
        if (peonCapturableAlPaso != null) {
            if (Math.abs(peonCapturableAlPaso.getColumn() - actualPos.getColumn()) == 1 && peonCapturableAlPaso.getRow() == actualPos.getRow() + generalMove) {
                posiciones.add(peonCapturableAlPaso);
            }
        }

        return posiciones;
    }

    @Override
    // Get Path: Retorna la dirección de imagen de la pieza.
    public String getPath() {
        if (equipo.toString().equals("B"))
            return System.getProperty("user.dir") + "\\src\\main\\java\\ajedrez\\interfaz\\peon_blanco.png";
        else
            return System.getProperty("user.dir") + "\\src\\main\\java\\ajedrez\\interfaz\\peon_negro.png";
    }

    @Override
    // Get Type: Retorna el tipo de la pieza.
    public String getType() {
        return "P";
    }

    @Override
    // To String: Método para obtener el tipo y el equipo de la pieza.
    public String toString() {
        if (equipo.name().equals("BLANCO"))
            return "PB";
        else
            return "PN";
    }
}
