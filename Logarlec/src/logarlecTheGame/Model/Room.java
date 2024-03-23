package logarlecTheGame.Model;

import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;
import logarlecTheGame.Model.Item.Item;

public class Room {
    private Skeleton sk;

    public Room(Skeleton s, String n) {
        sk = s;
        sk.names.put(this, n);
    }

    List<Door> doorlist = new ArrayList<>();
    Boolean gassed;
    int roomid;
    int capacity;
    boolean cleaner;

public
    void removeItem(Item i){System.out.println(sk.names.get(this) + "removeItem");}
    void removePlayer(Player p){System.out.println(sk.names.get(this) + "removePlayer");}
    void addItem(Item i){System.out.println(sk.names.get(this) + "addItem");}
    void addPlayer(Player p){System.out.println(sk.names.get(this) + "addPlayer");}
    boolean mergeRoom(Room r1, Room r2){System.out.println(sk.names.get(this) + "mergeRoom");return true;}
    void Split(Room r1, Room r2){System.out.println(sk.names.get(this) + "Split");}
    public void addDoor(Door d){System.out.println(sk.names.get(this) + "addDoor");}
    void pickupItem(int i, Player p){System.out.println(sk.names.get(this) + "pickUpItem");}
    boolean killPlayer(Player p){System.out.println(sk.names.get(this) + "killPlayer");return true;}
    void enough(){System.out.println(sk.names.get(this) + "enough");}
    
    void win(){System.out.println(sk.names.get(this) + "win");}
    boolean changeRoom(Player p, Room r){System.out.println(sk.names.get(this) + "changeRoom"); return true;}
    Room newRoom(){System.out.println(sk.names.get(this) + "newRoom");return room;}
    void stunRoom(){System.out.println(sk.names.get(this) + "stunRoom");}
    void pvp(Player p){System.out.println(sk.names.get(this) + "pvp");}
    void makeGassed(){System.out.println(sk.names.get(this) + "makeGassed");}
    void makeClean(){System.out.println(sk.names.get(this) + "makeClean");}
    void killAll(Player p){System.out.println(sk.names.get(this) + "killAll");}
    boolean acceptPairing(Board b,Room r){System.out.println(sk.names.get(this) + "acceptPairing");return true;}

}
