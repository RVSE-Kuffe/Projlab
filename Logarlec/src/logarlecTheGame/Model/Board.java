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
    void gameOver(){System.out.println(sk.names.get(this) + "gameOver");}
    void modify(){System.out.println(sk.names.get(this) + "modify");}
    void addRoom(Room r1){System.out.println(sk.names.get(this) + "addRoom");}
    void removeRoom(Room r1){System.out.println(sk.names.get(this) + "removeRoom");}
    boolean forceMerge(Room r1, Room r2){System.out.println(sk.names.get(this) + "forceMerge");return true;}
    void forcesplit(Room r){System.out.println(sk.names.get(this) + "forceSplit");}
    void win(){System.out.println(sk.names.get(this) + "win");}

}
