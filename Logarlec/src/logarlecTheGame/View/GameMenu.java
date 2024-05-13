package logarlecTheGame.View;

import javax.swing.*;
import java.awt.*;

public class GameMenu extends JFrame {
    public GameMenu() {
        setTitle("Játék Menü");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY); // Háttérszín beállítása

        // Gombok létrehozása és beállítása
        //JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton startButton = new JButton("Start");

        Dimension buttonSize = new Dimension(300, 70); // Gombok mérete
        Font buttonFont = new Font("Arial", Font.BOLD, 20); // Gombok betűmérete

        //saveButton.setPreferredSize(buttonSize);
        loadButton.setPreferredSize(buttonSize);
        startButton.setPreferredSize(buttonSize);

        //saveButton.setFont(buttonFont);
        loadButton.setFont(buttonFont);
        startButton.setFont(buttonFont);

        // Üres szegély hozzáadása a gombokhoz
        //saveButton.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
        loadButton.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
        startButton.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));

        // Gombok paneljének létrehozása és beállítása
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
        //buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(startButton);

        // Játékossal kapcsolatos mező létrehozása és beállítása
        JPanel playerPanel = new JPanel();
        playerPanel.setBackground(Color.LIGHT_GRAY);
        playerPanel.setLayout(new FlowLayout());
        playerPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
        JLabel playerLabel = new JLabel("Játékosok száma: ");
        playerLabel.setFont(buttonFont); // Betűméret beállítása
        playerPanel.add(playerLabel);
        JTextField playerCountField = new JTextField(10);
        playerCountField.setFont(buttonFont); // Betűméret beállítása
        playerPanel.add(playerCountField);

        // Gombok panelének igazítása
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.PAGE_START;
        mainPanel.add(buttonPanel, gbc);

        // Játékossal kapcsolatos panel igazítása
        gbc.gridy = 1;
        mainPanel.add(playerPanel, gbc);

        getContentPane().add(mainPanel);

        pack();
        setLocationRelativeTo(null); // Ablak középre helyezése
        setVisible(true);
    }
}
//Bálintnak save, load hoz (ebben a mentés, visszaolvasás formátuma más, de azon kívül használható)
/*
 JButton saveButton = new JButton("Save");
JButton loadButton = new JButton("Load");

controlPanel.add(saveButton);
controlPanel.add(loadButton);

private void saveToFile() {
    JFileChooser fileChooser = new JFileChooser();
    int result = fileChooser.showSaveDialog(this);

    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(selectedFile))) {
            out.writeObject(entities);
            JOptionPane.showMessageDialog(this, "Saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

private void loadFromFile() {
    JFileChooser fileChooser = new JFileChooser();
    int result = fileChooser.showOpenDialog(this);

    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(selectedFile))) {
            entities = (Entity[][]) in.readObject();
            entityList.clear();
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (entities[i][j] != null) {
                        entityList.add(entities[i][j]);
                    }
                }
            }
            repaint();
            JOptionPane.showMessageDialog(this, "Loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
*/
