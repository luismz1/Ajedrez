package ajedrez.logica;

// Enum de equipos.
 enum Team {
    BLANCO,
    NEGRO;
    
     @Override
    public String toString() {
         // Método toString de equipos.
        return switch (this) {
            case BLANCO-> "B";
            default -> "N";
        };
    }
}
