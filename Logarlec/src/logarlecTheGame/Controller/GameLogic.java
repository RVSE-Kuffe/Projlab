package logarlecTheGame.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import logarlecTheGame.Model.*;
import logarlecTheGame.View.View;

public class GameLogic implements Serializable{
    
    private static final long SerialVersionUID = 2L;
    private Board board;
    private List<Student> students;
    private List<Player> otherPlayers;
    private Player currentPlayer;
    private int currentPlayerIndex;
    private int actionPoints;
    private View view;

    public GameLogic(int numberOfStudents){
        board = new Board(this);
        students = new ArrayList<>();
        otherPlayers = new ArrayList<>();
        int id=1;
        for(int i=0; i<numberOfStudents;i++){
            Student student = new Student(id, null);
            students.add(student);
            String studentName = "student" + id;
            board.addToBoard(student, studentName);
            id++;
        }
        if(students.size()<=3){
            Teacher teacher = new Teacher(1, null);
            board.addToBoard(teacher,"teacher1");
            otherPlayers.add(teacher);
        }
        if(students.size()>3&&students.size()<=6){
            Teacher teacher1 = new Teacher(1, null);
            Teacher teacher2 = new Teacher(2, null);

            board.addToBoard(teacher1,"teacher1");
            board.addToBoard(teacher2,"teacher2");
            otherPlayers.add(teacher1);
            otherPlayers.add(teacher2);
        }
        if(students.size()>6&&students.size()<=9){
            Teacher teacher1 = new Teacher(1, null);
            Teacher teacher2 = new Teacher(2, null);
            Teacher teacher3 = new Teacher(3, null);

            board.addToBoard(teacher1,"teacher1");
            board.addToBoard(teacher2,"teacher2");
            board.addToBoard(teacher3,"teacher3");

            otherPlayers.add(teacher1);
            otherPlayers.add(teacher2);
            otherPlayers.add(teacher3);
        }
        if(students.size()>9){
            Teacher teacher1 = new Teacher(1, null);
            Teacher teacher2 = new Teacher(2, null);
            Teacher teacher3 = new Teacher(3, null);
            Teacher teacher4 = new Teacher(4, null);
            board.addToBoard(teacher1,"teacher1");
            board.addToBoard(teacher2,"teacher2");
            board.addToBoard(teacher3,"teacher3");
            board.addToBoard(teacher4,"teacher4");

            otherPlayers.add(teacher1);
            otherPlayers.add(teacher2);
            otherPlayers.add(teacher3);
            otherPlayers.add(teacher4);
        }

        actionPoints=3;
        board.init(students, otherPlayers);
    }

    public void healAll(){
        for(int i = 0; i < students.size(); i++){
            students.get(i).heal();
        }
    }

    public void deadStudent(Player student) {
        students.remove(student);
        if (students.isEmpty()) {
            endGame(false);
        }
    }

    public void turn() {
        if (!isGameEnded()) {
            actionPoints --;
            if (currentPlayer.getIsStunned() || actionPoints == 0) {
                currentPlayerIndex++;
                if (currentPlayerIndex >= students.size()) {
                    for (Player player : otherPlayers) {
                        player.randomAction();
                    }
                    //TODO: heal
                    
                    board.iterate();
                    currentPlayerIndex = 0;
                    actionPoints = 3; //új kör kezdete: visszaállítjuk az actionPoints-ot
                }
                currentPlayer = students.get(currentPlayerIndex);
            }
            view.update();
        }
    }


    public void endGame(boolean won) {
        if (won) {
            JOptionPane.showMessageDialog(null, "Gratulálunk, nyertek!");
        } else {
            JOptionPane.showMessageDialog(null, "Sajnos vesztettetek...");
        }
        System.exit(0);
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public Board getBoard() {
        return this.board;
    }

    public void setCurrentPlayerIndex(int index) {
        this.currentPlayerIndex =index;
    }

    private boolean isGameEnded() {
        if (students.isEmpty()) {
            endGame(false); // Ha üres a diákok listája, akkor veszítettek
            return true;
        }
        return false;
    }

    public void setView(View view){
        this.view=view;
    }

    public void addToOtherPlayers(Player p){
        this.otherPlayers.add(p);
    }



}
