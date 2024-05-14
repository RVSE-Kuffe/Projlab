package logarlecTheGame.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import logarlecTheGame.Controller.GameLogic;
import logarlecTheGame.Model.*;

public class RoomPanel extends JPanel {
    private Student player;
    private Board board;
    private JTextField roomAttributesField;
    private JTextField playersField;
    private JTextField currentPlayerField;
    private JButton saveButton;
    private GameLogic gameLogic;

    public RoomPanel(Student s, Board board, GameLogic gl) {
        gameLogic = gl;
        this.player = s;
        this.board = board;
        setLayout(new BorderLayout());

        JPanel textFieldsPanel = new JPanel(new GridLayout(3, 1)); // Changed grid layout to include 3 rows

        roomAttributesField = new JTextField();
        roomAttributesField.setEditable(false);
        textFieldsPanel.add(roomAttributesField);

        playersField = new JTextField();
        playersField.setEditable(false);
        textFieldsPanel.add(playersField);

        currentPlayerField = new JTextField();
        currentPlayerField.setEditable(false);
        textFieldsPanel.add(currentPlayerField); // Added the new text field for current player

        add(textFieldsPanel, BorderLayout.CENTER);

        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.NORTH);
        update(s, board);
    }

    public void updateRoomAttributes() {
        roomAttributesField.setText(player.getLocation().listMe(board, false, false, true));
    }

    public void updatePlayers() {
        playersField.setText(player.getLocation().listMe(board, true, false, false));
    }

    public void updateCurrentPlayer() {
        currentPlayerField.setText("Current player: " + board.objectToString(player)); // Displaying the current player's name
    }

    public void update(Student p, Board b) {
        player = p;
        board = b;
        updateRoomAttributes();
        updatePlayers();
        updateCurrentPlayer();
    }

    public void save() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(selectedFile))) {
                out.writeObject(gameLogic);
                JOptionPane.showMessageDialog(this, "Saved successfully!");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error saving.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
