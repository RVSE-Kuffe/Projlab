package logarlecTheGame.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Model.Item.*;

public class Room implements Serializable{
    protected List<Door> doorList = new ArrayList<>();
    protected List<Player> playerList = new ArrayList<>();
    protected List<Item> itemList = new ArrayList<>();
    protected boolean gassed=false;
    protected int roomid;
    protected int capacity;
    protected boolean cleaner=false;
    protected int stickyCount = 0;
    protected Board myBoard;
    protected int splitCounter = 0;

    /**
     * Szoba osztály konstruktora
     * beállítja a megfelelő attribútumokat
     */
    public Room(int rid, int cap, Board b) {
        roomid=rid;
        capacity=cap;
        myBoard = b;
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
        playerList.remove(p);
    }

     /**
     * szobába hozzáad egy item-et
     * beteszi a listájába
     * @param i az player amit hozzá akarunk adni
     */
    public void addItem(Item i){
        itemList.add(i);
        //ha ragacsos vagy valami akkor removeItem, tehár törlődik, nem lezs a játékban többet
    }

    public void removeDoor(Door d){
        doorList.remove(d);
        myBoard.removeFromMap(d);
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
        if(playerList.size()<capacity){
            p.setRoom(this);
            playerList.add(p);
            this.stickyCount++;
            if(gassed){
                p.stun();
                p.cleanRoom();
            }
            if(cleaner){
                p.stunTeacher();
            }

            p.sendPlayersOut();
            return true;
        }
        return false;
    }

    public boolean mergeRoom(Room r1){
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
        doorList.add(d);
    }

    public boolean killPlayer(Player p){
        myBoard.removeFromMap(p);
        playerList.remove(p);
        myBoard.studentDied(p);
        return true;
    }
    
    /**
     * játék megnyerését jelzi
     */
    public void win(){
        myBoard.win();
    }

    /**
     * játékos ajtón keresztül léptetését kezdeményezi
     * @param p a játékos akit léptet
     * @param d az ajtó amin lépteti 
     * @return true ha sikeres a transfer, false ha sikertelen
     */

    boolean changeRoom(Player p, Door d){
        return (d.transferPlayer(p,this));
    }

    /**
     * Splitelést végzi
     * Split közben nem kerül át senki/semmi sehova csak egy új ugyanolyan szoba lesz létrehozva 
     * létrehoz egy ajtót a két szoba közt és hozzá is adja a két szobához
     * @return room2 a létreött szoba
     */
   public Room newRoom(){
        Room room2 =new Room(this.roomid + 1, this.capacity, myBoard);
        Door splitDoor = new Door(this, room2,true,true);
        this.addDoor(splitDoor);
        myBoard.addToBoard(splitDoor, "splitDoor"+splitCounter);
        room2.addDoor(splitDoor);
        String newRoomName =   myBoard.objectToString(this) + "s" + splitCounter++;
        myBoard.addToBoard(room2, newRoomName);
        return room2;
    }

    void stunRoom(){}

    /**
     * szobába érkező játékost megpróbálja megöletni mindenkivel
     * @param  p player akit megpróbálnak megölni(önmagát nem próbálja)
     */
    public void pvp(Player p){
        for(int i=0;i<playerList.size();i++){
            if(! playerList.get(i).equals(p)){
                playerList.get(i).kill(p);
            }
        }
    }

    /**
     * elgázosítja a szobát
     * minden benne lévő játékost megpróbál stunolni is
     */
    public void makeGassed(){
        gassed=true;
        for(Player p : playerList){
            p.stun();
        }
    }

    public void makeUnGassed(){
        gassed=false;
    }

    /**
     * táblatörlőssé teszi a szobát
     * minden benne lévő játékost megpróbál stunolni is a megfelelő módon
     */
    public void makeClean(){
        cleaner=true;
        for(Player p : playerList){
            p.stunTeacher();
        }
    }

    /**
     * Végigmegy a szoba összes player-én és meghívja rá a die metódust, kivéve, ha nem egyenlő a paraméterrel kapottal
     * @param p     a Player, aki megöl mindenkit
     * @return      void
     */
    void killAll(Player p){
        for(int i=0;i<playerList.size();i++){
            if(! p.equals(playerList.get(i))){
                p.kill(playerList.get(i));
            }
        }
    }
    public boolean acceptPairing(Board b,Room r2){
        return b.pair(this,r2);
    }

    public void sendOut(Janitor j) {
        for (int i = 0; i < doorList.size(); i++) { // Az "i" változó érvényessége itt kezdődik
            for (int p = 0; p < playerList.size(); p++) { // A "p" változó érvényessége itt kezdődik
                if (playerList.get(p) == j) {
                    continue;
                }
                if (!playerList.get(p).changeR(doorList.get(i))) { // Az "i" változó itt is érvényes
                    break;
                }
            }
        }
    }

    public void resetStickyCount() {//stickycount nullazasra van, ha belép a takarito
        this.stickyCount=0;
    }

    public String listMe(Board b, boolean withPlayers, boolean withItems, boolean withAttribs){
        StringBuilder returnString = new StringBuilder(b.objectToString(this) + ": ");
        if(withPlayers){
            for (Player player : playerList) {
                returnString.append(b.objectToString(player) + '\n');
            }
        }
        if(withItems){
            for (Item item : itemList) {
                returnString.append(b.objectToString(item) + '\n');
            }
        }
        if(withAttribs){
            if(gassed){
                returnString.append("gassed\n");
            }
            if(cleaner){
                returnString.append("cleaner\n");
            }
            if(stickyCount>=5){
                returnString.append("sticky\n");
            }
        }
        return returnString.toString();
    }

    public List<Door> doors(){
        return doorList;
    }

    public List<Item> items(){
        return itemList;
    }
    public void makeSticky() {
        stickyCount = 420;
    }
}
