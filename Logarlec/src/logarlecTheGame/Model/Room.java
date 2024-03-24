package logarlecTheGame.Model;

import java.rmi.server.Skeleton;
import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;
import logarlecTheGame.Model.Item.Item;

public class Room {
    private Skeleton sk;
    List<Door> doorlist = new ArrayList<>();
    List<Player> playerlist = new ArrayList<>();
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
    public void makeGassed(){
        System.out.println(sk.names.get(this) + "makeGassed");
        gassed=true;
        for(Player p : playerlist){
            p.stun();
        }
        System.out.println(sk.names.get(this) + "makeGassed returned with void");
        return;
    }
    public void makeClean(){
        System.out.println(sk.names.get(this) + "makeClean");
        cleaner=true;
        for(Player p : playerlist){
            p.stunTeacher();
        }
        System.out.println(sk.names.get(this) + "makeClean returned with void");
        return;
    }
    void killAll(Player p){System.out.println(sk.names.get(this) + "killAll");}
    boolean acceptPairing(Board b,Room r){System.out.println(sk.names.get(this) + "acceptPairing");return true;}

}
