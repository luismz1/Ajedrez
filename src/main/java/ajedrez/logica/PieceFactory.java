package ajedrez.logica;

public class PieceFactory {
    // Create Piece: Método para crear una pieza según el tipo, el equipo y asignar una posición.
    public static Piece createPiece(String type, String team, Position pos){
        if (team.equals("N")){ // Piezas negras.
            return switch (type) {
                case "P" -> new Peon(Team.NEGRO, pos); // Peón del equipo negro.
                case "T" -> new Torre(Team.NEGRO, pos); // Torre del equipo negro.
                case "C" -> new Caballo(Team.NEGRO, pos); // Caballo del equipo negro.
                case "A" -> new Alfil(Team.NEGRO, pos); // Alfil del equipo negro.
                case "K" -> new Rey(Team.NEGRO, pos); // Rey del equipo negro.
                default -> new Reina(Team.NEGRO, pos); // Reina del equipo negro.
            };
        }else{ // Piezas blancas.
            return switch (type) {
                case "P" -> new Peon(Team.BLANCO, pos); // Peón del equipo blanco.
                case "T" -> new Torre(Team.BLANCO, pos); // Torre del equipo blanco.
                case "C" -> new Caballo(Team.BLANCO, pos); // Caballo del equipo blanco.
                case "A" -> new Alfil(Team.BLANCO, pos); // Alfil del equipo blanco.
                case "K" -> new Rey(Team.BLANCO, pos); // Rey del equipo blanco.
                default -> new Reina(Team.BLANCO, pos); // Reina del equipo blanco.
         };
       }
    }
}
    
