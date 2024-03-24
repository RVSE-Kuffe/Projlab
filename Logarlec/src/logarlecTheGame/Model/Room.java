package logarlecTheGame.Model;

import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;
import logarlecTheGame.Model.Item.Item;

public class Room {
<<<<<<< HEAD
    private Skeleton sk;
=======
    Skeleton sk;
>>>>>>> e07a787602d64e731d83e800e29ad961445b214f
    List<Door> doorlist = new ArrayList<>();
    Boolean gassed=false;
    int roomid;
    int capacity;
    boolean cleaner=false;
    public Room(Skeleton s, String n,int rid, int cap) {
        sk = s;
        sk.names.put(this, n);
        roomid=rid;
        capacity=cap;
    }

    

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
    
    void win(){System.out.println(sk.names.get(this) + "win");}

    boolean changeRoom(Player p, Room r){System.out.println(sk.names.get(this) + "changeRoom"); return true;}

    Room newRoom(){
        System.out.println(sk.names.get(this) + "newRoom");
        Room room2 =new Room(this.sk, "splitNewRoom", this.capacity);
        Door splitDoor = new Door(this.sk, "splitDoor", this, room2);
        this.addDoor(splitDoor);
        room2.addDoor(splitDoor);

        return room2;
    }



    void stunRoom(){System.out.println(sk.names.get(this) + "stunRoom");}
    void pvp(Player p){System.out.println(sk.names.get(this) + "pvp");}
    public void makeGassed(){
        System.out.println(sk.names.get(this) + "makeGassed");
        gassed=true;
        System.out.println(sk.names.get(this) + "makeGassed returned with void");
        return;
    }
    void makeClean(){
        System.out.println(sk.names.get(this) + "makeClean");
        cleaner=true;
        System.out.println(sk.names.get(this) + "makeClean returned with void");
        return;
    }
    void killAll(Player p){System.out.println(sk.names.get(this) + "killAll");}
    boolean acceptPairing(Board b,Room r){System.out.println(sk.names.get(this) + "acceptPairing");return true;}

}
