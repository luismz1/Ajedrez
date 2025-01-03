package ajedrez.control;

// Librerias importadas
import ajedrez.logica.Peon;
import ajedrez.logica.Piece;
import ajedrez.logica.Position;
import ajedrez.logica.Rey;
import ajedrez.logica.Tablero;
import ajedrez.logica.Torre;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Control implements Serializable {
    // Atributos privados
    private Position actualPosition;
    private String jugador1;
    private String jugador2;
    private static Control instance = null;
    private String turnoActual;
    private Tablero board;
    
    // Constructor
    private Control(){
        board = new Tablero();
        actualPosition = null;
        turnoActual = "B";
    }
    
    // Change Team: Cambia al equipo contrario para el siguiente turno.
   private void changeTeam(){
       if (turnoActual.equals("B")){
           turnoActual = "N";
       }else{
           turnoActual = "B";
       }
   }
   
    // Get Instance: Obtiene una nueva instancia de control.
    public static Control getInstance(){
        instance = new Control();
        return instance;
    }
    
    // Get Capturadas: Obtiene las piezas capturadas hasta el momento.
    public List<String> getCapturadas(){
        return board.getCapturadas();
    }
          
   // Reiniciar Juego: Reinicia el tablero y los atributos privados de la clase.
    public void reiniciarJuego(){
        board = new Tablero();
        jugador1 = null;
        jugador2 = null;
        actualPosition = null;
        turnoActual = "B";
    }
        
   // Obtener Position: Dada una tupla de coordenadas verifica que la posición sea válida y en caso de serlo retorna un objeto
    // de tipo Position
    public Position obtenerPosition(int x, int y){
        Position pos = new Position(x, y);
        if (!board.validPosition(pos))
            throw new IllegalArgumentException("Error: Position doesn't exist in board.");
        return pos;
        }
   
    // Jugador Juega: Recibe la casilla del próximo movimiento, verifica el tipo de movimiento o si la jugada no es válida
    // para el jugador actual.
   public int jugadorJuega(String positionBox){
       List<Integer> coords = (ArrayList) DataVerificator.boxPositionValues(positionBox);
       
       Position nextPos = obtenerPosition(coords.get(0), coords.get(1));
       // Si la pieza es del mismo equipo que el equipo que tiene derecho a hacer la actual jugada
       // entonces se asigna como posición actual y se retorna un cero.
        if (board.sameTeam(nextPos, turnoActual)){
            if (actualPosition == null){
                actualPosition = nextPos;
                return 0;
            }else{
                Piece firstPiece = board.getPiece(actualPosition);
                Piece secondPiece = board.getPiece(nextPos);
                boolean castlingIdentified = (firstPiece instanceof Rey) && (secondPiece instanceof Torre) ||
                        (secondPiece instanceof Rey) && (firstPiece instanceof Torre);
                if (castlingIdentified){
                    boolean castlingDecision = firstPiece instanceof Rey?
                            board.validCastling(firstPiece,secondPiece):
                            board.validCastling(secondPiece, firstPiece);
                    return castlingDecision? 1:-1;
                }else{
                    actualPosition = nextPos;
                    return 0;
                }                
            }
        // En caso de que la jugada sea válida entonces se retorna un uno.
        }else if (board.validMove(actualPosition, nextPos)){
            Piece piece = board.getPiece(actualPosition);
            if (piece instanceof Peon && (nextPos.getRow() == 0 || nextPos.getRow() == 7))
                return 2;
            else
                return 3;
        // Si no se cumplen ninguno de los dos casos mencionados entonces se retorna un menos uno
        // indicando que no se puede efectuar la jugada.
        }else{
            return -1;
        }
    }
    
   // Get Actual Position Box: Obtener la casilla del tablero en base a la posición actual.
   public String getActualPositionBox(){
       String positionBox = "";
       if (actualPosition != null){
            positionBox += DataVerificator.IntToLetter(actualPosition.getColumn());
            positionBox += 8 - actualPosition.getRow();
       }
       return positionBox;
   }
   
   // Path Constructor: Recibe las coordenadas de una posición y determina cual es su dirección de imagen.
   public String pathConstructor(int x, int y){
       Piece piece = board.getPiece(obtenerPosition(x, y));
       if (piece == null){
           return null;
       }else
           return piece.getPath();
    }
   
   // Castle Play: Efectua un movimiento de enroque, recibe como parámetro la casilla del rey, la casilla de la torre
   // y si la torre se encuentra al inicio o al final de la fila.
    public void castlePlay(String kingBox, String rookBox, boolean towerAtStart){
           List<Integer> kingCoords = (ArrayList) DataVerificator.boxPositionValues(kingBox);
           List<Integer> rookCoords = (ArrayList) DataVerificator.boxPositionValues(rookBox);

           Position kingPos = obtenerPosition(kingCoords.get(0), kingCoords.get(1));
           Position rookPos = obtenerPosition(rookCoords.get(0), rookCoords.get(1));
           actualPosition = null;

           board.castleMove(kingPos, rookPos, towerAtStart);
           changeTeam();
    }
    
    // Promote Play: Efectua un movimiento de promoción, recibe como parámetro la siguiente casilla
    // y el tipo de la pieza a la cual será promovida, retorna verdadero si la jugada resulta en Jaque Mate.
    public boolean promotePlay(String positionBox, String type){
        List<Integer> coords = (ArrayList) DataVerificator.boxPositionValues(positionBox);

        String pieceInfo = board.getPiece(actualPosition).toString();
        String actualBox = getActualPositionBox();
        Position nextPos = obtenerPosition(coords.get(0), coords.get(1));
        board.promotionMove(actualPosition, nextPos, turnoActual, type);
        actualPosition = null;

        // Se verifica si hay jaque mate
        String opposingTeam = turnoActual.equals("B")? "N"  :"B";
        if (board.jaqueMate(opposingTeam)){
            return true; // Termina el juego si hay jaque mate
        }

        changeTeam();
        return false;
    }
   
    // Promote Play: Efectua un movimiento normal, recibe como parámetro la siguiente casilla,
    // retorna verdadero si la jugada resulta en Jaque Mate.
    public boolean changePlayer(String positionBox){
        List<Integer> coords = (ArrayList) DataVerificator.boxPositionValues(positionBox);
        
        String pieceInfo = board.getPiece(actualPosition).toString();
        String actualBox = getActualPositionBox();
        Position nextPos = obtenerPosition(coords.get(0), coords.get(1));
        board.movePiece(actualPosition, nextPos);
        actualPosition = null;
       
        
        // Se verifica si hay jaque mate
        String opposingTeam = turnoActual.equals("B") ? "N" : "B";
        if (board.jaqueMate(opposingTeam)) {
            return true; // Termina el juego si hay jaque mate
        }

        changeTeam();
        return false;
    }
   
    // Get Equipo Actual: Retorna el equipo actual.
   public String getEquipoActual(){
       return turnoActual.equals("B")? "blanco":"negro";
   }
   
   // Get Jugador Actual: Retorna el jugador actual según el equipo al cual le pertenezca el turno.
   public String getJugadorActual(){
       return turnoActual.equals("B")? jugador1:jugador2;
   }
   
   // Get Jugador Opuesto: Retorna el jugador opuesto según el equipo actual al cual le perteneza el turno.
   public String getJugadorOpuesto(){
       return turnoActual.equals("B")? jugador2:jugador1;
   }
   
   // Save Player Names: Recibe como parámetro el nombre de ambos jugadores y los guarda.
   public void savePlayerNames(String jugador1, String jugador2){
       this.jugador1 = jugador1;
       this.jugador2 = jugador2;
   }
   
   // Player Set: Retorna verdadero en caso de que los nombres de ambos jugadores se encuentren disponibles.
   public boolean playersSet(){
       return jugador1 != null && jugador2 != null;
   }
       
    // Guardar Datos: Método para guardar los datos de una partida, recibe como parámetro un control y la dirección
   // donde se guarda la información.
    public static boolean guardarDatos(Control control, String path){
        try{
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream stream = new ObjectOutputStream(file);
            stream.writeObject(control);
            stream.close();
            file.close();
            System.out.println("Se han guardado los datos exitosamente.");
            return true;
        }catch(Exception e){
                System.out.println("Error: " + e);
                return false;
         }
    }
    
    // Cargar Datos: Método para cargar los datos, recibe una dirección donde se carga la información y retorna un control.
    public static Control cargarDatos(String path) throws FileNotFoundException, IOException, ClassNotFoundException{;
        Control control;
        FileInputStream file = new FileInputStream(path);
        ObjectInputStream stream = new ObjectInputStream(file);
        control = (Control) stream.readObject();
        return control;
    }
}
