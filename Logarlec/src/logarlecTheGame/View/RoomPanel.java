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
    private JTextField remainingRoundsField;
    private JButton saveButton;
    private GameLogic gameLogic;
    private transient JFileChooser fileChooser;


    public RoomPanel(Student s, Board board, GameLogic gl) {
        gameLogic = gl;
        this.player=s;
        this.board=board;
        fileChooser = new JFileChooser();
        setLayout(new BorderLayout());

        JPanel textFieldsPanel = new JPanel(new GridLayout(2, 1));

        roomAttributesField = new JTextField();
        roomAttributesField.setEditable(false);
        textFieldsPanel.add(roomAttributesField);

        playersField = new JTextField();
        playersField.setEditable(false);
        textFieldsPanel.add(playersField);

        currentPlayerField = new JTextField();
        currentPlayerField.setEditable(false);
        textFieldsPanel.add(currentPlayerField);

        remainingRoundsField = new JTextField();
        remainingRoundsField.setEditable(false);
        textFieldsPanel.add(remainingRoundsField);

        add(textFieldsPanel, BorderLayout.CENTER);

        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener(){    
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
        currentPlayerField.setText("current player: "+ board.objectToString(player));
    }

    public void updateRemainingRounds(){
        remainingRoundsField.setText("Hátralévő körök száma: " + gameLogic.getRemainingRounds());
    }

    public void update(Student p, Board b){
        player = p;
        board = b;
        updateRoomAttributes();
        updatePlayers();
        updateCurrentPlayer();
        updateRemainingRounds();
    }

    public void save(){
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
