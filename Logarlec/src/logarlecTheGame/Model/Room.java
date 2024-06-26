package logarlecTheGame.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Model.Item.*;


/**
 * A szoba osztály reprezentálja a játékban szereplő szobákat.
 * Minden szoba tartalmaz ajtókat, játékosokat és tárgyakat.
 * Egy szoba lehet gázos, tablatorlos, és lehet ragacsos.
 */
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
     * Szoba osztály konstruktora.
     * Beállítja a megfelelő attribútumokat.
     * @param rid A szoba azonosítója.
     * @param cap A szoba befogadóképessége.
     * @param b A tábla, amelyhez a szoba tartozik.
     */
    public Room(int rid, int cap, Board b) {
        roomid=rid;
        capacity=cap;
        myBoard = b;
    }

    /**
     * Visszaadja a szoba befogadóképességét.
     * @return A szoba befogadóképessége.
     */
    public int getCapacity(){
        return capacity;
    }

    /**
     * Visszaadja a szobában lévő játékosok számát.
     * @return A szobában lévő játékosok száma.
     */
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
    }


    /**
 * Törli a megadott ajtót a szobából és a tábláról.
 * @param d Az eltávolítandó ajtó.
 */
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

    /**
     * Két szoba egyesítését végzi.
     * Áthelyezi az itemeket és a játékosokat az első szobából a másodikba.
     * @param r1 Egyik szoba.
     * @return true, ha az egyesítés sikeres, különben false.
     */
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

    /**
 * Megöli a megadott játékost, eltávolítja a tábláról és a szobából.
 * @param p Az megölendő játékos.
 * @return Igaz, ha sikeresen meghalt a játékos
 */
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
        myBoard.addToBoard(splitDoor, "splitDoor"+myBoard.objectToString(this)+"-"+splitCounter);
        room2.addDoor(splitDoor);
        String newRoomName =   myBoard.objectToString(this) + "s" + splitCounter++;
        myBoard.addToBoard(room2, newRoomName);
        return room2;
    }

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

    /**
 * Megszünteti a szobában lévő gázt.
 */
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

    /**
 * Elfogadja a szoba párosítását 
 * 
 * @param b A tábla, amelyen a párosítást elvégzi.
 * @param r2 A másik szoba, amelyet ezzel a szobával párosít.
 * @return Igaz, ha sikerült a párosítás, különben hamis.
 */
    public boolean acceptPairing(Board b,Room r2){
        return b.pair(this,r2);
    }


    /**
 * Kiküld mindenkit a szobából
 * 
 * @param j A takarító
 */
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

    /**
 * Visszaállítja a ragacsos jelzőszámlálót, ha a takarító belép a szobába.
 */
    public void resetStickyCount() {
        this.stickyCount=0;
    }


    /**
 * Visszaadja a szoba adatait, beleértve a játékosokat, a tárgyakat és az attribútumokat.
 * 
 * @param b A tábla
 * @param withPlayers Ha igaz, akkor a játékosokat is listázza.
 * @param withItems Ha igaz, akkor a tárgyakat is listázza.
 * @param withAttribs Ha igaz, akkor a attribútumokat is listázza.
 * @return A szoba adatait tartalmazó szöveg.
 */
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

    /**
 * Visszaadja a szobában található ajtókat.
 * 
 * @return A szobában található ajtók listája.
 */
    public List<Door> doors(){
        return doorList;
    }

    /**
 * Visszaadja a szobában található elemeket.
 * 
 * @return A szobában található elemek listája.
 */
    public List<Item> items(){
        return itemList;
    }

    /**
 * Beállítja a ragacsos jelzőszámlálót 420-ra.
 */
    public void makeSticky() {
        stickyCount = 420;
    }
}
