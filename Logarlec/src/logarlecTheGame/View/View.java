package logarlecTheGame.View;
import javax.swing.*;

import logarlecTheGame.Controller.GameLogic;
import logarlecTheGame.Model.Board;
import logarlecTheGame.Model.Student;

import java.awt.*;
import java.io.Serializable;

/**
 * A View osztály felelős a játék grafikus felületének megjelenítéséért.
 * Ez az osztály létrehozza és frissíti az ablakot, valamint kezeli a játék
 * különböző állapotait, mint a győzelem és vereség.
 */
public class View implements Serializable{
    private JFrame frame;
    private RoomPanel roomPanel;
    private ActionPanel actionPanel;
    private BoardPanel boardPanel;
    private GameLogic gameLogic;
    private WinImage win;
    private LostImage loose;


/**
     * A View konstruktora, amely inicializálja a játék logikáját és a győzelem és vereség képeit.
     * Beállítja a láthatóságokat
     *
     * @param gl A játék logikáját kezelő GameLogic objektum.
     */
    public View(GameLogic gl){
        gameLogic = gl;
        win = new WinImage();
        loose = new LostImage();
        win.setVisible(false);
        loose.setVisible(false);
    }
 /**
     * A játék ablakának inicializálása és a különböző panelek hozzáadása.
     * Beállítja az ablak méretét, 
     * elrendezését és 
     * láthatóságát.
     * Minden panelt a megfelelő paraméterekkel inicializál és 
     * hozzáadja őket a frame-hez a megfelelő helyre
     */
    public void init() {
        frame = new JFrame("Game by ripgyork");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);

        frame.setLayout(new GridLayout(2, 2));

        boardPanel = new BoardPanel(gameLogic.getCurrentPlayer(), gameLogic.getBoard());
        frame.add(boardPanel);
        boardPanel.update(gameLogic.getCurrentPlayer(), gameLogic.getBoard());
        roomPanel = new RoomPanel(gameLogic.getCurrentPlayer(), gameLogic.getBoard(), gameLogic);
        frame.add(roomPanel);
        actionPanel = new ActionPanel(gameLogic.getCurrentPlayer(), gameLogic.getBoard(), this);
        frame.add(actionPanel);
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(Color.YELLOW);
        frame.add(emptyPanel);
        frame.setVisible(true);
    }
    /**
     * Frissíti a játék állapotát a különböző panelekben.
     * Lekéri az aktuális játékost és táblát,vagyis annak állását,
     * majd frissíti a paneleket.
     * Ezeknek átadja a jatékost és a táblát, hisz a paneleknek is ismernie kell őket
     */
    public void update(){
        Student p=gameLogic.getCurrentPlayer();
        Board b=gameLogic.getBoard();
        actionPanel.update(p,b);
        roomPanel.update(p,b);
        boardPanel.update(p,b);
    }

/**
     * Lehetővé teszi a játékban egy kör végrehajtását a játék logikája alapján.
     * Meghívja a logika turn metódusát
     */
    public void vTurn(){
        gameLogic.turn();
    }

     /**
     * Kezeli a vereség állapotát: 
     * elrejti a fő ablakot és megjeleníti a vereség képernyőt.
     */
    public void lost(){
        frame.setVisible(false);
        loose.setVisible(true);
        //ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        //Runnable task = () -> System.exit(0);
        //scheduler.schedule(task, 10, TimeUnit.SECONDS);
    }

    /**
     * Kezeli a győzelem állapotát: 
     * elrejti a fő ablakot és megjeleníti a győzelem képernyőt.
     */
    public void won(){
        frame.setVisible(false);
        win.setVisible(true);
    }
}