package logarlecTheGame.View;

import javax.swing.*;

import logarlecTheGame.Model.*;

import java.awt.*;

/**
 * Egy panel, ami felelős a játéktábla megjelenítéséért.
 */

public class BoardPanel extends JPanel{
    private Student currentPlayer;//Az aktuális játékos
    private Board board; // A játéktábla
    
/**
     * Konstruktor egy új BoardPanel létrehozásához a megadott aktuális játékossal és táblával.
     *
     * @param currentPlayer Az aktuális játékos.
     * @param board A játéktábla.
     */
    public BoardPanel(Student currentPlayer, Board board) {
        this.currentPlayer = currentPlayer;
        this.board = board;
        this.setBackground(Color.YELLOW);// háttér sárgára állítása
        // Azonnal kirajzolunk valamit
    }

    /**
     * Felülírja a paintComponent metódust a játéktábla kirajzolásához.
     *
     * @param g A Graphics objektum, amire rajzolunk.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBoard(g);
    }

    /**
     * Kirajzolja a játéktáblát a panelre.
     *
     * @param g A Graphics objektum, amire rajzolunk.
     */
    private void paintBoard(Graphics g) {
        int rows = 4;
        int cols = 4;
        int circleSize = 70;
        int gapy = 10;
        int gapx=25;

        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = col * (circleSize + gapx);
                int y = row * (circleSize + gapy);

                // kör kirajzolása
                g.setColor(getCircleColor(row, col));
                g.fillOval(x, y, circleSize, circleSize);

                // szobanév kiírása
                String roomName = getRoomName(row, col);
                g.setColor(Color.BLACK);
                g.drawString(roomName, x + circleSize / 4, y + circleSize / 2);
            }
        }
    }

    /**
     * Visszaadja a megadott sorban és oszlopban lévő szoba nevét.
     *
     * @param sor A sor indexe.
     * @param oszlop Az oszlop indexe.
     * @return A szoba neve.
     */
    private String getRoomName(int row, int col) {
        int index = row * 4 + col;
        if (index < board.rooms().size()) {
            Room room = board.rooms().get(index);
            return board.objectToString(room);
        }
        return "";
    }

    /**
     * Visszaadja a megadott sorban és oszlopban lévő szoba körének színét.
     *
     * @param sor A sor indexe.
     * @param oszlop Az oszlop indexe.
     * @return A kör színe.
     */
    private Color getCircleColor(int row, int col) {
        int index = row * 4 + col;
        if (index < board.rooms().size()) {
            Room room = board.rooms().get(index);
            if (currentPlayer.getLocation() == room) {
                return Color.BLUE;
            } else {
                return Color.GREEN; // Egyéb szobák zöld
            }
        }
        return Color.RED; // Ha a roomList-nél nagyobb az index, akkor piros
    }

   /**
     * Frissíti az aktuális játékost és a táblát, majd újrarajzolja a panelt.
     *
     * @param currentPlayer Az új aktuális játékos.
     * @param board Az új játéktábla.
     */ 
    public void update(Student currentPlayer, Board board) {
        this.currentPlayer = currentPlayer;
        this.board = board;
        repaint(); // Újrarajzoljuk a panelt
    }
}
