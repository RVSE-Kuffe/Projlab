package logarlecTheGame.Model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    List<Door> door = new ArrayList<>();
    Boolean gassed;
    int roomid;
    int capacity;
    boolean cleaner;
public
    void removeItem(Item i){System.out.println("Meghívtak");}
    void removePlayer(Player p){System.out.println("Meghívtak");}
    void addItem(Item i){System.out.println("Meghívtak");}
    void addPlayer(Player p){System.out.println("Meghívtak");}
    boolean mergeRoom(Room r1, Room r2){System.out.println("Meghívtak");return true;}
    void Split(Room r1, Room r2){System.out.println("Meghívtak");}
    void addDoor(Door d){System.out.println("Meghívtak");}
    void pickupItem(int i, Player p){System.out.println("Meghívtak");}
    boolean killPlayer(Player p){System.out.println("Meghívtak");return true;}
    void enough(){System.out.println("Meghívtak");}
    void win(){System.out.println("Meghívtak");}
    boolean changeRoom(Player p, Room r){System.out.println("Meghívtak"); return true;}
    Room newRoom(){System.out.println("Meghívtak");return room;}
    void stunRoom(){System.out.println("Meghívtak");}
    void pvp(Player p){System.out.println("Meghívtak");}
    void makeGassed(){System.out.println("Meghívtak");}
    void makeClean(){System.out.println("Meghívtak");}
    void killAll(Player p){System.out.println("Meghívtak");}
    boolean acceptPairing(Board b,Room r){System.out.println("Meghívtak");return true;}

}
