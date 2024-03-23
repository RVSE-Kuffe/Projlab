package logarlecTheGame.Model.Item;
import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;
import logarlecTheGame.Model.Player;
import logarlecTheGame.Model.Student;

public class Beer implements CycleBased {
    private int durability;
    private boolean active;
    private Skeleton sk;

    public Beer(Skeleton s, String n, int durab, boolean b) {
        sk = s;
        sk.names.put(this, n);
        durability=durab;
        active=b;
    }

    public boolean AcceptSP(Student s){}

    public void AcceptPickUp(Player p){}    

    boolean durabminus(){}
    
}
