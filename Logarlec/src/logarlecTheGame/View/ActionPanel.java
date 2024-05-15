package logarlecTheGame.View;

import javax.swing.*;

import logarlecTheGame.Model.*;
import logarlecTheGame.Model.Item.Item;
import logarlecTheGame.Model.Item.Transistor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class ActionPanel extends JPanel{

    Student student;
    Board board;
    View view;
    JComboBox<String> roomItemBox;
    JComboBox<String> playerItemBox1;
    JComboBox<String> playerItemBox2;
    JComboBox<String> roomDoorBox;

    public ActionPanel(Student s, Board b, View v) {
        //JPanel mainPanel = new JPanel();
        //mainPanel.setLayout(null);
        this.student=s;
        this.board=b;
        view = v;
        //JPanel panel = new JPanel();
        this.setLayout(new GridLayout(0, 3, 10, 10));
        this.setBounds(100, 400, 500, 160); // A panel pozíciójának és méretének beállítása

        //String[] options = {"Opció 1", "Opció 2", "Opció 3"};
        Font font = new Font("Arial", Font.PLAIN, 20);

        JLabel s1 = new JLabel("Szoba tárgyai:");
        s1.setFont(font);
        this.add(s1);
        roomItemBox = new JComboBox<>();
        this.add(roomItemBox);
        JButton pickUpButton = new JButton("Tárgyfelvétel");
        pickUpButton.addActionListener(new ActionListener() {    
            public void actionPerformed(ActionEvent e) {
                pickUp();
                view.vTurn();
            }
        });
        this.add(pickUpButton);

        JLabel s2 = new JLabel("Játékos tárgyai:");
        s2.setFont(font);
        this.add(s2);
        playerItemBox1 = new JComboBox<>();
        this.add(playerItemBox1);
        JButton putDownButton = new JButton("Tárgyletétel");
        putDownButton.addActionListener(new ActionListener() {    
            public void actionPerformed(ActionEvent e) {
                putDown();
                view.vTurn();
            }
        });
        this.add(putDownButton);

        JLabel s3 = new JLabel("Játékos tárgyai:");
        s3.setFont(font);
        this.add(s3);
        playerItemBox2 = new JComboBox<>();
        this.add(playerItemBox2);
        JButton pairButton = new JButton("Párosítás");
        pairButton.addActionListener(new ActionListener() {    
            public void actionPerformed(ActionEvent e) {
                pair();
                view.vTurn();
            }
        });
        this.add(pairButton);

        JLabel s4 = new JLabel("Szoba ajtói:");
        s4.setFont(font);
        this.add(s4);
        roomDoorBox = new JComboBox<>();
        this.add(roomDoorBox);
        JButton moveButton = new JButton("Ajtóhasználat");
        moveButton.addActionListener(new ActionListener() {    
            public void actionPerformed(ActionEvent e) {
                move();
                view.vTurn();
            }
        });
        this.add(moveButton);
        
        this.update(s, b);
    }

    public void update(Player s, Board b){
        student = (Student)s;
        board = b;
        updateBoxes();
    }

    public void updateBoxes(){
        roomItemBox.removeAllItems();
        for (int i = 0; i < student.getLocation().items().size(); i++) {
            roomItemBox.addItem(board.objectToString(student.getLocation().items().get(i)));
        }

        playerItemBox1.removeAllItems();
        playerItemBox2.removeAllItems();
        for (int i = 0; i < student.items().size(); i++) {
            playerItemBox1.addItem(board.objectToString(student.items().get(i)));
            playerItemBox2.addItem(board.objectToString(student.items().get(i)));
        }

        roomDoorBox.removeAllItems();
        for (int i = 0; i < student.getLocation().doors().size(); i++) {
            roomDoorBox.addItem(board.objectToString(student.getLocation().doors().get(i)));
        } 
    }

    public void pair(){
        try{
            Transistor t1 = (Transistor)board.stringToObject((String)playerItemBox1.getSelectedItem());
            Transistor t2 = (Transistor)board.stringToObject((String)playerItemBox2.getSelectedItem());
            student.pair(t1, t2);
        }
        catch(ClassCastException e){
            Item i1 = (Item)board.stringToObject((String)playerItemBox1.getSelectedItem());
            Item i2 = (Item)board.stringToObject((String)playerItemBox2.getSelectedItem());
            student.pair(i1, i2);
        }
    }

    public void move(){
        if(roomDoorBox.getSelectedItem()!=null){
        Door temp = (Door)board.stringToObject((String)roomDoorBox.getSelectedItem());
        student.changeR(temp);
        }
        else JOptionPane.showMessageDialog(null, "rontott kör!");
    }

    public void pickUp(){
        if(roomItemBox.getSelectedItem()!=null){
        Item i = (Item)board.stringToObject((String)roomItemBox.getSelectedItem());
        student.pickUpItem(i);
        }
        else JOptionPane.showMessageDialog(null, "rontott kör!");
    }

    public void putDown(){
        if(playerItemBox1.getSelectedItem()!=null){
        Item i = (Item)board.stringToObject((String)playerItemBox1.getSelectedItem());
        student.putDown(i);}
    }
}
