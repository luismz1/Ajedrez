package ajedrez.logica;

// Librerias importadas
import java.io.Serializable;


public class Position implements Serializable{
    // Atributos privados
    private int row;
    private int col;
    
    // Constructor
    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }

    // Get Row: Obtiene la fila de la posición.
    public int getRow() {
        return row;
    }
    
    // Get Column: Obtiene la columna de la posición.
    public int getColumn() {
        return col;
    }
  
    @Override
    // Equals: Verifica que dos objetos sean iguales en base a su fila y columna.
    public boolean equals(Object pos){
        if (pos instanceof Position position)
            return row ==position.getRow() && col == position.getColumn();
        else
            return false;
    }

    @Override
    public String toString(){
        return "(" + row + ", " + col + ")";
    }
}
