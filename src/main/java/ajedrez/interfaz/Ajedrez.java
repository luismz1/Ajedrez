package ajedrez.interfaz;

// Librerias importadas
import ajedrez.control.Control;
import ajedrez.control.DataVerificator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Ajedrez extends javax.swing.JFrame {
    // Atributos privados y protegidos
    private boolean gameReady;
    protected Control control;
    private Map <String , javax.swing.JButton> botones;
    
    // Constructor
    public Ajedrez() {
        control = Control.getInstance();
        botones = new TreeMap<>();
        initComponents();
        loadGame();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Tablas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        surrenderButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        actualPlayerLabel = new javax.swing.JLabel();
        choosedBoxLabel = new javax.swing.JLabel();
        tieButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        capturedPiecesButton = new javax.swing.JButton();
        piezasLabel = new javax.swing.JLabel();
        actualTeamLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chess");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Tablas.setBackground(new java.awt.Color(244, 231, 214));
        Tablas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("jLabel1");
        Tablas.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, -1, -1));

        saveButton.setText("Guardar Partida");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        Tablas.add(saveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 120, -1));

        surrenderButton.setText("Rendirse");
        surrenderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                surrenderButtonActionPerformed(evt);
            }
        });
        Tablas.add(surrenderButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, 130, -1));

        loadButton.setText("Cargar Partida");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });
        Tablas.add(loadButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, 130, -1));

        startButton.setText("Empezar Partida");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        Tablas.add(startButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, -1, -1));

        actualPlayerLabel.setForeground(new java.awt.Color(0, 0, 0));
        actualPlayerLabel.setText("actualPlayer");
        Tablas.add(actualPlayerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, -1, -1));

        choosedBoxLabel.setForeground(new java.awt.Color(0, 0, 0));
        choosedBoxLabel.setText("choosedBox");
        Tablas.add(choosedBoxLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, -1, -1));

        tieButton.setText("Tablas");
        tieButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tieButtonActionPerformed(evt);
            }
        });
        Tablas.add(tieButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 430, 130, -1));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("ⓇDrSmey");
        Tablas.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 450, -1, -1));

        capturedPiecesButton.setText("Ver capturadas");
        capturedPiecesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                capturedPiecesButtonActionPerformed(evt);
            }
        });
        Tablas.add(capturedPiecesButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, 120, -1));

        piezasLabel.setForeground(new java.awt.Color(0, 0, 0));
        piezasLabel.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\ajedrez\\interfaz\\piezas.png"));
        Tablas.add(piezasLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, 230, 340));

        actualTeamLabel.setForeground(new java.awt.Color(0, 0, 0));
        actualTeamLabel.setText("actualTeam");
        Tablas.add(actualTeamLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, -1, -1));

        getContentPane().add(Tablas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Load Game: Método para cargar el juego cuando inicia.
    private void loadGame(){
        javax.swing.JLabel tableroLabel = new javax.swing.JLabel();
        // Asignar los botones.
        for (int i = 0, y = 35; i <  8; i++, y+= 45){
            for (int j = 0, x = 45; j < 8; j++, x += 45){
                int rowTop = 8 - i;
                String positionBox = DataVerificator.IntToLetter(j) + rowTop;
                JButton button = new javax.swing.JButton();
                botones.put(positionBox, button);
                button.setOpaque(false);
                button.setContentAreaFilled(false);
                button.setPreferredSize(new java.awt.Dimension(45, 45));
                Tablas.add(button, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y, -1, -1));     
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        pingButton(positionBox, botones);
                    }
                });
                }
        }
        // Dejar el juego estático.
        setGameStatic();
        //Cambiar el ícono del tablero.
        tableroLabel.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\ajedrez\\interfaz\\tablero.png"));
        tableroLabel.setText("jLabel1");
        // Agregar el tablero al panel principal.
        Tablas.add(tableroLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 445, 428));
        tableroLabel.getAccessibleContext().setAccessibleName("");        

    }
    
    private void updateButtonsIcons(){
        // Por cada uno de los botones, se actualiza la imagen según la pieza del tablero.
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                int rowTop = 8 - i;
                String positionBox = DataVerificator.IntToLetter(j) + rowTop;
                JButton button = botones.get(positionBox);
                button.setIcon(new javax.swing.ImageIcon(control.pathConstructor(i, j)));
            }
        }
    }
    
    // Ping Button: Método para llevar a cabo las acciones entre las piezas.
    private void pingButton(String positionBox, Map<String, javax.swing.JButton> buttons){
        if (gameReady){ // Los botones van a funcionar solo cuando el juego se encuentre listo.
            // Se obtiene el resultado del movimiento del jugador.
            int jugadorResultado = control.jugadorJuega(positionBox);

            switch (jugadorResultado) {
                case 0 -> { // CASO #0: SELECCIONAR CASILLA.
                   choosedBoxLabel.setVisible(true);
                   choosedBoxLabel.setText("Casilla seleccionada: " + positionBox);
                }
                case -1 -> JOptionPane.showMessageDialog(this, "La posición no es válida, intente de nuevo.",
                            "Notificación", JOptionPane.ERROR_MESSAGE); // CASO # -1: POSICION NO VALIDA.
                case 1->{ // CASO #1: ENROQUE
                        // Se obtiene la casilla que fue consultada primeramente.
                        String firstBox = control.getActualPositionBox();
                        
                        // Declaración de atributos necesarios.
                        boolean towerAtStart;
                        
                        List<Integer> kingCoords;
                        List<Integer> rookCoords;
                        
                        JButton kingButton;
                        JButton rookButton;
                        
                        // Se obtienen los botones relacionados a las piezas
                        JButton oldFirstButton = botones.get(firstBox);
                        JButton oldSecondButton = botones.get(positionBox);

                        // Si la columna es "E" entonces es por que la primera casilla corresponda al rey.
                        boolean firstIsKing = firstBox.substring(0, 1).equals("E");

                        // Se revisa si la que fue seleccionada primero corresponde al rey.
                        if (firstIsKing){
                            kingCoords = (ArrayList) DataVerificator.boxPositionValues(firstBox);
                            rookCoords = (ArrayList) DataVerificator.boxPositionValues(positionBox);
                            towerAtStart = positionBox.substring(0, 1).equals("A");
                            control.castlePlay(firstBox, positionBox, towerAtStart);                                                          
                        // En caso de no serlo, se manejará la jugada con la otra casilla.
                        }else{
                            kingCoords = (ArrayList) DataVerificator.boxPositionValues(positionBox);
                            rookCoords = (ArrayList) DataVerificator.boxPositionValues(firstBox);
                            towerAtStart = firstBox.substring(0, 1).equals("A");
                            control.castlePlay(positionBox, firstBox, towerAtStart);
                        }
                        
                        // Torre del inicio
                        if(towerAtStart){

                            kingButton = botones.get("C"+firstBox.substring(1));
                            rookButton = botones.get("D"+firstBox.substring(1));

                            kingButton.setIcon(new javax.swing.ImageIcon(control.pathConstructor(kingCoords.get(0), kingCoords.get(1) - 2)));
                            rookButton.setIcon(new javax.swing.ImageIcon(control.pathConstructor(rookCoords.get(0), rookCoords.get(1) + 3)));
                        // Torre del final                                
                        }else{
                            kingButton = botones.get("G"+firstBox.substring(1));
                            rookButton = botones.get("F"+firstBox.substring(1));

                            kingButton.setIcon(new javax.swing.ImageIcon(control.pathConstructor(kingCoords.get(0), kingCoords.get(1) + 2)));
                            rookButton.setIcon(new javax.swing.ImageIcon(control.pathConstructor(rookCoords.get(0), rookCoords.get(1) - 2)));                         
                        }                        
                        
                        
                        oldFirstButton.setIcon(null);
                        oldSecondButton.setIcon(null);
                       
                        actualPlayerLabel.setText("Jugador actual: " + control.getJugadorActual());
                        actualTeamLabel.setText("Equipo actual: " + control.getEquipoActual());
                        choosedBoxLabel.setVisible(false);                                 
                } case 2 ->{ // CASO #2: PROMOCION DE PEON.
                    JButton firstAccessed = getFirstAccessedButton();
                    JButton newestAccessed = botones.get(positionBox);
                    // Se obtienen las coordenadas relativas a la casilla que fue seleccionada primeramente.
                    List <Integer> coords = (ArrayList) DataVerificator.boxPositionValues(positionBox);
                    
                    // Se abre la ventana para promover al peón.
                    PromotePawn pp = new PromotePawn(this, true);
                    pp.setLocationRelativeTo(this);
                    pp.setVisible(true);
                    
                    // Se obtiene el resultado de la selección del usuario.
                    String type = pp.getResult();
                    
                    // Si el movimiento termina en Jaque Mate entonces se finaliza el juego.
                    if(control.promotePlay(positionBox, type)){
                         newestAccessed.setIcon(new javax.swing.ImageIcon(control.pathConstructor(coords.get(0), coords.get(1))));
                        firstAccessed.setIcon(null);                       
                        endGame(control.getJugadorActual(), "Jaque Mate");
                    }else{ // En caso contrario se continuará el juego.
                        newestAccessed.setIcon(new javax.swing.ImageIcon(control.pathConstructor(coords.get(0), coords.get(1))));
                        firstAccessed.setIcon(null);
                        
                        actualPlayerLabel.setText("Jugador actual: " + control.getJugadorActual());
                        actualTeamLabel.setText("Equipo actual: " + control.getEquipoActual());
                        choosedBoxLabel.setVisible(false);                        
                    }
                }
                default -> { // CASO #3:  MOVIMIENTO NORMAL DE PIEZA.
                    // Se obtiene la casilla que fue consultada primeramente.
                    String firstBox = control.getActualPositionBox();
                    // Se obtiene el botón que fue consultado primeramente.
                    JButton firstAccessed = getFirstAccessedButton();
                    // Obtiene el boton acessado recientemente.
                    JButton newestAccessed = botones.get(positionBox);
                    // Se obtienen las coordenadas relativas a la casilla que fue seleccionada primeramente.
                    List <Integer> coords = (ArrayList) DataVerificator.boxPositionValues(firstBox);
                    // Al nuevo boton se le asigna la imagen del viejo.
                    newestAccessed.setIcon(new javax.swing.ImageIcon(control.pathConstructor(coords.get(0), coords.get(1))));
                    // Al viejo se le asigna que no tenga imagen.
                    firstAccessed.setIcon(null); 
                    
                    // Si el movimiento termina en Jaque Mate entonces se finaliza el juego.
                    if(control.changePlayer(positionBox)){
                        endGame(control.getJugadorActual(), "Jaque Mate");
                    }else{ // En caso contrario, se continua.
                        actualPlayerLabel.setText("Jugador actual: " + control.getJugadorActual());
                        actualTeamLabel.setText("Equipo actual: " + control.getEquipoActual());
                        choosedBoxLabel.setVisible(false);
                    }
                }
            }
            // Se actualizan los botones por si quedan cambios pendientes.
            updateButtonsIcons();
        }
    }
    
    // Get First Accessed Button: Obtiene el botón relacionado con la primera casilla seleccionada.
    private JButton getFirstAccessedButton(){
        String firstBox = control.getActualPositionBox();
        JButton firstAccessed = new javax.swing.JButton();
        // Obtiene el primer boton acessado.
        for (String actualBox: botones.keySet()){
            if (actualBox.equals(firstBox)){
                firstAccessed = botones.get(actualBox);
                break;
            }
        }
        return firstAccessed;
    }    
    
    // Save Button Action: Muestra en pantalla una ventana para guardar la partida actual en un archivo.
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
            SaveMatch sm = new SaveMatch(this, true, control);
            sm.setLocationRelativeTo(this);
            sm.setVisible(true);        
    }//GEN-LAST:event_saveButtonActionPerformed

    // Load Button Action: Muestra en pantalla una ventana para cargar la partida en el tablero.
    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
            // Se abre la ventana.
            LoadMatch lm = new LoadMatch(this, true);
            // Se guarda la instancia en el momento actual.
            Control controlActual = control;
            // Se abre el JDialog para obtener un cambio en el control.
            lm.setLocationRelativeTo(this);
            lm.setVisible(true);
            // Si el archivo cambió entonces se van a actualizar los atributos, de no ser así se mantienen igual.
            if (control != controlActual){
                setGameViewable();
                
                String actualBox = control.getActualPositionBox();
                if (!actualBox.equals("")){
                    choosedBoxLabel.setVisible(true);
                    choosedBoxLabel.setText("Casilla seleccionada: " + actualBox);
                }

                updateButtonsIcons();
            }
    }//GEN-LAST:event_loadButtonActionPerformed

    // Start Button Action: Muestra en pantalla una ventnaa para seleccionar los nombres y empezar una partida.
    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        PlayerNames pn = new PlayerNames(this, true);
        pn.setLocationRelativeTo(this);
        pn.setVisible(true);
        // Si los nombres fueron seleccionados correctamente entonces el juego se hará visible.
        if (control.playersSet())
            setGameViewable();
    }//GEN-LAST:event_startButtonActionPerformed
    
    // Surrender Button Action: Deja que el jugador del turno actual se rinda dando como ganador al jugador del otro equipo.
    private void surrenderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_surrenderButtonActionPerformed
            String equipoActual = control.getEquipoActual();
            int result = JOptionPane.showConfirmDialog(this, "¿Está seguro el jugador " + control.getJugadorActual()
                    + " de rendirse?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if (result == JOptionPane.YES_OPTION){   
                endGame(control.getJugadorOpuesto(), "Abandono");
            }
    }//GEN-LAST:event_surrenderButtonActionPerformed

    // Tie Button Action: Pide a ambos jugadores la confirmación para empatar una partida.
    private void tieButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tieButtonActionPerformed
            int actualPlayerResult = JOptionPane.showConfirmDialog(this, "Jugador " + control.getJugadorActual() + " ¿Desea hacer tablas?", 
                    "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (actualPlayerResult == JOptionPane.YES_OPTION){
                int otherPlayerResult = JOptionPane.showConfirmDialog(this, "Jugador " + control.getJugadorOpuesto() + " ¿Desea hacer tablas?", 
                        "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (otherPlayerResult == JOptionPane.YES_OPTION){
                    JOptionPane.showMessageDialog(this, "Ambos jugadores han empatado la partida.", 
                            "Tablas", JOptionPane.INFORMATION_MESSAGE);                    
                    playAgain();
                }
            }
    }//GEN-LAST:event_tieButtonActionPerformed

    private void capturedPiecesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_capturedPiecesButtonActionPerformed
        Capturadas cpt = new Capturadas(this, true);
        cpt.setLocationRelativeTo(this);
        cpt.setVisible(true);       
    }//GEN-LAST:event_capturedPiecesButtonActionPerformed
    
    // End Game: Muestra un mensaje con el ganador y el mótivo de la victoria.
    private void endGame(String winner, String defeatReason){
            JOptionPane.showMessageDialog(this, "El jugador " + winner + " ha ganado la partida.", 
                    defeatReason, JOptionPane.INFORMATION_MESSAGE);
            playAgain();
    }
    
    // Play Again: Muestra un mensaje para volver a jugar.
    private void playAgain(){
             int result = JOptionPane.showConfirmDialog(this, "¿Desea volver a jugar otra partida?",
                     "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
             if (result == JOptionPane.YES_OPTION){ // Si el usuario confirma entonces se reinicia el juego.
                 control.reiniciarJuego();
                 setGameStatic();
             }else{ // Si el usuario no selecciona la opción para confirmar entonces se cierra el juego.
                 dispose();
             }        
    }
        
    // Set Game Static: Hace que el juego permanezca estático.
    private void setGameStatic(){
        gameReady = false;
        updateButtonsIcons();
        capturedPiecesButton.setVisible(false);
        loadButton.setVisible(true);
        surrenderButton.setVisible(false);
        tieButton.setVisible(false);
        startButton.setVisible(true);
        actualPlayerLabel.setVisible(false);
        actualTeamLabel.setVisible(false);
        choosedBoxLabel.setVisible(false);
        saveButton.setVisible(false);
    }
    
    // Set Game Viewable: Deja que el juego se pueda ver y poder jugar.
    private void setGameViewable(){
        gameReady = true;
        startButton.setVisible(false);
        loadButton.setVisible(false);
        capturedPiecesButton.setVisible(true);
        surrenderButton.setVisible(true);
        tieButton.setVisible(true);
        saveButton.setVisible(true);
        actualPlayerLabel.setVisible(true);
        actualTeamLabel.setVisible(true);
        actualPlayerLabel.setText("Jugador actual: " + control.getJugadorActual());
        actualTeamLabel.setText("Equipo actual: " + control.getEquipoActual());
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ajedrez.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ajedrez.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ajedrez.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ajedrez.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ajedrez().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Tablas;
    private javax.swing.JLabel actualPlayerLabel;
    private javax.swing.JLabel actualTeamLabel;
    private javax.swing.JButton capturedPiecesButton;
    private javax.swing.JLabel choosedBoxLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton loadButton;
    private javax.swing.JLabel piezasLabel;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton startButton;
    private javax.swing.JButton surrenderButton;
    private javax.swing.JButton tieButton;
    // End of variables declaration//GEN-END:variables
}