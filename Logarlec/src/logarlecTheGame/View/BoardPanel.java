package logarlecTheGame.View;

import javax.swing.*;

import logarlecTheGame.Model.*;

import java.awt.*;



public class BoardPanel extends JPanel {
    private Student currentPlayer;
    private Board board;

    public BoardPanel(Student currentPlayer, Board board) {
        this.currentPlayer = currentPlayer;
        this.board = board;
        // Azonnal kirajzolunk valamit
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBoard(g);
    }

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

                // Draw circle
                g.setColor(getCircleColor(row, col));
                g.fillOval(x, y, circleSize, circleSize);

                // Draw room name
                String roomName = getRoomName(row, col);
                g.setColor(Color.BLACK);
                g.drawString(roomName, x + circleSize / 4, y + circleSize / 2);
            }
        }
    }

    private String getRoomName(int row, int col) {
        int index = row * 4 + col;
        if (index < board.rooms().size()) {
            Room room = board.rooms().get(index);
            return board.objectToString(room);
        }
        return "";
    }

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

    public void update(Student currentPlayer, Board board) {
        this.currentPlayer = currentPlayer;
        this.board = board;
        repaint(); // Újrarajzoljuk a panelt
    }
}
