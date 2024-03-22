package logarlecTheGame.Model;

import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;

public class Board {
    List<Room> room = new ArrayList<>();
    private Skeleton sk;

    public Board(Skeleton s, String n) {
        sk = s;
        sk.names.put(this, n);
    }
public
    void init(){System.out.println(sk.names.get(this) + "init");}
    void gameOver(){System.out.println("Meghívtak");}
    void modify(){System.out.println("Meghívtak");}
    void addRoom(Room r1){System.out.println("Meghívtak");}
    void removeRoom(Room r1){System.out.println("Meghívtak");}
    boolean forceMerge(Room r1, Room r2){System.out.println("Meghívtak");return true;}
    void forcesplit(Room r){System.out.println("Meghívtak");}
    void win(){System.out.println("Meghívtak");}

}
