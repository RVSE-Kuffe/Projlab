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
     * Tárgyak felvételét kezdeményezi
     * @param i     Felvenni kívánt tárgy
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
    public boolean kill(Player p){
        System.out.println(sk.names.get(this) + ".kill(" + sk.names.get(p) + ")");
        if(!p.equals(this)){
            if(p.die()){
                System.out.println(sk.names.get(this) + ".kill(" + sk.names.get(p) + ") returned True");
                return true;
            }
        }
        System.out.println(sk.names.get(this) + ".kill(" + sk.names.get(p) + ") returned False");
        return false;
    }

    /**
     * A játékosok megölését végzi. Megvizsgálja van-e védelme a játékosnak.
     * @return  Hamis, ha a játékosnak sikerült magát megvédenie, különben Igaz
     */
    public boolean die(){
        System.out.println(sk.names.get(this) + ".die()");
        for(Item i : itemList){
            if(i.acceptSP(this)){
                System.out.println(sk.names.get(this) + ".die() returned False");
                return false;
            }
        }
        System.out.println(sk.names.get(this) + ".die() returned True");
        return true;
    }

    public boolean changeR(Room r){
        System.out.println(sk.names.get(this) + " changeR(" + sk.names.get(r) + ")");
        System.out.println(sk.names.get(this) + " return changeR(" + sk.names.get(r) + ")");
        return this.location.changeRoom(this, r);
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

    @Override
    public boolean maskProtect(Mask m) {
    }

    @Override
    public boolean maskProtect(Item i) {
    }
}
