package logarlecTheGame.View;

import javax.swing.*;

import logarlecTheGame.Model.*;

import java.awt.*;


public class BoardPanel extends JPanel {

    Player player;
    Board board;

    public BoardPanel() {
        setSize(400, 400);

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.YELLOW);

        // Az eltolás a körök bal felső negyedében történik
        int offsetX = 150;
        int offsetY = 20;

        final int[] n = new int[]{1};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JPanel circlePanel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        String roomname = board.objectToString(player.getLocation());
                        if(roomname.equals("R"+n)) 
                            g.setColor(Color.BLUE);
                        else 
                            g.setColor(Color.GREEN);
                        g.fillOval(5, 5, getWidth() - 10, getHeight() - 10);
                        g.setColor(Color.BLACK);
                        g.drawOval(5, 5, getWidth() - 10, getHeight() - 10);
                        g.drawString("R"+n[0], getWidth() / 2 - 10, getHeight() / 2 + 5);
                        
                    }
                    
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension(80, 80);
                    }
                };
                circlePanel.setBackground(Color.YELLOW);
                // A körök pozíciója az eltolással
                int x = j * 100 + offsetX;
                int y = i * 100 + offsetY;
                circlePanel.setBounds(x, y, 80, 80);
                panel.add(circlePanel);
                n[0]++;
            }
            
        }

        add(panel);
    }

    public void update(Player p, Board b){
        player = p;
        board = b;
    }

}
