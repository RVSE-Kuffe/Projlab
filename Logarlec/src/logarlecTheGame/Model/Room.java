package logarlecTheGame.Model;

import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;
import logarlecTheGame.Model.Item.Item;

public class Room {
    Skeleton sk;
    List<Door> doorlist = new ArrayList<>();
    Boolean gassed;
    int roomid;
    int capacity;
    boolean cleaner;

    public Room(Skeleton s, String n, int c) {
        sk = s;
        sk.names.put(this, n);
        capacity = c;
    }

    public void removeItem(Item i){System.out.println(sk.names.get(this) + "removeItem");}
    public void removePlayer(Player p){System.out.println(sk.names.get(this) + "removePlayer");}
    public void addItem(Item i){System.out.println(sk.names.get(this) + "addItem");}
    public void addPlayer(Player p){System.out.println(sk.names.get(this) + "addPlayer");}
    public boolean mergeRoom(Room r1, Room r2){System.out.println(sk.names.get(this) + "mergeRoom");return true;}
    public void Split(Room r1, Room r2){System.out.println(sk.names.get(this) + "Split");}
    public void addDoor(Door d){System.out.println(sk.names.get(this) + "addDoor");}
    public void pickupItem(int i, Player p){System.out.println(sk.names.get(this) + "pickUpItem");}
    public boolean killPlayer(Player p){System.out.println(sk.names.get(this) + "killPlayer");return true;}
    public void enough(){System.out.println(sk.names.get(this) + "enough");}
    
    public void win(){System.out.println(sk.names.get(this) + "win");}
    public boolean changeRoom(Player p, Room r){System.out.println(sk.names.get(this) + "changeRoom"); return true;}
    public Room newRoom(){System.out.println(sk.names.get(this) + "newRoom");return Room;}
    public void stunRoom(){System.out.println(sk.names.get(this) + "stunRoom");}
    public void pvp(Player p){System.out.println(sk.names.get(this) + "pvp");}
    public void makeGassed(){System.out.println(sk.names.get(this) + "makeGassed");}
    public void makeClean(){System.out.println(sk.names.get(this) + "makeClean");}
    public void killAll(Player p){System.out.println(sk.names.get(this) + "killAll");}
    public boolean acceptPairing(Board b,Room r){System.out.println(sk.names.get(this) + "acceptPairing");return true;}

}
