package logarlecTheGame.Model;
import java.util.ArrayList;
import java.util.List;

import logarlecTheGame.Model.Interfaces.*;
import logarlecTheGame.Model.Item.*;
import logarlecTheGame.Model.Item.*;
import logarlecTheGame.Skeleton.*;

public class Player implements PickUp, GasProtect{
    protected int id;
    protected boolean stunned;
    protected boolean isProtected;    //Simán protected-nek nem nevezhetem el a változót, mert rinyál a fordító
    protected Room location;
    protected List<Item> itemList;

    public Player(Skeleton sk, String n, int i, Room l){
        sk.names.put(this, n);
        id = i;
        location = l;
        itemList = new ArrayList<>();
        stunned = false;
        isProtected = false;
    }

    public void pickUpItem(Item i){
        System.out.println(sk.names.get(this) + " pickUpItem(" + sk.names.get(i) + ")");

        System.out.println(sk.names.get(this) + " return pickUpItem(" + sk.names.get(i) + ")");
        return;
    }

    public void move(){
        System.out.println(sk.names.get(this) + " move");

        System.out.println(sk.names.get(this) + " return move");
        return;
    }

    public boolean stun(){
        System.out.println(sk.names.get(this) + " stun");

        System.out.println(sk.names.get(this) + " return stun");
        return false;;
    }

    public void dropItem(Item i){
        System.out.println(sk.names.get(this) + " dropItem(" + sk.names.get(i) + ")");

        System.out.println(sk.names.get(this) + " return dropItem(" + sk.names.get(i) + ")");
        return;
    }

    public void kill(Player p){
        System.out.println(sk.names.get(this) + " kill(" + sk.names.get(p) + ")");

        System.out.println(sk.names.get(this) + " return kill(" + sk.names.get(p) + ")");
        return;
    }

    public boolean die(){
        System.out.println(sk.names.get(this) + " die");

        System.out.println(sk.names.get(this) + " return die");
        return false;;
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
    }

    public void changeR(Room r){
        System.out.println(sk.names.get(this) + " changeR(" + sk.names.get(r) + ")");

        System.out.println(sk.names.get(this) + " return changeR(" + sk.names.get(r) + ")");
        return;
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

    public void loseItem(){
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

    public void pickUp(Logarlec l){

    }

    public void pickUp(Beer b){

    }

    public void pickUp(Item i){

    }

    public boolean maskProtect(Mask m){
        return true;
    }

    public boolean maskProtect(Item i){
        return true;
    }
}
