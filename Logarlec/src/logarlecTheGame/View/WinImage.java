package logarlecTheGame.View;

import javax.swing.*;
import java.awt.*;

public class WinImage extends JFrame {

    public WinImage() {
        setTitle("Win Image");
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
                g.setColor(new Color(255, 255, 102)); // Citromsárga háttér
                g.fillRect(0, 0, getWidth(), getHeight());
            }

            private void drawText(Graphics g) {
                Font font = new Font("Arial", Font.BOLD | Font.ITALIC, 150); // Kisebb betűméret
                g.setFont(font);
                g.setColor(new Color(255, 140, 0)); // Sötét narancssárga szöveg

                FontMetrics metrics = g.getFontMetrics(font);
                int x = (getWidth() - metrics.stringWidth("You win")) / 2;
                int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                g.drawString("You won", x, y);
            }
        };

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WinImage winImage = new WinImage();
            winImage.setVisible(true);
        });
    }
}

