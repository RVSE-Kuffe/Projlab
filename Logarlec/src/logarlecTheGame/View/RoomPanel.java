package logarlecTheGame.View;

import javax.swing.*;

import logarlecTheGame.Model.Board;
import logarlecTheGame.Model.Player;

import java.awt.*;

public class RoomPanel extends JPanel {
    Player player;
    Board board;
    private JTextField roomAttributesField;
    private JTextField playersField;
    private JButton saveButton;

    public RoomPanel() {
        setLayout(new BorderLayout());

        JPanel textFieldsPanel = new JPanel(new GridLayout(2, 1));

        roomAttributesField = new JTextField();
        roomAttributesField.setEditable(false);
        textFieldsPanel.add(roomAttributesField);

        playersField = new JTextField();
        playersField.setEditable(false);
        textFieldsPanel.add(playersField);

        add(textFieldsPanel, BorderLayout.CENTER);

        saveButton = new JButton("Save");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.NORTH);
    }

    public void updateRoomAttributes() {
        roomAttributesField.setText(player.getLocation().listMe(board, false, false, true));
    }

    public void updatePlayers() {
        playersField.setText(player.getLocation().listMe(board, true, false, false));
    }

    public void update(Player p, Board b){
        player = p;
        board = b;
        updateRoomAttributes();
        updatePlayers();
    }
}
