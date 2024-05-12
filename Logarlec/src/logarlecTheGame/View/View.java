package logarlecTheGame.View;
import javax.swing.*;

import logarlecTheGame.Controller.GameLogic;
import logarlecTheGame.Model.Board;
import logarlecTheGame.Model.Player;

import java.awt.*;

public class View {
    private JFrame frame;
    private RoomPanel roomPanel;
    private ActionPanel actionPanel;
    private BoardPanel boardPanel;
    private GameLogic gameLogic;

    public View(){}

    public void init() {
        frame = new JFrame("Game by ripgyork");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        frame.setLayout(new GridLayout(2, 2));

        roomPanel = new RoomPanel();
        frame.add(roomPanel);
        actionPanel = new ActionPanel();
        frame.add(actionPanel);
        boardPanel = new BoardPanel();
        frame.add(boardPanel);
        JPanel emptyPanel = new JPanel();
        frame.add(emptyPanel);
        frame.setVisible(true);
    }

    public void gameOver() {
        roomPanel.setVisible(false);
        actionPanel.setVisible(false);
        boardPanel.setVisible(false);
        
        JPanel gameOverPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 48));
                String message = "Game Over!";
                FontMetrics fm = g.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(message)) / 2;
                int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                g.drawString(message, x, y);
            }
        };
        frame.add(gameOverPanel, BorderLayout.CENTER);
        frame.revalidate(); // Panel újrarajzolása
    }

    public void update(){
        Player p=gameLogic.getCurrentPlayer();
        Board b=gameLogic.getBoard();
        actionPanel.update(p,b);
        roomPanel.update(p,b);
        boardPanel.update(p,b);
    }
}
