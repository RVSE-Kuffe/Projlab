package logarlecTheGame.View;

import javax.swing.*;

import logarlecTheGame.Controller.GameLogic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Egy menü ablak a játékhoz.
 */
public class GameMenu extends JFrame {
    private JTextField playerCountField; // Játékosok számát megadó mező
    private JLabel warningMessage; // Figyelmeztető üzenet
    private transient JFileChooser fileChooser; // Fájlkiválasztó

    /**
     * Konstruktor a menü ablak létrehozásához.
     * Beállítja az ablak címét és a felhasználói felület komponenseit.
     */
    public GameMenu() {
        setTitle("Game Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY); // Háttérszín beállítása

        // Gombok létrehozása és beállítása
        //JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(new ActionListener() {    
            public void actionPerformed(ActionEvent e) {
                load();
            }
        });
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {    
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });

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
        JLabel playerLabel = new JLabel("No. of players: ");
        playerLabel.setFont(buttonFont); // Betűméret beállítása
        playerPanel.add(playerLabel);
        playerCountField = new JTextField(10);
        playerCountField.setFont(buttonFont); // Betűméret beállítása
        playerPanel.add(playerCountField);
        warningMessage = new JLabel("You need to give a number (integer)");
        warningMessage.setForeground(Color.RED);
        warningMessage.setVisible(false);
        playerPanel.add(warningMessage);

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


    /**
     * Az indítás gomb eseménykezelője.
     * Ellenőrzi a játékosok számának helyességét, majd elindítja a játékot.
     */
    public void start(){
        String playerCountString = playerCountField.getText();
        try{
            int playerCount = Integer.parseInt(playerCountString);
            GameLogic gl = new GameLogic(playerCount);
            this.setVisible(false);
            View view = new View(gl);
            gl.setView(view);
            view.init();

        }
        catch(NumberFormatException e){
            playerCountField.setText("");
            warningMessage.setVisible(true);
        }
    }

    /**
     * A betöltés gomb eseménykezelője.
     * Megnyit egy fájlkiválasztó dialógust, és betölti a kiválasztott játékállapotot.
     */
    public void load(){
        fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(selectedFile))) {
                GameLogic gl = (GameLogic)in.readObject();
                View view = new View(gl);
                gl.setView(view);
                this.setVisible(false);
                view.init();
                JOptionPane.showMessageDialog(this, "Loaded successfully!");
            } 
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
