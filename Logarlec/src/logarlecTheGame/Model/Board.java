package logarlecTheGame.Model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    List<Room> room = new ArrayList<>();
public
    void init(){System.out.println("Meghívtak");}
    void gameOver(){System.out.println("Meghívtak");}
    void modify(){System.out.println("Meghívtak");}
    void addRoom(Room r1){System.out.println("Meghívtak");}
    void removeRoom(Room r1){System.out.println("Meghívtak");}
    boolean forceMerge(Room r1, Room r2){System.out.println("Meghívtak");return true;}
    void forcesplit(Room r){System.out.println("Meghívtak");}
    void win(){System.out.println("Meghívtak");}

}
