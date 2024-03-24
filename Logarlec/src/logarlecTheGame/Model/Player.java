package logarlecTheGame.Model;
import java.util.ArrayList;
import java.util.List;

import logarlecTheGame.Model.Interfaces.*;
import logarlecTheGame.Model.Item.*;
import logarlecTheGame.Skeleton.*;

public class Player implements PickUp, GasProtect{
    protected Skeleton sk;
    protected int id;
    protected boolean stunned;
    protected boolean isProtected;    //Simán protected-nek nem nevezhetem el a változót, mert rinyál a fordító
    protected Room location;
    protected List<Item> itemList;

    public Player(Skeleton s, String n, int i, Room l){
        sk = s;
        sk.names.put(this, n);
        id = i;
        location = l;
        itemList = new ArrayList<>();
        stunned = false;
        isProtected = false;
    }

    /**
     * Tárgyak felvételét kezdeményezi
     * @param i     Felvett tárgy
     */
    public void pickUpItem(Item i){
        System.out.println(sk.names.get(this) + ".pickUpItem(" + sk.names.get(i) + ")");
        i.acceptPickUp(this);
        System.out.println(sk.names.get(this) + ".pickUpItem(" + sk.names.get(i) + ") returned");
    }

    /**
     * Tárgyak felvételét valósítja meg
     * @param i     A felvenni kívánt tárgy
     */
    public void pickUp(Item i){
        System.out.println(sk.names.get(this) + ".pickUp(" + sk.names.get(i) + ")");
        location.removeItem(i);
        this.addItem(i);
        System.out.println(sk.names.get(this) + ".pickUp(" + sk.names.get(i) + ") returned");
    }

    /**
     * A Logarléc felvételét valósítja meg
     * @param i     Logarléc
     */
    public void pickUp(Logarlec i){
        System.out.println(sk.names.get(this) + ".pickUp(" + sk.names.get(i) + ")");
        location.win();
        System.out.println(sk.names.get(this) + ".pickUp(" + sk.names.get(i) + ") returned");
    }

    public void pickUp(Beer i){
        //TODO
    }


    /**
     * A Hallgatók bénítását végzi
     * @return  Hamissal tér vissza, ha a Hallgatót egy tárgya megvédte, különben Igaz
     */
    public boolean stun(){
        System.out.println(sk.names.get(this) + ".stun()");
        for(Item i: itemList){
            if(i.acceptGasProtect(this)){
                System.out.println(sk.names.get(this) + ".stun() returned False");
                return false;
            }
        }
        System.out.println(sk.names.get(this) + ".stun() returned True");
        return true;
    }

    /**
     * Az Oktatók bénítását végzi
     * @return  Hamissal tér vissza, ha az Oktatót egy tárgya megvédte, különben Igaz
     */
    public boolean stunTeacher(){
        System.out.println(sk.names.get(this) + ".stunTeacher()");
        for(Item i: itemList){
            if(i.acceptGasProtect(this)){
                System.out.println(sk.names.get(this) + ".stunTeacher() returned False");
                return false;
            }
        }
        System.out.println(sk.names.get(this) + ".stunTeacher() returned True");
        return true;
    }

    /**
     * A tárgyak eldobását végzi
     * @param i     Az eldobott tárgy
     */
    public void dropItem(Item i){
        System.out.println(sk.names.get(this) + ".dropItem(" + sk.names.get(i) + ")");
        this.location.addItem(i);
        itemList.remove(i);
        System.out.println(sk.names.get(this) + ".dropItem(" + sk.names.get(i) + ") returned");
    }

    /**
     * Játékosok megölésével próbálkozik, kezdeményezi azt
     * @param p     A játékos, akit próbálunk megölni
     * @return      Igazzal tér vissza, ha a játékos meghalt, különben hamis
     */
    public void kill(Player p){
        System.out.println(sk.names.get(this) + ".kill(" + sk.names.get(p) + ")");
        if(!p.equals(this) && (p.die())){
            System.out.println(sk.names.get(this) + ".kill(" + sk.names.get(p) + ") returned True");
        }
        System.out.println(sk.names.get(this) + ".kill(" + sk.names.get(p) + ") returned False");
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
    public boolean changeR(Room r){
        System.out.println(sk.names.get(this) + " changeR(" + sk.names.get(r) + ")");
        if(this.location.changeRoom(this, r)){
            System.out.println(sk.names.get(this) + ".changeR(" + sk.names.get(r) + ") returned True");
            return true;
        }
        System.out.println(sk.names.get(this) + ".changeR(" + sk.names.get(r) + ") returned False");
        return false;
    }

    /**
     * Tárgy törlése, putDownal ellentétben itt teljesen eltűnik
     * @param i     A törölt tárgy
     */
    public void destroyItem(Item i){
        System.out.println(sk.names.get(this) + " destroyItem(" + sk.names.get(i) + ")");
        itemList.remove(i);
        System.out.println(sk.names.get(this) + " return destroyItem(" + sk.names.get(i) + ")");
    }

    /**
     * Jelzi a szobának hogy nyertek a hallgatók
     */
    public void win(){
        System.out.println(sk.names.get(this) + ".win()");
        this.location.win();
        System.out.println(sk.names.get(this) + ".win() returned");
    }

    /**
     * A játékos kábítását oldja fel
     */
    public void heal(){
        System.out.println(sk.names.get(this) + ".heal()");
        stunned = false;
        System.out.println(sk.names.get(this) + ".heal() returned");
    }

    public void loseItem(){
        System.out.println(sk.names.get(this) + ".loseItem()");
        for(Item i : itemList){
            this.dropItem(i);
        }
        System.out.println(sk.names.get(this) + ".loseItem() returned");
    }

    /**
     * Két tranzisztor párosítását kezdi. A student felülírja, ezt csak az oktatók használják, mivel ők nem tudnak párosítani
     * @param t1    Első tranzisztor
     * @param t2    Második tranzisztor
     * @return      Ha sikerrel párosította, akkor Igazzal tér vissza, különben hamis
     */
    public boolean pairing(Transistor t1, Transistor t2){
        System.out.println(sk.names.get(this) + ".pairing(" + sk.names.get(t1) +", " +sk.names.get(t2)+ ")");
        
        return false;
    }

    /**
     * A játékos eszköztárához hozzáad egy új tárgyat
     * @param i     A tárgy ami a játékoshoz kerül
     */
    public void addItem(Item i){
        System.out.println(sk.names.get(this) + ".addItem(" + sk.names.get(i) + ")");
        itemList.add(i);
        System.out.println(sk.names.get(this) + ".addItem(" + sk.names.get(i) + ") returned");
    }

    /**
     * A játékost próbálja védeni a gáztól
     * @param m     A maszk amivel védekezik
     * @return      Ha sikerült a védés, Igazzal, ha nem, akkor Hamissal tér vssza
     */
    public boolean maskProtect(Mask m){
        System.out.println(sk.names.get(this) + ".maskProtect(" + sk.names.get(m) + ")");
        if(m.durabminus()){
            System.out.println(sk.names.get(this) + ".maskProtect(" + sk.names.get(m) + ") returned True");
            return true;
        }
        System.out.println(sk.names.get(this) + ".maskProtect(" + sk.names.get(m) + ") returned False");
        return false;
    }

     /**
     * A játékost próbálja védeni a gáztól
     * @param i     Az item (ami nem maszk), amivel védekezik
     * @return      Ha sikerült a védés, Igazzal, ha nem, akkor Hamissal tér vssza
     */
    public boolean maskProtect(Item i){
        System.out.println(sk.names.get(this) + ".maskProtect(" + sk.names.get(i) + ")");
        System.out.println(sk.names.get(this) + ".maskProtect(" + sk.names.get(i) + ") returned False");
        return false;
    }

    /**
     * Beállítja a játékos szobáját
     * @param r     A játékos új lokációja
     */
    public void setRoom(Room r){
        this.location=r;
    }

}
