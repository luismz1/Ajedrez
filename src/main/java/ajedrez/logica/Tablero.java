package ajedrez.logica;

// Librerias importadas
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tablero implements Serializable {
    // Atributos privados
    private List<ArrayList<Piece>> tablero;
    private List<Position> whitePositions;
    private List<Position> blackPositions;
    private List<String> capturadas;
    private Position peonCapturableAlPaso; 

    // Constructor
    public Tablero() {
        whitePositions = new ArrayList<>();
        blackPositions = new ArrayList<>();
        capturadas = new ArrayList<>();
        tablero = setTablero();
        peonCapturableAlPaso = null; 
    }

   // Set Tablero: Método encargado de inicializar el tablero con todas las piezas en su lugar.
    private List<ArrayList<Piece>> setTablero() {
        List<ArrayList<Piece>> board = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            board.add(new ArrayList<>());
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position piecePos = new Position(i, j);
                if (i < 2) {
                    if (i == 0 && (j == 0 || j == 7)) // Creación de torres del equipo negro.
                        board.get(i).add(PieceFactory.createPiece("T", "N", piecePos));
                    else if (i == 0 && (j == 1 || j == 6)) // Creación de caballos del equipo negro.
                        board.get(i).add(PieceFactory.createPiece("C", "N", piecePos));
                    else if (i == 0 && (j == 2 || j == 5)) // Creación de alfiles del equipo negro.
                        board.get(i).add(PieceFactory.createPiece("A", "N", piecePos));
                    else if (i == 0 && j == 3) // Creación de reinas del equipo negro.
                        board.get(i).add(PieceFactory.createPiece("Q", "N", piecePos));
                    else if (i == 0 && j == 4) // Creación de reyes del equipo negro.
                        board.get(i).add(PieceFactory.createPiece("K", "N", piecePos));
                    else // Creación de peones del equipo negro.
                        board.get(i).add(PieceFactory.createPiece("P", "N", piecePos));
                    blackPositions.add(piecePos); // Agregar posición a la lista de posiciones del equipo negro.
                } else if (i > 5) {
                    if (i == 6) // Creación de peones del equipo blanco.
                        board.get(i).add(PieceFactory.createPiece("P", "B", piecePos));
                    else if (i == 7 && (j == 0 || j == 7)) // Creación de torres del equipo blanco.
                        board.get(i).add(PieceFactory.createPiece("T", "B", piecePos));
                    else if (i == 7 && (j == 1 || j == 6)) // Creación de caballos del equipo blanco.
                        board.get(i).add(PieceFactory.createPiece("C", "B", piecePos));
                    else if (i == 7 && (j == 2 || j == 5)) // Creación de alfiles del equipo blanco.
                        board.get(i).add(PieceFactory.createPiece("A", "B", piecePos));
                    else if (i == 7 && j == 3) // Creación de reinas del equipo blanco.
                        board.get(i).add(PieceFactory.createPiece("Q", "B", piecePos));
                    else // Creación de reyes del equipo blanco.
                        board.get(i).add(PieceFactory.createPiece("K", "B", piecePos));
                    whitePositions.add(piecePos); // Agregar posición a la lista de posiciones del equipo blanco.
                } else {
                    board.get(i).add(null);
                }
            }
        }
        return board; // Se retorna el tablero una vez se hayan inicializado las piezas.
    }
    
    // Get Capturadas: Retorna las piezas capturadas en el tablero.
    public List<String> getCapturadas(){
        return capturadas;
    }
    
    // Get Piece: Método para obtener la pieza en una posición, retorna null si no hay pieza en la posición solicitada.
    public Piece getPiece(Position p) {
        return tablero.get(p.getRow()).get(p.getColumn());
    }
    
    // Set Piece: Método para establecer en una posición una pieza.
    public void setPiece(Position p, Piece piece) {
        tablero.get(p.getRow()).set(p.getColumn(), piece);
    }

    // Replace Position: Reemplaza en la lista de posiciones la posición anterior por la nueva posición según el equipo.
    public void replacePosition(String team, Position beforePos, Position afterPos) {
        if ("B".equals(team)) {
            whitePositions.remove(beforePos);
            whitePositions.add(afterPos);
        } else {
            blackPositions.remove(beforePos);
            blackPositions.add(afterPos);
        }
    }
    
    // Get Peon Capturable Al Paso: Retorna si un peón es capturable al paso.
    public Position getPeonCapturableAlPaso() {
        return peonCapturableAlPaso;
    }
    
    // Move Piece: Método para mover una pieza desde una posición a otra.
    public boolean movePiece(Position actualPos, Position nextPos) {
        // Se verifica que la posición sea válida.
        if (!validPosition(actualPos) || !validPosition(nextPos))
            throw new IllegalArgumentException("Position is not valid.");

        // Se obtiene la pieza de la posición actual.
        Piece piece = getPiece(actualPos);
        // Si no hay pieza en la posición solicitada entonces se lanza un error.
        if (piece == null)
            throw new IllegalArgumentException("No piece at actual position.");
        
        // Se obtiene la pieza de la posición siguiente.
        Piece nextPiece = getPiece(nextPos);
        // Se reemplaza la posición de la pieza actual con la de la posición siguiente.
        replacePosition(piece.getEquipo(), actualPos, nextPos);
        
        // Se le asigna la posición siguiente como posición actual de la pieza.
        piece.setPosition(nextPos);
        // Si la pieza obtenida no era null eso quiere decir que hay que eliminarla de la lista
        // de posiciones disponibles para el equipo contrario.
        if (nextPiece != null) {
            if ("B".equals(piece.getEquipo()))
                blackPositions.remove(nextPos);
            else
                whitePositions.remove(nextPos);
            // Se agrega a la lista de piezas capturadas.
            capturadas.add(nextPiece.toString());
        }
        // Se asigna null en la posición actual.
        setPiece(actualPos, null);
        // Se asigna la pieza en la posición siguiente.
        setPiece(nextPos, piece);

        // Manejo de captura al paso
        if (piece instanceof Peon) {
            int direction = "B".equals(piece.getEquipo()) ? -1 : 1;
            if (Math.abs(nextPos.getRow() - actualPos.getRow()) == 2) {
                peonCapturableAlPaso = new Position(actualPos.getRow() + direction, actualPos.getColumn());
            } else {
                // Verificar si es una captura al paso
                if (nextPos.equals(peonCapturableAlPaso)) {
                    Position posicionPeonCapturado = new Position(actualPos.getRow(), nextPos.getColumn());
                    Piece peonCapturado = getPiece(posicionPeonCapturado);
                    setPiece(posicionPeonCapturado, null);
                    if (peonCapturado != null) {
                        if ("B".equals(peonCapturado.getEquipo())) {
                            blackPositions.remove(posicionPeonCapturado);
                        } else {
                            whitePositions.remove(posicionPeonCapturado);
                        }
                        // Se agrega a la lista de piezas capturadas.
                        capturadas.add(peonCapturado.toString());
                    }
                }
                peonCapturableAlPaso = null;
            }
        } else {
            peonCapturableAlPaso = null;
        }
        
        // Verifica si el movimiento deja al rey en jaque
        if (jaque(piece.getEquipo())) {
            // Revertir el movimiento
            setPiece(actualPos, piece);
            setPiece(nextPos, nextPiece);
            piece.setPosition(actualPos);
            replacePosition(piece.getEquipo(), nextPos, actualPos);
            if (nextPiece != null) {
                replacePosition(nextPiece.getEquipo(), actualPos, nextPos);
            }
            return false;
        }

        return nextPiece instanceof Rey;
    }

    // Valid Castling: Método para revisar si un enroque es válido.
    public boolean validCastling(Piece king, Piece rook) {
        // Verifica que las piezas no se hayan movido anteriormente.
        if (king.moved || rook.moved)
            return false;
        
        // Verifica si actualmente el rey se encuentra en posición de jaque.
        if (jaque(king.getEquipo())){
            return false;
        }

        // Se obtiene la fila.
        int row = king.actualPos.getRow();
        
        int start = king.actualPos.getColumn(); // Se obtiene el inicio de la iteración.
        int stop = rook.actualPos.getColumn(); // Se obtiene el final de la iterción.
        int iterator = stop == 0 ? -1 : 1; // Se obtiene el iterador según la columna.
        
        // Se itera por cada uno de los campos entre el rey y la torre.
        for (int i = start + iterator; i < stop; i += iterator) {
            // Se recupera la posición iterada.
            Position p = new Position(row, i);
            // Si hay una pieza entre las casillas que se encuentran entre el rey y la torre entonces se
            // retorna falso para indicar que no se puede hacer el enroque.
            if (getPiece(p) != null)
                return false;
        }
        
        // Se obtiene la columna para el rey y la torre según el iterador.
        int kingColumn = iterator == -1 ? start - 2 : start + 2;
        int rookColumn = iterator == -1 ? stop + 3 : stop - 2;
        
        // Se asignan las nuevas posiciones de las piezas después del enroque.
        Position nextKingPos = new Position(row, kingColumn);
        Position nextRookPos = new Position(row, rookColumn);
        
        // Se simula el tablero con los cambios realizados para ambas piezas.
        Tablero simulatedBoard = simulateMove(king.actualPos, nextKingPos);
        simulatedBoard = simulatedBoard.simulateMove(rook.actualPos, nextRookPos);

        // En caso de que el enroque no genere jaque entonces se retorna verdadero, si genera jaque
        // retorna falso para indicar que no se puede hacer el enroque.
        return !simulatedBoard.jaque(king.getEquipo());
    }

    // Castle Move: Método para mover un rey y una torre en enroque. 
    public void castleMove(Position kingPos, Position rookPos, boolean towerAtStart) {
        // Se declaran las posiciones para la jugada de enroque a realizar.
        Position nextKingPos;
        Position nextRookPos;

        // Se obtienen las piezas en las posiciones solicitadas.
        Piece king = getPiece(kingPos);
        Piece rook = getPiece(rookPos);
        
        // Si la torre se encuentra al inicio de la fila se hace el cambio respectivo.
        if (towerAtStart) {
            nextKingPos = new Position(kingPos.getRow(), kingPos.getColumn() - 2);
            nextRookPos = new Position(rookPos.getRow(), rookPos.getColumn() + 3);
        } else { // En caso contrario, se hará lo mismo cuando la torre este al final de la fila.
            nextKingPos = new Position(kingPos.getRow(), kingPos.getColumn() + 2);
            nextRookPos = new Position(rookPos.getRow(), rookPos.getColumn() - 2);
        }

        // Se reemplaza en la lista de posiciones del mismo equipo con la posición del rey.
        replacePosition(king.getEquipo(), kingPos, nextKingPos);
        king.setPosition(nextKingPos);
        king.setMoved();

        // Se reemplaza en la lista de posiciones del mismo equipo pero con la posición de la torre.
        replacePosition(rook.getEquipo(), rookPos, nextRookPos);
        rook.setPosition(nextRookPos);
        rook.setMoved();
        
        // Se asigna null en la posición actual del rey.
        setPiece(kingPos, null);
        // Se asigna la pieza del rey en la posición siguiente.
        setPiece(nextKingPos, king);

        // Se asigna null en la posición actual de la torre. 
        setPiece(rookPos, null);
        // Se asigna la pieza de la torre en la posición siguiente.
        setPiece(nextRookPos, rook);
    }
    
    // Promotion Move: Se encarga de promover un peón y moverlo a la casilla selecccionada.
    public void promotionMove(Position actualPos, Position nextPos, String team, String type) {
        // Se crea la pieza con su tipo, su equipo y se le asigna la posición siguiente como posición actual.
        Piece promotedPiece = PieceFactory.createPiece(type, team, nextPos);
        Piece nextPiece = getPiece(nextPos);
            
        // Se asigna en la posición de la pieza actual nulo.
        setPiece(actualPos, null);
        
        // Si el equipo es blanco entonces se reemplaza la posición actual en la lista de posiciones ocupadas
        // por el equipo blanco.
        if (team.equals("B")) {
                replacePosition("B", actualPos, nextPos);
            // En caso de que el equipo sea negro, se hará lo mismo solo que en la lista de posiciones ocupadas
            // por el equipo negro.
            } else { 
                replacePosition("N", actualPos, nextPos);
            }

            // Se asigna en la posición siguiente la pieza promovida.
            setPiece(nextPos, promotedPiece);

        // Si la pieza obtenida no era null eso quiere decir que hay que eliminarla de la lista
        // de posiciones disponibles para el equipo contrario.
        if (nextPiece != null) {
            if ("B".equals(promotedPiece.getEquipo())){
                blackPositions.remove(nextPos);
            }else{
                whitePositions.remove(nextPos);
            }
            // Se agrega a la lista de piezas capturadas.
            capturadas.add(nextPiece.toString());
        }
        
        }

    // Valid Move: Método para verificar si un movimiento es válido.
    public boolean validMove(Position actualPos, Position nextPos) {
        // Si la posición actual es null entonces se retorna falso para indicar que el movimiento no es válido.
        if (actualPos == null) {
            return false;
        } else { // En caso contrario, se procederá a revisar si el movimiento es válido.
            // Se obtiene la pieza en la posición actual solicitada.
            Piece piece = getPiece(actualPos);
            // Si la pieza corresponde a null se retorna falso para indicar que el movimiento no es válido.
            if (piece == null) {
                return false;
            } else { // En caso contrario, se revisará en la lista de movimientos relacionadas a la pieza.
                // Se obtiene la lista de posiciones posibles para la pieza.
                List<Position> positions = piece.getMoves(this);
                // Si la posición siguiente está contenido dentro de la lista de posibles movimientos de la pieza
                // entonces se verificará otro paso más para verificar que el movimiento sea válido.
                if (positions.contains(nextPos)) {
                    // Se obtiene el tablero simulando el movimiento realizado.
                    Tablero simulatedBoard = simulateMove(actualPos, nextPos);
                    // En caso de que el movimiento sea realizado cuando el rey del equipo actual
                    // se encuentra en jaque entonces se retorna verdadero para indicar que el movimiento es válido.
                    if (!simulatedBoard.jaque(piece.getEquipo())) {
                        return true;
                    }
                }
                return false;
            }
        }
    }
    
    // Valid Position: Método para verificar si una posición cumple con los estandares dentro del tablero.
    public boolean validPosition(Position p) {
        return p.getRow() >= 0 && p.getRow() <= 7 && p.getColumn() >= 0 && p.getColumn() <= 7;
    }

    // Same Team: Verifica si una pieza en una posición es del mismo equipo que el equipo solicitado.
    public boolean sameTeam(Position p, String team) {
        // Se obtiene la pieza en la posición.
        Piece piece = getPiece(p);
        // Si la pieza actual es null entonces se retorna falso.
        if (piece == null) {
            return false;
        }
        // En caso contrario, se retornará el resultado de si el equipo de la pieza concuerda con el equipo solicitado.
        return piece.getEquipo().equals(team);
    }

    // Print Tablero: Método para imprimir el tablero en terminal.
    public void printTablero() {
        for (int i = 0; i < 8; i++) {
            System.out.println(tablero.get(i));
        }
        System.out.println("");
    }
    
    // Jaque: Verifica si hay jaque para un equipo solicitado.
    public boolean jaque(String team) {
        // Se declara la posición del rey.
        Position kingPosition = null;
        // Se obtiene la lista de posiciones enemigas.
        List<Position> enemyPositions = team.equals("B") ? blackPositions : whitePositions;

       // Se itera por cada una de las posiciones del mismo equipo hasta obtener la pieza del rey.
        for (Position pos : team.equals("B") ? whitePositions : blackPositions) {
            // Se obtiene la peiza del equipo solicitado.
            Piece piece = getPiece(pos);
            // Si la pieza es una instancia de Rey entonces se guarda la posición encontrada
            // en la posición del rey y se rompe el ciclo.
            if (piece instanceof Rey) {
                kingPosition = pos;
                break;
            }
        }
        
        // Si no se puede encontrar al rey entonces se generará un error.
        if (kingPosition == null) {
            throw new IllegalStateException("No king found for team " + team);
        }
        
        // Se itera por cada una de las posiciones enemigas para verificar si dentro de las jugadas
        // de los enemigos está la del rey.
        for (Position pos : enemyPositions) {
            // Se obtiene la pieza del enemigo.
            Piece enemyPiece = getPiece(pos);
            // Siempre que la pieza enemiga no represente null entonces se van a verificar sus movimientos.
            if (enemyPiece != null) {
                // Se obtiene la lista de movimientos para la pieza enemiga.
                List<Position> enemyMoves = enemyPiece.getMoves(this);
                // Si dentro de las posiciones enemigas está la del rey del equipo solicitado entonces
                // se retorna verdadero indicando que hay un jaque.
                if (enemyMoves.contains(kingPosition)) {
                    return true;
                }
            }
        }
        // En caso contrario, se retorna false para indicar que no existe jaque.
        return false;
    }

    public boolean jaqueMate(String team) {
        if (!jaque(team)) {
            return false;
        }

        List<Position> teamPositions = team.equals("B") ? whitePositions : blackPositions;

        for (Position pos : teamPositions) {
            Piece piece = getPiece(pos);
            if (piece != null) {
                List<Position> moves = piece.getMoves(this);
                for (Position move : moves) {
                    Tablero simulatedBoard = simulateMove(pos, move);
                    if (!simulatedBoard.jaque(team)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    // Simultate move: Simula un movimiento en un tablero.
    private Tablero simulateMove(Position actualPos, Position nextPos) {
        // Clona el tablero.
        Tablero simulatedBoard = cloneBoard();
        
        // Obtiene las piezas en las posiciones solicitadas.
        Piece piece = simulatedBoard.getPiece(actualPos);
        Piece nextPiece = simulatedBoard.getPiece(nextPos);
        
        // Borra de la lista de posiciones según el equipo.
        if ("B".equals(piece.getEquipo())) {
            simulatedBoard.whitePositions.remove(actualPos);
            simulatedBoard.whitePositions.add(nextPos);
        } else {
            simulatedBoard.blackPositions.remove(actualPos);
            simulatedBoard.blackPositions.add(nextPos);
        }
        
        // Asigna a la posición actual de la pieza la posición siguiente.
        piece.setPosition(nextPos);
        // Se asigna null en la posición actual.
        simulatedBoard.tablero.get(actualPos.getRow()).set(actualPos.getColumn(), null);
        // Se asigna la pieza en la posición siguiente.
        simulatedBoard.tablero.get(nextPos.getRow()).set(nextPos.getColumn(), piece);

        // Si la pieza obtenida no era null eso quiere decir que hay que eliminarla de la lista
        // de posiciones disponibles para el equipo contrario.
        if (nextPiece != null) {
            if ("B".equals(piece.getEquipo())) {
                simulatedBoard.blackPositions.remove(nextPos);
            } else {
                simulatedBoard.whitePositions.remove(nextPos);
            }
        }

        // Manejar captura al paso en el tablero simulado
        if (piece instanceof Peon) {
            int direction = piece.getEquipo().equals("B") ? -1 : 1;
            if (simulatedBoard.peonCapturableAlPaso != null && nextPos.equals(simulatedBoard.peonCapturableAlPaso)) {
                Position posicionPeonCapturado = new Position(nextPos.getRow() - direction, nextPos.getColumn());
                simulatedBoard.setPiece(posicionPeonCapturado, null);
                if ("B".equals(piece.getEquipo())) {
                    simulatedBoard.blackPositions.remove(posicionPeonCapturado);
                } else {
                    simulatedBoard.whitePositions.remove(posicionPeonCapturado);
                }
            }
        }

        return simulatedBoard; // Se retorna el tablero con la jugada realizada.
    }

    
    // Clone Board: Clona un tablero para simular jugadas.
    private Tablero cloneBoard() {
        Tablero newBoard = new Tablero();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position pos = new Position(i, j);
                Piece piece = this.getPiece(pos);
                if (piece != null) {
                    newBoard.tablero.get(i).set(j, PieceFactory.createPiece(piece.getType(), piece.getEquipo(), pos));
                } else {
                    newBoard.tablero.get(i).set(j, null);
                }
            }
        }

        newBoard.whitePositions = new ArrayList<>(this.whitePositions);
        newBoard.blackPositions = new ArrayList<>(this.blackPositions);
        newBoard.peonCapturableAlPaso = this.peonCapturableAlPaso; 

        return newBoard;
    }
}
