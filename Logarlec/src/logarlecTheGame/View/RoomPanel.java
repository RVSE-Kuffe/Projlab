package logarlecTheGame.View;

import javax.swing.*;

import logarlecTheGame.Model.*;

import java.awt.*;

public class RoomPanel extends JPanel {
    Player player;
    Board board;
    private JTextField roomAttributesField;
    private JTextField playersField;

    public RoomPanel() {
        setLayout(new GridLayout(1, 2));

        roomAttributesField = new JTextField();
        roomAttributesField.setEditable(false);
        add(roomAttributesField);

        playersField = new JTextField();
        playersField.setEditable(false);
        add(playersField);
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
    }

    public void redraw(){
        update(player, board);
    }
}