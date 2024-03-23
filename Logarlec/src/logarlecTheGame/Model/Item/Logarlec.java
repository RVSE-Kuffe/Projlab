package logarlecTheGame.Model.Item;
import java.rmi.server.Skeleton;
import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;
import logarlecTheGame.Model.Player;
import logarlecTheGame.Model.Student;

public class Logarlec {
    private Skeleton sk;

    public Logarlec(Skeleton s, String n) {
        sk = s;
        sk.names.put(this, n);
    }

    void acceptPickUp(Player p){
        p.pickUp(this);
    }
    
}
