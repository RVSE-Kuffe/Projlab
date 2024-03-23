package logarlecTheGame.Model.Item;
import java.rmi.server.Skeleton;
import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;
import logarlecTheGame.Model.Player;
import logarlecTheGame.Model.Student;
import logarlecTheGame.Model.Room;

public class Transistor {
    private Transistor pair=null;
    private Room location =null;
    private boolean active = false;
    private Skeleton sk;


    public Transistor(Skeleton s, String n) {
        sk = s;
        sk.names.put(this, n);
    }

    public boolean makePair(Transistor t){}

    public void activate(){}

    public void acceptPutDown(Student s){}

    public boolean teleportPlayer(Student s){}

    public void setRoom(Room r){
        this.location=r;
    }

    public boolean acceptPairing(Student s, Transistor t){}

    public boolean acceptPairing(Student s, Item i){}
    
}
