package logarlecTheGame.View;
import javax.swing.*;

import logarlecTheGame.Controller.GameLogic;
import logarlecTheGame.Model.Board;
import logarlecTheGame.Model.Player;
import logarlecTheGame.Model.Student;

import java.awt.*;
import java.io.Serializable;

public class View implements Serializable{
    private JFrame frame;
    private RoomPanel roomPanel;
    private ActionPanel actionPanel;
    private BoardPanel boardPanel;
    private GameLogic gameLogic;
    private WinImage win;
    private LostImage loose;

    public View(GameLogic gl){
        gameLogic = gl;
        win = new WinImage();
        loose = new LostImage();
        win.setVisible(false);
        loose.setVisible(false);
    }

    public void init() {
        frame = new JFrame("Game by ripgyork");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        frame.setLayout(new GridLayout(2, 2));

        boardPanel = new BoardPanel(gameLogic.getCurrentPlayer(), gameLogic.getBoard());
        frame.add(boardPanel);
        boardPanel.update(gameLogic.getCurrentPlayer(), gameLogic.getBoard());
        roomPanel = new RoomPanel(gameLogic.getCurrentPlayer(), gameLogic.getBoard(), gameLogic);
        frame.add(roomPanel);
        actionPanel = new ActionPanel(gameLogic.getCurrentPlayer(), gameLogic.getBoard(), this);
        frame.add(actionPanel);
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(Color.YELLOW);
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
        Student p=gameLogic.getCurrentPlayer();
        Board b=gameLogic.getBoard();
        actionPanel.update(p,b);
        roomPanel.update(p,b);
        boardPanel.update(p,b);
    }

    public void vTurn(){
        gameLogic.turn();
    }

    public void lost(){
        frame.setVisible(false);
        loose.setVisible(true);
    }

    public void won(){
        frame.setVisible(false);
        win.setVisible(true);
    }
}