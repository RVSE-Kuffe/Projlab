package logarlecTheGame.Model;

import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;
import logarlecTheGame.Model.Item.Item;

public class Room {
    protected Skeleton sk;
    protected List<Door> doorList = new ArrayList<>();
    protected List<Player> playerList = new ArrayList<>();
    protected List<Item> itemList = new ArrayList<>();
    protected boolean gassed=false;
    protected int roomid;
    protected int capacity;
    protected boolean cleaner=false;
    public Room(Skeleton s, String n,int rid, int cap) {
        sk = s;
        sk.names.put(this, n);
        roomid=rid;
        capacity=cap;
    }

    

    public void removeItem(Item i){
        System.out.println(sk.names.get(this) + "removeItem");
        itemList.remove(i);
    }

    void removePlayer(Player p){System.out.println(sk.names.get(this) + "removePlayer");}

    public void addItem(Item i){
        System.out.println(sk.names.get(this) + "addItem");
        itemList.add(i);
    }

    public boolean addPlayer(Player p){
        System.out.println(sk.names.get(this) + "addPlayer");
        if(playerList.size()<capacity){
            playerList.add(p);
            if(gassed){
                p.stun();
            }
            if(cleaner){
                p.stunTeacher();
            }
            System.out.println(sk.names.get(this) + " addPlayer returned true");
            return true;
        }
        System.out.println(sk.names.get(this) + " addPlayer returned false");
        return false;
    }

    boolean mergeRoom(Room r1, Room r2){System.out.println(sk.names.get(this) + "mergeRoom");return true;}
    void Split(Room r1, Room r2){System.out.println(sk.names.get(this) + "Split");}

    public void addDoor(Door d){
        System.out.println(sk.names.get(this) + " addDoor");
        doorList.add(d);
        System.out.println(sk.names.get(this) + " addDoor returned");
    }

    void pickupItem(int i, Player p){System.out.println(sk.names.get(this) + "pickUpItem");}
    boolean killPlayer(Player p){System.out.println(sk.names.get(this) + "killPlayer");return true;}
    void enough(){System.out.println(sk.names.get(this) + "enough");}
    
    void win(){System.out.println(sk.names.get(this) + "win");}

    boolean changeRoom(Player p, Door d){
        System.out.println(sk.names.get(this) + "changeRoom(" + sk.names.get(p) + ", " + sk.names.get(d) + ")");
        if(d.transferPlayer(p)){
            System.out.println(sk.names.get(this) + "changeRoom returned true");
            return true;
        } 
        System.out.println(sk.names.get(this) + "changeRoom returned false");
        return false;
    }

    Room newRoom(){
        System.out.println(sk.names.get(this) + " newRoom");
        Room room2 =new Room(this.sk, "splitNewRoom", this.roomid + 1, this.capacity);
        Door splitDoor = new Door(this.sk, "splitDoor", this, room2);
        this.addDoor(splitDoor);
        room2.addDoor(splitDoor);

        System.out.println(sk.names.get(this) + " newRoom returned");
        return room2;
    }

    void stunRoom(){System.out.println(sk.names.get(this) + "stunRoom");}

    public void pvp(Player p){
        System.out.println(sk.names.get(this) + " pvp(" + sk.names.get(this) + ")");
        for(Player pl : playerList){
            if(!pl.equals(p)){
                pl.kill(p);
            }
        }
    }

    public void makeGassed(){
        System.out.println(sk.names.get(this) + " makeGassed");
        gassed=true;
        for(Player p : playerList){
            p.stun();
        }
        System.out.println(sk.names.get(this) + " makeGassed returned with void");
        return;
    }

    public void makeClean(){
        System.out.println(sk.names.get(this) + "makeClean");
        cleaner=true;
        for(Player p : playerList){
            p.stunTeacher();
        }
        System.out.println(sk.names.get(this) + "makeClean returned with void");
        return;
    }

    /**
     * Végigmegy a szoba összes player-én és meghívja rá a die metódust, kivéve, ha nem egyenlő a paraméterrel kapottal
     * @param p     a Player, aki megöl mindenkit
     * @return      void
     */
    void killAll(Player p){
        System.out.println(sk.names.get(this) + "killAll(" + sk.names.get(p) + ")");

        for(Player pl : playerList){
            if(! p.equals(pl)){
                pl.die();
            }
        }
    }
    boolean acceptPairing(Board b,Room r){System.out.println(sk.names.get(this) + "acceptPairing");return true;}

}
