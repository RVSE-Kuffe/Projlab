package logarlecTheGame.Model;

import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;

public class Board {
    List<Room> roomList = new ArrayList<>();
    private Skeleton sk;

    public Board(Skeleton s, String n) {
        sk = s;
        sk.names.put(this, n);
    }

    public void init(){System.out.println(sk.names.get(this) + "init");}

    public void gameOver(){System.out.println(sk.names.get(this) + "gameOver");}

    public void modify(){System.out.println(sk.names.get(this) + "modify");}

    public void addRoom(Room r1){
        roomList.add(r1);
        System.out.println(sk.names.get(this) + " addRoom(" + sk.names.get(r1) + ")");

        System.out.println(sk.names.get(this) + " addRoom returned");
    }

    public void removeRoom(Room r1){
        roomList.remove(r1);
        System.out.println(sk.names.get(this) + "removeRoom");
    }
    public boolean forceMerge(Room r1, Room r2){System.out.println(sk.names.get(this) + "forceMerge");return true;}

    public void forceSplit(Room r){
        System.out.println(sk.names.get(this) + "forceSplit(" + sk.names.get(r) + ")");
        Room newRoom= r.newRoom();
    
        addRoom(newRoom);
        System.out.println(sk.names.get(this) + " return forceSplit(" + sk.names.get(r) + ")");
    }

    public void win(){System.out.println(sk.names.get(this) + "win");}

}
