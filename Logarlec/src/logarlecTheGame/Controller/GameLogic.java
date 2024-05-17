package logarlecTheGame.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import logarlecTheGame.Model.Board;
import logarlecTheGame.Model.Player;
import logarlecTheGame.Model.Student;
import logarlecTheGame.Model.Teacher;
import logarlecTheGame.View.View;

public class GameLogic implements Serializable{
    
    private static final long SerialVersionUID = 2L;
    private Board board;
    private List<Student> students;
    private List<Player> otherPlayers;
    private Student currentPlayer;
    private int currentPlayerIndex;
    private int actionPoints;
    private boolean win;
    private transient View view;

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
        setCurrentPlayer(students.get(currentPlayerIndex));
    }

    public boolean getWin(){
        return win;
    }

    public void healAll(){
        for(int i = 0; i < students.size(); i++){
            students.get(i).heal();
        }
        for(int i=0; i<otherPlayers.size();i++){
            otherPlayers.get(i).heal();
        }
    }

    public void deadStudent(Player student) {
        students.remove(student);
        JOptionPane.showMessageDialog(null, "died: "+ board.objectToString(student));
        if (students.isEmpty()) {
            endGame(false);
        }
        if(student==currentPlayer){
        currentPlayerIndex++;
                //System.out.println(currentPlayerIndex);
                if (currentPlayerIndex >= students.size()) {
                   // System.out.println("itt");
                    for (Player player : otherPlayers) {
                        player.randomAction();
                    }
                    healAll();
                    
                    board.iterate();
                    currentPlayerIndex = 0;
                }
                actionPoints = 3; //új kör kezdete: visszaállítjuk az actionPoints-ot
                currentPlayer = students.get(currentPlayerIndex);
                JOptionPane.showMessageDialog(null, "Következő játékos: "+board.objectToString(currentPlayer));
            }
    }

    public void turn() {
        if (!isGameEnded()) {
            actionPoints --;
            //System.out.println("before"+actionPoints);
            if (currentPlayer.getIsStunned() || actionPoints == 0) {
                //System.out.println(actionPoints);
                currentPlayerIndex++;
                //System.out.println(currentPlayerIndex);
                if (currentPlayerIndex >= students.size()) {
                    //System.out.println("itt");
                    for (Player player : otherPlayers) {
                        player.randomAction();
                        if(board.objectToString(player)=="janitor1")System.out.println(board.objectToString(player.getLocation()));
                    }
                    healAll();
                    
                    board.iterate();
                     currentPlayerIndex = 0;
                }
                actionPoints = 3; //új kör kezdete: visszaállítjuk az actionPoints-ot
                currentPlayer = students.get(currentPlayerIndex);
                //System.out.println("currentPlayer "+ currentPlayerIndex);
                //System.out.println("at end: "+actionPoints);
                JOptionPane.showMessageDialog(null, "Következő játékos: "+board.objectToString(currentPlayer));
            }
        }
        view.update();
    }

    public void setView(View v){
        view = v;
    }

    public void endGame(boolean won) {
        if (won) {
            win=true;
            JOptionPane.showMessageDialog(null, "Gratulálunk, nyertek!");
        } else {
            win=false;
            JOptionPane.showMessageDialog(null, "Sajnos vesztettetek...");
        }
    }

    public void setCurrentPlayer(Student player) {
        this.currentPlayer = player;
    }

    public Student getCurrentPlayer() {
        return this.currentPlayer;
    }

    public Board getBoard() {
        return this.board;
    }

    public void setCurrentPlayerIndex(int index) {
        this.currentPlayerIndex =index;
    }

    public boolean isGameEnded() {
        if (students.isEmpty()) {
            endGame(false); // Ha üres a diákok listája, akkor veszítettek
            return true;
        }
        return false;
    }

    public void addToOtherPlayers(Player p){
        this.otherPlayers.add(p);
    }

    public int getRemainingRounds(){
        return actionPoints;
    }
}