package logarlecTheGame.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import logarlecTheGame.Model.Board;
import logarlecTheGame.Model.Player;
import logarlecTheGame.Model.Student;
import logarlecTheGame.View.View;

public class GameLogic {
    
    private Board board;
    private List<Student> students = new ArrayList<>();
    private List<Player> otherPlayers = new ArrayList<>();
    private Player currentPlayer;
    private int currentPlayerIndex;
    private int actionPoints;
    private View view;



    public void decreaseActionPoints() {
        if (actionPoints > 0) {
            actionPoints--;
        }
    }

    public void deadStudent(Student student) {
        students.remove(student);
        if (students.isEmpty()) {
            endGame(false);
        }
    }

    public void turn() {
        if (!isGameEnded()) {
            if (currentPlayer.getIsStunned() || actionPoints == 0) {
                currentPlayerIndex++;
                if (currentPlayerIndex >= students.size()) {
                    for (Player player : otherPlayers) {
                        player.randomAction();
                        
                    }
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

    private boolean isGameEnded() {
        if (students.isEmpty()) {
            endGame(false); // Ha üres a diákok listája, akkor veszítettek
            return true;
        }
        return false;
    }



}
