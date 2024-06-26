package logarlecTheGame.View;

import javax.swing.*;

import logarlecTheGame.Model.*;
import logarlecTheGame.Model.Item.Item;
import logarlecTheGame.Model.Item.Transistor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionPanel extends JPanel{

    Student student;
    Board board;
    View view;
    JComboBox<String> roomItemBox;
    JComboBox<String> playerItemBox1;
    JComboBox<String> playerItemBox2;
    JComboBox<String> roomDoorBox;

    /**
     * konstruktor, inicializálja és elrendezi az elemeket a panelen
     * @param s     a student, akinek a köre van 
     * @param b     a board, amin rajta vannak
     * @param v     a view, aminek a része a panel
     */
    public ActionPanel(Student s, Board b, View v) {
        //JPanel mainPanel = new JPanel();
        //mainPanel.setLayout(null);
        this.student=s;
        this.board=b;
        this.setBackground(Color.YELLOW);
        view = v;
        //JPanel panel = new JPanel();
        this.setLayout(new GridLayout(0, 3, 10, 10));
        this.setBounds(100, 400, 500, 160); // A panel pozíciójának és méretének beállítása

        //String[] options = {"Opció 1", "Opció 2", "Opció 3"};
        Font font = new Font("Arial", Font.PLAIN, 20);

        JLabel s1 = new JLabel("Room's items:");
        s1.setFont(font);
        this.add(s1);
        roomItemBox = new JComboBox<>();
        roomItemBox.setBackground(Color.CYAN);
        this.add(roomItemBox);
        JButton pickUpButton = new JButton("Pick Up Item");
        pickUpButton.addActionListener(new ActionListener() {    
            public void actionPerformed(ActionEvent e) {
                pickUp();
                view.vTurn();
            }
        });
        this.add(pickUpButton);

        JLabel s2 = new JLabel("Player's inventory:");
        s2.setFont(font);
        this.add(s2);
        playerItemBox1 = new JComboBox<>();
        playerItemBox1.setBackground(Color.CYAN);
        this.add(playerItemBox1);
        JButton putDownButton = new JButton("Put Down Item");
        putDownButton.addActionListener(new ActionListener() {    
            public void actionPerformed(ActionEvent e) {
                putDown();
                view.vTurn();
            }
        });
        this.add(putDownButton);

        JLabel s3 = new JLabel("Player's inventory:");
        s3.setFont(font);
        this.add(s3);
        playerItemBox2 = new JComboBox<>();
        playerItemBox2.setBackground(Color.CYAN);
        this.add(playerItemBox2);
        JButton pairButton = new JButton("Pair");
        pairButton.addActionListener(new ActionListener() {    
            public void actionPerformed(ActionEvent e) {
                pair();
                view.vTurn();
            }
        });
        this.add(pairButton);

        JLabel s4 = new JLabel("Room's doors:");
        s4.setFont(font);
        this.add(s4);
        roomDoorBox = new JComboBox<>();
        roomDoorBox.setBackground(Color.CYAN);
        this.add(roomDoorBox);
        JButton moveButton = new JButton("Use Door");
        moveButton.addActionListener(new ActionListener() {    
            public void actionPerformed(ActionEvent e) {
                move();
                view.vTurn();
            }
        });
        this.add(moveButton);
        
        this.update(s, b);
    }

    /**
     * beállítja, hogy ki az éppen aktuális játékos, és milyen pályán vannak
     * @param s     az éppen aktuális student      
     * @param b     az éppen aktuális pálya
     */
    public void update(Player s, Board b){
        student = (Student)s;
        board = b;
        updateBoxes();
    }

    /**
     * törli a comboBoxok tartalmát, majd feltölti az éppen aktuális játékos és szoba dolgaival
     */
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

    /**
     * megpróbálja összepárosítani a comboboxokban kiválasztott tárgyakat
     */
    public void pair(){
        try{
            if(playerItemBox1.getSelectedItem()!=null &&playerItemBox2.getSelectedItem()!=null){
            Transistor t1 = (Transistor)board.stringToObject((String)playerItemBox1.getSelectedItem());
            Transistor t2 = (Transistor)board.stringToObject((String)playerItemBox2.getSelectedItem());
            if(student.pair(t1, t2)){
                JOptionPane.showMessageDialog(null, "Selected items successfully paired!");
            }
            else{
                JOptionPane.showMessageDialog(null, "Selected items could not be paired!");
            }
            }
            else{
                JOptionPane.showMessageDialog(null, "You have to select two items to pair, you wasted an actionpoint!");
            }
        }
        catch(ClassCastException e){
            if(playerItemBox1.getSelectedItem()!=null &&playerItemBox2.getSelectedItem()!=null){
            Item i1 = (Item)board.stringToObject((String)playerItemBox1.getSelectedItem());
            Item i2 = (Item)board.stringToObject((String)playerItemBox2.getSelectedItem());
            if(student.pair(i1, i2)){
                JOptionPane.showMessageDialog(null, "Selected items successfully paired!");
            }
            else{
                JOptionPane.showMessageDialog(null, "Selected items could not be paired!");
            }
            }
            else{
                JOptionPane.showMessageDialog(null, "You have to select two items to pair, you wasted an actionpoint!");
            }
        }
    }

    /**
     * kezdeményezi az éppen aktuális játékos átlépését a comboboxban kiválasztott ajtón
     */
    public void move(){
        if(roomDoorBox.getSelectedItem()!=null){
        Door temp = (Door)board.stringToObject((String)roomDoorBox.getSelectedItem());
        if(!student.changeR(temp)){
            JOptionPane.showMessageDialog(null, "You can't use this door (it is either closed or a one-way door)");
        }
        }
        else JOptionPane.showMessageDialog(null, "No selected door, you wasted an actionpoint!");
    }

    /**
     * az éppen aktuális játékos felveszi a comboboxban kiválasztott tárgyat a szobából
     */
    public void pickUp(){
        if(roomItemBox.getSelectedItem()!=null){
        Item i = (Item)board.stringToObject((String)roomItemBox.getSelectedItem());
        if(!student.pickUpItem(i)) JOptionPane.showMessageDialog(null, "Either your inventory is full or the room is sticky, you can't pick up the selected item. You wasted an actionpoint!");
           }
        else JOptionPane.showMessageDialog(null, "No selected item, you wasted an actionpoint!");
    }

    /**
     * az éppen aktuális játékos leteszi a comboboxban kiválasztott tárgyát
     */
    public void putDown(){
        if(playerItemBox1.getSelectedItem()!=null){
        Item i = (Item)board.stringToObject((String)playerItemBox1.getSelectedItem());
        student.putDown(i);
    }
        else JOptionPane.showMessageDialog(null, "No selected item, you wasted an actionpoint!");
    }
}
