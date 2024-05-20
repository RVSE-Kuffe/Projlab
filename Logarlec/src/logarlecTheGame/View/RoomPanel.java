package logarlecTheGame.View;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import logarlecTheGame.Controller.GameLogic;
import logarlecTheGame.Model.*;

public class RoomPanel extends JPanel {
    private Student player; // Játékos objektum
    private Board board; // Játéktábla objektum
    private JTextField roomAttributesField; // Szoba tulajdonságokat megjelenítő mező
    private JTextField playersField; // Játékosokat megjelenítő mező
    private JTextField currentPlayerField; // Aktuális játékost megjelenítő mező
    private JTextField remainingRoundsField; // Hátralévő köröket megjelenítő mező
    private JButton saveButton; // Mentés gomb
    private GameLogic gameLogic; // Játék logika objektum
    private transient JFileChooser fileChooser; // Fájl választó párbeszédablak

    // Konstruktor, amely inicializálja a paneleket és a mezőket
    public RoomPanel(Student s, Board board, GameLogic gl) {
        gameLogic = gl;
        this.player = s;
        this.board = board;
        fileChooser = new JFileChooser(); // Fájl választó párbeszédablak inicializálása
        setLayout(new BorderLayout()); // Panel elrendezésének beállítása

        JPanel textFieldsPanel = new JPanel(new GridLayout(2, 1)); // Két soros rács elrendezésű panel létrehozása
        textFieldsPanel.setBackground(Color.YELLOW); // Panel háttérszínének beállítása

        Font font = new Font("Arial", Font.PLAIN, 20); // Betűtípus beállítása

        // Szoba tulajdonságokat megjelenítő mező inicializálása
        roomAttributesField = new JTextField();
        roomAttributesField.setBackground(Color.YELLOW);
        roomAttributesField.setFont(font);
        roomAttributesField.setEditable(false);
        textFieldsPanel.add(roomAttributesField); // Mező hozzáadása a panelhez

        // Játékosokat megjelenítő mező inicializálása
        playersField = new JTextField();
        playersField.setBackground(Color.YELLOW);
        playersField.setFont(font);
        playersField.setEditable(false);
        textFieldsPanel.add(playersField); // Mező hozzáadása a panelhez

        // Aktuális játékost megjelenítő mező inicializálása
        currentPlayerField = new JTextField();
        currentPlayerField.setBackground(Color.YELLOW);
        currentPlayerField.setFont(font);
        currentPlayerField.setEditable(false);
        textFieldsPanel.add(currentPlayerField); // Mező hozzáadása a panelhez

        // Hátralévő köröket megjelenítő mező inicializálása
        remainingRoundsField = new JTextField();
        remainingRoundsField.setBackground(Color.YELLOW);
        remainingRoundsField.setFont(font);
        remainingRoundsField.setEditable(false);
        textFieldsPanel.add(remainingRoundsField); // Mező hozzáadása a panelhez

        add(textFieldsPanel, BorderLayout.CENTER); // Szöveges mezők paneljének hozzáadása a fő panelhez

        saveButton = new JButton("Save"); // Mentés gomb létrehozása
        saveButton.addActionListener(e -> save()); // Lambda kifejezés az ActionListener helyett
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Gomb panel létrehozása jobb oldali igazítással
        buttonPanel.setBackground(Color.YELLOW); // Panel háttérszínének beállítása
        buttonPanel.add(saveButton); // Mentés gomb hozzáadása a panelhez
        add(buttonPanel, BorderLayout.NORTH); // Gomb panel hozzáadása a fő panelhez

        update(s, board); // Frissítés meghívása a kezdeti adatokkal
    }

    // Szoba tulajdonságok frissítése
    public void updateRoomAttributes() {
        roomAttributesField.setText(player.getLocation().listMe(board, false, false, true));
    }

    // Játékosok listájának frissítése
    public void updatePlayers() {
        playersField.setText(player.getLocation().listMe(board, true, false, false));
    }

    // Aktuális játékos frissítése
    public void updateCurrentPlayer() {
        currentPlayerField.setText("Current player: " + board.objectToString(player));
    }

    // Hátralévő körök frissítése
    public void updateRemainingRounds() {
        remainingRoundsField.setText("Remaining actionpoints: " + gameLogic.getRemainingRounds());
    }

    // Frissítés a megadott játékos és tábla alapján
    public void update(Student p, Board b) {
        player = p;
        board = b;
        updateRoomAttributes();
        updatePlayers();
        updateCurrentPlayer();
        updateRemainingRounds();
    }

    // Játékállás mentése fájlba
    public void save() {
        int result = fileChooser.showSaveDialog(this); // Fájl választó párbeszédablak megjelenítése

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile(); // Kiválasztott fájl lekérése
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(selectedFile))) {
                out.writeObject(gameLogic); // Játék logika objektum mentése a fájlba
                JOptionPane.showMessageDialog(this, "Mentés sikeres!"); // Sikeres mentés üzenete
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Hiba mentés során.", "HIBA", JOptionPane.ERROR_MESSAGE); // Hibaüzenet megjelenítése
            }
        }
    }
}
