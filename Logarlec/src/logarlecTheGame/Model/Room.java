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
    protected int stickyCount = 0;

    /**
     * Szoba osztály konstruktora
     * beállítja a megfelelő attribútumokat
     */
    public Room(Skeleton s, String n,int rid, int cap) {
        sk = s;
        sk.names.put(this, n);
        roomid=rid;
        capacity=cap;
    }
    public int getCapacity(){
        return capacity;
    }
    public int getPlayerCount(){
        return playerList.size();
    }

    
    /**
     * szobából egy item eltávolítását végzi
     * kiveszi a listából
     * @param i az item amit ki akarunk venni
     */
    public boolean removeItem(Item i){
        System.out.println(sk.names.get(this) + "removeItem");
        if(stickyCount < 5){
            itemList.remove(i);
            return true;
        }
        return false;
    }
    /**
     * szobából egy player eltávolítását végzi
     * kiveszi a listából
     * @param p az player akit el akarunk távolítani
     */
    void removePlayer(Player p){
        System.out.println(sk.names.get(this) + "removePlayer");
        playerList.remove(p);
    }

     /**
     * szobába hozzáad egy item-et
     * beteszi a listájába
     * @param i az player amit hozzá akarunk adni
     */
    public void addItem(Item i){
        System.out.println(sk.names.get(this) + "addItem");
        itemList.add(i);
        //ha ragacsos vagy valami akkor removeItem, tehár törlődik, nem lezs a játékban többet
    }


    /**
     * szobába hozzáad egy playert
     * beteszi a listájába
     * vizsgálja, hogy betehető-e (kapacitás miatt)
     * ha igen akkor állapottól függően stunol
     * @param p az player amit hozzá akarunk adni
     * @return true ha hozzá lehet adni, false ha nem
     */
    public boolean addPlayer(Player p){
        //System.out.println(sk.names.get(this) + "addPlayer");
        if(playerList.size()<capacity){
            playerList.add(p);
            p.setRoom(this);
            this.stickyCount++;
            if(gassed){
                p.stun();
                p.cleanRoom();
            }
            if(cleaner){
                p.stunTeacher();
            }

            p.sendPlayersOut();

            //System.out.println(sk.names.get(this) + " addPlayer returned true");
            return true;
        }
        //System.out.println(sk.names.get(this) + " addPlayer returned false");
        return false;
    }

    public boolean mergeRoom(Room r1){
        System.out.println(sk.names.get(this) + "mergeRoom");
        for(Item i:itemList ){
            r1.addItem(i);
        }
        for(Player p:playerList ){
            r1.addPlayer(p);
        }
        for(Door d:doorList ){
            d.changeRoom(r1,this);
        }
        return true;
    }

    /**
     * szobába hozzáad egy door-t
     * beteszi a listájába
     * @param d a door amit hozzá akarunk adni
     */
    public void addDoor(Door d){
        System.out.println(sk.names.get(this) + " addDoor");
        doorList.add(d);
        System.out.println(sk.names.get(this) + " addDoor returned");
    }

    boolean killPlayer(Player p){System.out.println(sk.names.get(this) + "killPlayer");return true;}
    
    /**
     * játék megnyerését jelzi
     */
    public void win(){System.out.println(sk.names.get(this) + "win");}

    /**
     * játékos ajtón keresztül léptetését kezdeményezi
     * @param p a játékos akit léptet
     * @param d az ajtó amin lépteti 
     * @return true ha sikeres a transfer, false ha sikertelen
     */

    boolean changeRoom(Player p, Door d){
        System.out.println(sk.names.get(this) + "changeRoom(" + sk.names.get(p) + ", " + sk.names.get(d) + ")");
        if(d.transferPlayer(p,this)){
            System.out.println(sk.names.get(this) + "changeRoom returned true");
            return true;
        } 
        System.out.println(sk.names.get(this) + "changeRoom returned false");
        return false;
    }

    /**
     * Splitelést végzi
     * Split közben nem kerül át senki/semmi sehova csak egy új ugyanolyan szoba lesz létrehozva 
     * létrehoz egy ajtót a két szoba közt és hozzá is adja a két szobához
     * @return room2 a létreött szoba
     */
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

    /**
     * szobába érkező játékost megpróbálja megöletni mindenkivel
     * @param  p player akit megpróbálnak megölni(önmagát nem próbálja)
     */
    public void pvp(Player p){
        System.out.println(sk.names.get(this) + " pvp(" + sk.names.get(this) + ")");
        for(Player pl : playerList){
            if(!pl.equals(p)){
                pl.kill(p);
            }
        }
    }

    /**
     * elgázosítja a szobát
     * minden benne lévő játékost megpróbál stunolni is
     */
    public void makeGassed(){
        System.out.println(sk.names.get(this) + " makeGassed");
        gassed=true;
        for(Player p : playerList){
            p.stun();
        }
        System.out.println(sk.names.get(this) + " makeGassed returned with void");
        return;
    }

    public void makeUnGassed(){
        gassed=false;
    }

    /**
     * táblatörlőssé teszi a szobát
     * minden benne lévő játékost megpróbál stunolni is a megfelelő módon
     */
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
                p.kill(pl);
            }
        }
    }
    public boolean acceptPairing(Board b,Room r2){
        System.out.println(sk.names.get(this) + "acceptPairing");
        return b.pair(this,r2);
    }

    public void sendOut(Janitor j){
        for(Door d : doorList){
            for(Player p : playerList){
                if(p==j){
                    continue;
                }
                if(!p.changeR(d)){
                    break;
                }
            }
        }
    }

    public void resetStickyCount() {//stickycount nullazasra van, ha belép a takarito
        this.stickyCount=0;
    }

    public String listMe(Board b, boolean withPlayers, boolean withItems, boolean withAttribs){
        String returnString = b.objectToString(this) + ": ";
        if(withPlayers){
            for (Player player : playerList) {
                returnString += b.objectToString(player) + ", ";
            }
        }
        if(withItems){
            for (Item item : itemList) {
                returnString += b.objectToString(item) + ", ";
            }
        }
        if(withAttribs){
            if(gassed){
                returnString += "gassed, ";
            }
            if(cleaner){
                returnString += "cleaner, ";
            }
        }
        return returnString;
    }
}
