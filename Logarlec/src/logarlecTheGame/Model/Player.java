package logarlecTheGame.Model;
import java.util.ArrayList;
import java.util.List;

import logarlecTheGame.Model.Interfaces.*;
import logarlecTheGame.Model.Item.*;

public class Player implements PickUp, GasProtect{
    protected int id;
    protected boolean stunned;
    protected Room location;
    protected List<Item> itemList;

    /**
     * player osztály konstruktora
     * beállítja a megfelelő attribútumokat
     */
    public Player(int i, Room l){
        id = i;
        location = l;
        itemList = new ArrayList<>();
        stunned = false;
    }

    /**
     * Tárgyak felvételét kezdeményezi
     * @param i     Felvett tárgy
     */
    public boolean pickUpItem(Item i){
        return (itemList.size()<5&&i.acceptPickUp(this));
    }


    /**
     * Tárgyak felvételét valósítja meg
     * @param i     A felvenni kívánt tárgy
     */
    public boolean pickUp(Item i){
        if(location.removeItem(i)){
            this.addItem(i);
            return true;
        }
        return false;
    }

    /**
     * A Logarléc felvételét valósítja meg
     * felvételével meg van nyerve a játék
     * Teacher felüldefiniálja
     * @param i     Logarléc
     */
    public boolean pickUp(Logarlec i){
        if(location.removeItem(i)){
            location.win();
            return true;
        }
        return false;
    }

    public boolean pickUpFake(Logarlec l){
        if(location.removeItem(l)){
            itemList.add(l);
            return true;
        }
        return false;
    }

    /**
     * A sör felvételét kezeli, mivel ez egy speckó eset
     * @param i Sör
     */
    public boolean pickUp(Beer i){
        if(location.removeItem(i)){
            this.addItem(i);
            i.activate();
            return true;
        }
        return false;
    }


    /**
     * A Hallgatók bénítását végzi (stunned = true és itemek eldobása)
     * @return  Hamissal tér vissza, ha a Hallgatót egy tárgya megvédte, különben Igaz
     */
    public boolean stun() {
        // Ellenőrizzük, hogy valamelyik tárgy meg tudja-e védeni
        for (Item i : itemList) {
            if (i.acceptGasProtect(this)) {
                return false; // Ha van olyan tárgy, ami meg tud védeni, akkor kilépünk
            }
        }
    
        // Ha egyik tárgy sem tud védeni, akkor eldobhatjuk őket
        for (int i = 0; i < itemList.size(); i++) {
            dropItem(itemList.get(i)); // Az "i" indexű elemet eldobjuk
        }
        this.stunned =true;
        // Az "itemList" üres lesz a ciklusok végeztével
    
        // Visszatérünk, hogy sikeres volt-e a stun
        return true;
    }

    /**
     * Az Oktatók bénítását végzi
     * Teacher felüldefiniálja, mert ez csak rá van hatással 
     */
    public boolean stunTeacher(){
        return false;
    }

    /**
     * A tárgyak eldobását végzi
     * magától kiveszi a player és leteszi az adott szobába
     * @param i     Az eldobott tárgy
     */
    public void dropItem(Item i){
        this.location.addItem(i);
        itemList.remove(i);
    }

    /**
     * Játékosok megölésével próbálkozik, kezdeményezi azt
     * @param p     A játékos, akit próbálunk megölni
     * @return      Igazzal tér vissza, ha a játékos meghalt, különben hamis
     */
    public void kill(Player p){
        if(!p.equals(this))
            p.die();
    }

    /**
     * A játékosok megölését végzi. A student ezt felüldefiniálja, így ez csak az oktatókra hívódik, ők pedig nem tudnak meghalni
     * @return  Hamis, ha a játékosnak sikerült magát megvédenie, különben Igaz
     */
    public boolean die(){
        return false;
    }

    /**
     * A játékos szobaváltását kezdeményezi
     * @param r     A szoba, ahová lépünk
     * @return      Igaz, ha az 'r' szobába van elég hely, tehát át lehet lépni. Különben Hamis
     */
    public boolean changeR(Door d){
        return (!stunned && this.location.changeRoom(this, d));
    }

    /**
     * Jelzi a szobának hogy nyertek a hallgatók
     */
    public void win(){
        this.location.win();
    }

    /**
     * A játékos kábítását oldja fel
     */
    public void heal(){
        stunned = false;
    }

    /**
     * A játékos összes tárgyának az elvesztését kezeli
     */
    public void loseItem(){
        for(Item i : itemList){
            this.dropItem(i);
        }
    }

    /**
     * Két tranzisztor párosítását kezdi. A student felülírja, ezt csak az oktatók használják, mivel ők nem tudnak párosítani
     * @param t1    Első tranzisztor
     * @param t2    Második tranzisztor
     * @return      Ha sikerrel párosította, akkor Igazzal tér vissza, különben hamis
     */
    public boolean pairing(Transistor t1, Transistor t2){  
        return false;
    }

    /**
     * A játékos eszköztárához hozzáad egy új tárgyat
     * @param i     A tárgy ami a játékoshoz kerül
     */
    public void addItem(Item i){
        itemList.add(i);
    }

    /**
     * A játékost próbálja védeni a gáztól
     * @param m     A maszk amivel védekezik
     * @return      Ha sikerült a védés, Igazzal, ha nem, akkor Hamissal tér vssza
     */
    public boolean maskProtect(Mask m){
        return (m.durabminus());
    }

     /**
     * A játékost próbálja védeni a gáztól
     * @param i     Az item (ami nem maszk), amivel védekezik
     * @return      Ha sikerült a védés, Igazzal, ha nem, akkor Hamissal tér vssza
     */
    public boolean maskProtect(Item i){
        return false;
    }

    /**
     * Beállítja a játékos szobáját
     * @param r     A játékos új lokációja
     */
    public void setRoom(Room r){
        this.location=r;
    }

    public void cleanRoom(){
    }

    public void sendPlayersOut(){
    }

    public Room getLocation(){
        return location;
    }

    public boolean randomAction(){
        return false;
    }
    public boolean getIsStunned(){
        return stunned;
    }

    public String listMe(Board b, boolean withItems, boolean withAttribs){
        StringBuilder sb = new StringBuilder("");
        sb.append(b.objectToString(this) + ": ");
        if(withItems){
            for(Item i : itemList){
                sb.append(b.objectToString(i) + ", ");
            }
        }
        if(withAttribs){
            if(stunned){
                sb.append("stunned, "); 
            }
        }
        return sb.toString();
    }

    public List<Item> items(){
        return itemList;
    }
}
