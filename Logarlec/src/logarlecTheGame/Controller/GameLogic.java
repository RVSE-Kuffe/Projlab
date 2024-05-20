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

/**
 * A játék logikáját kezelő osztály.
 */
public class GameLogic implements Serializable{
    
    /** A játék táblája. */
    private Board board;

    /** A játékban lévő diákok listája, őket irányítják a felhasználók */
    private List<Student> students;

    /** A játékban lévő egyéb játékosok (tanárok, takarítók) listája */
    private List<Player> otherPlayers;

    /** Az aktuális diák, akinek a köre van */
    private Student currentPlayer;

    /** Az aktuális diák indexe */
    private int currentPlayerIndex;

    /** Az aktuális körben megmaradt, felhasználható akciópontok száma */
    private int actionPoints;

    /** A játék nézetét reprezentáló objektum */
    private transient View view;


    /**
     * Konstruktor, amely inicializálja a játékot a megadott számú diákkal, 
     * ami a GameMenu-ben állítható be
     * Ezen felül beállítja a kezdő játékost
     * @param numberOfStudents A játékban résztvevő diákok száma
     */
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

    /**
     * Minden diákot és egyéb játékost megpróbál meggyógyítani
     * Ciklusok (mindenki köre) után hívódik
     */
    public void healAll(){
        for(int i = 0; i < students.size(); i++){
            students.get(i).heal();
        }
        for(int i=0; i<otherPlayers.size();i++){
            otherPlayers.get(i).heal();
        }
    }


    /**
     * Kezeli a diák halálát, eltávolítja a listából és ellenőrzi a játék végét
     * Ha az épp aktuális játékos halt meg továbbadja a kört
     * @param student A meghalt diák.
     */
    public void deadStudent(Player student) {
        students.remove(student);
        JOptionPane.showMessageDialog(null, "Student died: "+ board.objectToString(student));
        if (students.isEmpty()) {
            endGame(false);
            return;
        }
        if(student==currentPlayer){
        currentPlayerIndex++;
                if (currentPlayerIndex >= students.size()) {
                    for (Player player : otherPlayers) {
                        player.randomAction();
                    }
                    healAll();
                    
                    board.iterate();
                    currentPlayerIndex = 0;
                }
                actionPoints = 3; //új kör kezdete: visszaállítjuk az actionPoints-ot
                currentPlayer = students.get(currentPlayerIndex);
                JOptionPane.showMessageDialog(null, "Next player: "+board.objectToString(currentPlayer));
            }
    }
    /**
     * A kör továbbadását kezelő függvény, vizsgálja a játék végét is
     * Ha még van akciópontja az aktuális játékosnak, akkor csak csökkenti azt és frissíti a nézetet
     * Ha már nincs vagy stunolva van akkor továbbadja a kört
     * Ha már nincs diák akinek tovább lehetne adni (ő a lista legkésőbbi eleme), akkor elvégzi az egyéb játékosok lépéseit,
     * megpróbál mindenkit gyógyítani és lépteti a körfüggő játékelemeket
     */
    public void turn() {
        if (!isGameEnded()) {
            actionPoints --;

            if (currentPlayer.getIsStunned() || actionPoints == 0) {
                currentPlayerIndex++;
                if (currentPlayerIndex >= students.size()) {
                    for (Player player : otherPlayers) {
                        player.randomAction();
                    }
                    healAll();

                    if(students.isEmpty()){
                        return;
                    }
                    
                    board.iterate();
                     currentPlayerIndex = 0;
                }
                actionPoints = 3; //új kör kezdete: visszaállítjuk az actionPoints-ot
                currentPlayer = students.get(currentPlayerIndex);
                JOptionPane.showMessageDialog(null, "Next player: "+board.objectToString(currentPlayer));
            }
        }
        view.update();
    }


    /**
     * Beállítja a játék nézetét
     * 
     * @param v A játék nézete
     */
    public void setView(View v){
        view = v;
    }


    /**
     * Befejezi a játékot, attól függően, hogy győzelem (true) vagy vesztés történt
     * és meghívja a nézet megfelelő metódusát
     * 
     * @param won Igaz, ha a játékosok nyertek, hamis, ha vesztettek.
     */
    public void endGame(boolean won) {
        if (won) {
            view.won();
        } else {
            view.lost();
        }
    }

    /**
     * Beállítja az aktuális játékost
     * 
     * @param player Az aktuális játékos referenciája
     */
    public void setCurrentPlayer(Student player) {
        this.currentPlayer = player;
    }

    /**
     * Visszaadja az aktuális játékost.
     * 
     * @return Az aktuális játékos.
     */
    public Student getCurrentPlayer() {
        return this.currentPlayer;
    }


     /**
     * Visszaadja a játék tábláját
     * 
     * @return A játék táblája
     */
    public Board getBoard() {
        return this.board;
    }

     /**
     * Beállítja az aktuális játékos indexét
     * 
     * @param index Az aktuális játékos indexe
     */
    public void setCurrentPlayerIndex(int index) {
        this.currentPlayerIndex =index;
    }


    /**
     * Ellenőrzi, hogy véget ért-e a játék
     * Akkor ér véget ebben az esetben ha már nincs több diák, ilyenkor vesztettek
     * @return Igaz, ha véget ért, különben hamis
     */
    public boolean isGameEnded() {
        if (students.isEmpty()) {
            endGame(false); // Ha üres a diákok listája, akkor veszítettek
            return true;
        }
        return false;
    }

    /**
     * Hozzáad egy játékost az egyéb játékosok listájához
     * 
     * @param p A hozzáadandó játékos
     */
    public void addToOtherPlayers(Player p){
        this.otherPlayers.add(p);
    }

    /**
     * Visszaadja az aktuális körben megmaradt akciópontokat
     * 
     * @return Az aktuális körben megmaradt akciópontok száma
     */
    public int getRemainingRounds(){
        return actionPoints;
    }
}