package logarlecTheGame.Model.Item;
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

    public Transistor getPair(){return pair;}

    public boolean makePair(Transistor t2){
        if(this.pair==null){
            this.pair=t2;
        return true;
        }   
        else{
            return false;
        }
    }

    public void activate(){
        this.active=true;
    }

    @Override
    public void acceptPutDown(Student s){}

    public boolean teleportPlayer(Student s){}

    public void setRoom(Room r){
        this.location=r;
    }
    @Override
    public boolean acceptPairing(Student s, Transistor t){}

    @Override
    public boolean acceptPairing(Student s, Item i){}
    
}
