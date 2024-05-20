package logarlecTheGame.View;

import javax.swing.*;
import java.awt.*;


/**
 * Egy ablak, ami "You lost" feliratot jelenít meg.
 */
public class LostImage extends JFrame {

    /**
     * Konstruktor az "You lost" felirat megjelenítéséhez.
     * Beállítja az ablak címét, méretét és helyzetét.
     * Létrehoz egy panelt, amire a szöveget és a háttér színt rajzolja.
     */
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

            /**
             * Kirajzolja a panel háttérszínét.
             *
             * @param g A Graphics objektum, amire rajzolunk.
             */
            private void drawBackground(Graphics g) {
                g.setColor(Color.BLACK); // Fekete háttér
                g.fillRect(0, 0, getWidth(), getHeight());
            }

            /**
             * Kirajzolja az "You lost" feliratot.
             *
             * @param g A Graphics objektum, amire rajzolunk.
             */
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
}

