package logarlecTheGame.Model.Item;
import java.rmi.server.Skeleton;
import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;
import logarlecTheGame.Model.Player;
import logarlecTheGame.Model.Student;
import logarlecTheGame.Model.Room;
import logarlecTheGame.Model.Item.Item;


public class Transistor extends Item  {
    private Transistor pair=null;
    private Room location =null;
    private boolean active = false;
    private Skeleton sk;


    public Transistor(Skeleton s, String n) {
        sk = s;
        sk.names.put(this, n);
    }

    public Transistor(Skeleton s, String n, Transistor p) {//teszteléshez kell csak, hogy legyen alapvető párja, amúgy nem használandó, amúgy túl hosszú teszt lenne
        sk = s;
        sk.names.put(this, n);
        this.pair=p;
    }

    public Transistor getPair(){
         System.out.println(sk.names.get(this) + "getPair");
        return pair;}

    public boolean makePair(Transistor t2){
        System.out.println(sk.names.get(this) + "makePair");
        if((this.pair==null&&t2.getPair()==null)||this.pair==t2){
            this.pair=t2;
         return true;
        }
         return false;

    }

    public void activate(){
        System.out.println(sk.names.get(this) + "activate");
        this.active=true;
    }

    @Override
    public void acceptPutDown(Student s){
        System.out.println(sk.names.get(this) + "acceptPutDown");
        s.use(this);
    }

    public boolean teleportPlayer(Student s){
        System.out.println(sk.names.get(this) + "teleportPlayer");
       return this.pair.arrivingPlayer(s);
        
    }
    public boolean arrivingPlayer(Student s){  //meg kell írni a Addplayert booleanra
        return this.location.addPlayer(s);
        
    }

    public void setRoom(Room r){
        System.out.println(sk.names.get(this) + "setRoom");
        this.location=r;
    }
    @Override
    public boolean acceptPairing(Student s, Transistor t){
        System.out.println(sk.names.get(this) + "acceptPairing");
        s.pair(this, t);
    }

    @Override
    public boolean acceptPairing(Student s, Item i){
        System.out.println(sk.names.get(this) + "acceptPairing");

    }
    
}
