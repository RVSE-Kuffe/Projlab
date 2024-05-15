package logarlecTheGame.View;

import javax.swing.*;
import java.awt.*;

public class LostImage extends JFrame {

    public LostImage() {
        setTitle("Lost Image");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Ablak középre igazítása

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBackground(g);
                drawText(g);
            }

            private void drawBackground(Graphics g) {
                g.setColor(Color.BLACK); // Fekete háttér
                g.fillRect(0, 0, getWidth(), getHeight());
            }

            private void drawText(Graphics g) {
                Font font = new Font("Arial", Font.BOLD | Font.ITALIC, 150); // Nagyobb betűméret
                g.setFont(font);
                g.setColor(new Color(255, 69, 0)); // Piros

                FontMetrics metrics = g.getFontMetrics(font);
                int x = (getWidth() - metrics.stringWidth("You lost")) / 2;
                int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                g.drawString("You lost", x, y);
            }
        };

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LostImage lostImage = new LostImage();
            lostImage.setVisible(true);
        });
    }
}

