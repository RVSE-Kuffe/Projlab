package logarlecTheGame.Model;
import java.util.ArrayList;
import java.util.List;

import logarlecTheGame.Model.Interfaces.*;
import logarlecTheGame.Model.Item.*;
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
<<<<<<< HEAD
     * Tárgyak felvételét kezdeményezi
     * @param i     Felvenni kívánt tárgy
     */
    public void pickUpItem(Item i){
        System.out.println(sk.names.get(this) + " pickUpItem(" + sk.names.get(i) + ")");

        i.acceptPickUp(this);

        System.out.println(sk.names.get(this) + " return pickUpItem(" + sk.names.get(i) + ")");
        return;
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

=======
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

>>>>>>> foldi
    public void pickUp(Beer i){
        //TODO
    }


    /**
     * A Hallgatók bénítását végzi
     * @return  Hamissal tér vissza, ha a Hallgatót egy tárgya megvédte, különben Igaz
     */
    public boolean stun(){
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> foldi
        System.out.println(sk.names.get(this) + ".stun()");
        for(Item i: itemList){
            if(i.acceptGasProtect(this)){
                System.out.println(sk.names.get(this) + ".stun() returned False");
                return false;
            }
        }
        System.out.println(sk.names.get(this) + ".stun() returned True");
        return true;
<<<<<<< HEAD
=======
        System.out.println(sk.names.get(this) + " stun");

        System.out.println(sk.names.get(this) + " return stun");
        return false;
>>>>>>> e1900923b58351629ff5b3defa34ede9206883fb
=======
>>>>>>> foldi
    }

    /**
     * Az Oktatók bénítását végzi
     * @return  Hamissal tér vissza, ha az Oktatót egy tárgya megvédte, különben Igaz
     */
    public void stunTeacher(){
        System.out.println(sk.names.get(this) + ".stunTeacher()");
        for(Item i: itemList){
            if(i.acceptGasProtect(this)){
                System.out.println(sk.names.get(this) + ".stunTeacher() returned False");
            }
        }
        System.out.println(sk.names.get(this) + ".stunTeacher() returned True");
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
        if(!p.equals(this)){
            if(p.die()){
                System.out.println(sk.names.get(this) + ".kill(" + sk.names.get(p) + ") returned True");
            }
        }
        System.out.println(sk.names.get(this) + ".kill(" + sk.names.get(p) + ") returned False");
    }

    /**
     * A játékosok megölését végzi. Megvizsgálja van-e védelme a játékosnak.
     * @return  Hamis, ha a játékosnak sikerült magát megvédenie, különben Igaz
     */
    public boolean die(){
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> foldi
        System.out.println(sk.names.get(this) + ".die()");
        for(Item i : itemList){
            if(i.acceptSP(this)){
                System.out.println(sk.names.get(this) + ".die() returned False");
                return false;
            }
        }
        System.out.println(sk.names.get(this) + ".die() returned True");
        return true;
<<<<<<< HEAD
=======
        System.out.println(sk.names.get(this) + " die");

        System.out.println(sk.names.get(this) + " return die");
        return false;
    }

    public void turn(){
        System.out.println(sk.names.get(this) + " turn");

        System.out.println(sk.names.get(this) + " return turn");
        return;
    }

    public void stunTeacher(){
        System.out.println(sk.names.get(this) + " stunTeacher");

        System.out.println(sk.names.get(this) + " return stunTeacher");
        return;
>>>>>>> e1900923b58351629ff5b3defa34ede9206883fb
=======
>>>>>>> foldi
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

    public void destroyItem(Item i){
        System.out.println(sk.names.get(this) + " destroyItem(" + sk.names.get(i) + ")");

        System.out.println(sk.names.get(this) + " return destroyItem(" + sk.names.get(i) + ")");
        return;
    }

    public void win(){
        System.out.println(sk.names.get(this) + " win");
        
        System.out.println(sk.names.get(this) + " return win");
        return;
    }

    public void heal(){
        System.out.println(sk.names.get(this) + " heal");

        System.out.println(sk.names.get(this) + " return heal");
        return;
    }

    public void useItem(){
        System.out.println(sk.names.get(this) + " loseItem");

        System.out.println(sk.names.get(this) + " return loseItem");
        return;
    }

    public void pairing(){
        System.out.println(sk.names.get(this) + " pairing");

        System.out.println(sk.names.get(this) + " return pairing");
        return;
    }

    public void newR(Room r){
        System.out.println(sk.names.get(this) + " destroyItem(" + sk.names.get(r) + ")");

        System.out.println(sk.names.get(this) + " return destroyItem(" + sk.names.get(r) + ")");
        return;
    }

    public void addItem(Item i){
        itemList.add(i);
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
    public void pickUp(Beer b){
        this.addItem(b);
        this.location.removeItem(b);
        b.activate();
    }

    public void pickUp(Item i){
        this.addItem(i);
        this.location.removeItem(i);
    }

>>>>>>> e1900923b58351629ff5b3defa34ede9206883fb
=======
>>>>>>> foldi
    public boolean maskProtect(Mask m){
        if(m.durabminus()){
            return true;
        }
        return false;
    }

    public boolean maskProtect(Item i){
        return false;
    }

    public void setRoom(Room r){
        this.location=r;
    }

}
