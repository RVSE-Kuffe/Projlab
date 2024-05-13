package logarlecTheGame.View;

import javax.swing.*;
import java.awt.*;

import logarlecTheGame.Model.*;

public class RoomPanel extends JPanel {
    Student player;
    Board board;
    private JTextField roomAttributesField;
    private JTextField playersField;
    private JTextField currentPlayerField;
    private JButton saveButton;

    public RoomPanel(Student s, Board board) {
        this.player=s;
        this.board=board;
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

        add(textFieldsPanel, BorderLayout.CENTER);

        saveButton = new JButton("Save");
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

    public void update(Student p, Board b){
        player = p;
        board = b;
        updateRoomAttributes();
        updatePlayers();
        updateCurrentPlayer();
    }
}
